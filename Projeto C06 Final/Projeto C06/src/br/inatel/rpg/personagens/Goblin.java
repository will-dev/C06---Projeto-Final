package br.inatel.rpg.personagens;

import br.inatel.rpg.interfaces.Inimigo;
import br.inatel.rpg.itens.Arma;
import java.util.Random;

public class Goblin extends Personagem implements Inimigo {
    private int ouro;
    Random random = new Random();

    public Goblin(String nome, float vida, int forca, Arma arma, int ouro) {
        super(nome, vida, forca,arma);
        this.ouro = ouro;
    }

    @Override
    public boolean defender() {
        if(random.nextInt(10) <= 1){
            System.out.printf("%s, o goblin, escapa do ataque e sai ileso.\n", nome);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public void atacar(Personagem p) {
        float dado = random.nextInt(21), dano = 0;
        if(dado == 0){
            System.out.printf("%s, o goblin, erra o ataque.\n", nome);
        }
        else if (dado == 20){
            dano = arma.getDano() + forca;
            System.out.printf("%s, o goblin, acerta um ataque crítico no oponente resultando em %.2f de dano.\n", nome, dano);
        }
        else {
            dano = (float) (dado * 0.05 * (forca + arma.getDano()));
            System.out.printf("%s, o goblin, acerta seu ataque resultando em %.2f de dano.\n", nome, dano);
        }

        float novaV = p.getVida() - dano;
        if(novaV < 0) {
            novaV = 0;
        }
        p.setVida(novaV);
    }

    @Override
    public void mostrarInfo() {
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.println("Informações do goblin:");
        System.out.println("Nome: " + nome);
        System.out.println("Vida: " + vida);
        System.out.println("Força: " + forca);
        System.out.println("-= Arma utilizada =-");
        System.out.println("Nome: " + arma.getNome());
        System.out.println("Dano: " + arma.getDano());
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
    }

    public int droprarOuro() {
        System.out.printf("%s, o goblin, deixa todo seu ouro cair ao ser derrotado, dropando %d de ouro", nome, ouro);
        return ouro;
    }

    @Override
    public void rage() {
        System.out.println("O goblin ficou com raiva e sua força aumentou");
        forca += 8;
    }
}
