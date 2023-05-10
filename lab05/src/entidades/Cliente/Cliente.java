package entidades.Cliente;

import java.util.ArrayList;
import entidades.Sinistro;
import entidades.Seguro.Seguro;

public abstract class Cliente extends Base {
    private ArrayList<Seguro> listaSeguros = new ArrayList<Seguro>();

    public Cliente(String nome, String telefone, String endereco, String email) {
        super(nome, telefone, endereco, email);
    }

    public abstract String getCadastro();

    public boolean adicionarSeguro(Seguro seguro) {
        if (!listaSeguros.contains(seguro)) {
            listaSeguros.add(seguro);
            return true;
        }
        return false;
    }

    public ArrayList<Sinistro> getSinistros() {
        ArrayList<Sinistro> sinistros = new ArrayList<Sinistro>();
        for (Seguro seguro : listaSeguros) {
            sinistros.addAll(seguro.getListaSinistros());
        }
        return sinistros;
    }

    public ArrayList<Seguro> getSeguros() {
        return listaSeguros;
    }
}
