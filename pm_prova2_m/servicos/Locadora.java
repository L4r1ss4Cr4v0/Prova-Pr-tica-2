package servicos;

import java.util.ArrayList;
import java.util.List;

import entidades.Aluguel;
import entidades.Veiculo;
import enums.StatusAluguel;
import enums.StatusVeiculo;
import interfaces.Avaliavel;

public class Locadora {
    private final List<Aluguel> alugueis;
    private final List<Veiculo> veiculos;

    public Locadora() {
        this.alugueis = new ArrayList<>();
        this.veiculos = new ArrayList<>();
    }

    public void cadastrarVeiculo(Veiculo v) {
        if (v == null) {
            throw new IllegalArgumentException("Veiculo invalido.");
        }
        veiculos.add(v);
    }

    public void cadastrarAluguel(Aluguel a) {
        if (a == null) {
            throw new IllegalArgumentException("Aluguel invalido.");
        }
        alugueis.add(a);
    }

    public List<Veiculo> getVeiculos() {
        return List.copyOf(veiculos);
    }

    public List<Aluguel> getAlugueis() {
        return List.copyOf(alugueis);
    }

    public List<Aluguel> getAlugueisAtivos() {
        List<Aluguel> ativos = new ArrayList<>();
        for (Aluguel a : alugueis) {
            if (a.getStatus() == StatusAluguel.ATIVO) {
                ativos.add(a);
            }
        }
        return ativos;
    }

    public void associarVeiculoAoAluguel(Aluguel a, Veiculo v) {
        a.adicionarVeiculo(v);
    }

    public List<Avaliavel> getAvaliaveis() {
        List<Avaliavel> avaliavels = new ArrayList<>();
        for (Veiculo v : veiculos) {
            if (v instanceof Avaliavel) {
                avaliavels.add((Avaliavel) v);
            }
        }
        for (Aluguel a : alugueis) {
            if (a instanceof Avaliavel) {
                avaliavels.add((Avaliavel) a);
            }
        }
        return avaliavels;
    }

//     public List<ControladorPresenca> getControladoresPresencaVeiculos() {
//         List<ControladorPresenca> controladores = new ArrayList<>();
//         for (Veiculo Veiculo : Veiculos) {
//             if (Veiculo instanceof ControladorPresenca && Veiculo.isAtiva()) {
//                 controladores.add((ControladorPresenca) Veiculo);
//             }
//         }
//         return controladores;
//     }

    public int removerVeiculosEmManutencao() {
        List<Veiculo> manutencao = new ArrayList<>();
        for (Veiculo v : veiculos) {
            if (v.getStatus() == StatusVeiculo.MANUTENCAO) {
                manutencao.add(v);
            }
        }
        for (Veiculo v : manutencao) {
            for (Aluguel a : alugueis) {
                a.removerVeiculo(v);
            }
            veiculos.remove(v);
        }
        return manutencao.size();
    }
}