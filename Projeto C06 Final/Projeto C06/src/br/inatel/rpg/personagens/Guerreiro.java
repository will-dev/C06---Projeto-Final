
package br.inatel.rpg.personagens;
import br.inatel.rpg.interfaces.Heroi;
import br.inatel.rpg.itens.Arma;
import br.inatel.rpg.itens.Item;
import java.util.Random;
import java.util.Vector;



public class Guerreiro extends Personagem implements Heroi {

    //atributos
    Random random = new Random();
    Vector<Item>inventario = new Vector<>();



    public Guerreiro(int força, int vida, Arma arma, String nome)
    {

        super (nome, vida, força, arma);

    }


    // metodos

    @Override
    public boolean defender() {
        if(random.nextInt(10) <= 5){ // 50% de chance de defender
            System.out.printf("%s, o Guerreiro, defende.\n", this.nome);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public void atacar(Personagem p) {
        float dado = random.nextInt(21), dano = 0;
        if(dado ==0 ){
            dano = forca;
            System.out.printf("%s, o Guerreiro, causa somente dano de força. Dano = %.2f.\n", this.nome,dano);
        }
        else if (dado > 0 && dado < 15 ){
            dano = arma.getDano() + forca;
            System.out.printf("%s, o Guerreiro, usa sua arma para dar %.2f de dano.\n", nome, dano);
        }
        else {
            dano = 2 * (forca + arma.getDano());
            System.out.printf("%s, o Guerreiro, dá golpe duplo, resultando em %.2f de dano.\n", nome, dano);
        }

        if(p instanceof Orc){
            dano -= ((Orc) p).getDefesa();
        }

        if(dano < 0){
            dano = 0;
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
        System.out.printf("Informações do %s, o Guerreiro:\n", nome);
        System.out.println("Nome: " + nome);
        System.out.println("Vida: " + vida);
        System.out.println("Força: " + forca);
        System.out.println("-= Arma utilizada =-");
        System.out.println("Nome: " + arma.getNome());
        System.out.println("Dano: " + arma.getDano());
        System.out.println("-= Inventario =-");

        for(int i = 0; i< inventario.size();i++)
        {
            System.out.println("Item" + i + " : " + inventario.get(i).getNome());
        }
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
    }

    public void bonusAtk(Personagem p)
    {
        float dado = random.nextInt(11);
        float dano = (float) (forca* (dado * 0.01));

        if(p instanceof Orc){
            dano -= ((Orc) p).getDefesa();
        }

        if(dano < 0){
            dano = 0;
        }

        float novaV = p.getVida() - dano;
        if(novaV < 0) {
            novaV = 0;
        }
        p.setVida(novaV);

    }


    public void addItem (Item item) throws Exception {

       //Verifica se ultrapassou o limite de 5 unidades
        if(inventario.size() == 5)
        {
            throw new Exception("I cannot carry any more!");
        }

        //se não ultrapassou, cria uma posição no final do vetor e retorna a posição.
        inventario.add(item);
        System.out.println("Item " + item.getNome() + " adicionado com sucesso ao inventario do Guerreiro");

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

        if(item.getNome().equals("Escudo de Carvalho")){
            float novaV = vida + 5;
            setVida(novaV);
        }

        if(item.getNome().equals("Poção de Força")){
            forca += 3;
        }


    }


}
