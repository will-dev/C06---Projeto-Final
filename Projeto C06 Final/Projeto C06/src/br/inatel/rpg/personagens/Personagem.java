package br.inatel.rpg.personagens;

import br.inatel.rpg.itens.Arma;

public abstract class Personagem {
    protected int forca;
    protected float vida;
    protected Arma arma;
    String nome;

    public Personagem(String nome, float vida, int forca, Arma arma) {
        this.forca = forca;
        this.vida = vida;
        this.arma = arma;
        this.nome = nome;
    }

    public float getVida() {
        return vida;
    }

    public String getNome() {
        return nome;
    }

    public void setVida(float vida) {
        this.vida = vida;
    }

    public abstract boolean defender();

    public abstract void atacar(Personagem p);

    public abstract void mostrarInfo();
}
