package entidades;

import java.util.ArrayList;
import java.util.UUID;

import entidades.Seguro.Seguro;

public class Frota {
    private String code = UUID.randomUUID().toString();
    private ArrayList<Veiculo> listaVeiculos = new ArrayList<Veiculo>();
    private Seguro seguro;

    public void destruirFrota() {
        seguro.getSeguradora().cancelarSeguro(seguro);
    }

    public boolean addVeiculo(Veiculo veiculo) {
        if (!listaVeiculos.contains(veiculo)) {
            listaVeiculos.add(veiculo);
            return true;
        }
        return false;
    }

    public boolean removeVeiculo(Veiculo veiculo) {
        return listaVeiculos.remove(veiculo);
    }

    @Override
    public String toString() {
        return "{" +
                " code='" + getCode() + "'" +
                ", listaVeiculos='" + getListaVeiculos() + "'" +
                "}";
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ArrayList<Veiculo> getListaVeiculos() {
        return this.listaVeiculos;
    }

    public void setListaVeiculos(ArrayList<Veiculo> listaVeiculos) {
        this.listaVeiculos = listaVeiculos;
    }

    public Seguro getSeguro() {
        return this.seguro;
    }

    public void setSeguro(Seguro seguro) {
        this.seguro = seguro;
    }
}
