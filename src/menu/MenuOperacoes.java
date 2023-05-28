package menu;

import java.util.ArrayList;
import java.util.Date;

import entidades.Frota;
import entidades.Seguradora;
import entidades.Veiculo;
import entidades.Cliente.Cliente;
import entidades.Cliente.ClientePF;
import entidades.Cliente.ClientePJ;
import entidades.Cliente.Condutor;
import entidades.Seguro.Seguro;
import execeptions.*;
import factories.*;
import utils.InputUtils;

public enum MenuOperacoes {
    CADASTRAR(1),
    LISTAR(2),
    EXCLUIR(3),
    GERAR_SINISTRO(4),
    GERAR_SEGURO(5),
    CALCULAR_RECEITA_SEGURADORA(6),
    CALCULAR_VALOR_SEGURO(7),
    ATUALIZAR_FROTA(8),
    AUTORIZAR_CONDUTOR(9),
    DESAUTORIZAR_CONDUTOR(10),
    SAIR(0);

    private final int value;

    MenuOperacoes(int value) {
        this.value = value;
    }

    public static void menu() {
        System.out.println("-------------- MENU -------------");
        System.out.println("1- Cadastros");
        System.out.println("2- Listar");
        System.out.println("3- Excluir");
        System.out.println("4- Gerar Sinistro");
        System.out.println("5- Gerar Seguro");
        System.out.println("6- Calcular Receita Seguradora");
        System.out.println("7- Calcular Seguro do Cliente");
        System.out.println("8- Atualizar Frota");
        System.out.println("9- Autorizar Condutor");
        System.out.println("10- Desautorizar Condutor");
        System.out.println("0- Sair");
        int operacao = InputUtils.lerInt();

        try {
            MenuOperacoes o = getOperacao(operacao);
            if (handle(o)) {
                menu();
            }
        } catch (ValorNaoEsperadoException e) {
            menu();
        }
    }

    private static boolean handle(MenuOperacoes operacao) {
        switch (operacao) {
            case CADASTRAR:
                MenuCadastrar.cadastrar();
                break;
            case LISTAR:
                MenuListar.listar();
                break;
            case EXCLUIR:
                MenuExcluir.excluir();
                break;
            case GERAR_SINISTRO:
                gerarSinistro();
                break;
            case GERAR_SEGURO:
                gerarSeguro();
                break;
            case CALCULAR_RECEITA_SEGURADORA:
                calcularReceitaSeguradora();
                break;
            case CALCULAR_VALOR_SEGURO:
                calcularValorSeguro();
                break;
            case ATUALIZAR_FROTA:
                atualizarFrota();
                break;
            case AUTORIZAR_CONDUTOR:
                autorizarCondutor();
                break;
            case DESAUTORIZAR_CONDUTOR:
                desautorizarCondutor();
                break;
            case SAIR:
                return false;
        }
        return true;
    }

    private static void gerarSinistro() {
        try {
            Integer idSeguro = InputUtils.lerInt("ID do seguro: ");
            Seguro seguro = BancoDados.getSeguro(idSeguro);
            String cpfCondutor = InputUtils.lerCPF("CPF do Condutor: ");
            Condutor condutor = BancoDados.getCondutor(cpfCondutor);
            Date data = InputUtils.lerData("Data do sinistro (dd/mm/yyyy): ");
            String endereco = InputUtils.lerString("Endereço: ");
            seguro.gerarSinistro(data, endereco, condutor);
            System.out.println("Sinistro gerado com sucesso.");
        } catch (SeguroNaoEncontradoException e) {
            System.out.println(e.getMessage());
        } catch (CondutorNaoEncontradoException e) {
            System.out.println(e.getMessage());
        } catch (CondutorNaoAssociadoException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void gerarSeguro() {
        try {
            Cliente cliente;
            Seguradora seguradora;
            String cadastro = InputUtils.lerCadastro("Insira o cadastro do cliente: ");
            cliente = BancoDados.getCliente(cadastro);
            String cnpjSeguradora = InputUtils.lerCNPJ("Insira o cnpj da seguradora: ");
            seguradora = BancoDados.getSeguradora(cnpjSeguradora);
            if (!seguradora.clienteCadastrado(cliente)) {
                System.out.println("O cliente " + cliente.getNome() + " não está cadastrado nessa seguradora!");
                return;
            }
            Date dataFim = InputUtils.lerData("Data do termino do seguro: ");
            if (cliente instanceof ClientePF) {
                String placa = InputUtils.lerString("Insira a placa do carro: ");
                Veiculo veiculo = ((ClientePF) cliente).buscarVeiculo(placa);
                seguradora.gerarSeguro((ClientePF) cliente, veiculo, dataFim);
            } else {
                String codigo = InputUtils.lerString("Insira o código da frota: ");
                Frota veiculo = ((ClientePJ) cliente).buscarFrota(codigo);
                seguradora.gerarSeguro((ClientePJ) cliente, veiculo, dataFim);
            }
            System.out.println("Seguro gerado com sucesso!");
        } catch (ClienteNaoEncontradoException e) {
            System.out.println(e.getMessage());
        } catch (VeiculoNaoEncontradoException e) {
            System.out.println(e.getMessage());
        } catch (FrotaNaoEncontradaException e) {
            System.out.println(e.getMessage());
        } catch (SeguradoraNaoEncontradaException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void autorizarCondutor() {
        try {
            Condutor condutor = CondutorFactory.lerCondutor();
            int id = InputUtils.lerInt("Insira o ID do seguro que você gostaria de cadastrar o condutor: ");
            Seguro seguro = BancoDados.getSeguro(id);
            BancoDados.save(condutor);
            if (seguro.autorizarCondutor(condutor)) {
                System.out
                        .println("O condutor '" + condutor.getNome() + "' foi cadastrado no seguro #" + seguro.getID());
            } else {
                System.out.println(
                        "O condutor '" + condutor.getNome() + "' já está cadastrado no seguro #" + seguro.getID());
            }
        } catch (SeguroNaoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void desautorizarCondutor() {
        try {
            String cpf = InputUtils.lerCPF("Insira o cpf do condutor: ");
            Condutor condutor = BancoDados.getCondutor(cpf);
            int id = InputUtils.lerInt("Insira o id do seguro: ");
            Seguro seguro = BancoDados.getSeguro(id);
            if (seguro.desautorizarCondutor(condutor)) {
                System.out.println("Condutor '" + condutor.getNome() + "' desautorizado do seguro #" + seguro.getID());
            } else {
                System.out.println("O condutor '" + condutor.getNome() + "' não tem está autorizado.");
            }
        } catch (CondutorNaoEncontradoException e) {
            System.out.println(e.getMessage());
        } catch (SeguroNaoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void atualizarFrota() {
        try {
            ClientePJ cliente;
            String code;
            Frota frota;
            String cnpj = InputUtils.lerCNPJ();
            cliente = (ClientePJ) BancoDados.getCliente(cnpj);
            code = InputUtils.lerString("Insira o código da frota");
            frota = cliente.buscarFrota(code);
            int tamFrota = InputUtils.lerInt("Novo tamanho da frota: ");
            ArrayList<Veiculo> veiculos = new ArrayList<Veiculo>();
            for (int i = 0; i < tamFrota; i++) {
                Veiculo veiculo = VeiculoFactory.lerVeiculo();
                if (veiculos.contains(veiculo)) {
                    System.out.println("Esse veículo já foi adicionado!");
                } else {
                    veiculos.add(veiculo);
                }
            }
            cliente.atualizarFrota(veiculos, frota);
            System.out.println("Frota " + frota.getCode() + " atualizada.");
        } catch (ClienteNaoEncontradoException e) {
            System.out.println(e.getMessage());
        } catch (FrotaNaoEncontradaException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void calcularReceitaSeguradora() {
        try {
            String cnpj = InputUtils.lerCNPJ("CNPJ da Seguradora: ");
            Seguradora seguradora = BancoDados.getSeguradora(cnpj);
            System.out.printf("A receita da seguradora '%s' é de R$ %.2f\n", seguradora.getNome(),
                    seguradora.getReceita());
        } catch (SeguradoraNaoEncontradaException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void calcularValorSeguro() {
        try {
            int id = InputUtils.lerInt("Insira o id do seguro: ");
            Seguro seguro = BancoDados.getSeguro(id);
            System.out.printf("O valor mensal do seguro #%d é R$ %.2f\n", id, seguro.getValorMensal());
        } catch (SeguroNaoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }

    public int getValue() {
        return value;
    }

    public static MenuOperacoes getOperacao(int operacao) throws ValorNaoEsperadoException {
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
                return GERAR_SINISTRO;
            case 6:
                return CALCULAR_RECEITA_SEGURADORA;
            case 7:
                return CALCULAR_VALOR_SEGURO;
            case 8:
                return ATUALIZAR_FROTA;
            case 9:
                return AUTORIZAR_CONDUTOR;
            case 10:
                return DESAUTORIZAR_CONDUTOR;
            case 0:
                return SAIR;
            default:
                throw new ValorNaoEsperadoException("Valor não esperado");
        }
    }
}
