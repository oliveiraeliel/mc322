package entidades;


public class Sinistro {
    private int id; // id unico e sequencial. baseado no número de sinistros já gerados pelo sistema
    private String data;
    private String endereco;
    private static int count = 0; // contagem do número de sinistros gerados 

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

    private void setId() {
        this.id = ++count;
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
