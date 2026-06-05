package br.inatel.rpg.interfaces;

import br.inatel.rpg.itens.Item;

public interface Heroi {
    public void addItem (Item item) throws Exception;

    public void usarInventario () throws Exception;
}
