package br.com.adsddm.pedidovenda.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 24/05/2017.
 */

public class PedidoVenda {
    private Integer id;
    private List<ItemPedidoVenda> itempedidovendas = new ArrayList<>();
    private Cliente cliente;

    public PedidoVenda() {
    }
    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public List<ItemPedidoVenda> getItempedidovendas() {
        return this.itempedidovendas;
    }
    public void setItempedidovendas(List<ItemPedidoVenda> itempedidovendas) {
        this.itempedidovendas = itempedidovendas;
    }
    public Cliente getCliente() {
        return this.cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
