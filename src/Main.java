import entidades.*;

public class Main {

	public static void main(String[] args) {
		Sinistro sinistro = new Sinistro("16/03/2023", "Rua Nanci Evangelista, 25");
		Cliente cliente = new Cliente("Eliel Oliveira da Silva", "69871874790", "20/02/2004", 19, "Rua Nanci Evangelista, 25");
		Veiculo veiculo = new Veiculo();
		Seguradora seguradora = new Seguradora();

		veiculo.setMarca("Chevrolet");
		veiculo.setModelo("Onix");
		veiculo.setPlaca("ABC-1234");


		seguradora.setEmail("e221437@dac.unicamp.br");
		seguradora.setEndereco("Rua nanci evangelista, 25");
		seguradora.setNome("Eliel Seguradoras");
		seguradora.setTelefone("12412313");

		System.out.println(sinistro.getId());
		System.out.println(cliente.getCpf());
	}

}
