package entidades.Cliente;

import java.util.Date;

public class ClientePJ extends Cliente{
    private final String CNPJ;
    private Date dataFundacao;

    public ClientePJ(String nome, String endereco, Date dataLicenca, String CNPJ, Date dataFundacao) {
        super(nome, endereco, dataLicenca);
        this.CNPJ = CNPJ;
        this.dataFundacao = dataFundacao;
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
}
