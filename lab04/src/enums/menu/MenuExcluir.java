package enums.menu;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

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

    public static void excluir(Map<String, Seguradora> seguradoras, Scanner scan) {
        System.out.println("1- Excluir Cliente");
        System.out.println("2- Excluir Veículo");
        System.out.println("3- Excluir Sinistro");
        System.out.println("4- Voltar");
        int operacao = scan.nextInt();
        scan.nextLine();

        if (handle(operacao, seguradoras, scan)) {
            excluir(seguradoras, scan);
        }
    }

    private static boolean handle(int operacao, Map<String, Seguradora> seguradoras, Scanner scan) {
        if (operacao == EXCLUIR_CLIENTE.getValue()) {
            String nomeSeguradora = InputUtils.lerNome("Nome da seguradora: ", scan);
            try {
                Seguradora seguradora = seguradoras.get(nomeSeguradora);
                String cadastro = InputUtils.lerCadastro(scan);
                if (seguradora.removerCliente(cadastro)) {
                    System.out.println("Cliente removido com sucesso!");
                } else {
                    System.out.println("Cliente não encontrado.");
                }
            } catch (Exception e) {
                System.out.printf("A seguradora %s não existe", nomeSeguradora);
            }
        } else if (operacao == EXCLUIR_SINISTRO.getValue()) {
            String nomeSeguradora = InputUtils.lerNome("Nome da seguradora: ", scan);
            try {
                Seguradora seguradora = seguradoras.get(nomeSeguradora);
                int sinistroID = scan.nextInt();
                scan.nextLine();
                if (seguradora.removerSinistro(sinistroID)) {
                    System.out.println("Sinistro removido com sucesso.");
                } else {
                    System.out.printf("Sinistro #%d não encontrado.\n", sinistroID);
                }
            } catch (InputMismatchException e) {
                System.out.println("Insira um número inteiro.");
            } catch (Exception e) {
                System.out.printf("A seguradora %s não existe", nomeSeguradora);
            }
        } else if (operacao == EXCLUIR_VEICULO.getValue()) {
            String nomeSeguradora = InputUtils.lerNome("Nome da seguradora: ", scan);
            try {
                Seguradora seguradora = seguradoras.get(nomeSeguradora);
                String cadastro = InputUtils.lerCadastro(scan);
                System.out.print("Placa: ");
                String placa = scan.nextLine();
                if (seguradora.removerVeiculo(cadastro, placa)) {
                    System.out.println("Veículo removido.");
                } else {
                    System.out.println("Não foi possível remover o veículo.");
                }
            } catch (Exception e) {
                System.out.printf("A seguradora %s não existe", nomeSeguradora);
            }
        }else if (operacao == VOLTAR.getValue()){
            return false;
        }
        return true;
    }

    public int getValue() {
        return value;
    }
}
