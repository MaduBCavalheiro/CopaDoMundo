package copa.trabalho;

import java.util.ArrayList;
import java.util.List;

public class EventoRivalidade extends Evento {
    private String paisA;
    private String paisB;

    public EventoRivalidade(String paisA, String paisB) {
        this.paisA = paisA.trim();
        this.paisB = paisB.trim();
        this.raridade = true; 
        gerarDescricao();
    }

    @Override
    public void gerarDescricao() {
        List<String> frases = new ArrayList<>();

        // Brasil e Argentina
        if ((paisA.equalsIgnoreCase("Brasil") && paisB.equalsIgnoreCase("Argentina")) ||
            (paisA.equalsIgnoreCase("Argentina") && paisB.equalsIgnoreCase("Brasil"))) {
            
            frases.add("Clássico sul-americano! Clima tenso entre os jogadores de Brasil e Argentina após falta dura.");
            frases.add("A rivalidade histórica incendeia o estádio! Provocações ecoam das arquibancadas.");
            frases.add("Jogo truncado! Ninguém quer perder o maior clássico das Américas.");
        }
        
        // Brasil e Alemanha
        else if ((paisA.equalsIgnoreCase("Brasil") && paisB.equalsIgnoreCase("Alemanha")) ||
                 (paisA.equalsIgnoreCase("Alemanha") && paisB.equalsIgnoreCase("Brasil"))) {
            
            frases.add("O confronto traz lembranças pesadas do passado! O Brasil busca se impor contra os alemães.");
            frases.add("Estilo contra estilo: a organização tática da Alemanha bate de frente com a habilidade do Brasil.");
        }
        
        // França e Inglaterra
        else if ((paisA.equalsIgnoreCase("França") && paisB.equalsIgnoreCase("Inglaterra")) ||
                 (paisA.equalsIgnoreCase("Inglaterra") && paisB.equalsIgnoreCase("França"))) {
            
            frases.add("O clássico europeu ferve! Inglaterra e França disputam cada centímetro do meio-campo.");
            frases.add("Duelo de gigantes da Europa! A tensão é visível até nos bancos de reservas.");
        }
        
        // Qualquer jogo do Brasil
        else if (paisA.equalsIgnoreCase("Brasil") || paisB.equalsIgnoreCase("Brasil")) {
            String adversario = paisA.equalsIgnoreCase("Brasil") ? paisB : paisA;
            frases.add("A torcida brasileira faz festa no estádio, empurrando a Seleção contra " + adversario + ".");
            frases.add("O " + adversario + " monta uma forte linha defensiva para tentar segurar o ataque do Brasil.");
        }
        
        // Qualquer outro confronto
        else {
            frases.add("Clima de Copa do Mundo! A rivalidade internacional entre " + paisA + " e " + paisB + " equilibra a partida.");
            frases.add("Orgulho nacional em jogo! Os jogadores de " + paisA + " e " + paisB + " dividem a bola com muita intensidade.");
        }

        this.descricao = frases.get(random.nextInt(frases.size()));
    }
}
