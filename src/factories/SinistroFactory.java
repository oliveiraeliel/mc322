package factories;

import entidades.Seguradora;
import entidades.Sinistro;
import entidades.Veiculo;
import entidades.Cliente.ClientePF;
import entidades.utils.DateUtils;

public class SinistroFactory {
    public static Sinistro generateSinitro() {
        Seguradora seguradora = SeguradoraFactory.generateSeguradora();
        ClientePF cliente = ClienteFactory.createClientePF();
        Veiculo veiculo = VeiculoFactory.generateVeiculo();
        cliente.addVeiculo(veiculo);
        return new Sinistro(DateUtils.localDate(), "Rua Saturnino, 322", cliente, veiculo, seguradora);
    }
}
