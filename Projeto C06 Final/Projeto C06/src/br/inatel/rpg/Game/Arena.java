package br.inatel.rpg.Game;

import br.inatel.rpg.interfaces.Heroi;
import br.inatel.rpg.interfaces.Inimigo;
import br.inatel.rpg.personagens.*;

import java.util.Random;

public class Arena implements Runnable {
    Personagem p1, p2;

    public Arena (Personagem p1, Personagem p2) {
        this.p1 = p1;
        this.p2 = p2;
        p1.mostrarInfo();
    }

    public Thread iniciarBatalha() {
        Thread t = new Thread(this);
        t.start();
        return t;
    }

    @Override
    public void run() {
        Random random = new Random();

        while(p1.getVida() > 0 && p2.getVida() > 0) {
            if(!p2.defender()  && p1.getVida() > 0) {
                p1.atacar(p2);
            }

            if(p1 instanceof Mago && p1.getVida() > 0) {
                ((Mago) p1).usarFeitico(p2);
            }


            if(p1 instanceof Guerreiro && p1.getVida() > 0) {
                if (random.nextInt(10) <= 2) {
                    ((Guerreiro) p1).bonusAtk(p2);
                }
            }

            if((p1 instanceof Mago || p1 instanceof Guerreiro) && p1.getVida() > 0) {
                if (random.nextInt(12) <= 3) {
                    try {
                        ((Heroi) p1).usarInventario();
                    } catch (Exception e) {
                        System.out.println(p1.getNome() + " não possuí itens no inventário");
                    }
                }
            }

            if(p1 instanceof Orc  && p1.getVida() > 0) {
                if (random.nextInt(10) <= 1) {
                    ((Orc) p1).Intimidacao(p2);
                }
            }

            if((p1 instanceof Orc || p1 instanceof Goblin) && p1.getVida() > 0) {
                if (random.nextInt(15) <= 3) {
                    ((Inimigo) p1).rage();
                }
            }
        }
        if(p1 instanceof Goblin && p1.getVida() <= 0) {
            ((Goblin) p1).droprarOuro();
        }

        if(p1.getVida() > 0) {
            System.out.printf("%s saiu vencedor da batalha com %.2f de vida restante\n", p1.getNome(), p1.getVida());
        }

    }
}
