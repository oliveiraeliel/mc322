package entidades.Seguradora;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import entidades.Base;
import entidades.Cliente.*;
import entidades.Frota.Frota;
import entidades.Seguro.*;
import entidades.Sinistro.Sinistro;
import entidades.Veiculo.Veiculo;
import execeptions.ClienteNaoAssociadoException;
import execeptions.ClienteNaoEncontradoException;
import execeptions.FrotaNaoAssociadaException;
import execeptions.VeiculoNaoAssociadoException;
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

    public Double calcularReceita() {
        return getReceita();
    }

    public Double somarReceita(Double valor) {
        Double rec = getReceita() + valor;
        setReceita(rec);
        return rec;
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

    public ArrayList<Sinistro> listarSinistros() {
        ArrayList<Sinistro> sinistros = new ArrayList<>();
        listaSeguros.forEach(seguro -> sinistros.addAll(seguro.getListaSinistros()));
        return sinistros;
    }

    public ArrayList<Veiculo> listarVeiculos() {
        ArrayList<Veiculo> veiculos = new ArrayList<>();
        listaSeguros.forEach(seguro -> {
            if (seguro instanceof SeguroPF) {
                Veiculo veiculo = ((SeguroPF) seguro).getVeiculo();
                veiculos.add(veiculo);
            }
        });
        return veiculos;
    }

    public ArrayList<Frota> listarFrotas() {
        ArrayList<Frota> frotas = new ArrayList<>();
        listaSeguros.forEach(seguro -> {
            if (seguro instanceof SeguroPJ) {
                Frota frota = ((SeguroPJ) seguro).getFrota();
                frotas.add(frota);
            }
        });
        return frotas;
    }

    public Seguro gerarSeguro(ClientePF cliente, Veiculo veiculo, Date dataFim)
            throws ClienteNaoAssociadoException, VeiculoNaoAssociadoException {
        if (listaClientes.contains(cliente) && cliente.getListaVeiculos().contains(veiculo)) {
            Seguro seguro = new SeguroPF(DateUtils.localDate(), dataFim, this, veiculo, cliente);
            listaSeguros.add(seguro);
            veiculo.desassociarSeguro();
            veiculo.setSeguro(seguro);
            return seguro;
        }

        if (!listaClientes.contains(cliente)) {
            throw new ClienteNaoAssociadoException(
                    "O cliente '" + cliente.getNome() + "' não está cadastrado na seguradora '" + getNome() + "'");
        }
        throw new VeiculoNaoAssociadoException("Veículo não associado ao cliente " + cliente.getNome());
    }

    public Seguro gerarSeguro(ClientePJ cliente, Frota frota, Date dataFim)
            throws ClienteNaoAssociadoException, FrotaNaoAssociadaException {
        if (listaClientes.contains(cliente) && cliente.getListaFrota().contains(frota)) {
            Seguro seguro = new SeguroPJ(DateUtils.localDate(), dataFim, this, frota, cliente);
            listaSeguros.add(seguro);
            frota.desassociarSeguro();
            frota.setSeguro(seguro);
            return seguro;
        }
        if (!listaClientes.contains(cliente)) {
            throw new ClienteNaoAssociadoException(
                    "O cliente '" + cliente.getNome() + "' não está cadastrado na seguradora '" + getNome() + "'");
        }
        throw new FrotaNaoAssociadaException("Frota não associado ao cliente " + cliente.getNome());
    }

    public boolean cadastrarCliente(Cliente cliente) {
        if (!listaClientes.contains(cliente)) {
            listaClientes.add(cliente);
            return true;
        }
        return false;
    }

    public boolean clienteCadastrado(Cliente cliente) {
        return listaClientes.contains(cliente);
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
            Iterator<Seguro> iter = listaSeguros.iterator();
            while (iter.hasNext()) {
                Seguro seguro = iter.next();
                if (seguro.getCliente().equals(cliente)) {
                    seguro.apagarSeguro();
                    iter.remove();
                }
            }
            calcularReceita();
            return true;
        }
        return false;
    }

    public boolean cancelarSeguro(Seguro seguro) {
        if (listaSeguros.remove(seguro)) {
            seguro.apagarSeguro();
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
        return seguradora.getCnpj().equals(cnpj);
    }

    @Override
    public String toString() {
        return "{" + super.toString() +
                ", cnpj='" + getCnpj() + "'" +
                "}";
    }

}
