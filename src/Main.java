import java.util.Scanner;

import entidades.*;
import entidades.Cliente.*;
import factories.*;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in); // evitando conflito de leitura utilizando apenas uma instancia do objeto scanner
		Seguradora seguradora = SeguradoraFactory.lerSeguradora(scan);
		Cliente cliente = ClienteFactory.lerCliente("PF", scan);
		Veiculo veiculo = VeiculoFactory.lerVeiculo(scan);

		cliente.addVeiculo(veiculo);
		seguradora.cadastrarCliente(cliente);

		System.out.println(seguradora);
		// Seguradora s = SeguradoraFactory.generateSeguradora();
		// Cliente c = ClienteFactory.createClientePF();
		// s.cadastrarCliente(c);
		// c.addVeiculo(VeiculoFactory.generateVeiculo());
		// c.addVeiculo(VeiculoFactory.generateVeiculo());
		// c.addVeiculo(VeiculoFactory.generateVeiculo());
		// System.out.println(s);
		scan.close();

	}
}
