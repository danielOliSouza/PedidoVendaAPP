package br.com.adsddm.pedidovenda.controller;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
    private Produto produtoSelecionado;

    private EditText etDescricao;
    private EditText etQtd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleciona_produto);

        StrictMode.ThreadPolicy sop= new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(sop);

        etDescricao = (EditText) findViewById(R.id.etDescricao);
        etQtd = (EditText) findViewById(R.id.etQtd);

        dados = DadosView.Instance();
        produtoService = new ProdutoService();

        dados.setProdutos(produtoService.pegarListaProdutos());
        etDescricao.requestFocus();
        listaProdutos();
    }



    public void onAddProduto(View view){
        ItemPedidoVenda item = new ItemPedidoVenda();
        item.setProduto(produtoSelecionado);
        item.setQtd(12);

        dados.getPedidoVenda().getItempedidovendas().add(item);

        produtoSelecionado = new Produto();
        preencheCampos();
        Toast.makeText(this,"Produto Inserido no Pedido Venda", Toast.LENGTH_SHORT).show();
    }

    public void listaProdutos(){
        listView = (ListView) findViewById(R.id.lvProdSelecionar);
        listView.setAdapter(new ProdutoAdapter(this, dados.getProdutos()));
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        produtoSelecionado = (Produto) parent.getAdapter().getItem(position);
        preencheCampos();
    }

    public void preencheCampos(){
        etDescricao.setText(produtoSelecionado.getNome());
        etQtd.requestFocus();
    }
}
