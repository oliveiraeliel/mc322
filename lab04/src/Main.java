import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import entidades.*;
import entidades.Cliente.*;
import factories.*;
import utils.DateUtils;
import utils.Validacao;

public class Main {
	public static void main(String[] args) {
		// Scanner scan = new Scanner(System.in);
		// scan.close();
		// Seguradora seguradora = SeguradoraFactory.generateSeguradora();
		// Cliente cliente = ClienteFactory.generateClientePF("23837495833");
		// Veiculo veiculo = VeiculoFactory.generateVeiculo("ABCD");
		// cliente.addVeiculo(veiculo);
		// seguradora.cadastrarCliente(cliente);
		// seguradora.gerarSinistro(cliente, veiculo, "adfsda");
		// System.out.println(cliente.calculaScore());
		// System.out.println(seguradora.calcularPrecoSeguroCliente(cliente));
		// System.out.println(seguradora.calcularReceita());

		Calendar cal = Calendar.getInstance();
		cal.set(2004, 3, 3);
		System.out.println(DateUtils.calcularIdade(cal.getTime()));

	}

	/**
	 * Metodo de menu. Recebe comandos do teclado e realiza operações.
	 * 
	 * @param seguradora
	 * @param scan
	 * @return boolean
	 */
	public static boolean menu(Seguradora seguradora, Scanner scan) {
		System.out.println("\n0- Sair");
		System.out.println("1- Criar um cliente PF");
		System.out.println("2- Criar um cliente PJ");
		System.out.println("3- Remover cliente");
		System.out.println("4- Adicionar veiculo");
		System.out.println("5- Listar veiculos do cliente");
		System.out.println("6- Remover veiculo");
		System.out.println("7- Ver cliente");
		System.out.println("8- Ver lista de clientes PF");
		System.out.println("9- Ver lista de clientes PJ");
		System.out.println("10- Gerar sinistro");
		System.out.println("11- Visualizar sinistros");
		System.out.println("12- Lista sinistros");
		String digito = scan.nextLine();

		if (digito.equals("0")) {
			return false;
		} else if (digito.equals("1")) {
			cadastrarClientePF(seguradora, scan);
		} else if (digito.equals("2")) {
			cadastrarClientePJ(seguradora, scan);
		} else if (digito.equals("3")) {
			removerCliente(seguradora, scan);
		} else if (digito.equals("4")) {
			adicionarVeiculo(seguradora, scan);
		} else if (digito.equals("5")) {
			listarVeiculosCliente(seguradora, scan);
		} else if (digito.equals("6")) {
			removerVeiculo(seguradora, scan);
		} else if (digito.equals("7")) {
			verCliente(seguradora, scan);
		} else if (digito.equals("8")) {
			verClientes(seguradora, scan, "PF");
		} else if (digito.equals("9")) {
			verClientes(seguradora, scan, "PJ");
		} else if (digito.equals("10")) {
			gerarSinistro(seguradora, scan);
		} else if (digito.equals("11")) {
			visualizarSinistro(seguradora, scan);
		} else if (digito.equals("12")) {
			listarSinistros(seguradora, scan);
		}
		return true;

	}

	/**
	 * Cadastra um cliente pessoa física na seguradora.
	 * 
	 * @param seguradora Seguradora
	 * @param scan       Scanner
	 */
	public static void cadastrarClientePF(Seguradora seguradora, Scanner scan) {
		if (!seguradora.cadastrarCliente(ClienteFactory.lerClientePF(scan))) {
			System.out.println("Cliente já cadastrado!");
		}
	}

	/**
	 * Cadastra um cliente pessoa jurídica na seguradora.
	 * 
	 * @param seguradora Seguradora
	 * @param scan       Scanner
	 */
	public static void cadastrarClientePJ(Seguradora seguradora, Scanner scan) {
		if (!seguradora.cadastrarCliente(ClienteFactory.lerClientePJ(scan))) {
			System.out.println("Cliente já cadastrado!");
		}
	}

	/**
	 * Remove um cliente da seguradora.
	 * 
	 * @param seguradora Seguradora
	 * @param scan       Scanner
	 */
	public static void removerCliente(Seguradora seguradora, Scanner scan) {
		String cadastro = lerCadastro(scan);
		if (!Validacao.validaCPF(cadastro) && !Validacao.validaCNPJ(cadastro)) {
			System.out.println("Insira um CPF/CNPJ válido!");
			return;
		}
		if (!seguradora.removerCliente(cadastro)) {
			System.out.println("Cliente não encontrado!");
			return;
		}
		System.out.println("Cliente removido.");
	}

	/**
	 * Adiciona um veículo a um cliente cadastrado na seguradora.
	 * 
	 * @param seguradora Seguradora
	 * @param scan       Scanner
	 */
	public static void adicionarVeiculo(Seguradora seguradora, Scanner scan) {
		Cliente cliente = encontrarCliente(seguradora, scan);
		if (cliente == null) {
			System.out.println("Cliente não encontrado.");
			return;
		}
		Veiculo veiculo = VeiculoFactory.lerVeiculo(scan);
		cliente.addVeiculo(veiculo);
	}

	/**
	 * Lista os veículos de um cliente cadastrado na seguradora.
	 * 
	 * @param seguradora A seguradora onde serão listados os veículos.
	 * @param scan       Scanner
	 */
	public static void listarVeiculosCliente(Seguradora seguradora, Scanner scan) {
		Cliente cliente = encontrarCliente(seguradora, scan);
		if (cliente == null) {
			System.out.println("Cliente não encontrado.");
			return;
		}
		if (cliente.getListaVeiculos().isEmpty()) {
			System.out.println("Nenhum veículo encontrado.");
			return;
		}
		System.out.println(cliente.getListaVeiculos());
	}

	/**
	 * Remove um veículo de um cliente cadastrado na seguradora.
	 * 
	 * @param seguradora Seguradora
	 * @param scan       Scanner
	 */
	public static void removerVeiculo(Seguradora seguradora, Scanner scan) {
		Cliente cliente = encontrarCliente(seguradora, scan);
		if (cliente == null) {
			System.out.println("Cliente não encontrado.");
			return;
		}
		System.out.print("Placa: ");
		String placa = scan.nextLine();
		if (!cliente.removeVeiculo(placa)) {
			System.out.println("Veículo não encontrado.");
			return;
		}
		System.out.println("Veículo removido.");
	}

	/**
	 * Exibe os dados de um cliente cadastrado na seguradora.
	 * 
	 * @param seguradora Seguradora
	 * @param scan       Scanner
	 */
	public static void verCliente(Seguradora seguradora, Scanner scan) {
		Cliente cliente = encontrarCliente(seguradora, scan);
		if (cliente == null) {
			System.out.println("Cliente não encontrado.");
			return;
		}
		System.out.println(cliente);
	}

	/**
	 * Lista os clientes cadastrados na seguradora de acordo com o tipo (PF ou PJ).
	 * 
	 * @param seguradora Seguradora
	 * @param scan       Scanner
	 * @param tipo       O tipo de cliente a ser listado (PF ou PJ).
	 */
	public static void verClientes(Seguradora seguradora, Scanner scan, String tipo) {
		List<Cliente> clientes = seguradora.listarClientes(tipo);
		if (clientes.isEmpty()) {
			System.out.printf("Nenhum cliente %s cadastrado.\n", tipo);
			return;
		}
		System.out.println(clientes);
	}

	/**
	 * Gera um sinistro para um veículo de um cliente cadastrado na seguradora.
	 * 
	 * @param seguradora Seguradora
	 * @param scan       Scanner
	 */
	public static void gerarSinistro(Seguradora seguradora, Scanner scan) {
		Cliente cliente = encontrarCliente(seguradora, scan);
		if (cliente == null) {
			System.out.println("Nenhum cliente cadastrado.");
			return;
		}
		Veiculo veiculo = encontrarVeiculo(cliente, scan);
		if (veiculo == null) {
			System.out.println("Veículo não encontrado.");
			return;
		}
		System.out.print("Endereço do ocorrido: ");
		String endereco = scan.nextLine();
		if (seguradora.gerarSinistro(cliente, veiculo, endereco)) {
			System.out.println("Sinistro gerado");
		} else {
			System.out.println("Sinistro não gerado :C");
		}
	}

	/**
	 * Exibe os sinistros de um cliente cadastrado na seguradora.
	 * 
	 * @param seguradora Seguradora
	 * @param scan       Scanner
	 */
	public static void visualizarSinistro(Seguradora seguradora, Scanner scan) {
		String cadastro = lerCadastro(scan);
		if (!seguradora.visualizarSinistro(cadastro)) {
			System.out.println("Nenhum sinistro encontrado relacionado com esse cadastro.");
		}
	}

	/**
	 * Lista todos os sinistros registrados no sistema de uma dada seguradora.
	 * 
	 * @param seguradora Seguradora
	 * @param scan       Scanner
	 * @param tipo       O tipo de cliente a ser listado (PF ou PJ).
	 */
	public static void listarSinistros(Seguradora seguradora, Scanner scan) {
		List<Sinistro> sinistros = seguradora.listarSinistros();
		if (sinistros.isEmpty()) {
			System.out.println("Nenhum sinistro encontrado no sistema.");
			return;
		}
		System.out.println(sinistros);
	}

	/**
	 * Lê um cadastro do usuário, e retorna o cliente correspondente, se estiver
	 * cadastrado na seguradora
	 * 
	 * @param seguradora Seguradora
	 * @param scan       Seguradora
	 * @return Cliente
	 */
	public static Cliente encontrarCliente(Seguradora seguradora, Scanner scan) {
		return seguradora.getClienteByCadastro(lerCadastro(scan));
	}

	/**
	 * Retorna um veículo relacionado à placa lida do teclado, se ele estiver na
	 * lista de veículos do cliente.
	 * 
	 * @param cliente Cliente
	 * @param scan    Scanner
	 * @return Veiculo | null
	 */
	public static Veiculo encontrarVeiculo(Cliente cliente, Scanner scan) {
		System.out.print("Placa: ");
		String placa = scan.nextLine();
		Veiculo veiculo = cliente.getVeiculo(placa);
		return veiculo;
	}

	/**
	 * Lê o cadastro de um cliente (CPF/CNPJ). Aceita apenas cadastros válidos.
	 * 
	 * @param scan Scanner
	 * @return String
	 */
	public static String lerCadastro(Scanner scan) {
		System.out.print("CPF/CNPJ: ");
		String cadastro = scan.nextLine();
		if (!Validacao.validaCPF(cadastro) && !Validacao.validaCNPJ(cadastro)) {
			System.out.println("Insira um CPF/CNPJ válido!");
			return lerCadastro(scan);
		}
		return cadastro;
	}

}
