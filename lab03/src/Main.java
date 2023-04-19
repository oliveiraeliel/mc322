import java.util.Scanner;

import entidades.*;
import entidades.Cliente.*;
import factories.*;
import utils.ValidatorUtils;
import utils.MenuUtils;

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

	/**
	 * Metodo de menu. Recebe comandos do teclado e realiza operações.
	 * 
	 * @param seguradora
	 * @param scan
	 * @return boolean
	 */
	private static boolean menu(Seguradora seguradora, Scanner scan) {
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
			MenuUtils.cadastrarClientePF(seguradora, scan);
		} else if (digito.equals("2")) {
			MenuUtils.cadastrarClientePJ(seguradora, scan);
		} else if (digito.equals("3")) {
			MenuUtils.removerCliente(seguradora, scan);
		} else if (digito.equals("4")) {
			MenuUtils.adicionarVeiculo(seguradora, scan);
		} else if (digito.equals("5")) {
			MenuUtils.listarVeiculosCliente(seguradora, scan);
		} else if (digito.equals("6")) {
			MenuUtils.removerVeiculo(seguradora, scan);
		} else if (digito.equals("7")) {
			MenuUtils.verCliente(seguradora, scan);
		} else if (digito.equals("8")) {
			MenuUtils.verClientes(seguradora, scan, "PF");
		} else if (digito.equals("9")) {
			MenuUtils.verClientes(seguradora, scan, "PJ");
		} else if (digito.equals("10")) {
			MenuUtils.gerarSinistro(seguradora, scan);
		} else if (digito.equals("11")) {
			MenuUtils.visualizarSinistro(seguradora, scan);
		} else if (digito.equals("12")) {
			MenuUtils.listarSinistros(seguradora, scan);
		}
		return true;
	}
}
