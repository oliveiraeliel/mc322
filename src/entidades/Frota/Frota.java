package entidades.Frota;

import java.util.ArrayList;
import java.util.UUID;

import entidades.Seguro.Seguro;
import entidades.Veiculo.Veiculo;

public class Frota {
    private final String code = UUID.randomUUID().toString();
    private ArrayList<Veiculo> listaVeiculos = new ArrayList<Veiculo>();
    private Seguro seguro;

    public void desassociarSeguro() {
        try {
            seguro.getSeguradora().cancelarSeguro(seguro);
        } catch (NullPointerException e) {
        }
    }

    public boolean addVeiculo(Veiculo veiculo) {
        if (!listaVeiculos.contains(veiculo)) {
            atualizarSeguro();
            listaVeiculos.add(veiculo);
            return true;
        }
        return false;
    }

    public void atualizarSeguro() {
        try {
            seguro.calculaValor();
        } catch (Exception e) {
        }
    }

    public boolean removeVeiculo(Veiculo veiculo) {
        if (listaVeiculos.remove(veiculo)) {
            atualizarSeguro();
            return true;
        }
        return false;
    }

    public String getCode() {
        return this.code;
    }

    public ArrayList<Veiculo> getListaVeiculos() {
        return this.listaVeiculos;
    }

    public void setListaVeiculos(ArrayList<Veiculo> listaVeiculos) {
        this.listaVeiculos = listaVeiculos;
        atualizarSeguro();
    }

    public Seguro getSeguro() {
        return this.seguro;
    }

    public void setSeguro(Seguro seguro) {
        this.seguro = seguro;
    }

    @Override
    public String toString() {
        return "{" +
                " code='" + getCode() + "'" +
                ", listaVeiculos='" + getListaVeiculos() + "'" +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Frota)) {
            return false;
        }
        Frota frota = (Frota) o;
        return frota.getCode().equals(code);
    }
}
