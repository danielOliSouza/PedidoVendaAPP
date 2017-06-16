package br.com.adsddm.pedidovenda.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import br.com.adsddm.pedidovenda.R;
import br.com.adsddm.pedidovenda.dadosview.DadosView;
import br.com.adsddm.pedidovenda.model.ItemPedidoVenda;
import br.com.adsddm.pedidovenda.model.Produto;

public class InfoProdutoActivity extends AppCompatActivity {
    private Produto produto = null;
    private DadosView dadosView = null;

    private TextView tvId = null;
    private TextView tvPreco = null;
    private TextView tvDescricao = null;
    private EditText etQtd = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_produto);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);//Abrir Teclado

        dadosView = DadosView.Instance();
        produto = dadosView.getProdutoSelecionado();

        tvId = (TextView) findViewById(R.id.tvInfoId);
        tvPreco = (TextView) findViewById(R.id.tvPreco);
        tvDescricao = (TextView) findViewById(R.id.tvDescricao);
        etQtd = (EditText) findViewById(R.id.etQtd);
        etQtd.requestFocus();

        preencheCampos();
        actionEnter();
    }
    //Adciona um ouvinte ao campo QTD (Ao precionar 'enter' executa o metodo onAddProdutoLista)
    public void actionEnter(){
        etQtd.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    onAddProdutoLista(v);
                    return true;
                }
                return false;
            }
        });
    }

    public void preencheCampos(){
        if(dadosView.getProdutoSelecionado() == null){
            Toast.makeText(this, "Erro no Processo Interno", Toast.LENGTH_LONG).show();
        }
        else{
            tvId.setText(String.valueOf(produto.getIdServidor()));
            tvPreco.setText(produto.getPrecoToString());
            tvDescricao.setText(produto.getNome());
        }
    }

    public void onAddProdutoLista(View view){
        ItemPedidoVenda item = new ItemPedidoVenda();
        item.setProduto(dadosView.getProdutoSelecionado());

        item.setQtd(Integer.parseInt(etQtd.getText().toString()));

        dadosView.getPedidoVenda().getItempedidovendas().add(item);
        finish();
    }
}
