package entidades.Seguro;

import java.util.Date;

import entidades.Frota;
import entidades.Seguradora;
import entidades.Cliente.ClientePJ;
import java.util.Objects;

public class SeguroPJ extends Seguro {
    private Frota frota;
    private ClientePJ cliente;

    public SeguroPJ(Date dataInicio, Date dataFim, Seguradora seguradora, Double valorMensal, Frota frota,
            ClientePJ cliente) {
        super(dataInicio, dataFim, seguradora, valorMensal);
        setFrota(frota);
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

    public Frota getFrota() {
        return this.frota;
    }

    public void setFrota(Frota frota) {
        this.frota = frota;
    }

    public ClientePJ getCliente() {
        return this.cliente;
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
                " frota='" + getFrota() + "'" +
                ", cliente='" + getCliente() + "'" +
                "}";
    }

}
