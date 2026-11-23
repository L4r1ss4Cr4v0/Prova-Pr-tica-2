import pm_prova2_m.Inspecionavel;
import pm_prova2_m.StatusVeiculo;
import pm_prova2_m.Veiculo;

public class Moto extends Veiculo implements Inspecionavel{

    private int cilindrada;

        public Moto(String placa, String modelo, int anoFabricacao, double valorDiaria, double quilometragem,
            StatusVeiculo status, int cilindrada) {
        super(placa, modelo, anoFabricacao, valorDiaria, quilometragem, status);
        this.cilindrada = cilindrada;
}
}
