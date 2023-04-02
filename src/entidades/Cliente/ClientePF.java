package entidades.Cliente;

import java.text.SimpleDateFormat;
import java.util.*;

import entidades.Veiculo;
import entidades.Cliente.validators.CPFvalidator;

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
        return super.toString() + ", 'CPF': '" + CPF + "', 'Data de nascimento': '"
                + new SimpleDateFormat("dd/MM/yyyy").format(dataNascimento) +
                "'Veículos': '" + this.getListaVeiculos()
                + "'}";
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ClientePF)) {
            return false;
        }

        ClientePF c = (ClientePF) obj;
        return c.getCPF().equals(getCPF());
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
