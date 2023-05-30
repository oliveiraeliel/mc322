package entidades.Seguro;

import java.util.Date;

import entidades.Cliente.CalcSeguro;
import entidades.Cliente.ClientePJ;
import entidades.Frota.Frota;
import entidades.Seguradora.Seguradora;

import java.util.Objects;

public class SeguroPJ extends Seguro {
    private Frota frota;
    private ClientePJ cliente;

    public SeguroPJ(Date dataInicio, Date dataFim, Seguradora seguradora, Frota frota,
            ClientePJ cliente) {
        super(dataInicio, dataFim, seguradora);
        setFrota(frota);
        setCliente(cliente);
        calculaValor();
    }

    @Override
    public Double calculaValor() {
        Double valor = (CalcSeguro.VALOR_BASE.getValor() * (10 + (double) cliente.getQuantidadeFunc() / 10)
                * (1 + 1 / (double) (cliente.getQuantidadeVeiculos() + 2))
                * (1 + 1 / (double) (cliente.anosPosFundacao() + 2))
                * (2 + (double) getQuantidadeSinistrosCliente(getCliente()) / 10)
                * (5 + (double) getQuantidadeSinistrosCondutores() / 10));
        Double valorAntigo = getValorMensal();
        setValorMensal(valor);
        getSeguradora().somarReceita(valor - valorAntigo);
        return valor;
    }

    @Override
    public ClientePJ getCliente() {
        return this.cliente;
    }

    public Frota getFrota() {
        return this.frota;
    }

    public void setFrota(Frota frota) {
        this.frota = frota;
    }

    public void setCliente(ClientePJ cliente) {
        this.cliente = cliente;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof SeguroPJ)) {
            return false;
        }
        SeguroPJ seguroPJ = (SeguroPJ) o;
        return Objects.equals(frota, seguroPJ.frota) && Objects.equals(cliente, seguroPJ.cliente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frota, cliente);
    }

    @Override
    public String toString() {
        return "{" +
                super.toString() +
                ", frota='" + getFrota() + "'" +
                ", cliente='" + getCliente() + "'" +
                "}";
    }

}
