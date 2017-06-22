package br.com.adsddm.pedidovenda.service;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.adsddm.pedidovenda.dadosview.DadosView;
import br.com.adsddm.pedidovenda.model.Produto;
import br.com.adsddm.pedidovenda.repository.ProdutoRepository;
import br.com.adsddm.pedidovenda.reqServer.ProdutoReqServer;

public class ProdutoService {
    private ProdutoReqServer produtoReqServer;
    private ProdutoRepository produtoRepository;

    public ProdutoService(Context context) {
        produtoReqServer = new ProdutoReqServer();
        produtoRepository = new ProdutoRepository(context);
    }

    public List<Produto> pegarListaProdutos()  throws Exception{
        String result = null;
        List<Produto> produtos = new ArrayList<>();

        result = produtoReqServer.pegarListaProdutos();
        JSONArray root = new JSONArray(result);
        for(int i = 0; i < root.length(); i++){
            JSONObject obj = new JSONObject(root.get(i).toString());
            Log.i(DadosView.TAG_APP, obj.toString());
            Produto produto = new Produto();

            produto.setIdServidor(Integer.parseInt(obj.getString("id")));
            produto.setPreco(Float.parseFloat(obj.getString("preco")));
            produto.setNome(obj.getString("nome"));

            produtos.add(produto);
        }

        return produtos;
    }


    public void salvar(Produto produto) throws Exception{
        produtoRepository.guardar(produto);
    }
    public List<Produto> salvar(List produtos) throws  Exception{
        return produtoRepository.guardar(produtos);
    }

    public List<Produto> atualizarProdutos() throws Exception{
        List<Produto> produtos = pegarListaProdutos();
        produtoRepository.deleteAll();
        salvar(produtos);
        return  listarTodosProdutos();
    }

    public List<Produto> listarTodosProdutos() throws  Exception{
        List<Produto> produtos =  produtoRepository.pesquisar();
        if(produtos.isEmpty()){
            produtos = salvar(pegarListaProdutos());
        };
        return produtos;
    }
}
