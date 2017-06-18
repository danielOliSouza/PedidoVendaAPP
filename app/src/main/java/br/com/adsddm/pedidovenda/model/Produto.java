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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Produto produto = (Produto) o;

        if (_id != produto._id) return false;
        if (idServidor != produto.idServidor) return false;
        if (Float.compare(produto.preco, preco) != 0) return false;
        return nome != null ? nome.equals(produto.nome) : produto.nome == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (_id ^ (_id >>> 32));
        result = 31 * result + (int) (idServidor ^ (idServidor >>> 32));
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (preco != +0.0f ? Float.floatToIntBits(preco) : 0);
        return result;
    }
}
