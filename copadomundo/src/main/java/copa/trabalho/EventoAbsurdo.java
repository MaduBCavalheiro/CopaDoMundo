package copa.trabalho;

import java.util.ArrayList;
import java.util.List;

public class EventoAbsurdo extends Evento {

    public EventoAbsurdo() {
        this.raridade = true; // Absurdos são raros
        gerarDescricao();
    }

    @Override
    public void gerarDescricao() {
        List<String> frases = new ArrayList<>();
        frases.add("Um drone apareceu misteriosamente no estádio.");
        frases.add("O bandeirinha perdeu a bandeira no vento.");
        frases.add("A torcida começou um karaokê coletivo.");
        frases.add("Um cachorro invadiu o gramado e driblou três volantes.");
        frases.add("O atacante chutou tão torto que a bola acabou quebrando o para-brisa do carro do presidente do clube.");
        frases.add("O sistema de irrigação do gramado ligou sozinho e deu um banho nos jogadores reservas.");
        frases.add("Um enxame de abelhas pousou na trave esquerda, forçando uma paralisação de 5 minutos.");
        frases.add("O árbitro se atrapalhou sozinho e caiu de maduro ao tentar acompanhar o contra-ataque.");
        frases.add("O gandula se empolgou, dominou a bola no peito e foi ovacionado pela torcida.");
        frases.add("A bola estourou no meio de um chute forte e o jogo teve que ser parado para a troca.");
        frases.add("O zagueiro foi comemorar o desarme, escorregou na poça d'água e deu uma cambalhota perfeita.");
        frases.add("Um torcedor fantasiado de dinossauro invadiu o campo para abraçar o goleiro.");
        frases.add("O vento estava tão forte que o tiro de meta do goleiro voltou bizarramente em direção à sua própria área.");
        frases.add("O técnico foi tentar chutar uma garrafa d'água de frustração, errou o chute e perdeu o sapato.");

        this.descricao = frases.get(random.nextInt(frases.size()));
    }
}
