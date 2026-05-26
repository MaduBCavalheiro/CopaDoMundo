package copa.trabalho;

import java.util.ArrayList;
import java.util.Collections;

// Cuidado aqui em cima às vezes importa "java.sql.Time" sem querer sozinho.
// Mantenha apenas os imports do ArrayList e Collections.

public class Copa {

    private String nome;
    private String sede;
    private Time campeao;

    private ArrayList<Time> timesAtivos;
    private ArrayList<Partida> partidas;
    private ArrayList<Time> classificados;
    // Removi a lista 'empatados' já que, em mata-mata (Copa), a classe Partida deve resolver empates internamente.

    public Copa(String nome, String sede) {
        this.nome = nome;
        this.sede = sede;

        timesAtivos = new ArrayList<>();
        partidas = new ArrayList<>();
        classificados = new ArrayList<>();
    }

    public void menu(){
        System.out.println("\n⊹ ࣪ ˖ ⋆𖦹°⚽︎⋆𖦹° ⊹ ࣪ ˖ mini COPA DO MUNDO ⊹ ࣪ ˖ ⋆𖦹°⚽︎⋆𖦹° ⊹ ࣪ ˖");
        System.out.println("1 - Iniciar Copa");
        System.out.println("2 - Gerar Rodada");
        System.out.println("3 - Adicionar Time");
        System.out.println("4 - Partida Jogador");
        System.out.println("5 - Simular Outras Partidas");
        System.out.println("6 - Verificar Campeão");
        System.out.println("7 - Mostrar Classificados");
    }

    public void iniciarCopa(){
        if (timesAtivos.size() < 2) {
            System.out.println("Não há times suficientes para iniciar a copa!");
            return;
        }
        System.out.println("Copa iniciada!");
        gerarRodada();
    }

    public void gerarRodada(){
        partidas.clear(); 
        classificados.clear(); // Limpa os classificados da rodada anterior
        
        Collections.shuffle(timesAtivos);

        for (int i = 0; i < timesAtivos.size(); i += 2) {
            if (i + 1 < timesAtivos.size()) {
                Time t1 = timesAtivos.get(i);
                Time t2 = timesAtivos.get(i + 1);

                Partida partida = new Partida(t1, t2);
                partidas.add(partida);

                System.out.println(t1.getNome() + " x " + t2.getNome());
            } else {
                // Tratamento para número ÍMPAR de times: o último passa direto
                // foi uma complicação que surgiu no meio do desenvolvimento, 
                // mas é uma regra comum em torneios com número ímpar de participantes.

                Time folga = timesAtivos.get(i);
                classificados.add(folga);
                System.out.println(folga.getNome() + " passou direto nesta rodada (Folga).");
            }
        }
    }

    public void adicionarTime(Time time){
        timesAtivos.add(time);
        System.out.println("Time " + time.getNome() + " adicionado com sucesso!");
    }

    public void partidaJogador(Time time1, Time time2){
        // Busca se essa partida já foi gerada na rodada atual
        Partida partidaAlvo = null;
        for (Partida p : partidas) {
            if ((p.getT1() == time1 && p.getT2() == time2) || (p.getT1() == time2 && p.getT2() == time1)) {
                partidaAlvo = p;
                break;
            }
        }

        if (partidaAlvo != null) {
            partidaAlvo.gerarResultado();
            partidaAlvo.relatorio();
            Time vencedor = partidaAlvo.verificarVencedor();
            classificados.add(vencedor);
            partidas.remove(partidaAlvo); // Remove da lista para não ser simulada de novo aaaaah!!!
        } else {
            System.out.println("Essa partida não pertence à rodada atual ou já foi jogada.");
        }
    }

    public void simularOutrasPartidas(){
        // Agora simula APENAS as partidas que ainda restam na lista... tbm considerando o time de folga
        // caso o numero de partidas doa rodada seja ímpar,
        //  o time que teve folga já foi adicionado aos classificados e não precisa ser simulado.
        if (partidas.isEmpty()) {
            System.out.println("Nenhuma partida gerada para simular.");
            return;
        }

        for (int i = 0; i < partidas.size(); i++) {
            Partida partida = partidas.get(i);
            partida.gerarResultado();
            
            Time vencedor = partida.verificarVencedor();
            classificados.add(vencedor);
        }

        // CORREÇÃO CRÍTICA: Cria uma nova lista para não clonar a referência e quebrar o .clear()
        // Tava bugando essa parte na lógica...
        timesAtivos = new ArrayList<>(classificados);
        partidas.clear(); // Limpa as partidas processadas
        
        System.out.println("Todas as partidas restantes foram simuladas!");
    }

    public void verificarCampeao(){
        if (timesAtivos.size() == 1) {
            campeao = timesAtivos.get(0);
            System.out.println("⋆✴︎˚｡⋆ O grande campeão é: " + campeao.getNome() + " !!!!!!! ⋆✴︎˚｡⋆");
        } else {
            System.out.println("Ainda restam " + timesAtivos.size() + " times ativos. A Copa não acabou.");
        }
    }

    public void mostrarClassificados(){
        if (!timesAtivos.isEmpty()) {
            System.out.println("\n⚡︎ ⋆｡𖦹°⚽︎⋆｡𖦹° ⚡︎ Times Ativos / Classificados ⚡︎ ⋆｡𖦹°⚽︎⋆｡𖦹° ⚡︎");
            for (Time time : timesAtivos) {
                System.out.println("- " + time.getNome());
            }
        } else {
            System.out.println("Não há times cadastrados ou ativos.");
        }
    }
}