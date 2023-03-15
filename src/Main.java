import entidades.*;

public class Main {

	public static void main(String[] args) {
		Sinistro sinistro = new Sinistro("16/03/2023", "Rua Nanci Evangelista, 25");
		Cliente cliente = new Cliente("Eliel Oliveira da Silva", "69871874790", "20/02/2004", 19, "Rua Nanci Evangelista, 25");

		System.out.println(sinistro.getId());
		System.out.println(cliente.getCpf());
	}

}
