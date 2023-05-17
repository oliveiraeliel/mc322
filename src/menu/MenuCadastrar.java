package menu;

import java.util.Map;

import entidades.Seguradora;
import entidades.Cliente.Cliente;
import entidades.Cliente.ClientePF;
import entidades.Cliente.Condutor;
import entidades.Cliente.TipoCliente;
import entidades.Seguro.Seguro;
import execeptions.ValorNaoEsperadoException;
import factories.ClienteFactory;
import factories.SeguradoraFactory;
import factories.VeiculoFactory;
import utils.DateUtils;
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

    public static void cadastrar(Map<String, Seguradora> seguradoras,
            Map<Integer, Seguro> seguros,
            Map<String, Condutor> condutores) {
        System.out.println("------------ CADASTRAR -----------");
        System.out.println("1- Cadastrar Cliente PF");
        System.out.println("2- Cadastrar Cliente PJ");
        System.out.println("3- Cadastrar Veículo");
        System.out.println("4- Cadastrar Seguradora");
        System.out.println("5- Voltar");
        int operacao = InputUtils.lerInt();

        try {
            MenuCadastrar o = getOperacao(operacao);
            if (handle(o, seguradoras)) {
                cadastrar(seguradoras, seguros, condutores);
            }
        } catch (ValorNaoEsperadoException e) {
            cadastrar(seguradoras, seguros, condutores);
        }
    }

}

    private static boolean handle(MenuCadastrar operacao, Map<String, Seguradora> seguradorasMap) {
        switch (operacao) {
            case CADASTRAR_CLIENTE_PF:
                cadastrarCliente(TipoCliente.PF, seguradorasMap);
                break;
            case CADASTRAR_CLIENTE_PJ:
                cadastrarCliente(TipoCliente.PJ, seguradorasMap);
                break;
            case CADASTRAR_VEICULO:
                cadastrarVeiculo(seguradorasMap);
                break;
            case CADASTRAR_SEGURADORA:
                cadastrarSeguradora(seguradorasMap);
                break;
            case VOLTAR:
                return false;
        }
        return true;
    }

    private static void cadastrarCliente(TipoCliente tipo, Map<String, Seguradora> seguradoras) {
        try{
            
        }
    }

    private static void cadastrarVeiculo(Map<String, Seguradora> seguradoras) {
        String cadastro = InputUtils.lerCadastro();
        String nomeSeguradora = InputUtils.lerNome("Nome da seguradora: ");
        if (seguradoras.containsKey(nomeSeguradora)) {
            Seguradora seguradora = seguradoras.get(nomeSeguradora);
            Cliente cliente = seguradora.getClienteByCadastro(cadastro);
            if (cliente != null) {
                if (cliente.addVeiculo(VeiculoFactory.lerVeiculo())) {
                    System.out.println("Veículo cadastrado com sucesso.");
                    System.out.printf("O valor do seu seguro é de R$ %.2f\n", cliente.getValorSeguro());
                } else {
                    System.out.println("Esse veículo já está cadastrado.");
                }
            } else {
                System.out.printf("Nenhum cliente com o cadastro %s encontrado na seguradora %s.", cadastro,
                        seguradora.getNome());
            }
        } else {
            System.out.printf("A seguradora %s não existe\n", nomeSeguradora);
        }
    }

    private static void cadastrarSeguradora(Map<String, Seguradora> seguradoras) {
        Seguradora seguradora = SeguradoraFactory.lerSeguradora();
        if (!seguradoras.containsKey(seguradora.getNome())) {
            seguradoras.put(seguradora.getNome(), seguradora);
            System.out.printf("Seguradora %s cadastrada com sucesso!\n", seguradora.getNome());
        } else {
            System.out.printf("Seguradora %s já foi cadastrada!\n", seguradora.getNome());
        }
    }

    public static MenuCadastrar getOperacao(int operacao) throws ValorNaoEsperadoException {
        switch (operacao) {
            case 1:
                return CADASTRAR_CLIENTE_PF;
            case 2:
                return CADASTRAR_CLIENTE_PJ;
            case 3:
                return CADASTRAR_VEICULO;
            case 4:
                return CADASTRAR_SEGURADORA;
            case 5:
                return VOLTAR;
            default:
                throw new ValorNaoEsperadoException();
        }
    }
}
