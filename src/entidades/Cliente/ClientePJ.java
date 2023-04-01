package entidades.Cliente;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import entidades.Veiculo;
import entidades.Cliente.validators.CNPJvalidator;

public class ClientePJ extends Cliente {
    private final String CNPJ;
    private Date dataFundacao;

    public ClientePJ(String nome, String endereco, Date dataLicenca, String educacao, String genero,
            String classeEconomica, String cnpj, Date dataFundacao) {
        super(nome, endereco, dataLicenca, educacao, genero, classeEconomica);
        this.CNPJ = cnpj;
        this.dataFundacao = dataFundacao;
    }

    public ClientePJ(String nome, String endereco, Date dataLicenca, String educacao, String genero,
            String classeEconomica, List<Veiculo> listaVeiculos, String cnpj, Date datafuncadao) {
        super(nome, endereco, dataLicenca, educacao, genero, classeEconomica, listaVeiculos);
        this.CNPJ = cnpj;
        this.dataFundacao = datafuncadao;
    }

    @Override
    public String toString() {
        return super.toString() + "\nCNPJ: " + CNPJ + "\nData de Fundacao: "
                + new SimpleDateFormat("dd/MM/yyyy").format(dataFundacao);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ClientePJ)) {
            return false;
        }

        ClientePJ c = (ClientePJ) obj;
        return c.getCNPJ().equals(getCNPJ());
    }

    public String getCNPJ() {
        return this.CNPJ;
    }

    public Date getDataFundacao() {
        return this.dataFundacao;
    }

    public void setDataFundacao(Date dataFundacao) {
        this.dataFundacao = dataFundacao;
    }

    public static boolean validarCNPJ(String cpf) {
        return cpf.length() == 14 && !CNPJvalidator.todosCharsIguais(cpf) && CNPJvalidator.digitosValidos(cpf);
    }
}
