package entidades.Cliente;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import entidades.Frota;
import utils.*;

public class ClientePJ extends Cliente {
    private final String cnpj;
    private Date dataFundacao;
    private ArrayList<Frota> listaFrota = new ArrayList<Frota>();

    public ClientePJ(String nome, String cnpj, String telefone, String email, String endereco, Date dataFundacao) {
        super(nome, telefone, endereco, email);
        cnpj = ValidatorUtils.formatarCNPJ(cnpj);
        this.cnpj = cnpj;
        setDataFundacao(dataFundacao);
    }

    public boolean cadastrarFrota(Frota frota) {
        if (!listaFrota.contains(frota)) {
            listaFrota.add(frota);
            return true;
        }
        return false;
    }

    public boolean getVeiculosPorFrota(Frota frota) {
        if (!frota.getListaVeiculos().isEmpty()) {
            System.out.println(frota.getListaVeiculos());
            return true;
        }
        return false;
    }

    @Override
    public String getCadastro() {
        return getCnpj();
    }

    public String getCnpj() {
        return this.cnpj;
    }

    public Date getDataFundacao() {
        return this.dataFundacao;
    }

    public void setDataFundacao(Date dataFundacao) {
        this.dataFundacao = dataFundacao;
    }

    public ArrayList<Frota> getListaFrota() {
        return this.listaFrota;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ClientePJ)) {
            return false;
        }
        ClientePJ clientePJ = (ClientePJ) o;
        return Objects.equals(cnpj, clientePJ.cnpj);
    }
}
