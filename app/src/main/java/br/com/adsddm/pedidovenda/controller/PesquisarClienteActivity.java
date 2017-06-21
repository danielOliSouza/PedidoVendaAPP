package br.com.adsddm.pedidovenda.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.adsddm.pedidovenda.R;
import br.com.adsddm.pedidovenda.dadosview.DadosView;
import br.com.adsddm.pedidovenda.model.Cliente;
import br.com.adsddm.pedidovenda.service.ClienteService;

public class PesquisarClienteActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ClienteService clienteService = new ClienteService();
    private DadosView dados = DadosView.Instance();
    private ListView lvCliente;

    private List<Cliente>  clientes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisar_cliente);

        lvCliente = (ListView)findViewById(R.id.lvClientes);

        try {
            clientes = clienteService.selecionarClientes();
            ArrayAdapter<Cliente> adapter = new ArrayAdapter<Cliente>(this,
                    android.R.layout.simple_list_item_1, clientes);

            lvCliente.setAdapter(adapter);
            lvCliente.setOnItemClickListener(this);

        } catch (Exception e) {
            Toast.makeText(this, "Servidor Indisponivel no Momento", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        dados.getPedidoVenda().setCliente(clientes.get(position));
        finish();
    }
}
