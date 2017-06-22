package br.com.adsddm.pedidovenda.model;

public class Cliente {
    private Integer id;
    private String nome;
    private String cpf;

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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\n"
                +"Nome: " + nome + "\n"
                +"CPF: " + cpf;
    }
}
