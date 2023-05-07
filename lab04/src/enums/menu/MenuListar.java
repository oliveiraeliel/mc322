package enums.menu;

import java.util.List;
import java.util.Map;

import entidades.Seguradora;
import entidades.Sinistro;
import entidades.Veiculo;
import entidades.Cliente.Cliente;
import utils.InputUtils;

public enum MenuListar {
    LISTAR_CLIENTE_PF(1),
    LISTAR_CLIENTE_PJ(2),
    LISTAR_SINISTROS(3),
    LISTAR_SINISTROS_CLIENTE(4),
    LISTAR_VEICULO_CLIENTE(5),
    LISTAR_VEICULO_SEGURADORA(6),
    VOLTAR(7);

    private final int value;
    
    MenuListar(int value) {
        this.value = value;
    }

    public static void listar(Map<String, Seguradora> seguradoras) {
        System.out.println("1- Listar Clientes PF");
        System.out.println("2- Listar Clientes PJ");
        System.out.println("3- Listar Sinistros");
        System.out.println("4- Listar Sinistros do Cliente");
        System.out.println("5- Listar Veículos do Cliente");
        System.out.println("6- Listar Veículos Seguradora");
        System.out.println("7- Voltar");
        int operacao = InputUtils.lerInt();

        MenuListar o = getOperacao(operacao);
        if (o == null) {
            listar(seguradoras);
        } else if (handle(o, seguradoras)) {
            listar(seguradoras);
        }
    }

    private static boolean handle(MenuListar operacao, Map<String, Seguradora> seguradoras) {
        switch (operacao) {
            case LISTAR_CLIENTE_PF:
                listarClientes("PF", seguradoras);
                break;
            case LISTAR_CLIENTE_PJ:
                listarClientes("PJ", seguradoras);
                break;
            case LISTAR_SINISTROS_CLIENTE:
                listarSinistrosCliente(seguradoras);
                break;
            case LISTAR_SINISTROS:
                listarSinistros(seguradoras);
                break;
            case LISTAR_VEICULO_CLIENTE:
                listarVeiculosCliente(seguradoras);
                break;
            case LISTAR_VEICULO_SEGURADORA:
                listarVeiculoSeguradora(seguradoras);
                break;
            case VOLTAR:
                return false;
        }
        return true;
    }

    private static void listarClientes(String tipo, Map<String, Seguradora> seguradoras) {
        String nomeSeguradora = InputUtils.lerNome("Nome da seguradora: ");
        if (seguradoras.containsKey(nomeSeguradora)) {
            Seguradora seguradora = seguradoras.get(nomeSeguradora);
            List<Cliente> clientes = seguradora.listarClientes(tipo);
            if (clientes.isEmpty()) {
                System.out.println("Nenhum cliente encontrado.");
            } else {
                System.out.println(clientes);
            }
        } else {
            System.out.printf("A seguradora %s não existe\n", nomeSeguradora);
        }
    }

    private static void listarSinistros(Map<String, Seguradora> seguradoras) {
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
            System.out.printf("A seguradora %s não existe", nomeSeguradora);
        }
    }

    private static void listarSinistrosCliente(Map<String, Seguradora> seguradoras) {
        String nomeSeguradora = InputUtils.lerNome("Nome da seguradora: ");
        if (seguradoras.containsKey(nomeSeguradora)) {
            Seguradora seguradora = seguradoras.get(nomeSeguradora);
            String cadastro = InputUtils.lerCadastro();
            if (!seguradora.visualizarSinistro(cadastro)) {
                System.out.printf("Nenhum sinistro encontrado relacionado com o cadastro %s\n", cadastro);
            }
        } else {
            System.out.printf("A seguradora %s não existe", nomeSeguradora);
        }
    }

    private static void listarVeiculosCliente(Map<String, Seguradora> seguradoras) {
        String nomeSeguradora = InputUtils.lerNome("Nome da seguradora: ");
        if (seguradoras.containsKey(nomeSeguradora)) {
            Seguradora seguradora = seguradoras.get(nomeSeguradora);
            String cadastro = InputUtils.lerCadastro();
            List<Veiculo> veiculos = seguradora.listarVeiculosCliente(cadastro);
            if (veiculos == null) {
                System.out.printf("Nenhum cliente com o cadastro %s foi encontrado na seguradora %s\n", cadastro,
                        nomeSeguradora);
            } else if (!veiculos.isEmpty()) {
                System.out.println(veiculos);
            } else {
                System.out.println("Nenhum veículo encontrado");
            }
        } else {
            System.out.printf("A seguradora %s não existe", nomeSeguradora);
        }
    }

    private static void listarVeiculoSeguradora(Map<String, Seguradora> seguradoras) {
        String nomeSeguradora = InputUtils.lerNome("Nome da seguradora: ");
        if (seguradoras.containsKey(nomeSeguradora)) {
            Seguradora seguradora = seguradoras.get(nomeSeguradora);
            List<Veiculo> veiculos = seguradora.listarVeiculos();
            if (!veiculos.isEmpty()) {
                System.out.println(veiculos);
            } else {
                System.out.printf("Nenhum veículo relacionado com a seguradora %s encontrado\n", nomeSeguradora);
            }
        } else {
            System.out.printf("A seguradora %s não existe", nomeSeguradora);
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
                return VOLTAR;
            default:
                return null;
        }
    }
}
