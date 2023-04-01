import java.util.*;

import entidades.*;
import entidades.Cliente.*;
import factories.*;

public class Main {
	public static void main(String[] args) {
		ClientePF cliente = ClienteFactory.createClientePF("12345678911");
		Seguradora seguradora = SeguradoraFactory.generateSeguradora();
		seguradora.cadastrarCliente(cliente);
		seguradora.cadastrarCliente(ClienteFactory.createClientePF("12345678911"));
		List<Cliente> l = seguradora.listarClientes("PF");
		System.out.println(l.size());

		cliente.addVeiculo(VeiculoFactory.generateVeiculo());
		cliente.addVeiculo(VeiculoFactory.generateVeiculo());
		// ClientePF c = ClientePF.lerCliente();
		// ClientePF c = ClientePFFactory.generateClientePF();
		// ClientePJ j = ClientePJFactory.lerClientePJ();
		// System.out.println(j);
		// System.out.println(c);
		ClienteFactory.lerCliente("PF");
	}
}
