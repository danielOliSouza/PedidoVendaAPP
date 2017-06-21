package br.com.adsddm.pedidovenda.model;

/**
 * Created by Daniel on 24/05/2017.
 */

public class Cliente {
    private Integer id;

    private String nome;

    public Cliente() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\n"
                +"Nome: " + nome;
    }
}
