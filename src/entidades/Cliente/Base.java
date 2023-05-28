package entidades.Cliente;

public abstract class Base {
    private String nome;
    private String telefone;
    private String endereco;
    private String email;

    public Base(String nome, String telefone, String endereco, String email) {
        setNome(nome);
        setTelefone(telefone);
        setEndereco(endereco);
        setEmail(email);
    }

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

    public String getEndereco() {
        return this.endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco.trim();
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email.trim();
    }

    @Override
    public String toString() {
        return " nome='" + getNome() + "'" +
                ", telefone='" + getTelefone() + "'" +
                ", endereco='" + getEndereco() + "'" +
                ", email='" + getEmail() + "'";
    }

}