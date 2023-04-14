package entidades.Cliente;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import entidades.Veiculo;
import utils.*;

public class ClientePJ extends Cliente {
    private static Set<String> cnpjSet = new HashSet<>();
    private final String CNPJ;
    private Date dataFundacao;

    public ClientePJ(String nome, String endereco, String cnpj, Date dataFundacao) {
        super(nome, endereco);
        cnpj = ValidatorUtils.formatarCNPJ(cnpj);
        this.CNPJ = cnpj;
        setDataFundacao(dataFundacao);
        cnpjSet.add(cnpj);
    }

    public ClientePJ(String nome, String endereco, List<Veiculo> listaVeiculos, String cnpj, Date datafuncadao) {
        super(nome, endereco, listaVeiculos);
        cnpj = ValidatorUtils.formatarCNPJ(cnpj);
        this.CNPJ = cnpj;
        setDataFundacao(dataFundacao);
        cnpjSet.add(cnpj);
    }

    public static boolean cnpjCadastrado(String cnpj) {
        return cnpjSet.contains(cnpj);
    }

    public static boolean validarCNPJ(String cnpj) {
        return cnpj.length() == 14 && !ValidatorUtils.todosCharsIguais(cnpj) && ValidatorUtils.digitosCnpjValidos(cnpj);
    }

    @Override
    public String toString() {
        return "{" +
                " nome= '" + super.getNome() + "'" +
                ", CNPJ='" + getCNPJ() + "'" +
                ", endereco='" + super.getEndereco() + "'" +
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
}
