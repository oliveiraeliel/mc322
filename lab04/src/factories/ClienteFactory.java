package factories;

import java.util.Calendar;
import java.util.Date;
import entidades.Cliente.*;
import utils.InputUtils;

// classe utilizada para a criação de objetos do tipo cliente
public class ClienteFactory {
    public static ClientePF generateClientePF() {
        Calendar cal = Calendar.getInstance();
        cal.set(2004, 3, 24);
        return new ClientePF("Joãozinho", "Rua Pitágoras", cal.getTime(), "Ensino Superior", "Masculino",
                "Classe média", "705.828.810-05", cal.getTime());
    }

    public static ClientePF generateClientePF(String cpf) {
        Calendar cal = Calendar.getInstance();
        cal.set(2004, 3, 24);
        return new ClientePF("Joãozinho", "Rua Pitágoras", cal.getTime(), "Ensino Superior", "Masculino",
                "Classe média", cpf, cal.getTime());
    }

    public static ClientePJ generateClientePJ() {
        Calendar cal = Calendar.getInstance();
        cal.set(2004, 3, 24);
        return new ClientePJ("Joãozinho Caminhões", "Rua Pitágoras", "97.909.112/0001-00", cal.getTime(), 0);
    }

    public static ClientePJ generateClientePJ(String cnpj) {
        Calendar cal = Calendar.getInstance();
        cal.set(2004, 3, 24);
        return new ClientePJ("Joãozinho Caminhões", "Rua Pitágoras", cnpj, cal.getTime(), 0);
    }

    public static Cliente lerClientePJ() {
        System.out.println("------------- Cliente PJ --------------");
        String nome = InputUtils.lerNome("Nome: ");
        String cnpj = InputUtils.lerCNPJ();
        String endereco = InputUtils.lerString("Endereco: ");
        Date dataFundacao = InputUtils.lerData("Data de Fundação (dd/mm/yyyy): ");
        int qtd = InputUtils.lerInt("Quantidade de funcionários: ");
        return new ClientePJ(nome, endereco, cnpj, dataFundacao, qtd);
    }

    public static Cliente lerClientePF() {
        System.out.println("------------- Cliente PF --------------");
        String nome = InputUtils.lerNome("Nome: ");
        String cpf = InputUtils.lerCPF();
        String endereco = InputUtils.lerString("Endereco: ");
        Date dataLicenca = InputUtils.lerData("Data da Licenca (dd/mm/yyyy): ");
        Date dataNascimento = InputUtils.lerData("Data de Nascimento (dd/mm/yyyy): ");
        String educacao = InputUtils.lerString("Educação: ");
        String genero = InputUtils.lerString("Genêro: ");
        String classeEconomica = InputUtils.lerString("Classe econômica: ");
        return new ClientePF(nome, endereco, dataLicenca, educacao, genero, classeEconomica, cpf, dataNascimento);
    }
}
