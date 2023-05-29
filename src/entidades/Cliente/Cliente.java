package entidades.Cliente;

import java.util.ArrayList;

import entidades.Base;
import entidades.Veiculo.Veiculo;

public abstract class Cliente extends Base {
    public Cliente(String nome, String telefone, String endereco, String email) {
        super(nome, telefone, endereco, email);
    }

    public abstract String getCadastro();

    public abstract int getQuantidadeVeiculos();

    public abstract ArrayList<Veiculo> listarVeiculos();


    @Override
    public String toString() {
        return super.toString();
    }
}
