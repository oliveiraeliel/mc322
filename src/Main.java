import java.util.List;
import java.util.Scanner;

import entidades.*;
import entidades.Cliente.*;
import factories.*;
import utils.ValidatorUtils;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Seguradora seguradora = SeguradoraFactory.generateSeguradora(); // instanciação dos objetos
		ClientePF clientePF = ClienteFactory.generateClientePF("776.457.590-97");
		ClientePJ clientePJ = ClienteFactory.generateClientePJ("88.085.363/0001-18");
		Veiculo veiculo = VeiculoFactory.generateVeiculo("ABCD1234");
		System.out.println(ClientePF.validarCPF(ValidatorUtils.formatarCPF("776.457.590-97")));
		System.out.println(ClientePJ.validarCNPJ(ValidatorUtils.formatarCNPJ("88.085.363/0001-18")));
		seguradora.cadastrarCliente(clientePF);
		System.out.println(seguradora.listarClientes("PF"));
		seguradora.removerCliente(clientePF.getCPF());
		System.out.println(seguradora.listarClientes("PF"));
		seguradora.cadastrarCliente(clientePF);
		seguradora.cadastrarCliente(clientePJ);
		seguradora.gerarSinistro(clientePF, veiculo, "Rua 20 de fevereiro, 2004");
		System.out.println(veiculo); // toString
		System.out.println(clientePF);
		System.out.println(clientePJ);
		while (menu(seguradora, scan)); // menu
		System.out.println(seguradora);
		scan.close();
	}

	private static boolean menu(Seguradora seguradora, Scanner scan) {
		System.out.println("\n0- Sair");
		System.out.println("1- Criar um cliente PF");
		System.out.println("2- Criar um cliente PJ");
		System.out.println("3- Adicionar veiculo");
		System.out.println("4- Remover veiculo");
		System.out.println("5- Listar veiculos do cliente");
		System.out.println("6- Ver cliente");
		System.out.println("7- Ver lista de clientes PF");
		System.out.println("8- Ver lista de clientes PJ");
		System.out.println("9- Gerar sinistro");
		System.out.println("10- Visualizar sinistros");
		System.out.println("11- Lista sinistros");
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
				if (cliente.getListaVeiculos().isEmpty()) {
					System.out.println("Nenhum veículo encontrado.");
					break;
				}
				System.out.println(cliente.getListaVeiculos());
				break;
			case "5":
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
			case "6":
				cliente = encontrarCliente(seguradora, scan);
				if (cliente == null) {
					System.out.println("Cliente não encontrado.");
					break;
				}
				System.out.println(cliente);
				break;
			case "7":
				List<Cliente> clientes = seguradora.listarClientes("PF");
				if (clientes.isEmpty()) {
					System.out.println("Nenhum cliente cadastrado.");
					break;
				}
				System.out.println(clientes);
				break;
			case "8":
				clientes = seguradora.listarClientes("PJ");
				if (clientes.isEmpty()) {
					System.out.println("Nenhum cliente cadastrado.");
					break;
				}
				System.out.println(clientes);
				break;
			case "9":
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
				System.out.print("Endereço do ocorrido: ");
				String endereco = scan.nextLine();
				if (seguradora.gerarSinistro(cliente, veiculo, endereco)) {
					System.out.println("Sinistro gerado");
				} else {
					System.out.println("Sinistro não gerado :C");
				}
				break;
			case "10":
				System.out.print("CPF/CNPJ: ");
				String cadastro = scan.nextLine();
				if (!seguradora.visualizarSinistro(cadastro)) {
					System.out.println("Nenhum sinistro encontrado relacionado com esse cadastro.");
				}
				break;

			case "11":
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
		if (!ClientePF.validarCPF(cadastro) && !ClientePJ.validarCNPJ(cadastro)) {
			System.out.println("Insira um CPF/CNPJ válido!");
			return encontrarCliente(seguradora, scan);
		}
		return seguradora.getClienteByCadastro(cadastro);
	}

	private static void addVeiculo(Seguradora seguradora, Scanner scan, Cliente cliente) {
		if (cliente == null) {
			System.out.println("Cliente não encontrado.");
			return;
		}
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
