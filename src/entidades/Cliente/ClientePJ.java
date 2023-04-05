package entidades.Cliente;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import entidades.Veiculo;
import entidades.Cliente.validators.CNPJvalidator;
import entidades.utils.DateUtils;

public class ClientePJ extends Cliente {
    private final String CNPJ;
    private Date dataFundacao;

    public ClientePJ(String nome, String endereco, Date dataLicenca, String cnpj, Date dataFundacao) {
        super(nome, endereco, dataLicenca);
        this.CNPJ = cnpj;
        this.dataFundacao = dataFundacao;
    }

    public ClientePJ(String nome, String endereco, Date dataLicenca, List<Veiculo> listaVeiculos, String cnpj,
            Date datafuncadao) {
        super(nome, endereco, dataLicenca, listaVeiculos);
        this.CNPJ = cnpj;
        this.dataFundacao = datafuncadao;
    }

    @Override
    public String toString() {
        return "{" +
                " nome= '" + super.getNome() + "'" +
                ", CNPJ='" + getCNPJ() + "'" +
                ", endereco='" + super.getEndereco() + "'" +
                ", dataLicenca='" + DateUtils.formatDate(super.getDataLicenca(), "dd/MM/yyyy") + "'" +
                ", dataFundacao='" + DateUtils.formatDate(getDataFundacao(), "dd/MM/yyyy") + "'" +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ClientePJ)) {
            return false;
        }
        ClientePJ clientePJ = (ClientePJ) o;
        return Objects.equals(CNPJ, clientePJ.CNPJ) && Objects.equals(dataFundacao, clientePJ.dataFundacao);
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
