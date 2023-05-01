import java.util.HashMap;
import java.util.Map;
import enums.MenuOperacoes;
import factories.ClienteFactory;
import factories.SeguradoraFactory;
import factories.VeiculoFactory;
import entidades.*;
import entidades.Cliente.Cliente;
public class Main {
	public static void main(String[] args) {
		Map<String, Seguradora> seguradoras = new HashMap<>();
		MenuOperacoes.menu(seguradoras);
	}
}
