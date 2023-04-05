package factories;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import entidades.Cliente.Cliente;
import entidades.Cliente.ClientePF;
import entidades.Cliente.ClientePJ;

public class ClienteFactory {
    public static ClientePF createClientePF() {
        Calendar cal = Calendar.getInstance();
        cal.set(2004, 3, 24);
        return new ClientePF("Joãozinho", "Rua Pitágoras", cal.getTime(), "Ensino Superior", "Masculino",
                "Classe média", "705.828.810-05", cal.getTime());
    }

    public static ClientePF createClientePF(String cpf) {
        Calendar cal = Calendar.getInstance();
        cal.set(2004, 3, 24);
        return new ClientePF("Joãozinho", "Rua Pitágoras", cal.getTime(), "Ensino Superior", "Masculino",
                "Classe média", cpf, cal.getTime());
    }

    public static ClientePF createClientePJ() {
        Calendar cal = Calendar.getInstance();
        cal.set(2004, 3, 24);
        return new ClientePF("Joãozinho Caminhões", "Rua Pitágoras", cal.getTime(), "Ensino Superior", "Masculino",
                "Classe média", "97.909.112/0001-00", cal.getTime());
    }

    public static ClientePF createClientePJ(String cnpj) {
        Calendar cal = Calendar.getInstance();
        cal.set(2004, 3, 24);
        return new ClientePF("Joãozinho Caminhões", "Rua Pitágoras", cal.getTime(), "Ensino Superior", "Masculino",
                "Classe média", cnpj, cal.getTime());
    }

    public static Cliente lerClientePJ(Scanner scan) {
        System.out.println("------------- Cliente --------------");
        System.out.print("Nome: ");
        String nome = scan.nextLine();
        System.out.printf("CNPJ: ");
        String cnpj = scan.nextLine();
        System.out.print("Endereço: ");
        String endereco = scan.nextLine();
        Date dataLicenca = lerData("Data da Licenca (dd/mm/yyyy): ", scan);
        Date dataFundacao = lerData("Data de fundação", scan);
        return new ClientePJ(nome, endereco, dataLicenca, cnpj, dataFundacao);
    }

    public static Cliente lerClientePF(Scanner scan) {
        System.out.println("------------- Cliente --------------");
        System.out.print("Nome: ");
        String nome = scan.nextLine();
        System.out.print("CPF: ");
        String cpf = scan.nextLine();
        System.out.print("Endereço: ");
        String endereco = scan.nextLine();
        Date dataLicenca = lerData("Data da Licenca (dd/mm/yyyy): ", scan);
        Date dataNascimento = lerData("Data de Nascimento (dd/MM/yyyy)", scan);
        System.out.print("Escolaridade: ");
        String educacao = scan.nextLine();
        System.out.print("Gênero: ");
        String genero = scan.nextLine();
        System.out.print("Classe econômica: ");
        String classeEconomica = scan.nextLine();
        return new ClientePF(nome, endereco, dataLicenca, educacao, genero, classeEconomica, cpf, dataNascimento);
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
