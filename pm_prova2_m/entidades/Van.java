import pm_prova2_m.Avaliavel;
import pm_prova2_m.Inspecionavel;
import pm_prova2_m.StatusVeiculo;
import pm_prova2_m.Veiculo;

public class Van extends Veiculo implements Avaliavel, Inspecionavel{

    private int passageirosMax;

    public Van(String placa, String modelo, int anoFabricacao, double valorDiaria, double quilometragem,
            StatusVeiculo status, int passageirosMax) {
        super(placa, modelo, anoFabricacao, valorDiaria, quilometragem, status);
        this.passageirosMax = passageirosMax;
    }
}
