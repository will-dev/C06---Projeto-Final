package br.inatel.rpg.itens;

public class Feitico {
    private String nome;
    private int dano;

    public Feitico(String nome, int dano) {
        this.nome = nome;
        this.dano = dano;
    }

    public String getNome() {
        return nome;
    }

    public int getDano() {
        return dano;
    }
}
