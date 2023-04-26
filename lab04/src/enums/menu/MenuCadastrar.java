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
    CADASTRAR_SEGURADORA(4);

    private final int value;
    private final static Scanner scan = new Scanner(System.in);

    MenuCadastrar(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static void handle(MenuCadastrar operacao, Map<String, Seguradora> seguradorasMap) {
        if (operacao == CADASTRAR_CLIENTE_PF || operacao == CADASTRAR_CLIENTE_PJ) {
            String cnpjSeguradora = lerCnpjSeguradora();
            if (seguradorasMap.containsKey(cnpjSeguradora)) {
                Cliente cliente = operacao == CADASTRAR_CLIENTE_PF ? ClienteFactory.lerClientePF(scan)
                        : ClienteFactory.lerClientePJ(scan);
                Seguradora seguradora = seguradorasMap.get(cnpjSeguradora);
                if (seguradora.cadastrarCliente(cliente)) {
                    System.out.printf("Cliente %s cadastrado na seguradora %s com sucesso!\n", cliente.getNome(),
                            seguradora.getNome());
                } else {
                    System.out.println("Cliente já cadastrado!");
                }
                return;
            }
            System.out.println("Não encontramos seguradora com esse CNPJ!");
            return;
        }
        if (operacao == CADASTRAR_VEICULO) {
            String cadastro = lerCadastro();
            String cnpjSeguradora = lerCnpjSeguradora();
            if (seguradorasMap.containsKey(cnpjSeguradora)) {
                Seguradora seguradora = seguradorasMap.get(cnpjSeguradora);
                Cliente cliente = seguradora.getClienteByCadastro(cadastro);
                if (cliente != null) {
                    cliente.addVeiculo(VeiculoFactory.lerVeiculo(scan));
                    return;
                }
                System.out.printf("Nenhum cliente com o cadastro %s encontrado na seguradora %s.", cadastro,
                        seguradora.getNome());
                return;
            }
            System.out.println("Não encontramos seguradora com esse CNPJ!");
            return;
        }
        if (operacao == CADASTRAR_SEGURADORA) {
            Seguradora seguradora = SeguradoraFactory.lerSeguradora(scan);
            if (!seguradorasMap.containsKey(seguradora.getCNPJ())) {
                seguradorasMap.put(seguradora.getCNPJ(), seguradora);
                System.out.printf("Seguradora %s cadastrada com sucesso!\n", seguradora.getNome());
                return;
            }
            System.out.printf("Seguradora %s já foi cadastrada!\n", seguradora.getNome());
            return;
        }
    }

    private static String lerCnpjSeguradora() {
        System.out.print("Insira o cnpj da seguradora: ");
        String cnpj = scan.nextLine();
        if (!Validacao.validaCNPJ(cnpj)) {
            System.out.println("Insira um cnpj válido!");
            return lerCnpjSeguradora();
        }
        return cnpj;
    }

    private static String lerCadastro() {
        System.out.print("Insira o cadastro do cliente: ");
        String cadastro = scan.nextLine();
        if (!Validacao.validaCNPJ(cadastro) && !Validacao.validaCPF(cadastro)) {
            System.out.println("Insira um cpf/cnpj válido!");
            return lerCadastro();
        }
        return Validacao.validaCNPJ(cadastro) ? ValidatorUtils.formatarCNPJ(cadastro)
                : ValidatorUtils.formatarCPF(cadastro);
    }
}
