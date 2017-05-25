package br.com.adsddm.pedidovenda.controller;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import br.com.adsddm.pedidovenda.R;
import br.com.adsddm.pedidovenda.adapter.ProdutoAdapter;
import br.com.adsddm.pedidovenda.model.PedidoVenda;

public class PedidoVendaActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private ListView listView;
    private PedidoVenda pedidoVenda;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_venda);

        pedidoVenda = new PedidoVenda();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        listaProduto();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String s = (String) parent.getAdapter().getItem(position);
        Toast.makeText(this, "Produto Selecionado: " + s +", posição: " + position,
                Toast.LENGTH_LONG).show();
    }

    public void inicializaPedidoTest(){
        //Enviar Teste
    }

    public void listaProduto(){
        listView = (ListView) findViewById(R.id.lvProdutos);
        listView.setAdapter(new ProdutoAdapter(this));
        listView.setOnItemClickListener(this);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {/*
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Toast.makeText(null, R.string.title_home, Toast.LENGTH_LONG).show();
                    return true;
                case R.id.navigation_dashboard:
                    Toast.makeText(null, R.string.title_dashboard, Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_notifications:
                    Toast.makeText(null, R.string.title_notifications, Toast.LENGTH_LONG).show();
                    return true;
            }*/
            return false;
        }

    };
}
