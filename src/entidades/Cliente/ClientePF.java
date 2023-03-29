package entidades.Cliente;

import java.util.Date;
import java.util.List;

import entidades.Veiculo;

public class ClientePF extends Cliente {
    private final String CPF;
    private Date dataNascimento;

    public ClientePF(String nome, String endereco, Date dataLicenca, String educacao, String genero, String classeEconomica, String cpf, Date dataNascimento) throws Exception {
        super(nome, endereco, dataLicenca, educacao, genero, classeEconomica);
        cpf = CPFvalidator.formatarCpf(cpf);
        if (!ClientePF.validarCPF(cpf)) {
            throw new Exception("CPF inválido");
        }
        this.CPF = cpf;
        this.dataNascimento = dataNascimento;
    }

    public ClientePF(String nome, String endereco, Date dataLicenca, String educacao, String genero, String classeEconomica, List<Veiculo> listaVeiculos, String cpf, Date dataNascimento) throws Exception{
        super(nome, endereco, dataLicenca, educacao, genero, classeEconomica, listaVeiculos);
        cpf = CPFvalidator.formatarCpf(cpf);
        if (!ClientePF.validarCPF(cpf)){
            throw new Exception("CPF inválido");
        }
        this.CPF = cpf;
        this.dataNascimento = dataNascimento;
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
    private static boolean validarCPF(String cpf) {
        return cpf.length() == 11 && !CPFvalidator.todosCharsIguais(cpf) && CPFvalidator.digitosValidos(cpf);
    }
}


// Classe com métodos de verificacão do cpf
class CPFvalidator {
    // verifica se todos os caracteres de uma string são iguais
    public static boolean todosCharsIguais(String string) {
        char c = string.charAt(0);
        for (int i = 1; i < string.length(); i++) {
            if (c != string.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    // verifica se os digitos verificadores do cpf são válidos
    public static boolean digitosValidos(String cpf) {
        return cpf.charAt(9) - '0' == primeiroDigitoCPF(cpf) && cpf.charAt(10) - '0' == segundoDigitoCPF(cpf);
    }

    // cálcula o primeiro digito verificador do cpf
    private static int primeiroDigitoCPF(String cpf) {
        int digito = 0, resto;
        for (int i = 0; i < 9; i++) {
            int d = cpf.charAt(i) - '0';
            digito += d * (10 - i);
        }
        resto = digito % 11;
        if (resto == 0 || resto == 1) {
            return 0;
        }
        return 11 - resto;
    }

    // cálcula o segundo digito verificador do cpf
    private static int segundoDigitoCPF(String cpf) {
        int digito = 0, resto;
        for (int i = 0; i < 10; i++) {
            int d = cpf.charAt(i) - '0';
            digito += d * (11 - i);
        }
        resto = digito % 11;
        if (resto == 0 || resto == 1) {
            return 0;
        }
        return 11 - resto;
    }

    // remove caracteres não númericos do cpf
    public static String formatarCpf(String cpf) {
        return cpf.replaceAll("\\.|-", "").trim();
    }
}
