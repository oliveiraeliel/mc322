package entidades.Cliente;

import java.util.ArrayList;
import java.util.Date;

import entidades.Sinistro;

public class Condutor extends Base {
    private final String cpf;
    private Date dataNasc;
    private ArrayList<Sinistro> listaSinistros = new ArrayList<Sinistro>();

    public Condutor(String nome, String cpf, String telefone, String endereco, String email, Date dataNasc) {
        super(nome, telefone, endereco, email);
        this.cpf = cpf;
        setDataNasc(dataNasc);
    }

    public int getQuantidadeSinistros() {
        return listaSinistros.size();
    }

    public boolean adicionarSinistro(Sinistro sinistro) {
        if (!listaSinistros.contains(sinistro)) {
            listaSinistros.add(sinistro);
            return true;
        }
        return false;
    }

    public boolean removerSinistro(Sinistro sinistro) {
        return listaSinistros.remove(sinistro);
    }

    public String getCpf() {
        return this.cpf;
    }

    public Date getDataNasc() {
        return this.dataNasc;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    public ArrayList<Sinistro> getListaSinistros() {
        return this.listaSinistros;
    }

    public void setListaSinistros(ArrayList<Sinistro> listaSinistros) {
        this.listaSinistros = listaSinistros;
    }
}
