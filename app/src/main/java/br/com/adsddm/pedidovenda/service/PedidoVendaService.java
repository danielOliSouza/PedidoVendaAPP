package br.com.adsddm.pedidovenda.service;

import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.adsddm.pedidovenda.model.Cliente;
import br.com.adsddm.pedidovenda.model.ItemPedidoVenda;
import br.com.adsddm.pedidovenda.model.PedidoVenda;
import br.com.adsddm.pedidovenda.model.Produto;
import br.com.adsddm.pedidovenda.reqServer.PedidoVendaReqServer;

/**
 * Created by Daniel on 25/05/2017.
 */

public class PedidoVendaService {
    private ObjectMapper mapper = new ObjectMapper();

    public void inicializaPedidoTest(PedidoVenda pedidoVenda){
        Cliente c = new Cliente();
        c.setId(1);
        c.setNome("Fulano de Tal");
        pedidoVenda.setCliente(c);

        ItemPedidoVenda item = new ItemPedidoVenda();
        item.setQtd(12);

        Produto p = new Produto();
        p.setNome("Prod A");
        p.setPreco(12.1f);
        p.setId(1);
        item.setProduto(p);
    }

    public  String enviarPedidoVenda(PedidoVenda pedidoVenda){
        return  new PedidoVendaReqServer().enviaPedidoVenda();
    }
}
