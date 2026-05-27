package copa.trabalho;

import java.util.ArrayList;
import java.util.List;

public class EventoGenerico extends Evento {

    public EventoGenerico() {
        this.raridade = false; //Generico não é raro
        gerarDescricao();
    }

    @Override
    public void gerarDescricao() {
        List<String> frases = new ArrayList<>();
        frases.add("O atacante chutou direto para fora, sem perigo.");
        frases.add("O técnico está gesticulando muito na beira do campo.");
        frases.add("Falta marcada no meio de campo.");
        frases.add("Substituição de jogador cansado pelo lado esquerdo.");

        this.descricao = frases.get(random.nextInt(frases.size()));
    }
}
