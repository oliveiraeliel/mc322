package factories;

import java.util.Date;

import entidades.Cliente.Condutor;
import utils.InputUtils;

public final class CondutorFactory {
    public static Condutor lerCondutor() {
        String nome = InputUtils.lerNome("Nome: ");
        String cpf = InputUtils.lerCPF();
        String telefone = InputUtils.lerString("Telefone: ");
        String endereco = InputUtils.lerString("Endereço: ");
        String email = InputUtils.lerString("Email: ");
        Date dataNascimento = InputUtils.lerData("Data de nascimento (dd/mm/yyyy): ");
        return new Condutor(nome, cpf, telefone, endereco, email, dataNascimento);
    }
}
