package entidades;

import enums.StatusVeiculo;
import interfaces.Avaliavel;

public class Carro extends Veiculo implements Avaliavel{

    private int qntPortas;

        public Carro(String placa, String modelo, int anoFabricacao, double valorDiaria, double quilometragem,
            StatusVeiculo status, int qntPortas) {
        super(placa, modelo, anoFabricacao, valorDiaria, quilometragem, status);
        this.qntPortas = qntPortas;
}

        @Override
        public void avaliar(int nota) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'avaliar'");
        }

        @Override
        public void exibirDetalhes() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'exibirDetalhes'");
        }

        @Override
        public boolean possuiSeguroEspecial() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'possuiSeguroEspecial'");
        }
}
