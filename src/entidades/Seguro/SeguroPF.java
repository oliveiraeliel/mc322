package entidades.Seguro;

import java.util.Date;

import entidades.Seguradora;
import entidades.Veiculo;
import entidades.Cliente.CalcSeguro;
import entidades.Cliente.ClientePF;

public class SeguroPF extends Seguro {
    private Veiculo veiculo;
    private ClientePF cliente;

    public SeguroPF(Date dataInicio, Date dataFim, Seguradora seguradora, Veiculo veiculo, ClientePF cliente) {
        super(dataInicio, dataFim, seguradora);
        setVeiculo(veiculo);
        setCliente(cliente);
        atualizarValorMensal();
    }

    @Override
    public Double calculaValor() {
        return (CalcSeguro.VALOR_BASE.getValor() * CalcSeguro.fatorIdade(cliente.getIdade())
                * (1 + 1 / (cliente.getQuantidadeVeiculos() + 2))
                * (2 + getQuantidadeSinistrosCliente(getCliente()) / 10)
                * (5 + getQuantidadeSinistrosCondutores() / 10));
    }

    @Override
    public ClientePF getCliente() {
        return this.cliente;
    }

    public Veiculo getVeiculo() {
        return this.veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public void setCliente(ClientePF cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "{" +
                super.toString() +
                ", veiculo='" + getVeiculo() + "'" +
                ", cliente='" + getCliente() + "'" +
                "}";
    }
}
