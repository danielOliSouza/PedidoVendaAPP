package br.com.adsddm.pedidovenda.controller;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

import br.com.adsddm.pedidovenda.R;
import br.com.adsddm.pedidovenda.dadosview.DadosView;
import br.com.adsddm.pedidovenda.model.ItemPedidoVenda;
import br.com.adsddm.pedidovenda.model.PedidoVenda;
import br.com.adsddm.pedidovenda.model.Produto;
import br.com.adsddm.pedidovenda.service.PedidoVendaService;

public class InfoProdutoActivity extends AppCompatActivity {
    private PedidoVendaService pedidoVendaService = new PedidoVendaService();

    private DadosView dadosView = DadosView.Instance();

    private ItemPedidoVenda itemPedidoVenda = null;
    private PedidoVenda pedidoVenda = dadosView.getPedidoVenda();

    private TextView tvId = null;
    private TextView tvPreco = null;
    private TextView tvDescricao = null;
    private EditText etQtd = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_produto);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);//Abrir Teclado

        tvId = (TextView) findViewById(R.id.tvInfoId);
        tvPreco = (TextView) findViewById(R.id.tvPreco);
        tvDescricao = (TextView) findViewById(R.id.tvDescricao);
        etQtd = (EditText) findViewById(R.id.etQtd);
        etQtd.requestFocus();

        inicializarItemPedidVenda();
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
        try {
            Produto produto = itemPedidoVenda.getProduto();
            if(produto == null){
                Toast.makeText(this, R.string.erro_processo_interno , Toast.LENGTH_LONG).show();
            }
            else{

                tvId.setText(String.valueOf(produto.getIdServidor()));
                tvPreco.setText(produto.getPrecoToString());
                tvDescricao.setText(produto.getNome());
                etQtd.setText(String.valueOf(itemPedidoVenda.getQtd()));

            }
        }catch (Exception e){
            Log.e("ADSDDM", "Erro: "+ e.getMessage());
        }
    }

    public void onCancelar(View v){
        finish();
    }
    public void onAddProdutoLista(View view){
        itemPedidoVenda.setQtd(Integer.parseInt(etQtd.getText().toString()));
        if(pedidoVenda.getItempedidovendas().indexOf(itemPedidoVenda) == -1){
            pedidoVenda.getItempedidovendas().add(itemPedidoVenda);
        }

        finish();
    }

    public void inicializarItemPedidVenda(){
        Produto produto = dadosView.getProdutoSelecionado();
        itemPedidoVenda = pedidoVendaService.pegarItemPedidoVendaDeProduto(produto, pedidoVenda);

        if( itemPedidoVenda == null){
            itemPedidoVenda = new ItemPedidoVenda();
            itemPedidoVenda.setProduto(produto);
            itemPedidoVenda.setQtd(01);
        }
    }
}
