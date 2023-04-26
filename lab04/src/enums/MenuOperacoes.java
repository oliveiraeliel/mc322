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

    MenuOperacoes(int value) {
        this.value = value;
    }

    public static void menu(Map<String, Seguradora> seguradoras, Scanner scan){
        System.out.println("1- Cadastros");
        System.out.println("2- Listar");
        System.out.println("3- Excluir");
        System.out.println("4- Gerar Sinistro");
        System.out.println("5- Transferir Seguro");
        System.out.println("6- Calcular Receita Seguradora");
        System.out.println("0- Sair");
        int operacao = scan.nextInt();
        scan.nextLine();

        if (handle(operacao, seguradoras, scan)){
            menu(seguradoras, scan);
        }

    }

    private static boolean handle(int operacao, Map<String, Seguradora> seguradoras, Scanner scan) {
        if (operacao == CADASTRAR.getValue()){
            MenuCadastrar.cadastrar(seguradoras, scan);
        }else if (operacao == LISTAR.getValue()){
            MenuListar.listar(seguradoras, scan);
        }else if (operacao == EXCLUIR.getValue()){
            MenuExcluir.excluir(seguradoras, scan);
        }else if (operacao == SAIR.getValue()){
            return false;
        }
        return true;
    }

    public int getValue() {
        return value;
    }
}
