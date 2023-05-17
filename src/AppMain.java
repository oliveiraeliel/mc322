import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import entidades.*;
import entidades.Cliente.*;
import entidades.Seguro.Seguro;
import factories.ClienteFactory;
import factories.SeguradoraFactory;
import menu.MenuOperacoes;

public class AppMain {
	public static void main(String[] args) {
		Map<String, Seguradora> seguradoras = new HashMap<>();
		Map<Integer, Seguro> seguros = new HashMap<>();
		Map<String, Condutor> condutores = new HashMap<>();
		MenuOperacoes.menu(seguradoras, seguro, condutores);
	}
}
