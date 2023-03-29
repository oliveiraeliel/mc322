package entidades.Cliente;

import java.util.Date;

public class Cliente {
    private String nome;
    private String dataNascimento;
    private int idade;
    private String endereco;
    private Date dataLicenca;

    public Cliente() {
    }

    public Cliente(String nome, String dataNascimento, int idade, String endereco) {
        setNome(nome);
        setDataNascimento(dataNascimento);
        setIdade(idade);
        setEndereco(endereco);
    }


    /**
     * Retorna uma String com os valores atuais do objeto
     */
    public String toString() {
        return String.format("Cliente: %s, CPF: %s, Data de Nascimento: %s, Idade: %d, Endereço: %s", nome, cpf, dataNascimento, idade, endereco);
    }

    // métodos getters e setters

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome.trim();
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento.trim();
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco.trim();
    }
}
