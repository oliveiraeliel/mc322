package enums;

import java.util.Map;
import java.util.Scanner;

import entidades.Seguradora;
import enums.menu.MenuCadastrar;
import enums.menu.MenuExcluir;
import enums.menu.MenuListar;

public enum MenuOperacoes {
    CADASTRAR(1),
    LISTAR(2),
    EXCLUIR(3),
    GERAR_SINISTRO(4),
    TRANSFERIR_SEGURO(5),
    CALCULAR_RECEITA_SEGURADORA(6),
    SAIR(0);

    private final int value;
    private static final Scanner scan = new Scanner(System.in);

    MenuOperacoes(int value) {
        this.value = value;
    }

    public static void menu(Map<String, Seguradora> seguradoras) {
        System.out.println("1- Cadastros");
        System.out.println("2- Listar");
        System.out.println("3- Excluir");
        System.out.println("4- Gerar Sinistro");
        System.out.println("5- Transferir Seguro");
        System.out.println("6- Calcular Receita Seguradora");
        System.out.println("0- Sair");
        int operacao = scan.nextInt();
        scan.nextLine();

        if (handle(getOperacao(operacao), seguradoras)) {
            menu(seguradoras);
        }
    }

    private static boolean handle(MenuOperacoes operacao, Map<String, Seguradora> seguradoras) {
        switch (operacao) {
            case CADASTRAR:
                MenuCadastrar.cadastrar(seguradoras);
                break;
            case LISTAR:
                MenuListar.listar(seguradoras);
                break;
            case EXCLUIR:
                MenuExcluir.excluir(seguradoras);
                break;
            case GERAR_SINISTRO:
                gerarSinistro(seguradoras);
                break;
            case TRANSFERIR_SEGURO:
                transferirSeguro(seguradoras);
                break;
            case CALCULAR_RECEITA_SEGURADORA:
                calcularReceitaSeguradora(seguradoras);
                break;
            case SAIR:
                return false;
            default:
                break;
        }
        return true;
    }

    private static void gerarSinistro(Map<String, Seguradora> seguradoras) {
        // todo
    }

    private static void transferirSeguro(Map<String, Seguradora> seguradoras) {
        // todo
    }

    private static void calcularReceitaSeguradora(Map<String, Seguradora> seguradoras) {
        // todo
    }

    public int getValue() {
        return value;
    }

    public static MenuOperacoes getOperacao(int operacao) {
        switch (operacao) {
            case 1:
                return CADASTRAR;
            case 2:
                return LISTAR;
            case 3:
                return EXCLUIR;
            case 4:
                return GERAR_SINISTRO;
            case 5:
                return TRANSFERIR_SEGURO;
            case 6:
                return CALCULAR_RECEITA_SEGURADORA;
            case 0:
                return SAIR;
            default:
                return null;
        }
    }
}
