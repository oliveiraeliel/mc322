package entidades.Cliente;

import java.util.*;

import entidades.Veiculo;
import entidades.Cliente.validators.CPFvalidator;

public class ClientePF extends Cliente {
    private final String CPF;
    private Date dataNascimento = new Date();

    public ClientePF(String nome, String endereco, Date dataLicenca, String educacao, String genero,
            String classeEconomica, String cpf, Date dataNascimento) {
        super(nome, endereco, dataLicenca, educacao, genero, classeEconomica);
        cpf = CPFvalidator.formatarCpf(cpf);
        this.CPF = cpf;
        this.dataNascimento = dataNascimento;
    }

    public ClientePF(String nome, String endereco, Date dataLicenca, String educacao, String genero,
            String classeEconomica, List<Veiculo> listaVeiculos, String cpf, Date dataNascimento) {
        super(nome, endereco, dataLicenca, educacao, genero, classeEconomica, listaVeiculos);
        cpf = CPFvalidator.formatarCpf(cpf);
        this.CPF = cpf;
        this.dataNascimento = dataNascimento;
    }

    public static ClientePF lerCliente() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Nome: ");
        String nome = scan.nextLine();
        System.out.print("Endereço: ");
        String endereco = scan.nextLine();
        System.out.print("Dia da licenca: ");
        int diaLicenca = scan.nextInt();
        System.out.print("Mês da licenca: ");
        int mesLicenca = scan.nextInt();
        System.out.print("Ano da licenca: ");
        int anoLicenca = scan.nextInt();
        System.out.print("Escolaridade: ");
        String educacao = scan.next();
        System.out.print("Gênero: ");
        String genero = scan.next();
        System.out.print("Classe econômica: ");
        String classeEconomica = scan.next();
        System.out.print("CPF: ");
        String cpf = scan.next();
        System.out.print("Dia do nascimento: ");
        int diaNascimento = scan.nextInt();
        System.out.print("Mês do nascimento: ");
        int mesNascimento = scan.nextInt();
        System.out.print("Ano do nascimento: ");
        int anoNascimento = scan.nextInt();
        scan.close();

        Calendar dtLicenca = Calendar.getInstance();
        dtLicenca.set(diaLicenca, mesLicenca, anoLicenca);

        Calendar dtNascimento = Calendar.getInstance();
        dtNascimento.set(diaNascimento, mesNascimento, anoNascimento);

        return new ClientePF(nome, endereco, dtLicenca.getTime(), educacao, genero, classeEconomica, cpf,
                dtNascimento.getTime());
    }

    @Override
    public String toString() {
        return super.toString() + "\nCPF: " + CPF + "\nData de nascimento: " + dataNascimento+"\n}";
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ClientePF)) {
            return false;
        }

        ClientePF c = (ClientePF) obj;
        return c.getCPF().equals(getCPF());
    }

    public String getCPF() {
        return this.CPF;
    }

    public Date getDataNascimento() {
        return this.dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    // verifica se a String recebida é um cpf válido
    public static boolean validarCPF(String cpf) {
        return cpf.length() == 11 && !CPFvalidator.todosCharsIguais(cpf) && CPFvalidator.digitosValidos(cpf);
    }
}
