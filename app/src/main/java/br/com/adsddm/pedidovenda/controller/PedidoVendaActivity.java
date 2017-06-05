package br.com.adsddm.pedidovenda.controller;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import br.com.adsddm.pedidovenda.dadosview.DadosView;
import br.com.adsddm.pedidovenda.R;
import br.com.adsddm.pedidovenda.adapter.ItemPedidoVendaAdapter;
import br.com.adsddm.pedidovenda.model.ItemPedidoVenda;
import br.com.adsddm.pedidovenda.service.PedidoVendaService;
import br.com.adsddm.pedidovenda.util.Mask;

public class PedidoVendaActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private ListView listView;
    private DadosView dadosView;
    private PedidoVendaService pedidoVendaService;
    private PedidoVendaActivity p;
    private EditText etCPF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_venda);

        StrictMode.ThreadPolicy sop= new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(sop);

        etCPF = (EditText) findViewById(R.id.etCPF);

        p = this;
        dadosView = DadosView.Instance();
        pedidoVendaService = new PedidoVendaService();

        etCPF.addTextChangedListener(Mask.insert("###.###.###-##", etCPF));
        pedidoVendaService.inicializaPedidoTest(dadosView.getPedidoVenda());
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        listaProduto();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ItemPedidoVenda s = (ItemPedidoVenda) parent.getAdapter().getItem(position);
        Toast.makeText(this, "Produto Selecionado: " + s.getProduto().getNome() +", posição: " + position,
                Toast.LENGTH_LONG).show();
    }

    public void onAddProduto(View view){
        Intent intent = new Intent(this, SelecionaProdutoActivity.class);
        startActivity(intent);
    }
    public void onSalvar(View view){
        pedidoVendaService.enviarPedidoVenda(dadosView.getPedidoVenda());
    }

    public void listaProduto(){
        listView = (ListView) findViewById(R.id.lvProdutos);
        listView.setAdapter(new ItemPedidoVendaAdapter(this, dadosView.getPedidoVenda().getItempedidovendas()));
        listView.setOnItemClickListener(this);
    }
}
