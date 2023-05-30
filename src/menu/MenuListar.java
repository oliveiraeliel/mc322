package menu;

import java.util.ArrayList;

import entidades.Cliente.Cliente;
import entidades.Cliente.ClientePJ;
import entidades.Cliente.TipoCliente;
import entidades.Seguradora.Seguradora;
import entidades.Sinistro.Sinistro;
import execeptions.*;
import utils.InputUtils;

public enum MenuListar {
    LISTAR_CLIENTE_PF(1),
    LISTAR_CLIENTE_PJ(2),
    LISTAR_SINISTROS(3),
    LISTAR_SINISTROS_CLIENTE(4),
    LISTAR_VEICULO_CLIENTE(5),
    LISTAR_FROTAS_CLIENTE(6),
    LISTAR_FROTAS_SEGURADORA(7),
    LISTAR_VEICULO_SEGURADORA(8),
    LISTAR_SEGURADORAS(9),
    VOLTAR(0);

    private final int value;

    MenuListar(int value) {
        this.value = value;
    }

    public static void listar() {
        System.out.println("------------- LISTAR ------------");
        System.out.println("1- Listar Clientes PF");
        System.out.println("2- Listar Clientes PJ");
        System.out.println("3- Listar Sinistros");
        System.out.println("4- Listar Sinistros do Cliente");
        System.out.println("5- Listar Veículos do Cliente");
        System.out.println("6- Listar Frotas do Cliente");
        System.out.println("7- Listar Frotas da Seguradora");
        System.out.println("8- Listar Veículos Seguradora");
        System.out.println("9- Listar Seguradoras");
        System.out.println("0- Voltar");
        int operacao = InputUtils.lerInt();

        try {
            MenuListar o = getOperacao(operacao);
            if (handle(o)) {
                listar();
            }
        } catch (ValorNaoEsperadoException e) {
            listar();
        }

    }

    private static boolean handle(MenuListar operacao) {
        switch (operacao) {
            case LISTAR_CLIENTE_PF:
                listarClientes(TipoCliente.PF);
                break;
            case LISTAR_CLIENTE_PJ:
                listarClientes(TipoCliente.PJ);
                break;
            case LISTAR_SINISTROS_CLIENTE:
                listarSinistrosCliente();
                break;
            case LISTAR_SINISTROS:
                listarSinistros();
                break;
            case LISTAR_VEICULO_CLIENTE:
                listarVeiculosCliente();
                break;
            case LISTAR_FROTAS_CLIENTE:
                listarFrotasCliente();
                break;
            case LISTAR_VEICULO_SEGURADORA:
                listarVeiculoSeguradora();
                break;
            case LISTAR_FROTAS_SEGURADORA:
                listarFrotasSeguradora();
                break;
            case LISTAR_SEGURADORAS:
                listarSeguradoras();
                break;
            case VOLTAR:
                return false;
        }
        return true;
    }

    private static void listarClientes(TipoCliente tipo) {
        try {
            String cnpj = InputUtils.lerCNPJ("CNPJ da seguradora: ");
            Seguradora seguradora = BancoDados.getSeguradora(cnpj);
            System.out.println(seguradora.listarClientes(tipo));
        } catch (SeguradoraNaoEncontradaException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void listarSinistros() {
        try {
            String cnpjSeguradora = InputUtils.lerCNPJ("Insira o cnpj da seguradora: ");
            Seguradora seguradora = BancoDados.getSeguradora(cnpjSeguradora);
            ArrayList<Sinistro> sinistros = seguradora.listarSinistros();
            if (!sinistros.isEmpty()) {
                System.out.println(sinistros);
            } else {
                System.out.println("A seguradora '" + seguradora.getNome() + "' não tem nenhum sinistro cadastrado.");
            }
        } catch (SeguradoraNaoEncontradaException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void listarSinistrosCliente() {
        try {
            String cnpjSeguradora = InputUtils.lerCNPJ("Insira o cnpj da seguradora: ");
            Seguradora seguradora = BancoDados.getSeguradora(cnpjSeguradora);
            String cadastro = InputUtils.lerCadastro("Insira o cadastro do cliente (cpf/cnpj): ");
            Cliente cliente = BancoDados.getCliente(cadastro);
            ArrayList<Sinistro> sinistros = seguradora.getSinistrosPorCliente(cliente);
            if (!sinistros.isEmpty()) {
                System.out.println(sinistros);
            } else {
                System.out.println("O cliente '" + cliente.getNome() + "' não possui nenhum sinistro.");
            }
        } catch (SeguradoraNaoEncontradaException e) {
            System.out.println(e.getMessage());
        } catch (ClienteNaoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void listarVeiculosCliente() {
        try {
            String cadastro = InputUtils.lerCadastro("Cadastro do cliente (CPF/CNPJ): ");
            Cliente cliente = BancoDados.getCliente(cadastro);
            System.out.println(cliente.listarVeiculos());
        } catch (ClienteNaoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void listarFrotasCliente() {
        try {
            String cnpj = InputUtils.lerCNPJ("Insira o cnpj do cliente: ");
            ClientePJ cliente = (ClientePJ) BancoDados.getCliente(cnpj);
            System.out.println(cliente.getListaFrota());
        } catch (ClienteNaoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void listarFrotasSeguradora() {
        try {
            String cnpj = InputUtils.lerCNPJ("CNPJ da seguradora: ");
            Seguradora seguradora = BancoDados.getSeguradora(cnpj);
            System.out.println(seguradora.listarFrotas());
        } catch (SeguradoraNaoEncontradaException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void listarVeiculoSeguradora() {
        try {
            String cnpj = InputUtils.lerCNPJ("CNPJ da seguradora: ");
            Seguradora seguradora = BancoDados.getSeguradora(cnpj);
            System.out.println(seguradora.listarVeiculos());
        } catch (SeguradoraNaoEncontradaException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void listarSeguradoras() {
        ArrayList<Seguradora> seguradoras = BancoDados.listarSeguradoras();
        if (!seguradoras.isEmpty()) {
            System.out.println(seguradoras);
        } else {
            System.out.println("Nenhuma seguradora cadastrada.");
        }
    }

    public int getValue() {
        return value;
    }

    public static MenuListar getOperacao(int operacao) throws ValorNaoEsperadoException {
        switch (operacao) {
            case 1:
                return LISTAR_CLIENTE_PF;
            case 2:
                return LISTAR_CLIENTE_PJ;
            case 3:
                return LISTAR_SINISTROS;
            case 4:
                return LISTAR_SINISTROS_CLIENTE;
            case 5:
                return LISTAR_VEICULO_CLIENTE;
            case 6:
                return LISTAR_FROTAS_CLIENTE;
            case 7:
                return LISTAR_FROTAS_SEGURADORA;
            case 8:
                return LISTAR_VEICULO_SEGURADORA;
            case 9:
                return LISTAR_SEGURADORAS;
            case 0:
                return VOLTAR;
            default:
                throw new ValorNaoEsperadoException();
        }
    }
}