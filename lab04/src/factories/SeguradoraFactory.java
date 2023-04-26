package factories;

import java.util.Scanner;

import entidades.Seguradora;
import utils.Validacao;
import utils.ValidatorUtils;

// classe utilizada para a criação de objetos do tipo seguradora
public class SeguradoraFactory {
    public static Seguradora generateSeguradora() {
        return new Seguradora("Eliel Seguros", "123456789", "seguros@eliel.seguros.com",
                "R. Saturnino de Brito, 573 - Cidade Universitária, Campinas - SP, 13083-852", "18767304000144");
    }

    public static Seguradora lerSeguradora(Scanner scan) {
        System.out.println("----------- Seguradora -------------");
        System.out.print("Nome da Seguradora: ");
        String nome = scan.nextLine();
        System.out.print("Telefone: ");
        String telefone = scan.nextLine();
        System.out.print("Email: ");
        String email = scan.nextLine();
        System.out.print("Endereço: ");
        String endereco = scan.nextLine();
        String cnpj = lerCNPJ(scan);
        return new Seguradora(nome, telefone, email, endereco, cnpj);
    }

    private static String lerCNPJ(Scanner scan) {
        System.out.print("CNPJ: ");
        String cnpj = ValidatorUtils.formatarCNPJ(scan.nextLine());
        if (!Validacao.validaCNPJ(cnpj)) {
            System.out.println("Insira um CNPJ válido!");
            return lerCNPJ(scan);
        }
        return cnpj;
    }
}
