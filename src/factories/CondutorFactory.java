package factories;

import java.util.Date;

import entidades.Condutor.Condutor;
import execeptions.CondutorNaoEncontradoException;
import menu.BancoDados;
import utils.DateUtils;
import utils.InputUtils;

public final class CondutorFactory {
    public static Condutor generateCondutor(String cpf){
        return new Condutor("Joãozinho", cpf, "1234578", "Rua 13", "adfsdf@gmail.com", DateUtils.novaData(13, 11, 1989));
    }

    public static Condutor lerCondutor() {
        System.out.println("------------- CONDUTOR --------------");
        String cpf = InputUtils.lerCPF();
        try {
            return BancoDados.getCondutor(cpf);
        } catch (CondutorNaoEncontradoException e) {
            String nome = InputUtils.lerNome("Nome: ");
            String telefone = InputUtils.lerString("Telefone: ");
            String endereco = InputUtils.lerString("Endereço: ");
            String email = InputUtils.lerString("Email: ");
            Date dataNascimento = InputUtils.lerData("Data de nascimento (dd/mm/yyyy): ");
            return new Condutor(nome, cpf, telefone, endereco, email, dataNascimento);
        }
    }
}
