import java.util.List;
import java.util.Scanner;

import entidades.*;
import entidades.Cliente.*;
import factories.*;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Seguradora seguradora = SeguradoraFactory.generateSeguradora();
		while (menu(seguradora, scan));
		System.out.println(seguradora);
		scan.close();
	}

	private static boolean menu(Seguradora seguradora, Scanner scan) {
		System.out.println("\n0- Sair");
		System.out.println("1- Criar um cliente PF");
		System.out.println("2- Criar um cliente PJ");
		System.out.println("3- Adicionar veiculo");
		System.out.println("4- Remover veiculo");
		System.out.println("5- Ver cliente");
		System.out.println("6- Ver lista de clientes PF");
		System.out.println("7- Ver lista de clientes PJ");
		System.out.println("8- Gerar sinistro");
		System.out.println("9- Visualizar sinistros");
		System.out.println("10- Lista sinistros");
		String digito = scan.nextLine();

		switch (digito) {
			case "0":
				return false;
			case "1":
				seguradora.cadastrarCliente(ClienteFactory.lerClientePF(scan));
				break;
			case "2":
				seguradora.cadastrarCliente(ClienteFactory.lerClientePJ(scan));
				break;
			case "3":
				Cliente cliente = encontrarCliente(seguradora, scan);
				addVeiculo(seguradora, scan, cliente);
				break;
			case "4":
				cliente = encontrarCliente(seguradora, scan);
				if (cliente == null) {
					System.out.println("Cliente não encontrado.");
					break;
				}
				String placa = scan.nextLine();
				if (cliente.removeVeiculo(placa)) {
					System.out.println("Veículo não encontrado.");
					break;
				}
				System.out.println("Veículo removido.");
				break;
			case "5":
				cliente = encontrarCliente(seguradora, scan);
				if (cliente == null) {
					System.out.println("Cliente não encontrado.");
					break;
				}
				System.out.println(cliente);
				break;
			case "6":
				List<Cliente> clientes = seguradora.listarClientes("PF");
				if (clientes.isEmpty()) {
					System.out.println("Nenhum cliente cadastrado.");
					break;
				}
				System.out.println(clientes);
				break;
			case "7":
				clientes = seguradora.listarClientes("PJ");
				if (clientes.isEmpty()) {
					System.out.println("Nenhum cliente cadastrado.");
					break;
				}
				System.out.println(clientes);
				break;
			case "8":
				cliente = encontrarCliente(seguradora, scan);
				if (cliente == null) {
					System.out.println("Nenhum cliente cadastrado.");
					break;
				}
				Veiculo veiculo = encontrarVeiculo(cliente, scan);
				if (veiculo == null) {
					System.out.println("Veículo não encontrado.");
					break;
				}
				String endereco = scan.nextLine();
				if (seguradora.gerarSinistro(cliente, veiculo, endereco)) {
					System.out.println("Sinistro gerado");
				} else {
					System.out.println("Sinistro não gerado :C");
				}
				break;
			case "9":
				System.out.println("CPF/CNPJ do cliente");
				String cadastro = scan.nextLine();
				if (!seguradora.visualizarSinistro(cadastro)) {
					System.out.println("Nenhum sinistro encontrado relacionado com esse cadastro.");
				}
				break;

			case "10":
				List<Sinistro> sinistros = seguradora.listarSinistros();
				if (sinistros.isEmpty()) {
					System.out.println("Nenhum sinistro encontrado relacionado com esse cadastro.");
					break;
				}
				System.out.println(sinistros);
				break;
			default:
				break;
		}
		return true;
	}

	private static Cliente encontrarCliente(Seguradora seguradora, Scanner scan) {
		System.out.print("CPF/CNPJ: ");
		String cadastro = scan.nextLine();
		return seguradora.getClienteByCadastro(cadastro);
	}

	private static void addVeiculo(Seguradora seguradora, Scanner scan, Cliente cliente) {
		if (cliente == null) {
			System.out.println("Cliente não encontrado.");
			return;
		}
		System.out.print("Placa: ");
		Veiculo veiculo = VeiculoFactory.lerVeiculo(scan);
		cliente.addVeiculo(veiculo);
	}

	private static Veiculo encontrarVeiculo(Cliente cliente, Scanner scan) {
		System.out.print("Placa: ");
		String placa = scan.nextLine();
		Veiculo veiculo = cliente.getVeiculo(placa);
		return veiculo;
	}
}
