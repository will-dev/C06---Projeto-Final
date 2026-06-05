package br.inatel.rpg.personagens;

import br.inatel.rpg.interfaces.Heroi;
import br.inatel.rpg.itens.Arma;
import br.inatel.rpg.itens.Feitico;
import br.inatel.rpg.itens.Grimorio;
import br.inatel.rpg.itens.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Mago extends Personagem implements Heroi {
    private Grimorio grimorio;
    private List<Item> inventario = new ArrayList<>();
    private int mana;
    Random random = new Random();


    public Mago(String nome, float vida, int forca, Arma arma, Grimorio grimorio, int mana){
        super(nome, vida, forca, arma);
        this.grimorio = grimorio;
        this.mana = mana;
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
            System.out.printf("%s, o Mago, erra o ataque.\n", nome);
        }
        else if (dado == 20){
            dano = arma.getDano() + forca;
            if(p instanceof Orc){
                dano -= ((Orc) p).getDefesa();
            }
            if(dano < 0){
                dano = 0;
            }
            System.out.printf("%s, o Mago, acerta um ataque crítico no oponente resultando em %f de dano.\n", nome, dano);
        }
        else {
            dano = (float) (dado * 0.05 * (forca + arma.getDano()));
            if(p instanceof Orc){
                dano -= ((Orc) p).getDefesa();
            }
            if(dano < 0){
                dano = 0;
            }
            System.out.printf("%s, o Mago, acerta seu ataque resultando em %.2f de dano.\n", nome, dano);
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
        System.out.println("Informações do Mago:");
        System.out.println("Nome: " + nome);
        System.out.println("Vida: " + vida);
        System.out.println("Força: " + forca);
        System.out.println("Mana: " + mana);
        System.out.println("Arma: " + arma.getNome());
        System.out.println("Dano da arma: " + arma.getDano());
        System.out.println("Inventario: ");
        for (Item item : inventario){
            System.out.println(item.getNome() + ": " + item.getDescricao());
        }
        System.out.println("Magias: ");
        for (Feitico feitico : grimorio.getFeiticos()){
            System.out.println(feitico.getNome() + "/ Dano: " + feitico.getDano());
        }
        System.out.println();
    }

    @Override
    public void addItem(Item item) throws Exception{
        if(inventario.size() >= 5){
            throw new Exception("Não é possivel mais adicionar itens, iventario cheio");
        }
        inventario.add(item);
        System.out.println("Item " + item.getNome() + " adicionado com sucesso ao inventario do mago");
    }

    @Override
    public void usarInventario() throws Exception{
        if (inventario.isEmpty()){
            throw new Exception("Sem itens no inventario");
        }
        Item item = inventario.get(random.nextInt(inventario.size()));
        System.out.println(nome + " usa o item " + item.getNome());

        if(item.getNome().equals("Poção de Vida")){
            float novaV = vida + 25;
            setVida(novaV);
        }

        if(item.getNome().equals("Anel da Mente")){
            mana += 15;
        }
    }

    public void usarFeitico(Personagem inimigo){
        if(grimorio == null || grimorio.getFeiticos().isEmpty()){
            System.out.println(nome + " não possui feitiços em seu grimorio");
            return;
        }

        if(mana <=0 ){
            System.out.println(nome + " não possui mana suficiente para usar o feitiço");
            return;
        }

        Feitico feitico = grimorio.getFeiticos().get(random.nextInt(grimorio.getFeiticos().size()));
        mana -= 2;
        float dano = feitico.getDano();

        if(inimigo instanceof Orc){
            dano -= ((Orc) inimigo).getDefesa();
        }

        if(dano < 0){
            dano = 0;
        }

        System.out.println(nome + " usa o feitço " + feitico.getNome() + " no " + inimigo.getNome() + " com o dano de " + dano + " (mana restante: " + mana + " )" );

        float novaV = inimigo.getVida() - dano;

        if(novaV < 0){
            novaV = 0;
        }
        inimigo.setVida(novaV);
    }
}
