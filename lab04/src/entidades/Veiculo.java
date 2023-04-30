package entidades;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Veiculo {
    private String placa;
    private String marca;
    private String modelo;
    private int anoFabricacao;
    private List<Sinistro> listaSinistros = new ArrayList<>();

    public Veiculo(String placa, String marca, String modelo, int anoFabricacao) {
        setPlaca(placa);
        setMarca(marca);
        setModelo(modelo);
        setAnoFabricacao(anoFabricacao);
    }

    @Override
    public String toString() {
        return "{" +
                " placa='" + getPlaca() + "'" +
                ", marca='" + getMarca() + "'" +
                ", modelo='" + getModelo() + "'" +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Veiculo)) {
            return false;
        }
        Veiculo veiculo = (Veiculo) o;
        return Objects.equals(placa, veiculo.getPlaca());
    }

    public boolean addSinistro(Sinistro sinistro){
        if (!listaSinistros.contains(sinistro)){
            listaSinistros.add(sinistro);
            return true;
        }
        return false;
    }

    public void excluirSinistros(){
        for (Sinistro sinistro: listaSinistros){
            sinistro.getSeguradora().removerSinistro(sinistro.getID());
        }
    }

    public String getPlaca() {
        return this.placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa.trim();
    }

    public String getMarca() {
        return this.marca;
    }

    public void setMarca(String marca) {
        this.marca = marca.trim();
    }

    public String getModelo() {
        return this.modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo.trim();
    }


    public int getAnoFabricacao() {
        return this.anoFabricacao;
    }

    public void setAnoFabricacao(int anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

}
