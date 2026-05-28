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
        frases.add("O goleiro fez uma defesa segura em dois tempos após chute de longe.");
        frases.add("O lateral cobrou o arremesso manual direto para dentro da área.");
        frases.add("O juiz parou o jogo para atendimento médico do camisa 10.");
        frases.add("Cartão amarelo aplicado por uma falta tática para parar o contra-ataque.");
        frases.add("A bola desviou na barreira e saiu caprichosamente em escanteio.");
        frases.add("O atacante foi flagrado em posição de impedimento pelo bandeirinha.");
        frases.add("Os jogadores aproveitam a pausa para hidratação devido ao forte calor.");
        frases.add("A zaga subiu mais alto que todo mundo e afastou o perigo de cabeça.");
        frases.add("O meia tentou um passe de calcanhar, mas a defesa antecipou a jogada.");
        frases.add("O quarto árbitro levanta a placa indicando os minutos de acréscimo.");

        this.descricao = frases.get(random.nextInt(frases.size()));
    }
}
