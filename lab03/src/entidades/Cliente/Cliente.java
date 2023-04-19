package entidades.Cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import entidades.Veiculo;

public class Cliente {
    private String nome;
    private String endereco;
    private List<Veiculo> listaVeiculos;

    public Cliente(String nome, String endereco) {
        setNome(nome);
        setEndereco(endereco);
        setListaVeiculos(new ArrayList<Veiculo>());
    }

    public Cliente(String nome, String endereco, List<Veiculo> listaVeiculos) {
        setNome(nome);
        setEndereco(endereco);
        setListaVeiculos(listaVeiculos);
    }

    /**
     * Adiciona um veículo na lista de veículos do cliente, se ainda não estiver
     * presente.
     * 
     * @param veiculo O veículo a ser adicionado.
     * @return boolean
     */
    public boolean addVeiculo(Veiculo veiculo) {
        if (this.listaVeiculos.contains(veiculo)) {
            return false;
        }
        this.listaVeiculos.add(veiculo);
        return true;
    }

    /**
     * Remove um veículo da lista de veículos do cliente, se presente.
     * 
     * @param placa A placa do veículo a ser removido.
     * @return boolean
     */
    public boolean removeVeiculo(String placa) {
        for (Veiculo v : listaVeiculos) {
            if (v.getPlaca().equals(placa)) {
                listaVeiculos.remove(v);
                return true;
            }
        }
        return false;
    }

    /**
     * Retorna o objeto Veiculo correspondente à placa informada, se presente na
     * lista de veículos do cliente.
     * 
     * @param placa A placa do veículo procurado.
     * @return Veiculo ou null
     */
    public Veiculo getVeiculo(String placa) {
        for (Veiculo veiculo : listaVeiculos) {
            if (veiculo.getPlaca().equals(placa)) {
                return veiculo;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "{" +
                " nome='" + getNome() + "'" +
                ", endereco='" + getEndereco() + "'" +
                ", listaVeiculos='" + getListaVeiculos() + "'" +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Cliente)) {
            return false;
        }
        Cliente cliente = (Cliente) o;
        return Objects.equals(nome, cliente.getNome()) && Objects.equals(endereco, cliente.getEndereco())
                && Objects.equals(listaVeiculos, cliente.getListaVeiculos());
    }

    // métodos getters e setters

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome.trim();
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco.trim();
    }

    public List<Veiculo> getListaVeiculos() {
        return this.listaVeiculos;
    }

    public void setListaVeiculos(List<Veiculo> listaVeiculos) {
        this.listaVeiculos = listaVeiculos;
    }
}
