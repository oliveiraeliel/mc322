
public class Cliente {
	private String nome;
	private String cpf;
	private String dataNascimento;
	private int idade;
	private String endereco;
	
	public Cliente(String nome, String cpf, String dataNascimento, int idade, String endereco) {
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.idade = idade;
		this.endereco = endereco;
	}
	
    private boolean validarCPF(String cpf){
        cpf.replaceAll("[^0-9]", "");
        if (cpf.length() != 11 && this.todosDigitosIguais(cpf)){
            return false;
        }

    }

    private boolean todosDigitosIguais(String cpf){
        char[] cpfChars = cpf.toCharArray();
        char c = cpfChars[0];
        for (int i=1;i < cpf.length();i++){
            if (c != cpfChars[i]){
                return false;
            }
        }
        return true;
    }

    private String calcularDigitoVerificador(String cpf){

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
		this.cpf = cpf;
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
