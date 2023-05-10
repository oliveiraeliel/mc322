import java.util.HashMap;
import java.util.Map;

import entidades.*;
import entidades.Cliente.*;

public class AppMain {
	public static void main(String[] args) {
		// Map<String, Seguradora> seguradoras = new HashMap<>();
		// Seguradora seguradora = SeguradoraFactory.generateSeguradora("Eliel
		// Seguros");

		// seguradoras.put(seguradora.getNome(), seguradora);
		// MenuOperacoes.menu(seguradoras);
		Cliente cliente = new ClientePF("null", "23837495833", "null", "null", "null", null, null, null, null);
		Seguradora seguradora = new Seguradora(null, "321312", null, null, null);
		Veiculo veiculo = new Veiculo("null", "null", "null", 0);
		seguradora.cadastrarCliente(cliente);
		seguradora.gerarSeguro((ClientePF)cliente, veiculo, null);
		System.out.println(cliente.getSeguros());
	}
}
