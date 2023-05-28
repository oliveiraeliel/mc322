package entidades.Seguro;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import entidades.Seguradora;
import entidades.Sinistro;
import entidades.Cliente.Cliente;
import entidades.Cliente.Condutor;
import execeptions.CondutorNaoAssociadoException;

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

    public void destruirSeguro() {
        Iterator<Sinistro> iterSinistro = getListaSinistros().iterator();
        while (iterSinistro.hasNext()) {
            Sinistro sinistro = iterSinistro.next();
            sinistro.getCondutor().removerSinistro(sinistro);
            iterSinistro.remove();
        }
        listaCondutores.clear();
        atualizarValorMensal();
        setSeguradora(null);
    }

    public Double atualizarValorMensal() {
        Double valorAntigo = getValorMensal();
        setValorMensal(calculaValor());
        seguradora.adicionarReceita(getValorMensal() - valorAntigo);
        return valorMensal;
    }

    protected int getQuantidadeSinistrosCondutores() {
        int n = 0;
        for (Condutor condutor : listaCondutores) {
            n += condutor.getQuantidadeSinistros(getSeguradora());
        }
        return n;
    }

    protected int getQuantidadeSinistrosCliente(Cliente cliente) {
        return getSeguradora().getSinistrosPorCliente(cliente).size();
    }

    public boolean desautorizarCondutor(Condutor condutor) {
        return listaCondutores.remove(condutor);
    }

    public boolean autorizarCondutor(Condutor condutor) {
        if (!listaCondutores.contains(condutor)) {
            listaCondutores.add(condutor);
            return true;
        }
        return false;
    }

    public Sinistro gerarSinistro(Date data, String endereco, Condutor condutor) throws CondutorNaoAssociadoException {
        if (listaCondutores.contains(condutor)) {
            Sinistro sinistro = new Sinistro(data, endereco, this, condutor);
            condutor.adicionarSinistro(sinistro);
            atualizarValorMensal();
            return sinistro;
        }
        throw new CondutorNaoAssociadoException("O condutor de CPF " + condutor.getCpf() + " não está associado ao seguro.");
    }

    public boolean removeSinistro(int id){
        Sinistro sinistro = buscarSinistro(id);
        if (listaSinistros.remove(sinistro)){
            sinistro.apagarSinistro();
            atualizarValorMensal();
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
                ", dataInicio='" + getDataInicio() + "'" +
                ", dataFim='" + getDataFim() + "'" +
                ", seguradora='" + getSeguradora() + "'" +
                ", listaSinistros='" + getListaSinistros() + "'" +
                ", valorMensal='" + getValorMensal() + "'";
    }

}
