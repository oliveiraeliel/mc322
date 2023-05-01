package utils;

import java.util.Scanner;

public class InputUtils {
    private static Scanner scan = new Scanner(System.in);

    public static String lerCPF() {
        System.out.print("CPF: ");
        String cpf = scan.nextLine();
        if (!Validacao.validaCPF(cpf)) {
            System.out.println("Insira um CPF válido!");
            return lerCPF();
        }
        return ValidatorUtils.formatarCPF(cpf);
    }

    public static String lerCadastro() {
        System.out.print("CPF/CNPJ: ");
        String cadastro = scan.nextLine();
        if (!Validacao.validaCPF(cadastro) && !Validacao.validaCNPJ(cadastro)) {
            System.out.println("Insira um CPF/CNPJ válido!");
            return lerCadastro();
        }

        if (Validacao.validaCPF(cadastro)) {
            return ValidatorUtils.formatarCPF(cadastro);
        }
        return ValidatorUtils.formatarCNPJ(cadastro);
    }

    public static String lerNome(String outString){
        String nome = lerString(outString);
        if (!Validacao.validaNome(nome)){
            System.out.println("Insira apenas caractéres válidos!");
            return lerNome(outString);
        }
        return nome;
    }

    public static String lerString(String outString){
        System.out.print(outString);
        return scan.nextLine();
    }
}
