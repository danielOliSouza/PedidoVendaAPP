package br.com.adsddm.pedidovenda.controller;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import br.com.adsddm.pedidovenda.R;
import br.com.adsddm.pedidovenda.adapter.ItemPedidoVendaAdapter;
import br.com.adsddm.pedidovenda.adapter.ProdutoAdapter;
import br.com.adsddm.pedidovenda.dadosview.DadosView;
import br.com.adsddm.pedidovenda.model.ItemPedidoVenda;
import br.com.adsddm.pedidovenda.model.Produto;
import br.com.adsddm.pedidovenda.service.ProdutoService;

public class SelecionaProdutoActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private DadosView dados;
    private ListView listView;
    private ProdutoService produtoService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleciona_produto);

        StrictMode.ThreadPolicy sop= new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(sop);

        dados = DadosView.Instance();
        produtoService = new ProdutoService(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        try {
            dados.setProdutos(produtoService.listarTodosProdutos());
            listaProdutos();
        }catch(Exception e){
            Log.e(DadosView.TAG_APP, e.getMessage());
            Toast.makeText(this, "Não foi possivel busca os Produtos", Toast.LENGTH_LONG).show();
        }
    }

    public void listaProdutos(){
        listView = (ListView) findViewById(R.id.lvProdSelecionar);
        listView.setAdapter(new ProdutoAdapter(this, dados.getProdutos()));
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        dados.setProdutoSelecionado((Produto) parent.getAdapter().getItem(position));
        Intent intent = new Intent(this, InfoProdutoActivity.class);
        startActivity(intent);
    }
}
