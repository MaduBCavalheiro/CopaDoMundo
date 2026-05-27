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

        this.descricao = frases.get(random.nextInt(frases.size()));
    }
}
