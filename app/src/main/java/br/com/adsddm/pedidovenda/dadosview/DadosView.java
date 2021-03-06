package br.com.adsddm.pedidovenda.dadosview;

import java.util.ArrayList;
import java.util.List;

import br.com.adsddm.pedidovenda.model.Cliente;
import br.com.adsddm.pedidovenda.model.PedidoVenda;
import br.com.adsddm.pedidovenda.model.Produto;

public class DadosView {
    private static DadosView _instance = null;

    private PedidoVenda pedidoVenda;
    private List<Produto> produtos;
    private List<Cliente> clientes;
    private Produto produtoSelecionado = null;

    public static final String TAG_APP = "ADSDDM";
    public static final String URL_SERVIDOR = "http://192.168.1.5:8080/";

    private DadosView(){
        pedidoVenda = new PedidoVenda();
        produtos = new ArrayList<>();
        clientes = new ArrayList<>();
    }

    public static DadosView Instance(){ //Singleton - Só uma instacia
        if(_instance == null){
            _instance = new DadosView();
        }
        return _instance;
    }

    public PedidoVenda getPedidoVenda() {
        return pedidoVenda;
    }

    public void setPedidoVenda(PedidoVenda pedidoVenda) {
        this.pedidoVenda = pedidoVenda;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public Produto getProdutoSelecionado() {
        return produtoSelecionado;
    }

    public void setProdutoSelecionado(Produto produtoSelecionado) {
        this.produtoSelecionado = produtoSelecionado;
    }
}
