import java.util.HashMap;
import java.util.Map;

import entidades.*;
import entidades.Cliente.Cliente;
import factories.*;
import menu.MenuOperacoes;

public class AppMain {
	public static void main(String[] args) {
		Map<String, Seguradora> seguradoras = new HashMap<>();
		Seguradora seguradora = SeguradoraFactory.generateSeguradora("Eliel Seguros");
		Cliente clientePF = ClienteFactory.generateClientePF("422.864.890-84");
		Cliente clientePJ = ClienteFactory.generateClientePJ("89.184.373/0001-73", 200);
		Veiculo veiculo1 = VeiculoFactory.generateVeiculo("ABC1234");
		Veiculo veiculo2 = VeiculoFactory.generateVeiculo("DEF1234");
		// cadastrar clientes
		seguradora.cadastrarCliente(clientePJ);
		seguradora.cadastrarCliente(clientePF);
		// adicionar veiculos
		clientePF.addVeiculo(veiculo1);
		clientePJ.addVeiculo(veiculo2);
		// gerar sinistros
		seguradora.gerarSinistro(clientePF, veiculo1, "Onde judas perdeu as botas.");
		seguradora.gerarSinistro(clientePJ, veiculo2, "Onde o gato achou as botas.");
		// listarClientes
		System.out.println("------------------ CLIENTES PF ----------------------------");
		System.out.println(seguradora.listarClientes("PF"));
		System.out.println("\n---------------- CLIENTES PF ----------------------------");
		System.out.println(seguradora.listarClientes("PJ"));
		System.out.println();
		// visualizarSinistro
		System.out.println("----------- VISUALIZAR SINISTROS CLIENTE PF ---------------");
		seguradora.visualizarSinistro(clientePF.getCadastro());
		System.out.println("\n----------- VISUALIZAR SINISTROS CLIENTE PF -------------");
		seguradora.visualizarSinistro(clientePJ.getCadastro());
		System.out.println();
		// listarSinistros
		System.out.println("-------------------- LISTAR SINISTROS ---------------------");
		System.out.println(seguradora.listarSinistros());
		System.out.println();
		// calcular preco seguro cliente
		seguradora.calcularPrecoSeguroCliente(clientePJ);
		seguradora.calcularPrecoSeguroCliente(clientePF);
		System.out.println("-------------CALCULAR PRECO SEGURO CLIENTE PF -------------");
		System.out.println(clientePF.getValorSeguro());
		System.out.println("\n-------------CALCULAR PRECO SEGURO CLIENTE PJ -------------");
		System.out.println(clientePJ.getValorSeguro());
		System.out.println();
		// calcular receita
		System.out.println("-------------------- CALCULAR RECEITA --------------------");
		System.out.println(seguradora.calcularReceita());
		System.out.println();
		// Menu
		seguradoras.put(seguradora.getNome(), seguradora);
		MenuOperacoes.menu(seguradoras);
	}
}
