package entidades;

import java.util.Date;
import java.util.Objects;

import entidades.Cliente.Cliente;

public class Sinistro {
    private final int ID = ++count; // id unico e sequencial. baseado no número de sinistros já gerados pelo sistema
    private Date data;
    private String endereco;
    private static int count = 0; // contagem do número de sinistros gerados
    private Cliente cliente;
    private Veiculo veiculo;
    private Seguradora seguradora;

    public Sinistro(Date data, String endereco, Cliente cliente, Veiculo veiculo, Seguradora seguradora) {
        setData(data);
        setEndereco(endereco);
        setCliente(cliente);
        setVeiculo(veiculo);
        setSeguradora(seguradora);
    }

    @Override
    public String toString() {
        return "{" +
                " ID='" + getID() + "'" +
                ", data='" + getData() + "'" +
                ", endereco='" + getEndereco() + "'" +
                ", cliente='" + getCliente() + "'" +
                ", veiculo='" + getVeiculo() + "'" +
                ", seguradora='" + getSeguradora() + "'" +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Sinistro)) {
            return false;
        }
        Sinistro sinistro = (Sinistro) o;
        return ID == sinistro.ID && Objects.equals(data, sinistro.data) && Objects.equals(endereco, sinistro.endereco)
                && Objects.equals(cliente, sinistro.cliente) && Objects.equals(veiculo, sinistro.veiculo)
                && Objects.equals(seguradora, sinistro.seguradora);
    }

    // getters e setters
    public int getID() {
        return this.ID;
    }

    public Date getData() {
        return this.data;
    }

    public void setData(Date data) {
        this.data = data;
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
