package entidades.Seguro;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import entidades.Cliente.Cliente;
import entidades.Condutor.Condutor;
import entidades.Seguradora.Seguradora;
import entidades.Sinistro.Sinistro;
import execeptions.CondutorNaoAssociadoException;
import utils.DateUtils;

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

    public abstract Cliente getCliente();

    public abstract Double calculaValor();

    public void apagarSeguro() {
        Iterator<Sinistro> iterSinistro = getListaSinistros().iterator();
        while (iterSinistro.hasNext()) {
            Sinistro sinistro = iterSinistro.next();
            sinistro.getCondutor().removerSinistro(sinistro);
            iterSinistro.remove();
        }
        listaCondutores.clear();
        seguradora.somarReceita(-getValorMensal());
        setSeguradora(null);
    }

    protected int getQuantidadeSinistrosCondutores() {
        int n = 0;
        for (Condutor condutor : listaCondutores) {
            for (Sinistro sinistro : condutor.getListaSinistros()) {
                if (sinistro.getSeguro().getSeguradora().equals(getSeguradora())) {
                    n++;
                }
            }
        }
        return n;
    }

    protected int getQuantidadeSinistrosCliente(Cliente cliente) {
        return getSeguradora().getSinistrosPorCliente(cliente).size();
    }

    public boolean desautorizarCondutor(Condutor condutor) {
        if (listaCondutores.remove(condutor)) {
            calculaValor();
            return true;
        }
        return false;
    }

    public boolean autorizarCondutor(Condutor condutor) {
        if (!listaCondutores.contains(condutor)) {
            listaCondutores.add(condutor);
            calculaValor();
            return true;
        }
        return false;
    }

    public Sinistro gerarSinistro(Date data, String endereco, Condutor condutor) throws CondutorNaoAssociadoException {
        if (listaCondutores.contains(condutor)) {
            Sinistro sinistro = new Sinistro(data, endereco, this, condutor);
            condutor.adicionarSinistro(sinistro);
            listaSinistros.add(sinistro);
            calculaValor();
            return sinistro;
        }
        throw new CondutorNaoAssociadoException(
                "O condutor de CPF " + condutor.getCpf() + " não está associado ao seguro.");
    }

    public boolean removeSinistro(int id) {
        Sinistro sinistro = buscarSinistro(id);
        if (listaSinistros.remove(sinistro)) {
            sinistro.apagarSinistro();
            calculaValor();
            return true;
        }
        return false;
    }

    public Sinistro buscarSinistro(int id) {
        for (Sinistro sinistro : listaSinistros) {
            if (sinistro.getID() == id) {
                return sinistro;
            }
        }
        return null;
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
                ", dataInicio='" + DateUtils.formatDate(getDataInicio(), "dd/MM/yyyy") + "'" +
                ", dataFim='" + DateUtils.formatDate(getDataFim(), "dd/MM/yyyy") + "'" +
                ", seguradora='" + getSeguradora().getNome() + "'" +
                ", cnpjSeguradora='" + getSeguradora().getCnpj() + "'" +
                ", listaSinistros='" + getListaSinistros() + "'" +
                ", valorMensal='" + getValorMensal() + "'";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Seguro)) {
            return false;
        }
        Seguro seguro = (Seguro) o;
        return ID == seguro.ID;
    }
}
