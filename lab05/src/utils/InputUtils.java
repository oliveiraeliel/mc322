package utils;

import java.util.Date;
import java.util.InputMismatchException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;


// classe utilitaria para leitura
public final class InputUtils {
    private static Scanner scan = new Scanner(System.in);

    public static int lerInt() {
        try {
            int num = scan.nextInt();
            scan.nextLine();
            return num;
        } catch (InputMismatchException e) {
            scan.nextLine();
            System.out.println("Insira apenas números inteiros!");
            return lerInt();
        }
    }

    public static int lerInt(String outString){
        try{
            System.out.print(outString);
            int num = scan.nextInt();
            scan.nextLine();
            return num;
        } catch (InputMismatchException e){
            scan.nextLine();
            System.out.println("Insira apenas números inteiros!");
            return lerInt(outString);
        }
    }

    public static String lerCPF() {
        String cpf = lerString("CPF: ");
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

    public static String lerCadastro(String outString) {
        System.out.print(outString);
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

    public static String lerNome(String outString) {
        String nome = lerString(outString);
        if (!Validacao.validaNome(nome)) {
            System.out.println("Insira apenas caractéres válidos!");
            return lerNome(outString);
        }
        return nome;
    }

    public static String lerString(String outString) {
        System.out.print(outString);
        return scan.nextLine();
    }

    public static Date lerData(String stringScan) {
        String d = lerString(stringScan);

        try {
            return new SimpleDateFormat("dd/MM/yyyy").parse(d);
        } catch (ParseException e) {
            System.out.println("Insira uma data no formato 'dd/mm/yyyy'!");
            return lerData(stringScan);
        }
    }

    public static String lerCNPJ() {
        String cnpj = lerString("CNPJ: ");
        if (!Validacao.validaCNPJ(cnpj)) {
            System.out.println("Insira um CNPJ válido!");
            return lerCNPJ();
        }
        return ValidatorUtils.formatarCNPJ(cnpj);
    }
}
