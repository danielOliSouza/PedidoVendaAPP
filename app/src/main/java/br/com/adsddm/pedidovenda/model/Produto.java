package br.com.adsddm.pedidovenda.model;

import java.nio.FloatBuffer;

/**
 * Created by Daniel on 24/05/2017.
 */

public class Produto {
    private long _id;
    private long idServidor;
    private String nome;
    private float preco;

    public long getIdServidor() {
        return idServidor;
    }
    public void setIdServidor(long idServidor) {
        this.idServidor = idServidor;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public float getPreco() {
        return preco;
    }
    public void setPreco(float preco) {
        this.preco = preco;
    }
    public String getPrecoToString(){
        return String.format("%.2f", getPreco()).replace(".",",");
    }
    public long get_id() {
        return _id;
    }
    public void set_id(long _id) {
        this._id = _id;
    }
}
