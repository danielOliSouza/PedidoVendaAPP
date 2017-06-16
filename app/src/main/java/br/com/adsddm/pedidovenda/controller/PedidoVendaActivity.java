package br.com.adsddm.pedidovenda.controller;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

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
    protected void onStart() {
        super.onStart();
        listaProduto();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ItemPedidoVenda s = (ItemPedidoVenda) parent.getAdapter().getItem(position);
       // Toast.makeText(this, "Produto Selecionado: " + s.getProduto().getNome() +", posição: " + position,
       //         Toast.LENGTH_LONG).show();
    }

    public void onAddProduto(View view){
        Intent intent = new Intent(this, SelecionaProdutoActivity.class);
        startActivity(intent);
    }
    public void onSalvar(View view){
        boolean status =  pedidoVendaService.enviarPedidoVenda(dadosView.getPedidoVenda());
        if(!status){
            Toast.makeText(this,"Não foi posivel enviar o Pedido de Venda", Toast.LENGTH_LONG).show();
        }
    }

    public void listaProduto(){
        List<ItemPedidoVenda> itens = dadosView.getPedidoVenda().getItempedidovendas();
        listView = (ListView) findViewById(R.id.lvProdutos);
        listView.setAdapter(new ItemPedidoVendaAdapter(this, itens));
        listView.setOnItemClickListener(this);

        if(itens.size() <= 5) {
            listView.getLayoutParams().height = (itens.size() * 85); //Aumentar tamanho da list de acordo com a quantidade de itens
        }
    }
}
