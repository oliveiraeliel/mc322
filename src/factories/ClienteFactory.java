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

    public static Cliente lerCliente(String tipo) {
        if (!tipo.equals("PF") && !tipo.equals("pj")) {
            throw new IllegalArgumentException("Tipo inválido. Insira 'PF' ou 'PJ'.");
        }
        String cString = tipo.equals("PF") ? "CPF" : "CNPJ";
        String dataString = tipo.equals("PF") ? "Data de Nascimento (dd/mm/yyyy): " : "Data de Fundação (dd/mm/yyyy): ";
        Scanner scan = new Scanner(System.in);
        System.out.print("Nome: ");
        String nome = scan.nextLine();
        System.out.printf("%s: ", cString);
        String cpfcnpj = scan.nextLine();
        System.out.print("Endereço: ");
        String endereco = scan.nextLine();
        Date dataLicenca = lerData("Data da Licenca (dd/mm/yyyy): ", scan);
        Date date = lerData(dataString, scan);
        System.out.print("Escolaridade: ");
        String educacao = scan.next();
        System.out.print("Gênero: ");
        String genero = scan.next();
        System.out.print("Classe econômica: ");
        String classeEconomica = scan.next();
        scan.close();

        if (tipo.equals("PF")) {
            return new ClientePF(nome, endereco, dataLicenca, educacao, genero, classeEconomica, cpfcnpj, date);
        } else {
            return new ClientePJ(nome, endereco, dataLicenca, educacao, genero, classeEconomica, cpfcnpj, date);
        }
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
