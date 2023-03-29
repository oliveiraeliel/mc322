package factories;

import java.util.Calendar;

import entidades.Cliente.ClientePF;

public class ClienteFactory {
    public static ClientePF createClientePF(String cpf) {
        Calendar cal = Calendar.getInstance();
        cal.set(2004, 3, 24);
        return new ClientePF("Joãozinho", "Rua Pitágoras", cal.getTime(), "Ensino Superior", "Masculino",
                "Classe média", cpf, cal.getTime());
    }

    public static ClientePF createClientePJ(String cnpj) {
        Calendar cal = Calendar.getInstance();
        cal.set(2004, 3, 24);
        return new ClientePF("Joãozinho Caminhões", "Rua Pitágoras", cal.getTime(), "Ensino Superior", "Masculino",
                "Classe média", cnpj, cal.getTime());
    }
}
