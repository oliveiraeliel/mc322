package entidades.Cliente;

import java.util.*;

import entidades.Veiculo;
import enums.CalcSeguro;
import utils.DateUtils;
import utils.ValidatorUtils;

public class ClientePF extends Cliente {
    private final String CPF;
    private Date dataNascimento;
    private Date dataLicenca;
    private String educacao;
    private String genero;
    private String classeEconomica;

    public ClientePF(String nome, String endereco, Date dataLicenca, String educacao, String genero,
            String classeEconomica, String cpf, Date dataNascimento) {
        super(nome, endereco);
        cpf = ValidatorUtils.formatarCPF(cpf);
        this.CPF = cpf;
        setDataLicenca(dataLicenca);
        setDataNascimento(dataNascimento);
        setEducacao(educacao);
        setGenero(genero);
        setClasseEconomica(classeEconomica);
    }

    public ClientePF(String nome, String endereco, Date dataLicenca, String educacao, String genero,
            String classeEconomica, List<Veiculo> listaVeiculos, String cpf, Date dataNascimento) {
        super(nome, endereco, listaVeiculos);
        cpf = ValidatorUtils.formatarCPF(cpf);
        this.CPF = cpf;
        setDataLicenca(dataLicenca);
        setDataNascimento(dataNascimento);
        setEducacao(educacao);
        setGenero(genero);
        setClasseEconomica(classeEconomica);
    }

    @Override
    public String getCadastro() {
        return getCPF();
    }

    @Override
    public Double calculaScore() {
        return CalcSeguro.VALOR_BASE.getValor() * CalcSeguro.fatorIdade(getIdade()) * quantidadeCarros();
    }

    @Override
    public String toString() {
        return "{" +
                " nome= '" + super.getNome() + "'" +
                ", CPF='" + getCPF() + "'" +
                ", endereco='" + getEndereco() + "'" +
                ", genero='" + getGenero() + "'" +
                ", classeEconomica='" + getClasseEconomica() + "'" +
                ", dataLicenca='" + DateUtils.formatDate(getDataLicenca(), "dd/MM/yyyy") + "'" +
                ", dataNascimento='" + DateUtils.formatDate(getDataLicenca(), "dd/MM/yyyy") + "'" +
                ", listaVeiculos='" + super.getListaVeiculos() + "'" +
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
        return Objects.equals(CPF, clientePF.getCPF());
    }

    public String getCPF() {
        return this.CPF;
    }

    public Date getDataNascimento() {
        return this.dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Date getDataLicenca() {
        return this.dataLicenca;
    }

    public void setDataLicenca(Date dataLicenca) {
        this.dataLicenca = dataLicenca;
    }

    public String getEducacao() {
        return this.educacao;
    }

    public void setEducacao(String educacao) {
        this.educacao = educacao.trim();
    }

    public String getGenero() {
        return this.genero;
    }

    public void setGenero(String genero) {
        this.genero = genero.trim();
    }

    public String getClasseEconomica() {
        return this.classeEconomica;
    }

    public void setClasseEconomica(String classeEconomica) {
        this.classeEconomica = classeEconomica.trim();
    }

    public int getIdade() {
        return 18;
    }
}
