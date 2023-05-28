package factories;

import java.util.Calendar;
import java.util.Date;
import entidades.Cliente.*;
import execeptions.ClienteNaoEncontradoException;
import menu.BancoDados;
import utils.DateUtils;
import utils.InputUtils;

// classe utilizada para a criação de objetos do tipo cliente
public class ClienteFactory {
    public static ClientePF generateClientePF(String cpf) {
        return new ClientePF("Joãozinho", cpf, "19123213123123", "joao@gmail.com", "Rua Pitágoras",
                "Ensino Superior", "Masculino",
                DateUtils.novaData(2, 2, 2002));
    }

    public static ClientePJ generateClientePJ(String cnpj, int quantidadeFunc) {
        Calendar cal = Calendar.getInstance();
        cal.set(2004, 3, 24);
        return new ClientePJ("Joãozinho Caminhões", cnpj, "12331412312", "asdfead@gmail.com",
                "Rua Pitágoras", DateUtils.novaData(15, 11, 2016), quantidadeFunc);
    }

    public static Cliente lerClientePJ() {
        System.out.println("------------- Cliente PJ --------------");
        String cnpj = InputUtils.lerCNPJ();
        try {
            return BancoDados.getCliente(cnpj);
        } catch (ClienteNaoEncontradoException e) {
            String nome = InputUtils.lerNome("Nome: ");
            String telefone = InputUtils.lerString("Telefone: ");
            String email = InputUtils.lerString("Email: ");
            String endereco = InputUtils.lerString("Endereco: ");
            Date dataFundacao = InputUtils.lerData("Data de Fundação (dd/mm/yyyy): ");
            int quantidadeFunc = InputUtils.lerInt("Quantidade de Funcionários: ");
            return new ClientePJ(nome, cnpj, telefone, email, endereco, dataFundacao, quantidadeFunc);
        }
    }

    public static Cliente lerClientePF() {
        System.out.println("------------- Cliente PF --------------");
        String cpf = InputUtils.lerCPF();
        try {
            return BancoDados.getCliente(cpf);
        } catch (ClienteNaoEncontradoException e) {
            String nome = InputUtils.lerNome("Nome: ");
            String telefone = InputUtils.lerString("Telefone: ");
            String email = InputUtils.lerString("Email: ");
            String endereco = InputUtils.lerString("Endereco: ");
            Date dataNascimento = InputUtils.lerData("Data de Nascimento (dd/mm/yyyy): ");
            String educacao = InputUtils.lerString("Educação: ");
            String genero = InputUtils.lerString("Genêro: ");
            return new ClientePF(nome, cpf, telefone, email, endereco, educacao, genero, dataNascimento);
        }
    }
}