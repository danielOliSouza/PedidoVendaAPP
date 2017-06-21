package br.com.adsddm.pedidovenda.service;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.adsddm.pedidovenda.dadosview.DadosView;
import br.com.adsddm.pedidovenda.model.Cliente;
import br.com.adsddm.pedidovenda.model.Produto;
import br.com.adsddm.pedidovenda.reqServer.ClienteReqServer;

/**
 * Created by Rosivaldo on 20/06/2017.
 */

public class ClienteService {
    private ClienteReqServer clienteReqServer = new ClienteReqServer();

    public List<Cliente> selecionarClientes()  throws Exception{
        String result = null;
        List<Cliente> clientes = new ArrayList<>();

        result = clienteReqServer.pegarListaClientes();
        JSONArray root = new JSONArray(result);
        for(int i = 0; i < root.length(); i++){
            JSONObject obj = new JSONObject(root.get(i).toString());
            Log.i(DadosView.TAG_APP, obj.toString());
            Cliente cliente= new Cliente();

            cliente.setId(Integer.parseInt(obj.getString("id")));
            cliente.setNome(obj.getString("nome"));

            clientes.add(cliente);
        }

        return clientes;
    }
}
