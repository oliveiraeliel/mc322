package entidades.Cliente;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import entidades.Veiculo;
import utils.*;

public class ClientePJ extends Cliente {
    private final String CNPJ;
    private Date dataFundacao;
    private int qtdeFuncionarios;

    public ClientePJ(String nome, String endereco, String cnpj, Date dataFundacao, int qtdeFuncionarios) {
        super(nome, endereco);
        cnpj = ValidatorUtils.formatarCNPJ(cnpj);
        this.CNPJ = cnpj;
        setDataFundacao(dataFundacao);
        setQtdeFuncionarios(qtdeFuncionarios);
    }

    public ClientePJ(String nome, String endereco, List<Veiculo> listaVeiculos, String cnpj, Date datafuncadao,
            int qtdeFuncionarios) {
        super(nome, endereco, listaVeiculos);
        cnpj = ValidatorUtils.formatarCNPJ(cnpj);
        this.CNPJ = cnpj;
        setDataFundacao(dataFundacao);
        setQtdeFuncionarios(qtdeFuncionarios);
    }

    public String getCadastro() {
        return getCNPJ();
    }

    public Double calculaScore() {
        return CalcSeguro.VALOR_BASE.getValor() * (1 + (getQtdeFuncionarios() / 100)) * quantidadeCarros();
    }

    @Override
    public String toString() {
        return "{" +
                " nome= '" + super.getNome() + "'" +
                ", CNPJ='" + getCNPJ() + "'" +
                ", endereco='" + super.getEndereco() + "'" +
                ", dataFundacao='" + DateUtils.formatDate(getDataFundacao(), "dd/MM/yyyy") + "'" +
                ", qntFuncionarios='" + getQtdeFuncionarios() + "'" +
                ", valorSeguro='" + super.getValorSeguro() + "'" +
                ", score='" + calculaScore() + "'" +
                ", listaVeiculos='" + super.getListaVeiculos() + "'" +
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
        return Objects.equals(CNPJ, clientePJ.getCNPJ());
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

    public int getQtdeFuncionarios() {
        return this.qtdeFuncionarios;
    }

    public void setQtdeFuncionarios(int qtdeFuncionarios) {
        this.qtdeFuncionarios = qtdeFuncionarios;
    }
}
