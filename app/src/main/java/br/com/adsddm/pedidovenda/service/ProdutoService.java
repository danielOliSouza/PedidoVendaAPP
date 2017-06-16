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

/**
 * Created by Daniel on 04/06/2017.
 */

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

    public void salvar() throws Exception{
        try {
            List<Produto> produtos = pegarListaProdutos();
            salvar(produtos);
        }catch (Exception e){
            Log.e(DadosView.TAG_APP, "salvar" + e.getMessage());
        }
    }
    public void salvar(Produto produto) throws Exception{
        produtoRepository.guardar(produto);
    }
    public void salvar(List produtos) throws  Exception{
        produtoRepository.guardar(produtos);
    }

    public List<Produto> atualizarProdutos() throws Exception{
        salvar();
        return  listarTodosProdutos();
    }

    public List<Produto> listarTodosProdutos() throws  Exception{
        if(produtoRepository.pesquisar().isEmpty()){
            salvar(pegarListaProdutos());
        };
        return produtoRepository.pesquisar();
    }
}
