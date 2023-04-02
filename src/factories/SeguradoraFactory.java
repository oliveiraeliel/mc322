package factories;

import java.util.Scanner;

import entidades.Seguradora;

public class SeguradoraFactory {
    public static Seguradora generateSeguradora() {
        return new Seguradora("Eliel Seguros", "12345679", "eliel123@dac.unicamp.br", "Rua Saturnino");
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
        return new Seguradora(nome, telefone, email, endereco);
    }
}
