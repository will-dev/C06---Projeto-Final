package br.inatel.rpg.itens;

import java.util.Vector;

public class Grimorio {
    Vector<Feitico> feiticos;

    public Grimorio() {
        this.feiticos = new Vector<>();

    }
    public Vector<Feitico> getFeiticos() {
        return feiticos;
    }


    public void addFeitico(String nome, int dano) {
        Feitico novoFeitico = new Feitico(nome, dano);
        this.feiticos.add(novoFeitico);
    }

}