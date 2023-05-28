package entidades.Cliente;

import java.util.*;

import entidades.Veiculo.Veiculo;
import execeptions.VeiculoNaoEncontradoException;
import utils.*;

public class ClientePF extends Cliente {
    private final String cpf;
    private String genero;
    private String educacao;
    private Date dataNasc;
    private ArrayList<Veiculo> listaVeiculos = new ArrayList<Veiculo>();

    public ClientePF(String nome, String cpf, String telefone, String email, String endereco,
            String educacao, String genero, Date dataNascimento) {
        super(nome, telefone, endereco, email);
        cpf = ValidatorUtils.formatarCPF(cpf);
        this.cpf = cpf;
        setDataNasc(dataNascimento);
        setEducacao(educacao);
        setGenero(genero);
    }

    @Override
    public ArrayList<Veiculo> listarVeiculos() {
        return getListaVeiculos();
    }

    @Override
    public int getQuantidadeVeiculos() {
        return listaVeiculos.size();
    }

    @Override
    public String getCadastro() {
        return getCpf();
    }

    public boolean removeVeiculo(Veiculo veiculo) {
        if (listaVeiculos.remove(veiculo)) {
            veiculo.desassociarSeguro();
            return true;
        }
        return false;
    }

    public boolean removeVeiculo(String placa) {
        try {
            Veiculo veiculo = buscarVeiculo(placa);
            return removeVeiculo(veiculo);
        } catch (VeiculoNaoEncontradoException e) {
            return false;
        }
    }

    public Veiculo buscarVeiculo(String placa) throws VeiculoNaoEncontradoException {
        Veiculo veiculo = listaVeiculos.stream().filter(v -> v.getPlaca().equals(placa)).findFirst().orElse(null);
        if (veiculo != null) {
            return veiculo;
        }
        throw new VeiculoNaoEncontradoException("A placa " + placa + " não corresponde à nenhum veículo na lista.");
    }

    public boolean cadastrarVeiculo(Veiculo veiculo) {
        if (!listaVeiculos.contains(veiculo)) {
            listaVeiculos.add(veiculo);
            return true;
        }
        return false;
    }

    public int getIdade() {
        return DateUtils.calcularIdade(getDataNasc());
    }

    public String getCpf() {
        return this.cpf;
    }

    public String getGenero() {
        return this.genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEducacao() {
        return this.educacao;
    }

    public void setEducacao(String educacao) {
        this.educacao = educacao;
    }

    public Date getDataNasc() {
        return this.dataNasc;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    public ArrayList<Veiculo> getListaVeiculos() {
        return this.listaVeiculos;
    }

    @Override
    public String toString() {
        return "{" + super.toString() +
                ", cpf='" + getCpf() + "'" +
                ", genero='" + getGenero() + "'" +
                ", educacao='" + getEducacao() + "'" +
                ", dataNasc='" + DateUtils.formatDate(getDataNasc(), "dd/MM/yyyy") + "'" +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ClientePF)) {
            return false;
        }
        ClientePF clientePF = (ClientePF) o;
        return clientePF.getCpf().equals(cpf);
    }
}