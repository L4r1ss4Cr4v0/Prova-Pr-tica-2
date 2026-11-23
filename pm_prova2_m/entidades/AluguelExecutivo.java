package entidades;

import enums.StatusAluguel;
import interfaces.Avaliavel;

public class AluguelExecutivo extends Aluguel implements Avaliavel{

    public AluguelExecutivo(String nomeCliente, int quantidadeDias, double valorTotal, StatusAluguel status) {
        super(nomeCliente, quantidadeDias, valorTotal, status);
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("Possui quilometragem ilimitada, oferece motorista particular");
    }

    @Override
    public boolean possuiSeguroIncluso() {
        return true;
    }

    @Override
    public void avaliar(int nota) {
        System.out.println("avaliou com nova: "+ nota);
    }
    
}
