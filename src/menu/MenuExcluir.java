package menu;

import entidades.Cliente.Cliente;
import entidades.Cliente.ClientePF;
import entidades.Cliente.ClientePJ;
import entidades.Frota.Frota;
import entidades.Seguradora.Seguradora;
import entidades.Seguro.Seguro;
import entidades.Veiculo.Veiculo;
import execeptions.*;
import utils.InputUtils;

public enum MenuExcluir {
    EXCLUIR_CLIENTE(1),
    EXCLUIR_VEICULO(2),
    EXCLUIR_FROTA(3),
    EXCLUIR_SINISTRO(4),
    EXCLUIR_SEGURO(5),
    EXCLUIR_SEGURADORA(6),
    VOLTAR(0);

    private final int value;

    MenuExcluir(int value) {
        this.value = value;
    }

    public static void excluir() {
        System.out.println("------------- EXCLUIR ------------");
        System.out.println("1- Excluir Cliente");
        System.out.println("2- Excluir Veículo");
        System.out.println("3- Excluir Frota");
        System.out.println("4- Excluir Sinistro");
        System.out.println("5- Excluir Seguro");
        System.out.println("6- Excluir Seguradora");
        System.out.println("0- Voltar");
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
            case EXCLUIR_FROTA:
                excluirFrota();
                break;
            case EXCLUIR_SEGURO:
                excluirSeguro();
                break;
            case EXCLUIR_SEGURADORA:
                excluirSeguradora();
                break;
            case VOLTAR:
                return false;
        }
        return true;
    }

    private static void excluirCliente() {
        try {
            String cnpj = InputUtils.lerCNPJ("Insira o cnpj da seguradora: ");
            Seguradora seguradora = BancoDados.getSeguradora(cnpj);
            String cadastro = InputUtils.lerCadastro("Insira o cadastro do cliente (cpf/cnpj): ");
            Cliente cliente = BancoDados.getCliente(cadastro);
            Double receita = seguradora.getReceita();
            if (seguradora.removerCliente(cliente)) {
                System.out.println("Cliente excluido da seguradora '" + seguradora.getNome() + "'");
                System.out.printf("A receita da seguradora '%s' era de R$ %.2f, agora é de R$ %.2f\n",
                        seguradora.getNome(), receita, seguradora.getReceita());
            } else {
                System.out.println("O cliente '" + cliente.getNome() + "' não está cadastrado na seguradora '"
                        + seguradora.getNome() + "'");
            }
        } catch (SeguradoraNaoEncontradaException e) {
            System.out.println(e.getMessage());
        } catch (ClienteNaoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void excluirSinistro() {
        try {
            int idSeguro = InputUtils.lerInt("Insira o id do seguro: ");
            Seguro seguro = BancoDados.getSeguro(idSeguro);
            int idSinistro = InputUtils.lerInt("Insira o id do sinistro: ");
            if (seguro.removeSinistro(idSinistro)) {
                System.out.println("Sinistro removido.");
            } else {
                System.out.println("O sinistro #" + idSinistro + " não existe no seguro #" + idSeguro);
            }
        } catch (SeguroNaoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void excluirVeiculo() {
        try {
            String cpf = InputUtils.lerCPF("Insira o cpf do cliente: ");
            ClientePF cliente = (ClientePF) BancoDados.getCliente(cpf);
            String placa = InputUtils.lerString("Insira a placa do veículo: ");
            Veiculo veiculo = cliente.buscarVeiculo(placa);
            BancoDados.excluirSeguro(veiculo.getSeguro());
            veiculo.desassociarSeguro();
            cliente.removeVeiculo(placa);
            System.out.println("Veículo removido com sucesso.");
        } catch (ClienteNaoEncontradoException e) {
            System.out.println(e.getMessage());
        } catch (VeiculoNaoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void excluirFrota() {
        try {
            String cnpj = InputUtils.lerCNPJ("Insira o cnpj do cliente: ");
            ClientePJ cliente = (ClientePJ) BancoDados.getCliente(cnpj);
            String code = InputUtils.lerString("Insira o código da frota: ");
            Frota frota = cliente.buscarFrota(code);
            BancoDados.excluirSeguro(frota.getSeguro());
            cliente.removeFrota(frota);
            frota.desassociarSeguro();
            System.out.println("Frota removida com sucesso.");
        } catch (ClienteNaoEncontradoException e) {
            System.out.println(e.getMessage());
        } catch (FrotaNaoEncontradaException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void excluirSeguro() {
        try {
            String cnpj = InputUtils.lerCNPJ("Insira o cnpj da seguradora: ");
            Seguradora seguradora = BancoDados.getSeguradora(cnpj);
            int id = InputUtils.lerInt("Insira o id do seguro: ");
            Seguro seguro = BancoDados.getSeguro(id);
            Double receita = seguradora.getReceita();
            if (seguradora.cancelarSeguro(seguro)) {
                System.out.println("Seguro do cliente '" + seguro.getCliente().getNome() + "' excluído com sucesso.");
                System.out.printf("A receita da seguradora '%s' era de R$ %.2f, agora é de R$ %.2f",
                        seguradora.getNome(), receita, seguradora.getReceita());
            } else {
                System.out.println("O seguro não foi cadastrado nessa seguradora.");
            }
        } catch (SeguradoraNaoEncontradaException e) {
            System.out.println(e.getMessage());
        } catch (SeguroNaoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void excluirSeguradora() {
        try {
            String cnpj = InputUtils.lerCNPJ("Insira o cnpj da seguradora: ");
            Seguradora seguradora = BancoDados.getSeguradora(cnpj);
            BancoDados.excluirSeguradora(seguradora);
            System.out.println("Seguradora excluida com sucesso.");
        } catch (SeguradoraNaoEncontradaException e) {
            System.out.println(e.getMessage());
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
                return EXCLUIR_FROTA;
            case 4:
                return EXCLUIR_SINISTRO;
            case 5:
                return EXCLUIR_SEGURO;
            case 6:
                return EXCLUIR_SEGURADORA;
            case 0:
                return VOLTAR;
            default:
                return null;
        }
    }
}
