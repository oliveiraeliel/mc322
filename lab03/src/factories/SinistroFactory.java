package factories;

import entidades.Seguradora;
import entidades.Sinistro;
import entidades.Veiculo;
import entidades.Cliente.ClientePF;
import utils.DateUtils;

// classe utilizada para a criação de objetos do tipo cliente
public class SinistroFactory {
    public static Sinistro generateSinitro() {
        Seguradora seguradora = SeguradoraFactory.generateSeguradora();
        ClientePF cliente = ClienteFactory.generateClientePF();
        Veiculo veiculo = VeiculoFactory.generateVeiculo("ABC1234");
        cliente.addVeiculo(veiculo);
        return new Sinistro(DateUtils.localDate(), "Rua Saturnino, 322", cliente, veiculo, seguradora);
    }
}
