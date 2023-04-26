package enums.menu;

import java.util.Map;
import java.util.Scanner;

import entidades.Seguradora;
import entidades.Cliente.Cliente;
import factories.ClienteFactory;
import factories.SeguradoraFactory;
import factories.VeiculoFactory;
import utils.Validacao;
import utils.ValidatorUtils;

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
        if (operacao == CADASTRAR_CLIENTE_PF.getValue() || operacao == CADASTRAR_CLIENTE_PJ.getValue()) {
            String cnpjSeguradora = lerCnpjSeguradora(scan);
            if (seguradorasMap.containsKey(cnpjSeguradora)) {
                Cliente cliente = operacao == CADASTRAR_CLIENTE_PF.getValue() ? ClienteFactory.lerClientePF(scan)
                        : ClienteFactory.lerClientePJ(scan);
                Seguradora seguradora = seguradorasMap.get(cnpjSeguradora);
                if (seguradora.cadastrarCliente(cliente)) {
                    System.out.printf("Cliente %s cadastrado na seguradora %s com sucesso!\n", cliente.getNome(),
                            seguradora.getNome());
                } else {
                    System.out.println("Cliente já cadastrado!");
                }
            } else {
                System.out.println("Não encontramos seguradora com esse CNPJ!");
            }
        } else if (operacao == CADASTRAR_VEICULO.getValue()) {
            String cadastro = lerCadastro(scan);
            String cnpjSeguradora = lerCnpjSeguradora(scan);
            if (seguradorasMap.containsKey(cnpjSeguradora)) {
                Seguradora seguradora = seguradorasMap.get(cnpjSeguradora);
                Cliente cliente = seguradora.getClienteByCadastro(cadastro);
                if (cliente != null) {
                    cliente.addVeiculo(VeiculoFactory.lerVeiculo(scan));
                } else {
                    System.out.printf("Nenhum cliente com o cadastro %s encontrado na seguradora %s.", cadastro,
                            seguradora.getNome());
                }
            } else {
                System.out.println("Não encontramos seguradora com esse CNPJ!");
            }
        } else if (operacao == CADASTRAR_SEGURADORA.getValue()) {
            Seguradora seguradora = SeguradoraFactory.lerSeguradora(scan);
            if (!seguradorasMap.containsKey(seguradora.getCNPJ())) {
                seguradorasMap.put(seguradora.getCNPJ(), seguradora);
                System.out.printf("Seguradora %s cadastrada com sucesso!\n", seguradora.getNome());
            } else {
                System.out.printf("Seguradora %s já foi cadastrada!\n", seguradora.getNome());
            }
        } else if (operacao == VOLTAR.getValue()) {
            return false;
        }
        return true;
    }

    private static String lerCnpjSeguradora(Scanner scan) {
        System.out.print("Insira o cnpj da seguradora: ");
        String cnpj = scan.nextLine();
        if (!Validacao.validaCNPJ(cnpj)) {
            System.out.println("Insira um cnpj válido!");
            return lerCnpjSeguradora(scan);
        }
        return ValidatorUtils.formatarCNPJ(cnpj);
    }

    private static String lerCadastro(Scanner scan) {
        System.out.print("Insira o cadastro do cliente: ");
        String cadastro = scan.nextLine();
        if (!Validacao.validaCNPJ(cadastro) && !Validacao.validaCPF(cadastro)) {
            System.out.println("Insira um cpf/cnpj válido!");
            return lerCadastro(scan);
        }
        return Validacao.validaCNPJ(cadastro) ? ValidatorUtils.formatarCNPJ(cadastro)
                : ValidatorUtils.formatarCPF(cadastro);
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
