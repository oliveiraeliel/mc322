import java.util.*;

import entidades.*;
import entidades.Cliente.*;
import factories.ClienteFactory;
import factories.SeguradoraFactory;

public class Main {
	public static void main(String[] args) {
		ClientePF cliente = ClienteFactory.createClientePF("12345678911");
		Seguradora seguradora = SeguradoraFactory.generateSeguradora();
		seguradora.cadastrarCliente(cliente);
		seguradora.cadastrarCliente(ClienteFactory.createClientePF("12345678911"));
		List<Cliente> l = seguradora.listarClientes("PF");
		System.out.println(l.size());
	}
}
