package entidades.Seguro;

import java.util.Date;

import entidades.Frota;
import entidades.Seguradora;
import entidades.Cliente.CalcSeguro;
import entidades.Cliente.ClientePJ;
import java.util.Objects;

public class SeguroPJ extends Seguro {
    private Frota frota;
    private ClientePJ cliente;

    public SeguroPJ(Date dataInicio, Date dataFim, Seguradora seguradora, Frota frota,
            ClientePJ cliente) {
        super(dataInicio, dataFim, seguradora);
        setFrota(frota);
        setCliente(cliente);
        atualizarValorMensal();
    }

    @Override
    public Double calculaValor() {
        return (CalcSeguro.VALOR_BASE.getValor() * (10 + cliente.getQuantidadeFunc() / 10)
                * (1 + 1 / (cliente.getQuantidadeVeiculos() + 2))
                * (1 + 1/(cliente.anosPosFundacao() + 2))
                * (2 + getQuantidadeSinistrosCliente(getCliente()) / 10)
                * (5 + getQuantidadeSinistrosCondutores() / 10));
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
