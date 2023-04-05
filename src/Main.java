import java.util.Scanner;

import entidades.*;
import entidades.Cliente.*;
import factories.*;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in); // evitando conflito de leitura utilizando apenas uma instancia do objeto scanner
		Seguradora seguradora = SeguradoraFactory.lerSeguradora(scan);
		Cliente cliente = ClienteFactory.lerClientePF(scan);
		Veiculo veiculo = VeiculoFactory.lerVeiculo(scan);

		cliente.addVeiculo(veiculo);
		seguradora.cadastrarCliente(cliente);
		seguradora.gerarSinistro(cliente, veiculo, "av. 13 de maio");

		System.out.println(seguradora);
		scan.close();
		// Seguradora seguradora = SeguradoraFactory.generateSeguradora();
		// Cliente cliente = ClienteFactory.createClientePF();
		// Veiculo veiculo = VeiculoFactory.generateVeiculo();
		// cliente.addVeiculo(veiculo);
		// seguradora.cadastrarCliente(cliente);
		// seguradora.gerarSinistro(cliente, veiculo, "adfas");
		// System.out.println(seguradora);
		}
}
