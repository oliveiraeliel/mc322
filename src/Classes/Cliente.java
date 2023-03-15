package Classes;

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
	
     private boolean validarCPF(String cpf){
         String cpfFormatado = cpf.replaceAll("[^0-9]", "");
		 return cpfFormatado.length() == 11 && !this.todosDigitosIguais(cpfFormatado) && this.digitosValidos(cpfFormatado);
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

     private boolean digitosValidos(String cpf){
		String digitos = "" + this.primeiroDigitoCPF(cpf) + this.segundoDigitoCPF(cpf);

		return digitos.equals("" + cpf.charAt(9) + cpf.charAt(10));
	 }
	 private int primeiroDigitoCPF(String cpf){
		int digito = 0;
		char[] digitos = cpf.toCharArray();
		for (int i = 0;i<9;i++){
			int d = digitos[i] - '0';
			digito += d*(10 - i);
		}
		int resto = digito%11;
		if (resto == 0 || resto == 1) {
			return 0;
		}
		return 11 - resto;
	 }

	 private int segundoDigitoCPF(String cpf){
		 int digito = 0;
		 char[] digitos = cpf.toCharArray();
		 for (int i = 0;i<10;i++){
			 int d = digitos[i] - '0';
			 digito += d*(11 - i);
		 }
		 int resto = digito%11;
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
		if (this.validarCPF(cpf)) {
			this.cpf = cpf;
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
