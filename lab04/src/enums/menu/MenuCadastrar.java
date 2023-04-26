package enums.menu;

import java.util.Map;
import java.util.Scanner;

import entidades.Seguradora;
import entidades.Cliente.Cliente;
import factories.ClienteFactory;
import factories.SeguradoraFactory;
import factories.VeiculoFactory;
import utils.InputUtils;

public enum MenuCadastrar {
    CADASTRAR_CLIENTE_PF(1),
    CADASTRAR_CLIENTE_PJ(2),
    CADASTRAR_VEICULO(3),
    CADASTRAR_SEGURADORA(4),
    VOLTAR(5);

    private final int value;

    MenuCadastrar(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static void cadastrar(Map<String, Seguradora> seguradorasMap, Scanner scan) {
        System.out.println("1- Cadastrar Cliente PF");
        System.out.println("2- Cadastrar Cliente PJ");
        System.out.println("3- Cadastrar Veículo");
        System.out.println("4- Cadastrar Seguradora");
        System.out.println("5- Voltar");
        int operacao = scan.nextInt();
        scan.nextLine();

        if (handle(operacao, seguradorasMap, scan)) {
            cadastrar(seguradorasMap, scan);
        }
    }

    private static boolean handle(int operacao, Map<String, Seguradora> seguradorasMap, Scanner scan) {
        if (operacao == CADASTRAR_CLIENTE_PF.getValue()) {
            cadastrarCliente("PF", seguradorasMap, scan);
        } else if (operacao == CADASTRAR_CLIENTE_PJ.getValue()) {
            cadastrarCliente("PJ", seguradorasMap, scan);
        } else if (operacao == CADASTRAR_VEICULO.getValue()) {
            cadastrarVeiculo(seguradorasMap, scan);
        } else if (operacao == CADASTRAR_SEGURADORA.getValue()) {
            cadastrarSeguradora(seguradorasMap, scan);
        } else if (operacao == VOLTAR.getValue()) {
            return false;
        }
        return true;
    }

    private static void cadastrarCliente(String tipo, Map<String, Seguradora> seguradoras, Scanner scan) {
        String nomeSeguradora = scan.nextLine();
        if (seguradoras.containsKey(nomeSeguradora)) {
            Cliente cliente = (tipo.equals("PF") ? ClienteFactory.lerClientePF(scan)
                    : ClienteFactory.lerClientePJ(scan));
            Seguradora seguradora = seguradoras.get(nomeSeguradora);
            if (seguradora.cadastrarCliente(cliente)) {
                System.out.printf("Cliente %s cadastrado na seguradora %s com sucesso!\n", cliente.getNome(),
                        seguradora.getNome());
            } else {
                System.out.println("Cliente já cadastrado!");
            }
        } else {
            System.out.printf("A seguradora %s não existe", nomeSeguradora);
        }
    }

    private static void cadastrarVeiculo(Map<String, Seguradora> seguradoras, Scanner scan) {
        String cadastro = InputUtils.lerCadastro(scan);
        String nomeSeguradora = scan.nextLine();
        if (seguradoras.containsKey(nomeSeguradora)) {
            Seguradora seguradora = seguradoras.get(nomeSeguradora);
            Cliente cliente = seguradora.getClienteByCadastro(cadastro);
            if (cliente != null) {
                cliente.addVeiculo(VeiculoFactory.lerVeiculo(scan));
            } else {
                System.out.printf("Nenhum cliente com o cadastro %s encontrado na seguradora %s.", cadastro,
                        seguradora.getNome());
            }
        } else {
            System.out.printf("A seguradora %s não existe", nomeSeguradora);
        }
    }

    private static void cadastrarSeguradora(Map<String, Seguradora> seguradoras, Scanner scan) {
        Seguradora seguradora = SeguradoraFactory.lerSeguradora(scan);
        if (!seguradoras.containsKey(seguradora.getNome())) {
            seguradoras.put(seguradora.getNome(), seguradora);
            System.out.printf("Seguradora %s cadastrada com sucesso!\n", seguradora.getNome());
        } else {
            System.out.printf("Seguradora %s já foi cadastrada!\n", seguradora.getNome());
        }
    }

    public static MenuCadastrar getOperacao(int operacao) {
        if (operacao == 1) {
            return CADASTRAR_CLIENTE_PF;
        }
        if (operacao == 2) {
            return CADASTRAR_CLIENTE_PJ;
        }
        if (operacao == 3) {
            return CADASTRAR_VEICULO;
        }
        if (operacao == 4) {
            return CADASTRAR_SEGURADORA;
        }
        if (operacao == 5) {
            return VOLTAR;
        }
        return null;
    }
}
