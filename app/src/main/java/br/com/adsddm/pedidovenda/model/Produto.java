package br.com.adsddm.pedidovenda.model;

/**
 * Created by Daniel on 24/05/2017.
 */

public class Produto {
    private long id;
    private String nome;
    private float preco;
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
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
}
