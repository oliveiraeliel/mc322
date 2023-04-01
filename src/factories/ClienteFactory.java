package factories;

import java.util.Calendar;
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
        if (!tipo.equals("PF") && !tipo.equals("pj")){
            throw new IllegalArgumentException("Tipo inválido. Insira 'PF' ou 'PJ'.");
        }
        String cString = tipo.equals("PF") ? "CPF" : "CNPJ";
        String dataString = tipo.equals("PF") ? "Nascimento" : "Fundação";
        Scanner scan = new Scanner(System.in);
        System.out.print("Nome: ");
        String nome = scan.nextLine();
        System.out.printf("%s: ", cString);
        String cpfcnpj = scan.nextLine();
        System.out.print("Endereço: ");
        String endereco = scan.nextLine();
        System.out.print("Dia da licenca: ");
        int diaLicenca = scan.nextInt();
        System.out.print("Mês da licenca: ");
        int mesLicenca = scan.nextInt();
        System.out.print("Ano da licenca: ");
        int anoLicenca = scan.nextInt();
        System.out.printf("Dia %s: ", dataString);
        int dia = scan.nextInt();
        System.out.printf("Mês %s: ", dataString);
        int mes = scan.nextInt();
        System.out.printf("Ano %s: ", dataString);
        int ano = scan.nextInt();
        System.out.print("Escolaridade: ");
        String educacao = scan.next();
        System.out.print("Gênero: ");
        String genero = scan.next();
        System.out.print("Classe econômica: ");
        String classeEconomica = scan.next();
        scan.close();
        Calendar dtLicenca = Calendar.getInstance();
        dtLicenca.set(diaLicenca, mesLicenca, anoLicenca);
        Calendar dt = Calendar.getInstance();
        dt.set(dia, mes, ano);

        if (tipo.equals("PF")) {
            return new ClientePF(nome, endereco, dtLicenca.getTime(), educacao, genero, classeEconomica, cpfcnpj,
                    dt.getTime());
        }else{
            return new ClientePJ(nome, endereco, dtLicenca.getTime(), educacao, genero, classeEconomica, cpfcnpj,
                    dt.getTime());
        }
    }

}
