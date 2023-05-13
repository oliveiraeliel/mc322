package entidades.Seguro;

import java.util.ArrayList;
import java.util.Date;

import entidades.Seguradora;
import entidades.Sinistro;
import entidades.Cliente.Condutor;

public abstract class Seguro {
    private final int ID = ++count;
    private static int count = 0;
    private Date dataInicio;
    private Date dataFim;
    private Seguradora seguradora;
    private ArrayList<Sinistro> listaSinistros = new ArrayList<Sinistro>();
    private ArrayList<Condutor> listaCondutores = new ArrayList<Condutor>();
    private Double valorMensal = 0.0;

    public Seguro(Date dataInicio, Date dataFim, Seguradora seguradora) {
        setDataInicio(dataInicio);
        setDataFim(dataFim);
        setSeguradora(seguradora);
    }

    public abstract Double calculaScore();

    public Double atualizarValorMensal() {
        Double valorAntigo = getValorMensal();
        setValorMensal(calculaScore());
        seguradora.adicionarReceita(getValorMensal() - valorAntigo);
        return valorMensal;
    }

    public int getQuantidadeSinistrosCondutores() {
        int n = 0;
        for (Condutor condutor : listaCondutores) {
            n += condutor.getQuantidadeSinistros();
        }
        return n;
    }

    public boolean desautorizarCondutor(Condutor condutor) {
        if (listaCondutores.contains(condutor)) {
            listaCondutores.remove(condutor);
            return true;
        }
        return false;
    }

    public boolean autorizarCondutor(Condutor condutor) {
        if (!listaCondutores.contains(condutor)) {
            listaCondutores.add(condutor);
            return true;
        }
        return false;
    }

    public Sinistro gerarSinistro(Date data, String endereco, Condutor condutor) {
        Sinistro sinistro = new Sinistro(data, endereco, this, condutor);
        condutor.adicionarSinistro(sinistro);
        atualizarValorMensal();
        return sinistro;
    }

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
