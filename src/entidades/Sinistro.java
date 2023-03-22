package entidades;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Sinistro {
    private int id;
    private String data;
    private String endereco;
    private static Set<Integer> idSet = new HashSet<Integer>(); // previne a duplicidade de IDs

    public Sinistro(){
        this.setId();
    }

    public Sinistro(String data, String endereco) {
        this.setData(data);
        this.setEndereco(endereco);
        this.setId();
    }
    
    /*
     * Gera e um id aleatorio e *único*
     */
    public static int generateRandomID(){
        Random random = new Random();
        int num = random.nextInt(100000, 999999);
        if (idSet.contains(num)){
            return generateRandomID();
        }
        return num;
    }

    /**
     *  Retorna uma String com os valores atuais do objeto
     */
    public String toString(){
        return String.format("ID: %d, Data: %s, Endereço: %s", id, data, endereco);
    }

    // getters e setters

    public int getId() {
        return this.id;
    }

    private void setId() {
        int id = generateRandomID();
        idSet.add(id);
        this.id = id;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data.trim();
    }

    public String getEndereco() {
        return this.endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco.trim();
    }
}
