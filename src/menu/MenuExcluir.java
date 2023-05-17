package menu;

import java.util.Map;

import entidades.Seguradora;
import utils.InputUtils;

public enum MenuExcluir {
    EXCLUIR_CLIENTE(1),
    EXCLUIR_VEICULO(2),
    EXCLUIR_SINISTRO(3),
    VOLTAR(4);

    private final int value;

    MenuExcluir(int value) {
        this.value = value;
    }

    public static void excluir() {
        System.out.println("------------- EXCLUIR ------------");
        System.out.println("1- Excluir Cliente");
        System.out.println("2- Excluir Veículo");
        System.out.println("3- Excluir Sinistro");
        System.out.println("4- Voltar");
        int operacao = InputUtils.lerInt();

        MenuExcluir o = getOperacao(operacao);
        if (o == null) {
            excluir();
        } else if (handle(o)) {
            excluir();
        }
    }

    private static boolean handle(MenuExcluir operacao) {
        switch (operacao) {
            case EXCLUIR_CLIENTE:
                excluirCliente();
                break;
            case EXCLUIR_SINISTRO:
                excluirSinistro();
                break;
            case EXCLUIR_VEICULO:
                excluirVeiculo();
                break;
            case VOLTAR:
                return false;
        }
        return true;
    }

    private static void excluirCliente() {
        String nomeSeguradora = InputUtils.lerNome("Nome da seguradora: ");
        if (seguradoras.containsKey(nomeSeguradora)) {
            Seguradora seguradora = seguradoras.get(nomeSeguradora);
            String cadastro = InputUtils.lerCadastro();
            if (seguradora.removerCliente(cadastro)) {
                System.out.println("Cliente removido com sucesso!");
            } else {
                System.out.println("Cliente não encontrado.");
            }
        } else {
            System.out.printf("A seguradora %s não existe\n", nomeSeguradora);
        }
    }

    private static void excluirSinistro() {
        String nomeSeguradora = InputUtils.lerNome("Nome da seguradora: ");
        if (seguradoras.containsKey(nomeSeguradora)) {
            Seguradora seguradora = seguradoras.get(nomeSeguradora);
            int sinistroID = InputUtils.lerInt("ID do sinistro: ");
            if (seguradora.removerSinistro(sinistroID)) {
                System.out.println("Sinistro removido com sucesso.");
            } else {
                System.out.printf("Sinistro #%d não encontrado.\n", sinistroID);
            }
        } else {
            System.out.printf("A seguradora %s não existe\n", nomeSeguradora);
        }
    }

    private static void excluirVeiculo() {
        String nomeSeguradora = InputUtils.lerNome("Nome da seguradora: ");
        if (seguradoras.containsKey(nomeSeguradora)) {
            Seguradora seguradora = seguradoras.get(nomeSeguradora);
            String cadastro = InputUtils.lerCadastro();
            String placa = InputUtils.lerString("Placa: ");
            if (seguradora.removerVeiculo(cadastro, placa)) {
                System.out.println("Veículo removido.");
            } else {
                System.out.println("Não foi possível remover o veículo.");
            }
        } else {
            System.out.printf("A seguradora %s não existe\n", nomeSeguradora);
        }
    }

    public int getValue() {
        return value;
    }

    public static MenuExcluir getOperacao(int operacao) {
        switch (operacao) {
            case 1:
                return EXCLUIR_CLIENTE;
            case 2:
                return EXCLUIR_VEICULO;
            case 3:
                return EXCLUIR_SINISTRO;
            case 4:
                return VOLTAR;
            default:
                return null;
        }
    }
}
