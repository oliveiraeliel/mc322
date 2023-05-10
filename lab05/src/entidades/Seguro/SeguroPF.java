package entidades.Seguro;

import java.util.Date;

import entidades.Seguradora;
import entidades.Veiculo;
import entidades.Cliente.ClientePF;

public class SeguroPF extends Seguro {
    private Veiculo veiculo;
    private ClientePF cliente;

    public SeguroPF(Date dataInicio, Date dataFim, Seguradora seguradora, Double valorMensal, Veiculo veiculo,
            ClientePF cliente) {
        super(dataInicio, dataFim, seguradora, valorMensal);
        setVeiculo(veiculo);
        setCliente(cliente);
    }

    @Override
    public Double calcularValor() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void desautorizarCondutor() {
        // TODO Auto-generated method stub

    }

    public Veiculo getVeiculo() {
        return this.veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public ClientePF getCliente() {
        return this.cliente;
    }

    public void setCliente(ClientePF cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "{" +
                super.toString() +
                " veiculo='" + getVeiculo() + "'" +
                ", cliente='" + getCliente() + "'" +
                "}";
    }
}
