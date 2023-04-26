package entidades;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import entidades.Cliente.Cliente;
import entidades.Cliente.ClientePF;
import entidades.Cliente.ClientePJ;
import utils.DateUtils;

public class Seguradora {
    private String nome;
    private final String CNPJ;
    private String telefone;
    private String email;
    private String endereco;
    private List<Cliente> listaClientes = new ArrayList<Cliente>();
    private List<Sinistro> listaSinistros = new ArrayList<Sinistro>();

    public Seguradora(String nome, String telefone, String email, String endereco, String CNPJ) {
        this.CNPJ = CNPJ;
        setNome(nome);
        setTelefone(telefone);
        setEmail(email);
        setEndereco(endereco);
    }

    public Double calcularReceita() {
        Double receita = 0.0;
        for (Cliente cliente : listaClientes) {
            receita += calcularPrecoSeguroCliente(cliente);
        }
        return receita;
    }

    public Double calcularPrecoSeguroCliente(Cliente cliente) {
        Double preco = cliente.calculaScore() * (1 + quantidadeSinistros(cliente));
        cliente.setValorSeguro(preco);
        return preco;
    }

    private int quantidadeSinistros(Cliente cliente) {
        int i = 0;
        for (Sinistro sinistro : listaSinistros) {
            if (sinistro.getCliente().equals(cliente)) {
                i++;
            }
        }
        return i;
    }

    /**
     * Dado um valor de cadastro, retorna um cliente que corresponda a esse
     * cadastro, se existir.
     * 
     * @param cliente Identificador único do cliente (CPF/CNPJ).
     * @return Cliente se existir, null se não existir
     */
    public Cliente getClienteByCadastro(String cliente) {
        for (Cliente cli : this.listaClientes) {
            if (cli.getCadastro().equals(cliente)) {
                return cli;
            }
        }
        return null;
    }

    /**
     * Cadastra um cliente no sistema, se ele ainda não foi cadastrado.
     * 
     * @param cliente Cliente
     * @return boolean
     */
    public boolean cadastrarCliente(Cliente cliente) {
        if (!listaClientes.contains(cliente)) {
            addCliente(cliente);
            return true;
        }
        return false;
    }

    /**
     * Dado um tipo de cliente, retorna uma lista de todos os clientes que são
     * daquele tipo.
     * 
     * @param tipoCliente PF | PJ
     * @return List<Cliente>
     */
    public List<Cliente> listarClientes(String tipoCliente) {
        List<Cliente> listaClientes = new ArrayList<>();
        for (Cliente cliente : this.listaClientes) {
            if (cliente instanceof ClientePF && tipoCliente.equals("PF")) {
                listaClientes.add(cliente);
            } else if (cliente instanceof ClientePJ && tipoCliente.equals("PJ")) {
                listaClientes.add(cliente);
            }
        }
        return listaClientes;
    }

    /**
     * Lista os sinistros cadastrados no sistema.
     * 
     * @return List<Sinistro>
     */
    public List<Sinistro> listarSinistros() {
        return listaSinistros;
    }

    /**
     * Gera um sinistro, dado um cliente, veículo e endereço, se o cliente estiver
     * cadastrado e
     * o veículo for do cliente.
     * 
     * @param cliente  Cliente
     * @param veiculo  Veiculo
     * @param endereco Endereço do ocorrido
     * @return boolean
     */
    public boolean gerarSinistro(Cliente cliente, Veiculo veiculo, String endereco) {
        if (this.listaClientes.contains(cliente) && cliente.getListaVeiculos().contains(veiculo)) {
            Sinistro sinistro = new Sinistro(DateUtils.localDate(), endereco, cliente, veiculo, this);
            addSinistro(sinistro);
            return true;
        }
        return false;
    }

    /**
     * Remove um cliente, se ele estiver cadastrado.
     * 
     * @param cliente Identificador único do cliente (CPF/CNPJ).
     * @return boolean
     */
    public boolean removerCliente(String cliente) {
        for (Cliente cli : listaClientes) {
            if (cli.getCadastro().equals(cliente)) {
                listaClientes.remove(cli);
                removerSinistros(cli);
                return true;
            }
        }
        return false;
    }

    /**
     * Remove os sinistros de um cliente, se ele possuir algum
     * 
     * @param cliente Identificador único do cliente (CPF/CNPJ).
     */
    private void removerSinistros(Cliente cliente) {
        Iterator<Sinistro> iter = listaSinistros.iterator();
        while (iter.hasNext()) {
            Sinistro sinistro = iter.next();
            if (sinistro.getCliente().equals(cliente)) {
                iter.remove();
            }
        }
    }

    /**
     * Visualiza os sinistros de um cliente, se ele possuir algum.
     * 
     * @param cliente Identificador único do cliente (CPF/CNPJ).
     * @return boolean.
     */
    public boolean visualizarSinistro(String cliente) {
        List<Sinistro> sinistros = new ArrayList<>();
        for (Sinistro sinistro : listaSinistros) {
            Cliente cli = sinistro.getCliente();
            if (cli.getCadastro().equals(cliente)) {
                sinistros.add(sinistro);
            }
        }
        if (!sinistros.isEmpty()) {
            System.out.println(sinistros);
            return true;
        }
        return false;
    }

    /**
     * Adiciona um sinistro à lista de sinistros, se ainda não estiver presente.
     * 
     * @param sinistro Sinistro a ser adicionado.
     * @return boolean
     */
    private boolean addSinistro(Sinistro sinistro) {
        if (!listaSinistros.contains(sinistro)) {
            listaSinistros.add(sinistro);
            return true;
        }
        return false;
    }

    /**
     * Adiciona um cliente à lista de clientes
     * 
     * @param cliente Cliente a ser adicionado.
     * @return boolean
     */
    private boolean addCliente(Cliente cliente) {
        if (!listaClientes.contains(cliente)) {
            listaClientes.add(cliente);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "{" +
                " nome='" + getNome() + "'" +
                ", telefone='" + getTelefone() + "'" +
                ", email='" + getEmail() + "'" +
                ", endereco='" + getEndereco() + "'" +
                ", listaClientes='" + getListaClientes() + "'" +
                ", listaSinistros='" + listarSinistros() + "'" +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Seguradora)) {
            return false;
        }
        Seguradora seguradora = (Seguradora) o;
        return Objects.equals(nome, seguradora.getNome()) && Objects.equals(telefone, seguradora.getTelefone())
                && Objects.equals(email, seguradora.getEmail()) && Objects.equals(endereco, seguradora.getEndereco())
                && Objects.equals(listaClientes, seguradora.getListaClientes())
                && Objects.equals(listaSinistros, seguradora.listarSinistros());
    }

    // getters e setters

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome.trim();
    }

    public String getTelefone() {
        return this.telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone.trim();
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email.trim();
    }

    public String getEndereco() {
        return this.endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco.trim();
    }

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    public String getCNPJ() {
        return this.CNPJ;
    }
}
