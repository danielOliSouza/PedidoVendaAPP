package br.com.adsddm.pedidovenda.controller;

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
import br.com.adsddm.pedidovenda.adapter.ProdutoAdapter;
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

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        etCPF = (EditText) findViewById(R.id.etCPF);

        p = this;
        dadosView = DadosView.Instance();
        pedidoVendaService = new PedidoVendaService();

        listaProduto();
        etCPF.addTextChangedListener(Mask.insert("###.###.###-##", etCPF));
        pedidoVendaService.inicializaPedidoTest(dadosView.getPedidoVenda());
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ItemPedidoVenda s = (ItemPedidoVenda) parent.getAdapter().getItem(position);
        Toast.makeText(this, "Produto Selecionado: " + s.getProduto().getNome() +", posição: " + position,
                Toast.LENGTH_LONG).show();
    }

    public void listaProduto(){
        listView = (ListView) findViewById(R.id.lvProdutos);
        listView.setAdapter(new ProdutoAdapter(this, dadosView.getPedidoVenda().getItempedidovendas()));
        listView.setOnItemClickListener(this);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Boolean result = true;

            switch (item.getItemId()){
                case R.id.salvar:
                    pedidoVendaService.enviarPedidoVenda(dadosView.getPedidoVenda());
                    break;
                case R.id.cancelar:
                    Toast.makeText(p, "Cancelar", Toast.LENGTH_LONG).show();
                    break;
                default:
                    result = false;
                    break;
            }
            return result;
        }

    };

}
