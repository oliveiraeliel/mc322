package entidades;

public class Veiculo {
    private String placa;
    private String marca;
    private String modelo;

    public Veiculo(String placa, String marca, String modelo) {
        this.setPlaca(placa);
        this.setMarca(marca);
        this.setModelo(modelo);
    }

    /**
     *  Retorna uma String com os valores atuais do objeto
     */
    public String toString(){
        return String.format("Placa: %s, Marca: %s, Modelo: %s", placa, marca, modelo);
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
}
