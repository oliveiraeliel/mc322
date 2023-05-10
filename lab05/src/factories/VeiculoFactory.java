package factories;

import entidades.Veiculo;
import utils.InputUtils;

// classe utilizada para a criação de objetos do tipo cliente
public class VeiculoFactory {
    public static Veiculo generateVeiculo(String placa) {
        return new Veiculo(placa, "Ford", "K", 2004);
    }

    public static Veiculo lerVeiculo() {
        System.out.println("------------- Veículo --------------");
        String placa = InputUtils.lerString("Placa: ");
        String marca = InputUtils.lerString("Marca: ");
        String modelo = InputUtils.lerString("Modelo: ");
        int anoFabricacao = InputUtils.lerInt("Ano de fabricação: ");
        return new Veiculo(placa, marca, modelo, anoFabricacao);
    }
}
