package entidades;

import enums.StatusAluguel;

public class AluguelBasico extends Aluguel{

    public AluguelBasico(String nomeCliente, int quantidadeDias, double valorTotal, StatusAluguel status) {
        super(nomeCliente, quantidadeDias, valorTotal, status);
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("Permite até 100 km por dia");
    }

    @Override
    public boolean possuiSeguroIncluso() {
        return false;
    }
    
}
