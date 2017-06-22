package br.com.adsddm.pedidovenda.service;

import android.util.Log;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.EmptyStackException;
import java.util.List;

import br.com.adsddm.pedidovenda.R;
import br.com.adsddm.pedidovenda.model.Cliente;
import br.com.adsddm.pedidovenda.model.ItemPedidoVenda;
import br.com.adsddm.pedidovenda.model.PedidoVenda;
import br.com.adsddm.pedidovenda.model.Produto;
import br.com.adsddm.pedidovenda.reqServer.PedidoVendaReqServer;

public class PedidoVendaService {
    private PedidoVendaReqServer pedidoVendaReqServer = new PedidoVendaReqServer();

    public void enviarPedidoVenda(PedidoVenda pedidoVenda) throws Exception {
        String json = null;
        validarPedidoVenda(pedidoVenda);
        json = pedidoVendaToJson(pedidoVenda);
        pedidoVendaReqServer.enviaPedidoVenda(json);
    }

    public String pedidoVendaToJson(PedidoVenda pedidoVenda) throws Exception {
        JSONObject root = new JSONObject();
        JSONObject obj = new JSONObject();
        JSONArray jItems = new JSONArray();

        try {
            obj.put("idcliente", pedidoVenda.getCliente().getId());

            for (int i = 0; i < pedidoVenda.getItempedidovendas().size(); i++) {
                JSONObject item = new JSONObject();
                item.put("idproduto", pedidoVenda.getItempedidovendas().get(i).getProduto().getIdServidor());
                item.put("qtd", pedidoVenda.getItempedidovendas().get(i).getQtd());

                jItems.put(item);
            }

            obj.put("items", jItems);
            root.put("pedidovenda", obj);
        } catch (JSONException e) {
            throw new Exception("Erro Interno: Contate com seu Administrador");
        }
        Log.i("PEDIDOVENDA", root.toString());
        return root.toString();
    }

    public void validarPedidoVenda(PedidoVenda pedidoVenda) throws Exception {
        String msgInvalida = null;
        if (pedidoVenda == null)
            msgInvalida = "\n" + "Pedido de Venda:" + "Não Pode Ser Nulo";
        if (pedidoVenda.getCliente() == null)
            msgInvalida = "\n" + "Cliente :" + "Não Inserido ";
        if (pedidoVenda.getItempedidovendas() == null)
            msgInvalida = "\n" + "Itens Pedido de Venda: " + "Não Inserido";
        else {
            if (pedidoVenda.getItempedidovendas().isEmpty())
                msgInvalida = "\n" + "Itens Pedido de Venda: " + "Não Inserido";

            for (int i = 0; i < pedidoVenda.getItempedidovendas().size(); i++) {
                if (pedidoVenda.getItempedidovendas().get(i).getProduto() == null)
                    msgInvalida = "\n" + "Item Pedido Posição[" + i + "] :" + "Produto " + "Não Inserido";
                if (pedidoVenda.getItempedidovendas().get(i).getQtd() <= 0)
                    msgInvalida = "\n" + "Item Pedido Posição[" + i + "] :" + "Qtd " + "Não Inserido" + " Ou Invalido";
            }
        }
        if (msgInvalida != null)
            throw new Exception(msgInvalida);
    }

    public Double subTotal(PedidoVenda pedidoVenda){
        Double subTotal = 0d;
        for (ItemPedidoVenda i : pedidoVenda.getItempedidovendas()){
            subTotal += (double) (i.getQtd() * i.getProduto().getPreco());
        }
        return subTotal;
    }

    //Retorna objeto itemPedidoVenda em que o produto está
    public ItemPedidoVenda pegarItemPedidoVendaDeProduto(Produto produto, PedidoVenda pedidoVenda) {
        List<ItemPedidoVenda> items = pedidoVenda.getItempedidovendas();
        Produto produtoPedidoVenda = null;
        ItemPedidoVenda item = null;

        for(int i =0; i < items.size() && item == null; i++){
            if(items.get(i).getProduto().equals(produto)){
                item = items.get(i);
            };
        }
        return item;
    }
    public PedidoVenda limparPedidoVenda(){
        PedidoVenda pedidoVenda = new PedidoVenda();
        return pedidoVenda;
    }
}
