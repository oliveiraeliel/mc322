package entidades.Cliente;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import entidades.Frota.Frota;
import entidades.Veiculo.Veiculo;
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
    public ArrayList<Veiculo> listarVeiculos() {
        ArrayList<Veiculo> veiculos = new ArrayList<>();
        for (Frota frota : listaFrota) {
            veiculos.addAll(frota.getListaVeiculos());
        }
        return veiculos;
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

    public int anosPosFundacao() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getDataFundacao());
        int yyyy = cal.get(Calendar.YEAR);

        cal.setTime(DateUtils.localDate());
        int yHoje = cal.get(Calendar.YEAR);
        return yHoje - yyyy;
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
            if (frota.getSeguro() != null){
                frota.atualizarSeguro();
            }
            return true;
        }
        return false;
    }

    public boolean atualizarFrota(Frota frota) {
        frota.setListaVeiculos(new ArrayList<Veiculo>());
        return true;
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
        if (listaFrota.remove(frota)) {
            frota.desassociarSeguro();
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

    public ArrayList<Veiculo> getVeiculosPorFrota(Frota frota) {
        return frota.getListaVeiculos();
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
    public String toString() {
        return "{" + super.toString() +
                ", cnpj='" + getCnpj() + "'" +
                ", dataFundacao='" + DateUtils.formatDate(getDataFundacao(), "dd/MM/yyyy") + "'" +
                ", quantidadeFunc='" + getQuantidadeFunc() + "'" +
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
        return clientePJ.getCnpj().equals(cnpj);
    }
}
