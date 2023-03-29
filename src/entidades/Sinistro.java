package entidades;

import entidades.Cliente.Cliente;

public class Sinistro {
    private final int ID = ++count; // id unico e sequencial. baseado no número de sinistros já gerados pelo sistema
    private String data;
    private String endereco;
    private static int count = 0; // contagem do número de sinistros gerados 
    private Cliente cliente;
    private Veiculo veiculo;
    private Seguradora seguradora;


    public Sinistro(String data, String endereco, Cliente cliente, Veiculo veiculo, Seguradora seguradora) {
        setData(data);
        setEndereco(endereco);
        setCliente(cliente);
        setVeiculo(veiculo);
        setSeguradora(seguradora);
    }

    /**
     *  Retorna uma String com os valores atuais do objeto
     */
    public String toString(){
        return String.format("ID: %d, Data: %s, Endereço: %s", ID, data, endereco);
    }

    // getters e setters

    public int getID() {
        return this.ID;
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

    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


    public Veiculo getVeiculo() {
        return this.veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Seguradora getSeguradora() {
        return this.seguradora;
    }

    public void setSeguradora(Seguradora seguradora) {
        this.seguradora = seguradora;
    }

}
