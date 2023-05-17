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
        return new ClientePF("Joãozinho", "705.828.810-05", "19123213123123", "joao@gmail.com", "Rua Pitágoras",
                cal.getTime(), "Ensino Superior", "Masculino", cal.getTime());
    }

    public static ClientePF generateClientePF(String cpf) {
        Calendar cal = Calendar.getInstance();
        cal.set(2004, 3, 24);
        return new ClientePF("Joãozinho", cpf, "19123213123123", "joao@gmail.com", "Rua Pitágoras",
                cal.getTime(), "Ensino Superior", "Masculino", cal.getTime());
    }

    public static ClientePJ generateClientePJ() {
        Calendar cal = Calendar.getInstance();
        cal.set(2004, 3, 24);
        return new ClientePJ("Joãozinho Caminhões", "97.909.112/0001-00", "12331412312", "asdfead@gmail.com",
                "Rua Pitágoras", cal.getTime(), 100);
    }

    public static ClientePJ generateClientePJ(String cnpj, int quantidadeFunc) {
        Calendar cal = Calendar.getInstance();
        cal.set(2004, 3, 24);
        return new ClientePJ("Joãozinho Caminhões", cnpj, "12331412312", "asdfead@gmail.com",
                "Rua Pitágoras", cal.getTime(), quantidadeFunc);
    }

    public static Cliente lerClientePJ() {
        System.out.println("------------- Cliente PJ --------------");
        String nome = InputUtils.lerNome("Nome: ");
        String cnpj = InputUtils.lerCNPJ();
        String telefone = InputUtils.lerString("Telefone: ");
        String email = InputUtils.lerString("Email: ");
        String endereco = InputUtils.lerString("Endereco: ");
        Date dataFundacao = InputUtils.lerData("Data de Fundação (dd/mm/yyyy): ");
        int quantidadeFunc = InputUtils.lerInt("Quantidade de Funcionários: ");
        return new ClientePJ(nome, cnpj, telefone, email, endereco, dataFundacao, quantidadeFunc);
    }

    public static Cliente lerClientePF() {
        System.out.println("------------- Cliente PF --------------");
        String nome = InputUtils.lerNome("Nome: ");
        String cpf = InputUtils.lerCPF();
        String telefone = InputUtils.lerString("Telefone: ");
        String email = InputUtils.lerString("Email: ");
        String endereco = InputUtils.lerString("Endereco: ");
        Date dataLicenca = InputUtils.lerData("Data da Licenca (dd/mm/yyyy): ");
        Date dataNascimento = InputUtils.lerData("Data de Nascimento (dd/mm/yyyy): ");
        String educacao = InputUtils.lerString("Educação: ");
        String genero = InputUtils.lerString("Genêro: ");
        return new ClientePF(nome, cpf, telefone, email, endereco, dataLicenca, educacao, genero, dataNascimento);
    }
}
