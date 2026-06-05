package br.inatel.rpg.personagens;

import br.inatel.rpg.interfaces.Inimigo;
import br.inatel.rpg.itens.Arma;


import java.util.Random;


public class Orc extends Personagem implements Inimigo {
    private int defesa;
    Random random = new Random();



    public Orc(String nome, float vida, int forca, Arma arma, int defesa){
        super(nome, vida, forca, arma);
        this.defesa = defesa;
    }

    public int getDefesa() {
        return defesa;
    }

    @Override
    public boolean defender(){
        if(random.nextInt(30) <= 1){
            System.out.println(nome + " não sofreu dano");
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
            System.out.printf("%s, o Orc, erra o ataque.\n", nome);
        }
        else if (dado == 20){
            dano = arma.getDano() + forca;
            System.out.printf("%s, o Orc, acerta um ataque crítico no oponente resultando em %f de dano.\n", nome, dano);
        }
        else {
            dano = (float) (dado * 0.05 * (forca + arma.getDano()));
            System.out.printf("%s, o Orc, acerta seu ataque resultando em %.2f de dano.\n", nome, dano);
        }

        float novaV = p.getVida() - dano;
        if(novaV < 0) {
            novaV = 0;
        }
        p.setVida(novaV);
    }

    @Override
    public void mostrarInfo(){
        System.out.println("----------------------------------------");
        System.out.println("Informações do ORC:");
        System.out.println("Nome: " + nome);
        System.out.println("Vida: " + vida);
        System.out.println("Força: " + forca);
        System.out.println("Defesa: " + defesa);
        System.out.println("Arma: " + arma.getNome());
        System.out.println("Dano da arma: " + arma.getDano());
        System.out.println();
    }

    public void Intimidacao(Personagem personagem) {
        System.out.println("Orc intimidou o " + personagem.nome);
        personagem.forca -= 3;
        personagem.vida -= 3;
    }

    @Override
    public void rage(){
        System.out.println("Orc ficou com raiva, sua força e defesa aumentaram");
        forca += 10;
        defesa += 5;
    }
}