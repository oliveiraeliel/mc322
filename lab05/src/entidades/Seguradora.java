package entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import entidades.Cliente.Base;
import entidades.Cliente.Cliente;
import entidades.Cliente.ClientePF;
import entidades.Cliente.ClientePJ;
import entidades.Seguro.*;
import enums.TipoCliente;
import utils.DateUtils;
import utils.ValidatorUtils;

public class Seguradora extends Base {
    private final String cnpj;
    private ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
    private ArrayList<Seguro> listaSeguros = new ArrayList<Seguro>();

    public Seguradora(String nome, String cnpj, String telefone, String email, String endereco) {
        super(nome, telefone, endereco, email);
        cnpj = ValidatorUtils.formatarCNPJ(cnpj);
        this.cnpj = cnpj;
    }

    public ArrayList<Cliente> listarClientes(TipoCliente tipo) {
        ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
        for (Cliente cliente : this.listaClientes) {
            if (cliente instanceof ClientePF && tipo == TipoCliente.PF) {
                listaClientes.add(cliente);
            } else if (cliente instanceof ClientePF && tipo == TipoCliente.PJ) {
                listaClientes.add(cliente);
            }
        }
        return listaClientes;
    }

    public boolean gerarSeguro(ClientePF cliente, Veiculo veiculo, Date dataFim) {
        if (listaClientes.contains(cliente)) {
            Seguro seguro = new SeguroPF(DateUtils.localDate(), dataFim, this, 0.0, veiculo, cliente);
            listaSeguros.add(seguro);
            cliente.adicionarSeguro(seguro);
            return true;
        }
        return false;

    }

    public boolean gerarSeguro(ClientePJ cliente, Frota frota, Date dataFim) {
        if (listaClientes.contains(cliente)) {
            Seguro seguro = new SeguroPJ(DateUtils.localDate(), dataFim, this, 0.0, frota, cliente);
            listaSeguros.add(seguro);
            cliente.adicionarSeguro(seguro);
            return true;
        }
        return false;
    }

    public boolean cadastrarCliente(Cliente cliente) {
        if (!listaClientes.contains(cliente)) {
            listaClientes.add(cliente);
            return true;
        }
        return false;
    }

    public String getCnpj() {
        return this.cnpj;
    }

    public List<Cliente> getListaClientes() {
        return this.listaClientes;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Seguradora)) {
            return false;
        }
        Seguradora seguradora = (Seguradora) o;
        return Objects.equals(cnpj, seguradora.cnpj);
    }

    @Override
    public String toString() {
        return "{" +
                " cnpj='" + getCnpj() + "'" +
                ", listaClientes='" + getListaClientes() + "'" +
                "}";
    }

}
