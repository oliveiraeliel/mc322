package menu;

import entidades.Frota;
import entidades.Seguradora;
import entidades.Veiculo;
import entidades.Cliente.Cliente;
import entidades.Cliente.ClientePF;
import entidades.Cliente.ClientePJ;
import entidades.Cliente.TipoCliente;
import execeptions.ClienteNaoEncontradoException;
import execeptions.SeguradoraNaoEncontradaException;
import execeptions.ValorNaoEsperadoException;
import factories.ClienteFactory;
import factories.SeguradoraFactory;
import factories.VeiculoFactory;
import utils.InputUtils;

public enum MenuCadastrar {
    CADASTRAR_CLIENTE_PF(1),
    CADASTRAR_CLIENTE_PJ(2),
    CADASTRAR_VEICULO(3),
    CADASTRAR_FROTA(4),
    CADASTRAR_SEGURADORA(5),
    VOLTAR(6);

    private final int value;

    MenuCadastrar(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static void cadastrar() {
        System.out.println("------------ CADASTRAR -----------");
        System.out.println("1- Cadastrar Cliente PF");
        System.out.println("2- Cadastrar Cliente PJ");
        System.out.println("3- Cadastrar Veículo");
        System.out.println("4- Cadastrar Frota");
        System.out.println("5- Cadastrar Seguradora");
        System.out.println("6- Voltar");
        int operacao = InputUtils.lerInt();

        try {
            MenuCadastrar o = getOperacao(operacao);
            if (handle(o)) {
                cadastrar();
            }
        } catch (ValorNaoEsperadoException e) {
            cadastrar();
        }
    }

    private static boolean handle(MenuCadastrar operacao) {
        switch (operacao) {
            case CADASTRAR_CLIENTE_PF:
                cadastrarCliente(TipoCliente.PF);
                break;
            case CADASTRAR_CLIENTE_PJ:
                cadastrarCliente(TipoCliente.PJ);
                break;
            case CADASTRAR_VEICULO:
                cadastrarVeiculo();
                break;
            case CADASTRAR_SEGURADORA:
                cadastrarSeguradora();
                break;
            case CADASTRAR_FROTA:
                cadastrarFrota();
                break;
            case VOLTAR:
                return false;
        }
        return true;
    }

    private static void cadastrarCliente(TipoCliente tipo) {
        try {
            String cnpj = InputUtils.lerCNPJ();
            Seguradora seguradora = BancoDados.getSeguradora(cnpj);
            Cliente cliente = tipo == TipoCliente.PF ? ClienteFactory.lerClientePF() : ClienteFactory.lerClientePJ();
            if (seguradora.cadastrarCliente(cliente)) {
                System.out.println("Cliente cadastrado com sucesso.");
            } else {
                System.out.println("O cliente já está cadastrado.");
            }
        } catch (SeguradoraNaoEncontradaException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void cadastrarVeiculo() {
        try {
            String cpf = InputUtils.lerCPF();
            ClientePF cliente = (ClientePF) BancoDados.getCliente(cpf);
            Veiculo veiculo = VeiculoFactory.lerVeiculo();
            if (cliente.cadastrarVeiculo(veiculo)) {
                System.out.println("Veículo cadastrado com sucesso");
            } else {
                System.out.println("Esse veículo já está cadastrado");
            }
        } catch (ClienteNaoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void cadastrarFrota() {
        try {
            String cnpj = InputUtils.lerCNPJ();
            ClientePJ cliente = (ClientePJ) BancoDados.getCliente(cnpj);
            Integer tamFrota = InputUtils.lerInt("Tamanho da frota: ");
            Frota frota = new Frota();
            cliente.cadastrarFrota(frota);
            for (int i = 0; i < tamFrota; i++) {
                adicionarVeiculoFrota(frota);
            }
            System.out.println("Frota cadastrada com sucesso.");
            System.out.println("Código da frota: " + frota.getCode());
        } catch (ClienteNaoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void adicionarVeiculoFrota(Frota frota) {
        Veiculo veiculo = VeiculoFactory.lerVeiculo();
        if (frota.addVeiculo(veiculo)) {
            System.out.println("Veículo adicionado à frota.");
        } else {
            System.out.println("O veículo já está na frota.");
        }
    }

    private static void cadastrarSeguradora() {
        Seguradora seguradora = SeguradoraFactory.lerSeguradora();
        if (!BancoDados.seguradoraExiste(seguradora)) {
            BancoDados.adicionarSeguradora(seguradora);
            System.out.println("A seguradora foi cadastrada com sucesso.");
        } else {
            System.out.println("A seguradora já está cadastrada.");
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
                return CADASTRAR_FROTA;
            case 5:
                return CADASTRAR_SEGURADORA;
            case 6:
                return VOLTAR;
            default:
                throw new ValorNaoEsperadoException();
        }
    }
}
