package entidades.Cliente;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import entidades.Frota;
import entidades.Veiculo;
import execeptions.FrotaNaoEncontradaException;
import utils.*;

public class ClientePJ extends Cliente {
    private final String cnpj;
    private Date dataFundacao;
    private ArrayList<Frota> listaFrota = new ArrayList<Frota>();
    private int quantidadeFunc;

    public ClientePJ(String nome, String cnpj, String telefone, String email, String endereco, Date dataFundacao,
            int quantidadeFunc) {
        super(nome, telefone, endereco, email);
        cnpj = ValidatorUtils.formatarCNPJ(cnpj);
        this.cnpj = cnpj;
        setDataFundacao(dataFundacao);
        setQuantidadeFunc(quantidadeFunc);
    }

    @Override
    public int getQuantidadeVeiculos() {
        int n = 0;
        for (Frota frota : listaFrota) {
            n += frota.getListaVeiculos().size();
        }
        return n;
    }

    @Override
    public String getCadastro() {
        return getCnpj();
    }

    public boolean cadastrarFrota(Frota frota) {
        if (!listaFrota.contains(frota)) {
            listaFrota.add(frota);
            return true;
        }
        return false;
    }

    public Frota buscarFrota(String code) throws FrotaNaoEncontradaException {
        for (Frota frota : listaFrota) {
            if (frota.getCode().equals(code)) {
                return frota;
            }
        }
        throw new FrotaNaoEncontradaException("O código " + code + " não corresponde à nenhuma frota");
    }

    public boolean atualizarFrota(ArrayList<Veiculo> veiculos, String code) {
        try {
            Frota frota = buscarFrota(code);
            frota.setListaVeiculos(veiculos);
            return true;
        } catch (FrotaNaoEncontradaException e) {
            return false;
        }
    }

    public boolean atualizarFrota(ArrayList<Veiculo> veiculos, Frota frota) {
        if (listaFrota.contains(frota)) {
            frota.setListaVeiculos(veiculos);
            return true;
        }
        return false;
    }

    public boolean removeFrota(String code) {
        try {
            Frota frota = buscarFrota(code);
            return removeFrota(frota);
        } catch (FrotaNaoEncontradaException e) {
            return false;
        }
    }

    public boolean removeFrota(Frota frota) {
        if (listaFrota.remove(frota)){
            frota.destruirFrota();
            return true;
        }
        return false;
    }

    public boolean removeVeiculo(Frota frota, Veiculo veiculo) {
        if (listaFrota.contains(frota)) {
            return frota.removeVeiculo(veiculo);
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

    public int getQuantidadeFunc() {
        return this.quantidadeFunc;
    }

    public void setQuantidadeFunc(int quantidadeFunc) {
        this.quantidadeFunc = quantidadeFunc;
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
