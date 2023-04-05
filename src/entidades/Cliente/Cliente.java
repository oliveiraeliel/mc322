package entidades.Cliente;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import entidades.Veiculo;
import entidades.utils.DateUtils;

public class Cliente {
    private String nome;
    private String endereco;
    private Date dataLicenca;
    private String educacao;
    private String genero;
    private String classeEconomica;
    private List<Veiculo> listaVeiculos;

    public Cliente(String nome, String endereco, Date dataLicenca, List<Veiculo> listaVeiculos) {
        setNome(nome);
        setEndereco(endereco);
        setDataLicenca(dataLicenca);
        setListaVeiculos(listaVeiculos);
    }

    public Cliente(String nome, String endereco, Date dataLicenca) {
        setNome(nome);
        setEndereco(endereco);
        setDataLicenca(dataLicenca);
        setListaVeiculos(new ArrayList<Veiculo>());
    }

    public Cliente(String nome, String endereco, Date dataLicenca, String educacao, String genero,
            String classeEconomica) {
        setNome(nome);
        setEndereco(endereco);
        setDataLicenca(dataLicenca);
        setEducacao(educacao);
        setGenero(genero);
        setClasseEconomica(classeEconomica);
        setListaVeiculos(new ArrayList<Veiculo>());
    }

    public Cliente(String nome, String endereco, Date dataLicenca, String educacao, String genero,
            String classeEconomica, List<Veiculo> listaVeiculos) {
        setNome(nome);
        setEndereco(endereco);
        setDataLicenca(dataLicenca);
        setEducacao(educacao);
        setGenero(genero);
        setClasseEconomica(classeEconomica);
        setListaVeiculos(listaVeiculos);
    }

    public boolean addVeiculo(Veiculo veiculo) {
        if (this.listaVeiculos.contains(veiculo)) {
            return false;
        }
        this.listaVeiculos.add(veiculo);
        return true;
    }

    public boolean removeVeiculo(Veiculo veiculo) {
        if (!this.listaVeiculos.contains(veiculo)) {
            return false;
        }
        this.listaVeiculos.remove(veiculo);
        return true;
    }

    @Override
    public String toString() {
        return "{" +
                " nome='" + getNome() + "'" +
                ", endereco='" + getEndereco() + "'" +
                ", dataLicenca='" + DateUtils.formatDate(getDataLicenca(), "dd/MM/yyyy") + "'" +
                ", educacao='" + getEducacao() + "'" +
                ", genero='" + getGenero() + "'" +
                ", classeEconomica='" + getClasseEconomica() + "'" +
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
        return Objects.equals(nome, cliente.nome) && Objects.equals(endereco, cliente.endereco)
                && Objects.equals(dataLicenca, cliente.dataLicenca) && Objects.equals(educacao, cliente.educacao)
                && Objects.equals(genero, cliente.genero) && Objects.equals(classeEconomica, cliente.classeEconomica)
                && Objects.equals(listaVeiculos, cliente.listaVeiculos);
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

    public Date getDataLicenca() {
        return this.dataLicenca;
    }

    public void setDataLicenca(Date dataLicenca) {
        this.dataLicenca = dataLicenca;
    }

    public String getEducacao() {
        return this.educacao;
    }

    public void setEducacao(String educacao) {
        this.educacao = educacao;
    }

    public String getGenero() {
        return this.genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getClasseEconomica() {
        return this.classeEconomica;
    }

    public void setClasseEconomica(String classeEconomica) {
        this.classeEconomica = classeEconomica;
    }

    public List<Veiculo> getListaVeiculos() {
        return this.listaVeiculos;
    }

    public void setListaVeiculos(List<Veiculo> listaVeiculos) {
        this.listaVeiculos = listaVeiculos;
    }
}
