import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import entidades.*;
import entidades.Cliente.*;
import factories.ClienteFactory;
import factories.SeguradoraFactory;

public class AppMain {
	public static void main(String[] args) {
		// Map<String, Seguradora> seguradoras = new HashMap<>();
		// Seguradora seguradora = SeguradoraFactory.generateSeguradora("Eliel
		// Seguros");

		// seguradoras.put(seguradora.getNome(), seguradora);
		// MenuOperacoes.menu(seguradoras);
		Cliente cliente = ClienteFactory.generateClientePF("1234");
		Seguradora seguradora = SeguradoraFactory.generateSeguradora();
		Veiculo veiculo = new Veiculo("null", "null", "null", 2004);
		seguradora.cadastrarCliente(cliente);
		seguradora.gerarSeguro((ClientePF)cliente, veiculo, new Date());
		System.out.println(cliente.getListaSeguros().get(0).getValorMensal());
		System.out.println(seguradora.calcularReceita());
	}
}
