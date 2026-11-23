import java.util.List;
import java.util.Scanner;

import entidades.Aluguel;
import entidades.AluguelBasico;
import entidades.AluguelPremium;
import entidades.Carro;
import entidades.Moto;
import entidades.Van;
import entidades.Veiculo;
import enums.StatusAluguel;
import enums.StatusVeiculo;
import interfaces.Avaliavel;
import servicos.Locadora;

public class App {
private static final Scanner scanner = new Scanner(System.in);
    private static final Locadora locadora = new Locadora();

    public static void main(String[] args) {
        carregarDadosIniciais();
        int opcao;
        do {
            exibirMenu();
            opcao = lerInteiro("Escolha uma opcao: ");
            try {
                executarOpcao(opcao);
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } while (opcao != 0);
        scanner.close();
    }

    private static void carregarDadosIniciais() {
        Carro carro = new Carro("TESTE", "carro", 2019, 100, 10000, StatusVeiculo.DISPONIVEL, 4);
        Van van = new Van("TESTE", "van", 2020, 10, 7000, StatusVeiculo.ALUGADO, 8);
        Moto moto = new Moto("TESTE", "moto", 1800, 90, 3000, StatusVeiculo.MANUTENCAO, 10);
        locadora.cadastrarVeiculo(carro);
        locadora.cadastrarVeiculo(van);
        locadora.cadastrarVeiculo(moto);
        Aluguel basico = new AluguelBasico("Larissa", 12, 100, StatusAluguel.ATIVO);
        Aluguel premium = new AluguelPremium("Gleider", 30, 159.90, StatusAluguel.ATIVO);
        locadora.cadastrarAluguel(basico);
        locadora.cadastrarAluguel(premium);

        locadora.associarVeiculoAoAluguel(basico, van);
        locadora.associarVeiculoAoAluguel(premium, moto);
        locadora.associarVeiculoAoAluguel(premium, carro);
    }

    private static void exibirMenu() {
        System.out.println();
        System.out.println("1 - Associar veículos a um aluguel");
        System.out.println("2 - Exibir todas os veículos cadastrados");
        System.out.println("3 - Exibir todos os alugueis cadastrados");
        System.out.println("4 - Exibir veículos disponíveis");
        System.out.println("5 - Avaliar veiculos");
        System.out.println("6 - Realizar inspeção");
        System.out.println("7 - Remover veículos em manutenção do sistema");
        System.out.println("0 - Sair");
    }

    private static void executarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                associarVeiculo();
                break;
            case 2:
                exibirVeiculos();
                break;
            case 3:
                exibirAlugueis(locadora.getAlugueis(), "AluguelS CADASTRADOS");
                break;
            case 4:
                exibirAlugueis(locadora.getAlugueisAtivos(), "AluguelS ATIVOS");
                break;
            case 5:
                avaliarVeiculos();
                break;
            case 6:
                // realizarInspecao();
                break;
            case 7:
                removerVeiculosManutencao();
                break;
            case 0:
                System.out.println("Encerrando o sistema...");
                break;
            default:
                System.out.println("Opcao invalida.");
        }
    }

    private static void associarVeiculo() {
        Aluguel aluguel = selecionarAluguel(locadora.getAlugueis());
        if (aluguel == null) {
            return;
        }
        Veiculo veiculo = selecionarVeiculos(locadora.getVeiculos());
        if (veiculo == null) {
            return;
        }
        locadora.associarVeiculoAoAluguel(aluguel, veiculo);
        System.out.println("Veiculo " + veiculo.getModelo() + " associada ao Aluguel do" + aluguel.getNomeCliente() + ".");
    }

    private static void exibirVeiculos() {
        List<Veiculo> veiculos = locadora.getVeiculos();
        System.out.println("\n--- VEICULOS CADASTRADAS ---");
        if (veiculos.isEmpty()) {
            System.out.println("Nenhuma atividade cadastrada.");
            return;
        }
        for (int i = 0; i < veiculos.size(); i++) {
            System.out.println((i + 1) + ". " + veiculos.get(i));
        }
    }

    private static void exibirAlugueis(List<Aluguel> Alugueis, String titulo) {
        System.out.println("\n--- " + titulo + " ---");
        if (Alugueis.isEmpty()) {
            System.out.println("Nenhum Aluguel encontrado.");
            return;
        }
        for (int i = 0; i < Alugueis.size(); i++) {
            System.out.println((i + 1) + ". " + Alugueis.get(i));
        }
    }

    private static void removerVeiculosManutencao() {
        int removidas = locadora.removerVeiculosEmManutencao();
        System.out.println(removidas + " veiculo(s) em manutenção removido(s).");
    }

    private static void selecionarAluguel(List<Aluguel> Alugueis) {
        if (Alugueis.isEmpty()) {
            System.out.println("Nenhum Aluguel cadastrado.");
        }
        System.out.println("\n--- SELECIONE O ALUGUEL ---");
        for (int i = 0; i < Alugueis.size(); i++) {
            System.out.println((i + 1) + ". " + Alugueis.get(i).getNomeCliente() + " (" + Alugueis.get(i).getStatus() + ")");
        }
    }

    private static void selecionarVeiculos(List<Veiculo> veiculos) {
        if (veiculos.isEmpty()) {
            System.out.println("Nenhuma atividade cadastrada.");
            return null;
        }
        System.out.println("\n--- SELECIONE A ATIVIDADE ---");
        for (int i = 0; i < veiculos.size(); i++) {
            Veiculo veiculo = veiculos.get(i);
            System.out.println((i + 1) + ". " + veiculo.getModelo() + " - " + veiculo.getPlaca() + " (" + veiculo.getStatus() + ")");
        }
    }

    private static void avaliarVeiculos() {
        List<Avaliavel> avaliaveis = locadora.getAvaliaveis();
        if (avaliaveis.isEmpty()) {
            System.out.println("Nenhum veiculo é avaliável.");
            return;
        }
        System.out.println("\n--- ITENS QUE SÃO AVALIAVEIS ---");
        for (int i = 0; i < avaliaveis.size(); i++) {
            System.out.println((i + 1) + ". " + (avaliaveis.get(i)));
        }
        int indice = lerIndice(avaliaveis.size());
        if (indice < 0) {
            return;
        }
        int nota = lerInteiro("Nota dada: ");
        avaliaveis.get(indice).avaliar(nota);
    }

    private static int lerIndice(int tamanho) {
        int escolha = lerInteiro("Numero (0 para cancelar): ");
        if (escolha == 0) {
            System.out.println("Operacao cancelada.");
            return -1;
        }
        if (escolha < 1 || escolha > tamanho) {
            System.out.println("Opcao invalida.");
            return -1;
        }
        return escolha - 1;
    }

    private static int lerInteiro(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String entrada = scanner.nextLine().trim();
            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Digite um numero inteiro valido.");
            }
        }
    }

}
