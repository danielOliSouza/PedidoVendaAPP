package br.com.adsddm.pedidovenda.controller;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.adsddm.pedidovenda.model.*;


import java.util.ArrayList;
import java.util.List;

import br.com.adsddm.pedidovenda.R;
import br.com.adsddm.pedidovenda.adapter.ProdutoAdapter;
import br.com.adsddm.pedidovenda.model.Cliente;
import br.com.adsddm.pedidovenda.model.ItemPedidoVenda;
import br.com.adsddm.pedidovenda.model.PedidoVenda;
import br.com.adsddm.pedidovenda.service.PedidoVendaService;

public class PedidoVendaActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private ListView listView;
    private PedidoVenda pedidoVenda;
    private PedidoVendaService pedidoVendaService;
    private PedidoVendaActivity p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_venda);

        StrictMode.ThreadPolicy sop= new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(sop);

        p = this;
        pedidoVenda = new PedidoVenda();
        pedidoVendaService = new PedidoVendaService();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        listaProduto();
        pedidoVendaService.inicializaPedidoTest(pedidoVenda);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String s = (String) parent.getAdapter().getItem(position);
        Toast.makeText(this, "Produto Selecionado: " + s +", posição: " + position,
                Toast.LENGTH_LONG).show();
    }

    public void listaProduto(){
        listView = (ListView) findViewById(R.id.lvProdutos);
        listView.setAdapter(new ProdutoAdapter(this));
        listView.setOnItemClickListener(this);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.salvar:
                    Toast.makeText(p, pedidoVendaService.enviarPedidoVenda(pedidoVenda), Toast.LENGTH_LONG).show();
                    return true;
                case R.id.cancelar:
                    Toast.makeText(p, "Cancelar", Toast.LENGTH_LONG).show();
                    return true;
            }
            /*
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
