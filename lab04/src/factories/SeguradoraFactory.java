package factories;

import entidades.Seguradora;
import utils.InputUtils;

// classe utilizada para a criação de objetos do tipo seguradora
public class SeguradoraFactory {
    public static Seguradora generateSeguradora() {
        return new Seguradora("Eliel Seguros", "123456789", "seguros@eliel.seguros.com",
                "R. Saturnino de Brito, 573 - Cidade Universitária, Campinas - SP, 13083-852");
    }

    public static Seguradora generateSeguradora(String nome) {
        return new Seguradora(nome, "123456789", "seguros@eliel.seguros.com",
                "R. Saturnino de Brito, 573 - Cidade Universitária, Campinas - SP, 13083-852");
    }

    public static Seguradora lerSeguradora() {
        System.out.println("----------- Seguradora -------------");
        String nome = InputUtils.lerNome("Nome: ");
        String telefone = InputUtils.lerString("Telefone: ");
        String email = InputUtils.lerString("Email: ");
        String endereco = InputUtils.lerString("Endereço: ");
        return new Seguradora(nome, telefone, email, endereco);
    }
}
