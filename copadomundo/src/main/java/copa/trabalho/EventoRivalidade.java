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
            frases.add("Duelo psicológico! O goleiro argentino tenta desconcentrar o batedor brasileiro antes da cobrança.");
            frases.add("Dividida de Copa do Mundo! Ninguém alivia o pé quando a bola está entre Brasil e Argentina.");
            frases.add("A catimba tomou conta do jogo! Discussões intermináveis a cada falta marcada.");
        }
        
        // Brasil e Alemanha
        else if ((paisA.equalsIgnoreCase("Brasil") && paisB.equalsIgnoreCase("Alemanha")) ||
                 (paisA.equalsIgnoreCase("Alemanha") && paisB.equalsIgnoreCase("Brasil"))) {
            
            frases.add("O confronto traz lembranças pesadas do passado! O Brasil busca se impor contra os alemães.");
            frases.add("Estilo contra estilo: a organização tática da Alemanha bate de frente com a habilidade do Brasil.");
            frases.add("Fantasmas do passado! A torcida prende a respiração a cada ataque da Alemanha na área do Brasil.");
            frases.add("O Brasil tenta ditar o ritmo com ginga e passes curtos, quebrando as linhas de forte marcação física da Alemanha.");
            frases.add("Olhar encarador! O zagueiro brasileiro deixa o corpo para parar o contra-ataque alemão, mostrando que hoje não haverá facilidade.");
        }
        
        // França e Inglaterra
        else if ((paisA.equalsIgnoreCase("França") && paisB.equalsIgnoreCase("Inglaterra")) ||
                 (paisA.equalsIgnoreCase("Inglaterra") && paisB.equalsIgnoreCase("França"))) {
            
            frases.add("O clássico europeu ferve! Inglaterra e França disputam cada centímetro do meio-campo.");
            frases.add("Duelo de gigantes da Europa! A tensão é visível até nos bancos de reservas.");
            frases.add("A rivalidade histórica do Canal da Mancha se reflete no campo! França e Inglaterra disputam a posse de bola com intensidade máxima.");
            frases.add("Duelo tático de alto nível! O talento técnico da França tenta furar a sólida e tradicional organização defensiva da Inglaterra.");
            frases.add("Clima pesado na Europa! Os torcedores ingleses vaiam a posse de bola francesa, enquanto os franceses respondem com cantos de provocação.");
        }
        
        // Qualquer jogo do Brasil
        else if (paisA.equalsIgnoreCase("Brasil") || paisB.equalsIgnoreCase("Brasil")) {
            String adversario = paisA.equalsIgnoreCase("Brasil") ? paisB : paisA;
            frases.add("A torcida brasileira faz festa no estádio, empurrando a Seleção contra " + adversario + ".");
            frases.add("O " + adversario + " monta uma forte linha defensiva para tentar segurar o ataque do Brasil.");
            frases.add("O camisa 10 do Brasil arriscou uma carretilha, inflamando a torcida contra a marcação de " + adversario + ".");
            frases.add("Clima de revanche! Os jogadores de " + adversario + " parecem engasgados com a última derrota para o Brasil.");
            frases.add("Gritos de 'Olé' começam a ecoar timidamente da torcida brasileira, irritando o time de " + adversario + ".");
        }
        
        // Qualquer outro confronto
        else {
            frases.add("Clima de Copa do Mundo! A rivalidade internacional entre " + paisA + " e " + paisB + " equilibra a partida.");
            frases.add("Orgulho nacional em jogo! Os jogadores de " + paisA + " e " + paisB + " dividem a bola com muita intensidade.");
            frases.add("Os capitães de " + paisA + " e " + paisB + " discutem asperamente com o árbitro central.");
            frases.add("Entrada duríssima! O clima de rivalidade faz os jogadores esquecerem que é apenas futebol.");
            frases.add("É xadrez em campo! O técnico de " + paisA + " ajusta a marcação para anular a principal estrela de " + paisB + ".");
            frases.add("Clima de caldeirão! A torcida local vaia intensamente sempre que " + paisB + " toca na bola.");
        }

        this.descricao = frases.get(random.nextInt(frases.size()));
    }
}
