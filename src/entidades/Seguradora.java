package entidades;

import java.util.ArrayList;
import java.util.List;

import entidades.Cliente.Cliente;
import entidades.Cliente.ClientePF;
import entidades.Cliente.ClientePJ;

public class Seguradora {
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private List<Cliente> listaClientes = new ArrayList<Cliente>();
    private List<Sinistro> listaSinistros = new ArrayList<Sinistro>();

    public Seguradora(String nome, String telefone, String email, String endereco) {
        setNome(nome);
        setTelefone(telefone);
        setEmail(email);
        setEndereco(endereco);
    }

    public boolean cadastrarCliente(Cliente cliente){
        if (listaClientes.contains(cliente)){
            return false;
        }
        listaClientes.add(cliente);
        return true;
    }

    public List<Cliente> listarClientes(String tipoCliente){
        List<Cliente> listaClientes = new ArrayList<>();
        for (Cliente cliente: this.listaClientes){
            if (cliente instanceof ClientePF && tipoCliente.equals("PF")){
                listaClientes.add(cliente);
            }else if (cliente instanceof ClientePJ && tipoCliente.equals("PJ")){
                listaClientes.add(cliente);
            }
        }

        return listaClientes;
    }

    public boolean gerarSinistro(Cliente cliente, Veiculo veiculo){
        if (!this.listaClientes.contains(cliente)){
            return false;
        }
        Sinistro sinistro = new Sinistro("data", "endereco", cliente, veiculo, this);
        this.listaSinistros.add(sinistro);
        return true;
    }

    public boolean removerCliente(Cliente cliente) {
        if (!listaClientes.contains(cliente)) {
            return false;
        }
        // listaClientes.removeIf(c -> c.equals(cliente));
        listaClientes.remove(cliente);
        return true;
    }

    /**
     *  Retorna uma String com os valores atuais do objeto
     */
    public String toString(){
        return String.format("Seguradora: %s, Telefone: %s, Email: %s, Endereço: %s", nome, telefone, email, endereco);
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
}
