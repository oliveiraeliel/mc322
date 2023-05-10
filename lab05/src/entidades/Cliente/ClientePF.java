package entidades.Cliente;

import java.util.*;

import entidades.Veiculo;
import utils.ValidatorUtils;

public class ClientePF extends Cliente {
    private final String cpf;
    private String genero;
    private String educacao;
    private Date dataNasc;
    private ArrayList<Veiculo> listaVeiculos = new ArrayList<Veiculo>();

    public ClientePF(String nome, String cpf, String telefone, String email, String endereco, Date dataLicenca,
            String educacao, String genero, Date dataNascimento) {
        super(nome, telefone, endereco, email);
        cpf = ValidatorUtils.formatarCPF(cpf);
        this.cpf = cpf;
        setDataNasc(dataNascimento);
        setEducacao(educacao);
        setGenero(genero);
    }

    @Override
    public String getCadastro() {
        return getCpf();
    }

    public boolean cadastrarVeiculo(Veiculo veiculo) {
        if (!listaVeiculos.contains(veiculo)) {
            listaVeiculos.add(veiculo);
            return true;
        }
        return false;
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
        return "{" +
                " cpf='" + getCpf() + "'" +
                ", genero='" + getGenero() + "'" +
                ", educacao='" + getEducacao() + "'" +
                ", dataNasc='" + getDataNasc() + "'" +
                ", listaVeiculos='" + getListaVeiculos() + "'" +
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
        return Objects.equals(cpf, clientePF.getCpf());
    }
}
