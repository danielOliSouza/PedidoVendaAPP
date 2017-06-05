package br.com.adsddm.pedidovenda.service;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.adsddm.pedidovenda.dadosview.DadosView;
import br.com.adsddm.pedidovenda.model.Produto;
import br.com.adsddm.pedidovenda.reqServer.ProdutoReqServer;

/**
 * Created by Daniel on 04/06/2017.
 */

public class ProdutoService {
    ProdutoReqServer produtoReqServer;

    public ProdutoService(){
        produtoReqServer = new ProdutoReqServer();
    }
    public List<Produto> pegarListaProdutos(){
        String result = produtoReqServer.pegarListaProdutos();
        List<Produto> produtos = new ArrayList<>();



        try {
            JSONArray root = new JSONArray(result);
            for(int i = 0; i < root.length(); i++){
                JSONObject obj = new JSONObject(root.get(i).toString());
                Log.i(DadosView.TAG_APP, obj.toString());
                Produto produto = new Produto();

                produto.setId(Integer.parseInt(obj.getString("id")));
                produto.setPreco(Float.parseFloat(obj.getString("preco")));
                produto.setNome(obj.getString("nome"));

                produtos.add(produto);
            }
        }catch (Exception e){
            Log.e(DadosView.TAG_APP, e.getMessage());
        }
        return produtos;
    }
}
