import java.util.HashMap;
import java.util.Map;
import enums.MenuOperacoes;
import entidades.*;
public class Main {
	public static void main(String[] args) {
		Map<String, Seguradora> seguradoras = new HashMap<>();
		MenuOperacoes.menu(seguradoras);
	}
}
