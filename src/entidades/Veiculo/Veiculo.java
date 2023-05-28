package entidades.Veiculo;

import entidades.Seguro.Seguro;

public class Veiculo {
    private String placa;
    private String marca;
    private String modelo;
    private int anoFabricacao;
    private Seguro seguro;

    public Veiculo(String placa, String marca, String modelo, int anoFabricacao) {
        setPlaca(placa);
        setMarca(marca);
        setModelo(modelo);
        setAnoFabricacao(anoFabricacao);
    }

    public void desassociarSeguro() {
        try {
            seguro.getSeguradora().cancelarSeguro(seguro);
        } catch (NullPointerException e) {
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

    public Seguro getSeguro() {
        return this.seguro;
    }

    public void setSeguro(Seguro seguro) {
        this.seguro = seguro;
    }

    @Override
    public String toString() {
        return "{" +
                " placa='" + getPlaca() + "'" +
                ", marca='" + getMarca() + "'" +
                ", modelo='" + getModelo() + "'" +
                ", anoFabricacao='" + getAnoFabricacao() + "'" +
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
        return veiculo.getPlaca().equals(placa);
    }
}
