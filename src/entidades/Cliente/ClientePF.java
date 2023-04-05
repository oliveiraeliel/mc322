package entidades.Cliente;

import java.util.*;

import entidades.Veiculo;
import entidades.Cliente.validators.CPFvalidator;
import entidades.utils.DateUtils;

public class ClientePF extends Cliente {
    private final String CPF;
    private Date dataNascimento = new Date();

    public ClientePF(String nome, String endereco, Date dataLicenca, String educacao, String genero,
            String classeEconomica, String cpf, Date dataNascimento) {
        super(nome, endereco, dataLicenca, educacao, genero, classeEconomica);
        cpf = CPFvalidator.formatarCpf(cpf);
        this.CPF = cpf;
        this.dataNascimento = dataNascimento;
    }

    public ClientePF(String nome, String endereco, Date dataLicenca, String educacao, String genero,
            String classeEconomica, List<Veiculo> listaVeiculos, String cpf, Date dataNascimento) {
        super(nome, endereco, dataLicenca, educacao, genero, classeEconomica, listaVeiculos);
        cpf = CPFvalidator.formatarCpf(cpf);
        this.CPF = cpf;
        this.dataNascimento = dataNascimento;
    }

    @Override
    public String toString() {
        return "{" +
                " nome= '" + super.getNome() + "'" +
                ", CPF='" + getCPF() + "'" +
                ", endereco='" + super.getEndereco() + "'" +
                ", genero='" + super.getGenero() + "'" +
                ", classeEconomica='" + super.getClasseEconomica() + "'" +
                ", dataLicenca='" + DateUtils.formatDate(super.getDataLicenca(), "dd/MM/yyyy") + "'" +
                ", dataNascimento='" + DateUtils.formatDate(getDataLicenca(), "dd/MM/yyyy") + "'" +
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
        return Objects.equals(CPF, clientePF.CPF);
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

    // verifica se a String recebida é um cpf válido
    public static boolean validarCPF(String cpf) {
        return cpf.length() == 11 && !CPFvalidator.todosCharsIguais(cpf) && CPFvalidator.digitosValidos(cpf);
    }
}
