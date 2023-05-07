package enums.menu;

import java.util.Map;
import entidades.Seguradora;
import entidades.Veiculo;
import entidades.Cliente.Cliente;
import utils.InputUtils;

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

    public static void menu(Map<String, Seguradora> seguradoras) {
        System.out.println("1- Cadastros");
        System.out.println("2- Listar");
        System.out.println("3- Excluir");
        System.out.println("4- Gerar Sinistro");
        System.out.println("5- Transferir Seguro");
        System.out.println("6- Calcular Receita Seguradora");
        System.out.println("0- Sair");
        int operacao = InputUtils.lerInt();

        MenuOperacoes o = getOperacao(operacao);
        if (o == null) {
            menu(seguradoras);
        } else if (handle(o, seguradoras)) {
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
        }
        return true;
    }

    private static void gerarSinistro(Map<String, Seguradora> seguradoras) {
        String nomeSeguradora = InputUtils.lerNome("Nome da seguradora: ");
        if (seguradoras.containsKey(nomeSeguradora)) {
            Seguradora seguradora = seguradoras.get(nomeSeguradora);
            String placa = InputUtils.lerString("Placa: ");
            String cadastro = InputUtils.lerCadastro();
            Cliente cliente = seguradora.getClienteByCadastro(cadastro);
            if (cliente != null) {
                Veiculo veiculo = cliente.getVeiculo(placa);
                if (veiculo != null) {
                    String endereco = InputUtils.lerString("Endereço: ");
                    seguradora.gerarSinistro(cliente, veiculo, endereco);
                } else {
                    System.out.println("Veículo não encontrado.");
                }
            } else {
                System.out.println("Cliente não encontrado.");
            }
        } else {
            System.out.printf("A seguradora %s não existe\n", nomeSeguradora);
        }
    }

    private static void transferirSeguro(Map<String, Seguradora> seguradoras) {
        String nomeSeguradora1, nomeSeguradora2, cadastroDe, cadastroPara;
        Cliente de, para;
        nomeSeguradora1 = InputUtils.lerNome("Nome da seguradora: ");
        if (seguradoras.containsKey(nomeSeguradora1)) {
            Seguradora seguradora = seguradoras.get(nomeSeguradora1);
            cadastroDe = InputUtils.lerCadastro("Cadastro do cliente que irá transferir: ");
            de = seguradora.getClienteByCadastro(cadastroDe);
            if (de == null) {
                return;
            }
        } else {
            System.out.printf("A seguradora %s não existe\n", nomeSeguradora1);
            return;
        }
        nomeSeguradora2 = InputUtils.lerNome("Nome da seguradora: ");
        if (seguradoras.containsKey(nomeSeguradora2)) {
            Seguradora seguradora = seguradoras.get(nomeSeguradora2);
            cadastroPara = InputUtils.lerCadastro("Cadastro do cliente que irá receber: ");
            para = seguradora.getClienteByCadastro(cadastroPara);
            if (para == null) {
                return;
            }
        } else {
            System.out.printf("A seguradora %s não existe\n", nomeSeguradora1);
            return;
        }
        de.transferirSeguro(para);
        System.out.printf("Seguro do cliente %s foi transferido para o cliente %s.\n", de.getNome(), para.getNome());
    }

    private static void calcularReceitaSeguradora(Map<String, Seguradora> seguradoras) {
        String nomeSeguradora = InputUtils.lerNome("Nome da seguradora: ");
        if (seguradoras.containsKey(nomeSeguradora)) {
            Seguradora seguradora = seguradoras.get(nomeSeguradora);
            Double receita = seguradora.calcularReceita();
            System.out.printf("A receita da seguradora %s é de R$ %.2f\n", nomeSeguradora, receita);
        } else {
            System.out.printf("A seguradora %s não existe\n", nomeSeguradora);
        }
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
