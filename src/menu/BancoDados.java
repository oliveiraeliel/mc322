package menu;

import java.util.HashMap;
import java.util.Map;

import entidades.Frota;
import entidades.Seguradora;
import entidades.Cliente.Cliente;
import entidades.Cliente.Condutor;
import entidades.Seguro.Seguro;
import execeptions.*;

// não, isso não é um banco de dados
public class BancoDados {
    private static Map<Integer, Seguro> seguros = new HashMap<>();
    private static Map<String, Condutor> condutores = new HashMap<>();
    private static Map<String, Seguradora> seguradoras = new HashMap<>();
    private static Map<String, Cliente> clientes = new HashMap<>();
    
    public static boolean seguradoraExiste(String cnpj) {
        return seguradoras.containsKey(cnpj);
    }

    public static boolean seguradoraExiste(Seguradora seguradora) {
        return seguradoras.containsKey(seguradora.getCnpj());
    }

    public static boolean condutorExiste(String cpf) {
        return condutores.containsKey(cpf);
    }

    public static boolean condutorExiste(Condutor condutor) {
        return condutores.containsKey(condutor.getCpf());
    }

    public static boolean segurosExiste(Integer id) {
        return seguros.containsKey(id);
    }

    public static boolean segurosExiste(Seguro seguro) {
        return seguros.containsKey(seguro.getID());
    }

    public static boolean save(Seguro seguro) {
        if (!seguros.containsKey(seguro.getID())) {
            seguros.put(seguro.getID(), seguro);
            return true;
        }
        return false;
    }

    public static boolean save(Condutor condutor) {
        if (!condutores.containsKey(condutor.getCpf())) {
            condutores.put(condutor.getCpf(), condutor);
            return true;
        }
        return false;
    }

    public static boolean save(Seguradora seguradora) {
        if (!seguradoras.containsKey(seguradora.getCnpj())) {
            seguradoras.put(seguradora.getCnpj(), seguradora);
            return true;
        }
        return false;
    }

    public static boolean save(Cliente cliente) {
        if (!clientes.containsKey(cliente.getCadastro())) {
            clientes.put(cliente.getCadastro(), cliente);
            return true;
        }
        return false;
    }

    public static Cliente getCliente(String cadastro) throws ClienteNaoEncontradoException {
        Cliente cliente = clientes.get(cadastro);
        if (cliente != null) {
            return cliente;
        }
        throw new ClienteNaoEncontradoException("Cliente com o cadastro " + cadastro + " não foi encontrado.");
    }

    public static Condutor getCondutor(String cpf)
            throws CondutorNaoEncontradoException {
        Condutor condutor = condutores.get(cpf);
        if (condutor != null) {
            return condutor;
        }
        throw new CondutorNaoEncontradoException("O CPF " + cpf + " não corresponde à nenhum condutor.");
    }

    public static Seguro getSeguro(Integer id)
            throws SeguroNaoEncontradoException {
        Seguro seguro = seguros.get(id);
        if (seguro != null) {
            return seguro;
        }
        throw new SeguroNaoEncontradoException("O #id " + id + " não corresponde à nenhum seguro.");
    }

    public static Seguradora getSeguradora(String cnpj)
            throws SeguradoraNaoEncontradaException {
        Seguradora seguradora = seguradoras.get(cnpj);
        if (seguradora != null) {
            return seguradora;
        }
        throw new SeguradoraNaoEncontradaException("O CNPJ " + cnpj + " não corresponde à nenhuma seguradora.");
    }

    public static boolean excluirSeguro(Integer id) {
        return seguros.remove(id, seguros.get(id));
    }
}
