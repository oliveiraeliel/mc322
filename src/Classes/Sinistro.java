package Classes;

import java.util.Random;

public class Sinistro {
    private int id;
    private String data;
    private String endereco;

    public Sinistro(){
        this.setId();
    }
    public Sinistro(String data, String endereco) {
        this.data = data;
        this.endereco = endereco;
        this.setId();
    }

    public int getId() {
        return this.id;
    }

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
        this.data = data;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
 


}
