package entidades;

import java.util.Date;

import entidades.Cliente.Cliente;
import utils.DateUtils;

public class Sinistro {
    private final int ID;
    private Date data;
    private String endereco;
    private static int count = 0;
    private Cliente cliente;
    private Veiculo veiculo;
    private Seguradora seguradora;

    public Sinistro(Date data, String endereco, Cliente cliente, Veiculo veiculo, Seguradora seguradora) {
        setData(data);
        setEndereco(endereco);
        setCliente(cliente);
        setVeiculo(veiculo);
        setSeguradora(seguradora);
        ID = ++count;
    }

    @Override
    public String toString() {
        return "{" +
                " ID='" + getID() + "'" +
                ", data='" + DateUtils.formatDate(getData(), "dd/MM/yyyy") + "'" +
                ", endereco='" + getEndereco() + "'" +
                ", cliente='" + getCliente() + "'" +
                ", veiculo='" + getVeiculo() + "'" +
                ", seguradora='" + getSeguradora().getNome() + "'" +
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
        return ID == sinistro.getID();
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
