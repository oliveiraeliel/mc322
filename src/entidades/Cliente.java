package entidades;

public class Cliente {
    private String nome;
    private String cpf;
    private String dataNascimento;
    private int idade;
    private String endereco;

    public Cliente() {
    }

    public Cliente(String nome, String cpf, String dataNascimento, int idade, String endereco) {
        this.setNome(nome);
        this.setCpf(cpf);
        this.setDataNascimento(dataNascimento);
        this.setIdade(idade);
        this.setEndereco(endereco);
    }

    // verifica se a String recebida é um cpf válido
    private boolean validarCPF(String cpf) {
        return cpf.length() == 11 && !this.todosCharsIguais(cpf) && this.digitosValidos(cpf);
    }

    // verifica se todos os caracteres de uma string são iguais
    private boolean todosCharsIguais(String string) {
        char c = string.charAt(0);
        for (int i = 1; i < string.length(); i++) {
            if (c != string.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    // verifica se os digitos verificadores do cpf são válidos
    private boolean digitosValidos(String cpf) {
        return cpf.charAt(9) - '0' == this.primeiroDigitoCPF(cpf) && cpf.charAt(10) - '0' == this.segundoDigitoCPF(cpf);
    }

    // cálcula o primeiro digito verificador do cpf
    private int primeiroDigitoCPF(String cpf) {
        int digito = 0, resto;
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

    // cálcula o segundo digito verificador do cpf
    private int segundoDigitoCPF(String cpf) {
        int digito = 0, resto;
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

    private String formatarCpf(String cpf) {
        // remove caracteres não númericos do cpf
        return cpf.replaceAll("[^0-9]", "");
    }

    /**
     *  Retorna uma String com os valores atuais do objeto
     */
    public String toString(){
        return String.format("Cliente: %s, CPF: %s, Data de Nascimento: %s, Idade: %d, Endereço: %s", nome, cpf, dataNascimento, idade, endereco);
    }

    // métodos getters e setters

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome.trim();
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
