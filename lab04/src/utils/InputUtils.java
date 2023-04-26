package utils;

import java.util.Scanner;

public class InputUtils {
    public static String lerCPF(Scanner scan) {
        System.out.print("CPF: ");
        String cpf = scan.nextLine();
        if (!Validacao.validaCPF(cpf)) {
            System.out.println("Insira um CPF válido!");
            return lerCPF(scan);
        }
        return ValidatorUtils.formatarCPF(cpf);
    }

    public static String lerCadastro(Scanner scan) {
        System.out.print("CPF/CNPJ: ");
        String cadastro = scan.nextLine();
        if (!Validacao.validaCPF(cadastro) && !Validacao.validaCNPJ(cadastro)) {
            System.out.println("Insira um CPF/CNPJ válido!");
            return lerCadastro(scan);
        }

        if (Validacao.validaCPF(cadastro)) {
            return ValidatorUtils.formatarCPF(cadastro);
        }
        return ValidatorUtils.formatarCNPJ(cadastro);
    }

    public static String lerNome(String outString, Scanner scan){
        System.out.print(outString);
        String nome = scan.nextLine();
        if (!Validacao.validaNome(nome)){
            System.out.println("Insira apenas caractéres válidos!");
            return lerNome(outString, scan);
        }
        return nome;
    }
}
