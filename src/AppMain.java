import entidades.Cliente.Cliente;
import entidades.Cliente.ClientePF;
import entidades.Cliente.ClientePJ;
import entidades.Condutor.Condutor;
import entidades.Frota.Frota;
import entidades.Seguradora.Seguradora;
import entidades.Seguro.Seguro;
import entidades.Veiculo.Veiculo;
import factories.ClienteFactory;
import factories.CondutorFactory;
import factories.SeguradoraFactory;
import factories.VeiculoFactory;
import menu.BancoDados;
import menu.MenuOperacoes;
import utils.DateUtils;

public class AppMain {
	public static void main(String[] args) {
		// cliente pf
		Cliente clientePF = ClienteFactory.generateClientePF("850.231.860-83");
		Cliente clientePJ = ClienteFactory.generateClientePJ("79.636.554/0001-30", 100);
		// veiculos
		Veiculo veiculo1 = VeiculoFactory.generateVeiculo("AAAA111");
		Veiculo veiculo2 = VeiculoFactory.generateVeiculo("BBBB111");
		Veiculo veiculo3 = VeiculoFactory.generateVeiculo("CCC111");
		Veiculo veiculo4 = VeiculoFactory.generateVeiculo("AAAA222");
		Veiculo veiculo5 = VeiculoFactory.generateVeiculo("AAAA333");
		Veiculo veiculo6 = VeiculoFactory.generateVeiculo("AAAA444");
		// adicionar veiculo
		((ClientePF) clientePF).cadastrarVeiculo(veiculo1);
		((ClientePF) clientePF).cadastrarVeiculo(veiculo2);
		((ClientePF) clientePF).cadastrarVeiculo(veiculo3);
		// adicionar frota
		Frota frota = new Frota();
		System.out.println("código frota: " + frota.getCode());
		((ClientePJ) clientePJ).cadastrarFrota(frota);
		frota.addVeiculo(veiculo4);
		frota.addVeiculo(veiculo5);
		frota.addVeiculo(veiculo6);
		// seguradora
		Seguradora seguradora1 = SeguradoraFactory.generateSeguradora("Eliel Seguradoras",
				"98.275.829/0001-00");
		Seguradora seguradora2 = SeguradoraFactory.generateSeguradora("Seguradora do Eliel",
				"92.634.218/0001-16");
		seguradora1.cadastrarCliente(clientePJ);
		seguradora1.cadastrarCliente(clientePF);
		seguradora2.cadastrarCliente(clientePJ);
		seguradora2.cadastrarCliente(clientePF);
		// condutores
		Condutor condutor1 = CondutorFactory.generateCondutor("679.810.220-45");
		Condutor condutor2 = CondutorFactory.generateCondutor("628.492.440-23");
		Condutor condutor3 = CondutorFactory.generateCondutor("097.867.240-20");
		try {
			// seguro
			Seguro seguro1 = seguradora1.gerarSeguro((ClientePF) clientePF, veiculo1, DateUtils.novaData(1, 2, 2025));
			Seguro seguro2 = seguradora1.gerarSeguro((ClientePJ) clientePJ, frota, DateUtils.novaData(15, 9, 2024));
			Seguro seguro3 = seguradora2.gerarSeguro((ClientePF) clientePF, veiculo2, DateUtils.novaData(18, 12, 2024));
			Seguro seguro4 = seguradora2.gerarSeguro((ClientePF) clientePF, veiculo3, DateUtils.novaData(9, 2, 2026));
			// seguro
			seguro1.autorizarCondutor(condutor1);
			seguro2.autorizarCondutor(condutor2);
			seguro2.autorizarCondutor(condutor3);
			seguro3.autorizarCondutor(condutor2);
			seguro4.autorizarCondutor(condutor1);
			seguro4.autorizarCondutor(condutor2);
			seguro4.autorizarCondutor(condutor3);
			// sinistro
			seguro1.gerarSinistro(DateUtils.localDate(), "Rua pitagoras", condutor1);
			seguro1.gerarSinistro(DateUtils.localDate(), "Rua pitagoras", condutor1);
			seguro2.gerarSinistro(DateUtils.localDate(), "Rua pitagoras", condutor2);
			seguro3.gerarSinistro(DateUtils.localDate(), "Rua pitagoras", condutor2);
			seguro4.gerarSinistro(DateUtils.localDate(), "Rua pitagoras", condutor3);
			// tostring
			System.out.println("========clientepf=======");
			System.out.println(clientePF);
			System.out.println("========clientepj=======");
			System.out.println(clientePJ);
			System.out.println("========seguradora1=======");
			System.out.println(seguradora1);
			System.out.println("========seguradora2=======");
			System.out.println(seguradora1);
			System.out.println("========condutor2=======");
			System.out.println(condutor2);

			BancoDados.save(clientePJ);
			BancoDados.save(clientePF);
			BancoDados.save(seguradora1);
			BancoDados.save(seguradora2);
			BancoDados.save(seguro1);
			BancoDados.save(seguro2);
			BancoDados.save(seguro3);
			BancoDados.save(seguro4);
			BancoDados.save(condutor1);
			BancoDados.save(condutor2);
			BancoDados.save(condutor3);
			MenuOperacoes.menu();
		} catch (Exception e){}
	}
}
