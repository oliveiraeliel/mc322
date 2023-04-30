import java.util.HashMap;
import java.util.Map;
import enums.MenuOperacoes;
import entidades.*;
public class Main {
	public static void main(String[] args) {
		// Scanner scan = new Scanner(System.in);
		Map<String, Seguradora> seguradoras = new HashMap<>();
		MenuOperacoes.menu(seguradoras);
	}
}
