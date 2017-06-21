package br.com.adsddm.pedidovenda.controller;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.com.adsddm.pedidovenda.dadosview.DadosView;
import br.com.adsddm.pedidovenda.R;
import br.com.adsddm.pedidovenda.adapter.ItemPedidoVendaAdapter;
import br.com.adsddm.pedidovenda.model.Cliente;
import br.com.adsddm.pedidovenda.model.ItemPedidoVenda;
import br.com.adsddm.pedidovenda.model.PedidoVenda;
import br.com.adsddm.pedidovenda.service.PedidoVendaService;
import br.com.adsddm.pedidovenda.util.Mask;

public class PedidoVendaActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private ListView listView;
    private EditText etCPF;
    private TextView tvSubTotal;
    private EditText etCliente;

    private DadosView dadosView = DadosView.Instance();
    private PedidoVendaService pedidoVendaService = new PedidoVendaService();

    private PedidoVenda pedidoVenda = dadosView.getPedidoVenda();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_venda);

        StrictMode.ThreadPolicy sop= new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(sop);

        etCPF = (EditText) findViewById(R.id.etCPF);
        tvSubTotal = (TextView) findViewById(R.id.subTotal);
        listView = (ListView) findViewById(R.id.lvProdutos);
        etCliente = (EditText) findViewById(R.id.etCliente);

        pedidoVendaService = new PedidoVendaService();

        etCPF.addTextChangedListener(Mask.insert("###.###.###-##", etCPF));
    }

    @Override
    protected void onStart() {
        super.onStart();
        listaProduto();
        tvSubTotal.setText(String.format("%.2f", pedidoVendaService.subTotal(pedidoVenda)).replace(".",","));
        if(pedidoVenda.getCliente() != null)
            etCliente.setText(pedidoVenda.getCliente().getNome());
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    }

    public void onAddProduto(View view){
        Intent intent = new Intent(this, SelecionaProdutoActivity.class);
        startActivity(intent);
    }

    public void onSalvar(View view){
        try {
            pedidoVendaService.enviarPedidoVenda(pedidoVenda);
            pedidoVenda = pedidoVendaService.limparPedidoVenda();
            dadosView.setPedidoVenda(pedidoVenda);
            listaProduto();
        }catch (Exception e) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this, android.R.style.Theme_Holo_Dialog);
            builder.setTitle("Alerta")
                    .setMessage(e.getMessage())
                    .setIcon(android.R.drawable.stat_sys_warning)
                    .show();
        }

    }

    public void listaProduto(){
        List<ItemPedidoVenda> itens = pedidoVenda.getItempedidovendas();
        listView = (ListView) findViewById(R.id.lvProdutos);
        listView.setAdapter(new ItemPedidoVendaAdapter(this, itens));
        listView.setOnItemClickListener(this);

        //Gambiarra, cambiarra pura!!!
        //O scroll do list não esta funcionando
        //O list nao expande (wrap_context)
        listView.getLayoutParams().height = itens.size() * 180;
    }
    public void selecionarCliente(View view){
        Intent intent = new Intent(this, PesquisarClienteActivity.class);
        startActivity(intent);

    }


}
