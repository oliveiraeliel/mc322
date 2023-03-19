import entidades.*;

public class Main {

	public static void main(String[] args) {
		Sinistro sinistro = new Sinistro("16/03/2023", "Rua Nanci Evangelista, 25");
		Cliente cliente = new Cliente(" Eliel Oliveira da Silva ", " 539.499.396-36 ", "20/02/2004", 19, "Rua Nanci Evangelista, 25");
		Veiculo veiculo = new Veiculo("ABC-1234", "Chevrolet", "Onix");
		Seguradora seguradora = new Seguradora("Eliel Seguradoras", "123456789", "e221437@dac.unicamp.br", "Rua Saturnino, 110");

		System.out.println(cliente.toString());
		System.out.println(veiculo.toString());
		System.out.println(sinistro.toString());
		System.out.println(seguradora.toString());
	}

}
