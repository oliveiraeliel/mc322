package menu;

import java.util.HashMap;
import java.util.Map;

import entidades.Seguradora;
import entidades.Cliente.Condutor;
import entidades.Seguro.Seguro;
import execeptions.CondutorNaoEncontradoException;
import execeptions.SeguradoraNaoEncontradaException;
import execeptions.SeguroNaoEncontradoException;

// não, isso não é um banco de dados
public class BancoDados {
    private static Map<Integer, Seguro> seguros = new HashMap<>();
    private static Map<String, Condutor> condutores = new HashMap<>();
    private static Map<String, Seguradora> seguradoras = new HashMap<>();

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

    public static boolean adicionarSeguro(Integer id, Seguro seguro) {
        if (!seguros.containsKey(id)) {
            seguros.put(id, seguro);
            return true;
        }
        return false;
    }

    public static boolean adicinarCondutor(String cpf, Condutor condutor) {
        if (!condutores.containsKey(cpf)) {
            condutores.put(cpf, condutor);
            return true;
        }
        return false;
    }

    public static boolean adicionarSeguradora(String cnpj, Seguradora seguradora) {
        if (!seguradoras.containsKey(cnpj)) {
            seguradoras.put(cnpj, seguradora);
            return true;
        }
        return false;
    }

    public static Condutor getCondutor(Map<String, Condutor> condutores, String cpf)
            throws CondutorNaoEncontradoException {
        Condutor condutor = condutores.get(cpf);
        if (condutor != null) {
            return condutor;
        }
        throw new CondutorNaoEncontradoException("O CPF " + cpf + " não corresponde à nenhum condutor.");
    }

    public static Seguro getSeguro(Map<Integer, Seguro> seguros, Integer id)
            throws SeguroNaoEncontradoException {
        Seguro seguro = seguros.get(id);
        if (seguro != null) {
            return seguro;
        }
        throw new SeguroNaoEncontradoException("O #id " + id + " não corresponde à nenhum seguro.");
    }

    public static Seguradora getSeguradora(Map<String, Seguradora> seguradoras, String cnpj)
            throws SeguradoraNaoEncontradaException {
        Seguradora seguradora = seguradoras.get(cnpj);
        if (seguradora != null) {
            return seguradora;
        }
        throw new SeguradoraNaoEncontradaException("O CNPJ " + cnpj + " não corresponde à nenhuma seguradora.");
    }

    public static boolean excluirSeguro(Integer id){
        return seguros.remove(id, seguros.get(id));
    }
}
