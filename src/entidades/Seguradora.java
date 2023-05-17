package entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import entidades.Cliente.*;
import entidades.Seguro.*;
import execeptions.ClienteNaoEncontradoException;
import utils.DateUtils;
import utils.ValidatorUtils;

public class Seguradora extends Base {
    private final String cnpj;
    private ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
    private ArrayList<Seguro> listaSeguros = new ArrayList<Seguro>();
    private Double receita = 0.0;

    public Seguradora(String nome, String cnpj, String telefone, String email, String endereco) {
        super(nome, telefone, endereco, email);
        cnpj = ValidatorUtils.formatarCNPJ(cnpj);
        this.cnpj = cnpj;
    }

    public void adicionarReceita(Double receita) {
        setReceita(getReceita() + receita);
    }

    public Double calcularReceita() {
        return getReceita();
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
            Seguro seguro = new SeguroPF(DateUtils.localDate(), dataFim, this, veiculo, cliente);
            listaSeguros.add(seguro);
            return true;
        }
        return false;
    }

    public boolean gerarSeguro(ClientePJ cliente, Frota frota, Date dataFim) {
        if (listaClientes.contains(cliente)) {
            Seguro seguro = new SeguroPJ(DateUtils.localDate(), dataFim, this, frota, cliente);
            listaSeguros.add(seguro);
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

    public boolean removerCliente(String cadastro) {
        try {
            Cliente cliente = getClientePorCadastro(cadastro);
            return removerCliente(cliente);
        } catch (ClienteNaoEncontradoException e) {
            return false;
        }
    }

    public boolean removerCliente(Cliente cliente) {
        if (listaClientes.remove(cliente)) {
            for (Seguro seguro : listaSeguros)
                if (seguro.getCliente().equals(cliente))
                    cancelarSeguro(seguro);
            return true;
        }
        return false;
    }

    public boolean cancelarSeguro(Seguro seguro) {
        if (listaSeguros.remove(seguro)) {
            Double valorSeguro = seguro.getValorMensal();
            seguro.destruirSeguro();
            setReceita(getReceita() - valorSeguro);
            return true;
        }
        return false;
    }

    public ArrayList<Seguro> getSegurosPorCliente(String cadastro) throws ClienteNaoEncontradoException {
        Cliente cliente = getClientePorCadastro(cadastro);
        return getSegurosPorCliente(cliente);
    }

    public ArrayList<Seguro> getSegurosPorCliente(Cliente cliente) {
        ArrayList<Seguro> seguros = new ArrayList<Seguro>();
        for (Seguro seguro : listaSeguros) {
            if (seguro.getCliente().equals(cliente)) {
                seguros.add(seguro);
            }
        }
        return seguros;
    }

    public ArrayList<Sinistro> getSinistrosPorCliente(String cadastro) throws ClienteNaoEncontradoException {
        Cliente cliente = getClientePorCadastro(cadastro);
        return getSinistrosPorCliente(cliente);

    }

    public ArrayList<Sinistro> getSinistrosPorCliente(Cliente cliente) {
        ArrayList<Sinistro> sinistros = new ArrayList<Sinistro>();
        for (Seguro seguro : listaSeguros) {
            if (seguro.getCliente().equals(cliente)) {
                sinistros.addAll(seguro.getListaSinistros());
            }
        }
        return sinistros;
    }

    public Cliente getClientePorCadastro(String cadastro) throws ClienteNaoEncontradoException {
        for (Cliente cliente : listaClientes) {
            if (cliente.getCadastro().equals(cadastro)) {
                return cliente;
            }
        }
        throw new ClienteNaoEncontradoException("Nenhum Cliente com o cadastro" + cadastro + "foi encontrado");
    }

    public String getCnpj() {
        return this.cnpj;
    }

    public List<Cliente> getListaClientes() {
        return this.listaClientes;
    }

    public void setListaClientes(ArrayList<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public ArrayList<Seguro> getListaSeguros() {
        return this.listaSeguros;
    }

    public Double getReceita() {
        return this.receita;
    }

    public void setReceita(Double receita) {
        this.receita = receita;
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
