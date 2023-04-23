package factories;

import java.util.Scanner;

import entidades.Veiculo;

// classe utilizada para a criação de objetos do tipo cliente
public class VeiculoFactory {
    public static Veiculo generateVeiculo(String placa) {
        return new Veiculo(placa, "Ford", "K", 2004);
    }

    public static Veiculo lerVeiculo(Scanner scan) {
        System.out.println("------------- Veículo --------------");
        System.out.print("Placa do veículo: ");
        String placa = scan.nextLine();
        System.out.print("Marca: ");
        String marca = scan.nextLine();
        System.out.print("Modelo: ");
        String modelo = scan.nextLine();

        return new Veiculo(placa, marca, modelo, 2004);
    }
}
