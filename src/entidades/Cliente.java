package entidades;

public class Cliente {
    private String nome;
    private String cpf;
    private String dataNascimento;
    private int idade;
    private String endereco;

    public Cliente(){}
    public Cliente(String nome, String cpf, String dataNascimento, int idade, String endereco) {
        this.nome = nome;
        this.setCpf(cpf);
        this.dataNascimento = dataNascimento;
        this.idade = idade;
        this.endereco = endereco;
    }

//    public Cliente() {
//    }

    private boolean validarCPF(String cpf) {
        return cpf.length() == 11 && !this.todosDigitosIguais(cpf)
                && this.digitosValidos(cpf);
    }

    private boolean todosDigitosIguais(String cpf) {
        char c = cpf.charAt(0);
        for (int i = 1; i < cpf.length(); i++) {
            if (c != cpf.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    private boolean digitosValidos(String cpf) {
        return cpf.charAt(9)-'0' == this.primeiroDigitoCPF(cpf) && cpf.charAt(10)-'0' == this.segundoDigitoCPF(cpf);
    }

    private int primeiroDigitoCPF(String cpf) {
        int digito = 0;
        int resto;
        for (int i = 0; i < 9; i++) {
            int d = cpf.charAt(i) - '0';
            digito += d * (10 - i);
        }
        resto = digito % 11;
        if (resto == 0 || resto == 1) {
            return 0;
        }
        return 11 - resto;
    }

    private String formatarCpf(String cpf){
        return cpf.replaceAll("[^0-9]", "");
    }
    private int segundoDigitoCPF(String cpf) {
        int digito = 0;
        int resto;
        for (int i = 0; i < 10; i++) {
            int d = cpf.charAt(i) - '0';
            digito += d * (11 - i);
        }
        resto = digito % 11;
        if (resto == 0 || resto == 1) {
            return 0;
        }
        return 11 - resto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        String _cpf = this.formatarCpf(cpf);
        if (this.validarCPF(_cpf)) {
            this.cpf = _cpf;
            return;
        }
        System.out.println("CPF inválido!");
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
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
        this.endereco = endereco;
    }
}
