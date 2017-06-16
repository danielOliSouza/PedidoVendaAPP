package br.com.adsddm.pedidovenda.service;

import android.util.Log;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.adsddm.pedidovenda.R;
import br.com.adsddm.pedidovenda.model.Cliente;
import br.com.adsddm.pedidovenda.model.ItemPedidoVenda;
import br.com.adsddm.pedidovenda.model.PedidoVenda;
import br.com.adsddm.pedidovenda.model.Produto;
import br.com.adsddm.pedidovenda.reqServer.PedidoVendaReqServer;

/**
 * Created by Daniel on 25/05/2017.
 */

public class PedidoVendaService {
    public void inicializaPedidoTest(PedidoVenda pedidoVenda){
        Cliente c = new Cliente();
        c.setId(1);
        c.setNome("Fulano de Tal");
        pedidoVenda.setCliente(c);
    }

    public boolean enviarPedidoVenda(PedidoVenda pedidoVenda)  {
        boolean status = true;

        JSONObject root = new JSONObject();
        JSONObject obj = new JSONObject();
        JSONArray jItems = new JSONArray();
        try {
            obj.put("idcliente", pedidoVenda.getCliente().getId());

            for(int i = 0; i < pedidoVenda.getItempedidovendas().size(); i++){
                JSONObject item = new JSONObject();
                item.put("idproduto", pedidoVenda.getItempedidovendas().get(i).getProduto().getIdServidor());
                item.put("qtd", pedidoVenda.getItempedidovendas().get(i).getQtd());

                jItems.put(item);
            }

            obj.put("items", jItems);
            root.put("pedidovenda", obj);
            Log.i("PEDIDOVENDA", root.toString());
            new PedidoVendaReqServer().enviaPedidoVenda(root.toString());
        }catch(Exception e){
            Log.e("PEDIDOVENDA", e.getMessage());
            status=false;
        }
        return status;
    }
}
