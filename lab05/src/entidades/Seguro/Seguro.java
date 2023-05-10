package entidades.Seguro;

import java.util.ArrayList;
import java.util.Date;

import entidades.Seguradora;
import entidades.Sinistro;

public abstract class Seguro {
    private final int ID = ++count;
    private static int count = 0;
    private Date dataInicio;
    private Date dataFim;
    private Seguradora seguradora;
    private ArrayList<Sinistro> listaSinistros = new ArrayList<Sinistro>();
    private Double valorMensal;

    public Seguro(Date dataInicio, Date dataFim, Seguradora seguradora, Double valorMensal) {
        setDataInicio(dataInicio);
        setDataFim(dataFim);
        setSeguradora(seguradora);
        setValorMensal(valorMensal);
    }

    public abstract void desautorizarCondutor();

    public abstract Double calcularValor();

    public int getID() {
        return this.ID;
    }

    public Date getDataInicio() {
        return this.dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return this.dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Seguradora getSeguradora() {
        return this.seguradora;
    }

    public void setSeguradora(Seguradora seguradora) {
        this.seguradora = seguradora;
    }

    public ArrayList<Sinistro> getListaSinistros() {
        return this.listaSinistros;
    }

    public void setListaSinistros(ArrayList<Sinistro> listaSinistros) {
        this.listaSinistros = listaSinistros;
    }

    public Double getValorMensal() {
        return this.valorMensal;
    }

    public void setValorMensal(Double valorMensal) {
        this.valorMensal = valorMensal;
    }

    public Seguro dataInicio(Date dataInicio) {
        setDataInicio(dataInicio);
        return this;
    }

    public Seguro dataFim(Date dataFim) {
        setDataFim(dataFim);
        return this;
    }

    public Seguro seguradora(Seguradora seguradora) {
        setSeguradora(seguradora);
        return this;
    }

    public Seguro listaSinistros(ArrayList<Sinistro> listaSinistros) {
        setListaSinistros(listaSinistros);
        return this;
    }

    public Seguro valorMensal(Double valorMensal) {
        setValorMensal(valorMensal);
        return this;
    }

    @Override
    public String toString() {
        return " ID='" + getID() + "'" +
                ", dataInicio='" + getDataInicio() + "'" +
                ", dataFim='" + getDataFim() + "'" +
                ", seguradora='" + getSeguradora() + "'" +
                ", listaSinistros='" + getListaSinistros() + "'" +
                ", valorMensal='" + getValorMensal() + "'";
    }

}
