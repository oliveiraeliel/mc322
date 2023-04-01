package entidades.Cliente;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import entidades.Veiculo;

public class Cliente {
    private String nome;
    private String endereco;
    private Date dataLicenca;
    private String educacao;
    private String genero;
    private String classeEconomica;
    private List<Veiculo> listaVeiculos;

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

    public Cliente(InputStream in) {
        Scanner scan = new Scanner(in);
        System.out.print("Nome: ");
        setNome(scan.nextLine());
        System.out.print("Endereço: ");
        setEndereco(scan.nextLine());
        System.out.print("Dia da licenca: ");
        int diaLicenca = scan.nextInt();
        System.out.print("Mês da licenca: ");
        int mesLicenca = scan.nextInt();
        System.out.print("Ano da licenca: ");
        int anoLicenca = scan.nextInt();
        System.out.print("Escolaridade: ");
        setEducacao(scan.next());
        System.out.print("Gênero: ");
        setGenero(scan.next());
        System.out.print("Classe econômica: ");
        setClasseEconomica(scan.next());
        scan.close();
        Calendar dtLicenca = Calendar.getInstance();
        dtLicenca.set(diaLicenca, mesLicenca, anoLicenca);
        setDataLicenca(dtLicenca.getTime());
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

    /**
     * Retorna uma String com os valores atuais do objeto
     */
    @Override
    public String toString() {
        return "{" +
                "\nCliente: " + nome +
                "\nEndereço: " + endereco +
                "\nData Licenca: " + dataLicenca +
                "\nEscolaridade: " + educacao +
                "\nGênero: " + genero +
                "\nClasse Econômica: " + classeEconomica +
                "\nVeículos: " + listaVeiculos;
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
