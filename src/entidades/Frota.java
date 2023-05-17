package entidades;

import java.util.ArrayList;
import java.util.UUID;

import entidades.Seguro.Seguro;

public class Frota {
    private String code = UUID.randomUUID().toString();
    private ArrayList<Veiculo> listaVeiculos = new ArrayList<Veiculo>();
    private ArrayList<Seguro> listaSeguros = new ArrayList<Seguro>();

    public void destruirFrota(){
        for (Seguro seguro: listaSeguros){
            seguro.destruirSeguro();
        }
    }

    public boolean addVeiculo(Veiculo veiculo){
        if (!listaVeiculos.contains(veiculo)){
            listaVeiculos.add(veiculo);
            return true;
        }
        return false;
    }

    public boolean removeVeiculo(Veiculo veiculo){
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

    public ArrayList<Seguro> getListaSeguros() {
        return this.listaSeguros;
    }

    public void setListaSeguros(ArrayList<Seguro> listaSeguros) {
        this.listaSeguros = listaSeguros;
    }

}
