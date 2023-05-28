package entidades.Condutor;

import java.util.ArrayList;
import java.util.Date;

import entidades.Cliente.Base;
import entidades.Seguradora.Seguradora;
import entidades.Sinistro.Sinistro;
import utils.DateUtils;
import utils.ValidatorUtils;

public class Condutor extends Base {
    private final String cpf;
    private Date dataNasc;
    private ArrayList<Sinistro> listaSinistros = new ArrayList<Sinistro>();

    public Condutor(String nome, String cpf, String telefone, String endereco, String email, Date dataNasc) {
        super(nome, telefone, endereco, email);
        this.cpf = ValidatorUtils.formatarCPF(cpf);
        setDataNasc(dataNasc);
    }

    public int getQuantidadeSinistros(Seguradora seguradora) {
        int quantidade = 0;
        for (Sinistro sinistro : listaSinistros) {
            if (sinistro.getSeguro().getSeguradora().equals(seguradora)) {
                quantidade++;
            }
        }
        return quantidade;
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

    @Override
    public String toString() {
        return "{" + super.toString() +
                ", cpf='" + getCpf() + "'" +
                ", dataNasc='" + DateUtils.formatDate(getDataNasc(), "dd/MM/yyyy") + "'" +
                ", listaSinistros='" + getListaSinistros() + "'" +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Condutor)) {
            return false;
        }
        Condutor condutor = (Condutor) o;
        return condutor.getCpf().equals(cpf);
    }
}
