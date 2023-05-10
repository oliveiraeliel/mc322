package entidades;

import java.util.Date;

import entidades.Cliente.Condutor;
import entidades.Seguro.Seguro;
import utils.DateUtils;

public class Sinistro {
    private final int ID = ++count;
    private static int count = 0;
    private Date data;
    private String endereco;
    private Condutor condutor;
    private Seguro seguro;

    public Sinistro(Date data, String endereco, Seguro seguro, Condutor condutor) {
        setData(data);
        setEndereco(endereco);
        setCondutor(condutor);
        setSeguro(seguro);
    }

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

    public Condutor getCondutor() {
        return this.condutor;
    }

    public void setCondutor(Condutor condutor) {
        this.condutor = condutor;
    }

    public Seguro getSeguro() {
        return this.seguro;
    }

    public void setSeguro(Seguro seguro) {
        this.seguro = seguro;
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

    @Override
    public String toString() {
        return "{" +
                " ID='" + getID() + "'" +
                ", data='" + DateUtils.formatDate(getData(), "dd/mm/yyyy") + "'" +
                ", endereco='" + getEndereco() + "'" +
                ", condutor='" + getCondutor() + "'" +
                ", seguro='" + getSeguro() + "'" +
                "}";
    }

}
