package entidades;

public class Seguradora {
    private String nome;
    private String telefone;
    private String email;
    private String endereco;

    public Seguradora() {
    }

    public Seguradora(String nome, String telefone, String email, String endereco) {
        this.setNome(nome);
        this.setTelefone(telefone);
        this.setEmail(email);
        this.setEndereco(endereco);
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
