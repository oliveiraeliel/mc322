package menu;

import java.util.List;
import java.util.Map;

import entidades.Frota;
import entidades.Seguradora;
import entidades.Sinistro;
import entidades.Veiculo;
import entidades.Cliente.Cliente;
import entidades.Cliente.ClientePJ;
import entidades.Cliente.TipoCliente;
import execeptions.ClienteNaoEncontradoException;
import execeptions.FrotaNaoEncontradaException;
import execeptions.SeguradoraNaoEncontradaException;
import utils.InputUtils;

public enum MenuListar {
    LISTAR_CLIENTE_PF(1),
    LISTAR_CLIENTE_PJ(2),
    LISTAR_SINISTROS(3),
    LISTAR_SINISTROS_CLIENTE(4),
    LISTAR_VEICULO_CLIENTE(5),
    LISTAR_VEICULO_SEGURADORA(6),
    LISTAR_SEGURADORAS(7),
    VOLTAR(8);

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
        System.out.println("6- Listar Veículos Seguradora");
        System.out.println("7- Listar Seguradoras");
        System.out.println("8- Voltar");
        int operacao = InputUtils.lerInt();

        MenuListar o = getOperacao(operacao);
        if (o == null) {
            listar();
        } else if (handle(o)) {
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
            case LISTAR_VEICULO_SEGURADORA:
                listarVeiculoSeguradora();
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
        String nomeSeguradora = InputUtils.lerNome("Nome da seguradora: ");
        if (seguradoras.containsKey(nomeSeguradora)) {
            Seguradora seguradora = seguradoras.get(nomeSeguradora);
            List<Sinistro> sinistros = seguradora.listarSinistros();
            if (!sinistros.isEmpty()) {
                System.out.println(sinistros);
            } else {
                System.out.printf("Nenhum sinistro registrado na seguradora %s\n", nomeSeguradora);
            }
        } else {
            System.out.printf("A seguradora %s não existe\n", nomeSeguradora);
        }
    }

    private static void listarSinistrosCliente() {
        try {
            String cadastro = InputUtils.lerCadastro("Cadastro do cliente (CPF/CNPJ): ");
            Cliente cliente = BancoDados.getCliente(cadastro);
            System.out.println(cliente.getSinistros());
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
            String cnpj = InputUtils.lerCNPJ();
            ClientePJ cliente = (ClientePJ) BancoDados.getCliente(cnpj);
            System.out.println(cliente.getListaFrota());
        } catch (ClienteNaoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void listarVeiculosFrota() {
        try {
            String cnpj = InputUtils.lerCNPJ();
            ClientePJ cliente = (ClientePJ) BancoDados.getCliente(cnpj);
            String code = InputUtils.lerString("Insira o código da frota: ");
            Frota frota = cliente.buscarFrota(code);
            System.out.println(frota.getListaVeiculos());
        } catch (ClienteNaoEncontradoException e) {
            System.out.println(e.getMessage());
        } catch (FrotaNaoEncontradaException e){
            System.out.println(e.getMessage());
        }
    }

    private static void listarVeiculoSeguradora() {
        try {
            String cnpj = InputUtils.lerCNPJ("CNPJ da seguradora: ");
            Seguradora seguradora = BancoDados.getSeguradora(cnpj);
            System.out.println(seguradora.);
        } catch (SeguradoraNaoEncontradaException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void listarSeguradoras() {
        if (!seguradoras.isEmpty()) {
            System.out.println(seguradoras.keySet());
        } else {
            System.out.println("Nenhuma seguradora cadastrada.");
        }
    }

    public int getValue() {
        return value;
    }

    public static MenuListar getOperacao(int operacao) {
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
                return LISTAR_VEICULO_SEGURADORA;
            case 7:
                return LISTAR_SEGURADORAS;
            case 8:
                return VOLTAR;
            default:
                return null;
        }
    }
}
