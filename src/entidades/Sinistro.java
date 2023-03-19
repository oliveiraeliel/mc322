package entidades;

import java.util.Random;

public class Sinistro {
    private int id;
    private String data;
    private String endereco;

    public Sinistro(){
        this.setId();
    }

    public Sinistro(String data, String endereco) {
        this.setData(data);
        this.setEndereco(endereco);
        this.setId();
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

    // gera e um id aleatorio e *único*
    private void setId() {
        int num = 1;
        Random random = new Random();
        for (int i =0; i<15;i++){
            if (i%2==0){
                num *= random.nextInt(1,15);;
            }else{
                num += random.nextInt(1,50);
            }
        }
        this.id = num;
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
