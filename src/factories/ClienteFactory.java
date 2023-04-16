package factories;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import entidades.Cliente.*;
import utils.ValidatorUtils;

public class ClienteFactory {
    public static ClientePF generateClientePF() {
        Calendar cal = Calendar.getInstance();
        cal.set(2004, 3, 24);
        return new ClientePF("Joãozinho", "Rua Pitágoras", cal.getTime(), "Ensino Superior", "Masculino",
                "Classe média", "705.828.810-05", cal.getTime());
    }

    public static ClientePF generateClientePF(String cpf) {
        Calendar cal = Calendar.getInstance();
        cal.set(2004, 3, 24);
        return new ClientePF("Joãozinho", "Rua Pitágoras", cal.getTime(), "Ensino Superior", "Masculino",
                "Classe média", cpf, cal.getTime());
    }

    public static ClientePJ generateClientePJ() {
        Calendar cal = Calendar.getInstance();
        cal.set(2004, 3, 24);
        return new ClientePJ("Joãozinho Caminhões", "Rua Pitágoras", "97.909.112/0001-00", cal.getTime());
    }

    public static ClientePJ generateClientePJ(String cnpj) {
        Calendar cal = Calendar.getInstance();
        cal.set(2004, 3, 24);
        return new ClientePJ("Joãozinho Caminhões", "Rua Pitágoras", cnpj, cal.getTime());
    }

    public static Cliente lerClientePJ(Scanner scan) {
        System.out.println("------------- Cliente --------------");
        System.out.print("Nome: ");
        String nome = scan.nextLine();
        String cnpj = lerCNPJ(scan);
        System.out.print("Endereço: ");
        String endereco = scan.nextLine();
        Date dataFundacao = lerData("Data de fundação", scan);
        return new ClientePJ(nome, endereco, cnpj, dataFundacao);
    }

    private static String lerCNPJ(Scanner scan){
        System.out.print("CNPJ: ");
        String cnpj = ValidatorUtils.formatarCNPJ(scan.nextLine());
        if (!ClientePJ.validarCNPJ(cnpj)) {
            System.out.println("Insira um CNPJ válido!");
            return lerCNPJ(scan);
        }
        if (ClientePJ.cnpjCadastrado(cnpj)) {
            System.out.println("CNPJ já cadastrado!");
            return lerCNPJ(scan);
        }
        return cnpj;
    }

    public static Cliente lerClientePF(Scanner scan) {
        System.out.println("------------- Cliente --------------");
        System.out.print("Nome: ");
        String nome = scan.nextLine();
        String cpf = lerCPF(scan);
        System.out.print("Endereço: ");
        String endereco = scan.nextLine();
        Date dataLicenca = lerData("Data da Licenca (dd/mm/yyyy): ", scan);
        Date dataNascimento = lerData("Data de Nascimento (dd/MM/yyyy): ", scan);
        System.out.print("Escolaridade: ");
        String educacao = scan.nextLine();
        System.out.print("Gênero: ");
        String genero = scan.nextLine();
        System.out.print("Classe econômica: ");
        String classeEconomica = scan.nextLine();
        return new ClientePF(nome, endereco, dataLicenca, educacao, genero, classeEconomica, cpf, dataNascimento);
    }


    private static String lerCPF(Scanner scan) {
        System.out.print("CPF: ");
        String cpf = ValidatorUtils.formatarCPF(scan.nextLine());
        if (!ClientePF.validarCPF(cpf)) {
            System.out.println("Insira um CPF válido!");
            return lerCPF(scan);
        }
        if (ClientePF.cpfCadastrado(cpf)){
            System.out.println("CPF já cadastrado!");
            return lerCPF(scan);
        }
        return cpf;
    }

    private static Date lerData(String stringScan, Scanner scan) {
        System.out.print(stringScan);
        String d = scan.nextLine();

        try {
            return new SimpleDateFormat("dd/MM/yyyy").parse(d);
        } catch (ParseException e) {
            System.out.println("Insira uma data no formato 'dd/mm/yyyy'!");
            return lerData(stringScan, scan);
        }
    }
}
