package copa.trabalho;

<<<<<<< HEAD
import java.sql.Time;
import java.util.ArrayList;
=======
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

// Cuidado aqui em cima às vezes importa "java.sql.Time" sem querer sozinho.
// Mantenha apenas os imports do ArrayList e Collections.
>>>>>>> 7103edad8eb8f7a7a49072ae19f5022ad690a5b5

public class Copa {

    private String nome;
    private String sede;
    private Time campeao;
<<<<<<< HEAD

    private ArrayList<Time> times;
    private ArrayList<Partida> partidas;
    private ArrayList<Time> empatados;

=======
    private Time timeDoCoracao;
    private int saldoPontos = 0; //Fiz já a adaptação aceitar a aposta...
    private Aposta apostaAtual;
    private Scanner scanner;
    

    private ArrayList<Time> timesAtivos;
    private ArrayList<Partida> partidas;
    private ArrayList<Time> classificados;
    // Removi a lista 'empatados' já que, em mata-mata (Copa), a classe Partida deve resolver empates internamente.
>>>>>>> 7103edad8eb8f7a7a49072ae19f5022ad690a5b5

    public Copa(String nome, String sede) {

        this.nome = nome;
        this.sede = sede;

<<<<<<< HEAD
        times = new ArrayList<>();
        partidas = new ArrayList<>();
        empatados = new ArrayList<>();
    }

    public void menu(){

        System.out.println("--+=====+ mini COPA DO MUNDO +=====+--");

        System.out.println("1 - Iniciar Copa");
        System.out.println("2 - Gerar Rodada");
        System.out.println("3 - Partida Jogador");
        System.out.println("4 - Simular Outras Partidas");
        System.out.println("5 - Verificar Campeão");
        System.out.println("6 - Mostrar Classificados");
=======
        scanner = new Scanner(System.in);
        timesAtivos = new ArrayList<>();
        partidas = new ArrayList<>();
        classificados = new ArrayList<>();
    }

    public void menu(){
        System.out.println("\n⊹ ࣪ ˖ ⋆𖦹°⚽︎⋆𖦹° ⊹ ࣪ ˖ mini COPA DO MUNDO ⊹ ࣪ ˖ ⋆𖦹°⚽︎⋆𖦹° ⊹ ࣪ ˖");
        System.out.println("1 - Adicionar Time (Cadastrar)");
        System.out.println("2 - Escolher Time do Coração");
        System.out.println("3 - Iniciar Copa / Gerar Rodada");
        System.out.println("4 - Jogar Minha Partida (Time do Coração)");
        System.out.println("5 - Simular Outras Partidas da Rodada");
        System.out.println("6 - Verificar Campeão");
        System.out.println("7 - Mostrar Times Ativos / Classificados");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");

        int escolha = scanner.nextInt();
        scanner.nextLine();

        switch(escolha) {
            case 1:
                System.out.print("Digite o nome do time: ");
                String nomeTime = scanner.nextLine();  
                Time novoTime = new Time(nomeTime);
                adicionarTime(novoTime);
                break;

            case 2:
                if (timesAtivos.isEmpty()) {
                    System.out.println("\n *+[Erro]+* Cadastre os times primeiro antes de escolher seu favorito!\n");
                } else {
                    escolherTimeDoCoracao();
                }
                break;

            case 3:
                if (timeDoCoracao == null) {
                    System.out.println("\n *+[Aviso]+* Você não escolheu um time do coração. O campeonato será 100% automático.");
                }
                iniciarCopa(); 
                break;

            case 4:
                if (partidas.isEmpty()) {
                    System.out.println("\nNenhuma partida gerada para esta rodada. Gere a rodada primeiro (Opção 3).\n");
                    break;
                }

                Partida partidaDoJogador = null;
                for (Partida p : partidas) {
                    if (p.getT1() == timeDoCoracao || p.getT2() == timeDoCoracao) {
                        partidaDoJogador = p;
                        break;
                    }
                }

                if (partidaDoJogador != null) {                  
                    partidaJogador(partidaDoJogador.getT1(), partidaDoJogador.getT2());
                } else {
                    System.out.println("\n+== Seu time do coração não tem jogos pendentes nesta rodada (Pode estar de folga ou já eliminado). ==+\n");
                }
                break;

            case 5:
                simularOutrasPartidas();
                break;

            case 6:
                verificarCampeao();
                break;

            case 7:
                mostrarClassificados();
                break;
                
            case 0:
                System.out.println("\nObrigado por jogar! Até a próxima. ⚽*+*\n");
                break;

            default:
                System.out.println("\n== Opção inválida. Por favor, escolha uma opção válida. ==\n");
        }
    }

    public void escolherTimeDoCoracao() {

        System.out.println("\n⚽︎⋆｡𖦹 ESCOLHA SEU TIME DO CORAÇÃO ⚽︎⋆｡𖦹");
        for (int i = 0; i < timesAtivos.size(); i++) {
            System.out.println(i + " - " + timesAtivos.get(i).getNome());
        }
        System.out.print("Digite o número do seu time: ");
        int indice = scanner.nextInt();
        this.timeDoCoracao = timesAtivos.get(indice);
        System.out.println("Você agora torce para: " + timeDoCoracao.getNome() + "!\n");
>>>>>>> 7103edad8eb8f7a7a49072ae19f5022ad690a5b5
    }

    public void iniciarCopa(){

<<<<<<< HEAD
        System.out.println("Copa iniciada!");

        gerarRodada();
    }

    public void gerarRodada(){

=======
        if (timesAtivos.size() < 2) {
            System.out.println("\nNão há times suficientes para iniciar a copa!\n");
            return;
        }

        if (this.timeDoCoracao == null) {
        escolherTimeDoCoracao();
        }   

        System.out.println("\nCopa iniciada!\n");
        gerarRodada();
    }

    // Reajustei aqui para trabalhar melhor com o time do coração e apostas \(0-0)/

    public void gerarRodada() {

        partidas.clear();
        classificados.clear();

        // Se o time do coração ainda estiver vivo, o jogador escolhe o oponente
        // Se só sobrou ele, ele já é o campeão

        if (timesAtivos.contains(timeDoCoracao)) {
            if (timesAtivos.size() == 1) {               
                return;
            }

            System.out.println("\n★⚽︎₊⊹ ᰔ °⋆ SEU TIME ESTÁ NA RODADA! ★⚽︎₊⊹ ᰔ °⋆\n");
            System.out.println("\nSeu time: " + timeDoCoracao.getNome());
            System.out.println("\nEscolha seu oponente para esta rodada:\n");
            
            ArrayList<Time> possiveisOponentes = new ArrayList<>(timesAtivos);
            possiveisOponentes.remove(timeDoCoracao); // Não pode jogar contra si mesmo, duh

            for (int i = 0; i < possiveisOponentes.size(); i++) {
                System.out.println(i + " - " + possiveisOponentes.get(i).getNome());
            }
            
            System.out.print("\nDigite o número do oponente: \n");
            int escolhaOp = scanner.nextInt();
            Time oponenteEscolhido = possiveisOponentes.get(escolhaOp);

            Partida partidaJogador = new Partida(timeDoCoracao, oponenteEscolhido);
            partidas.add(partidaJogador);

            // Remove esses dois da lista temporária para emparelhar o resto automaticamente, tá ok?
            ArrayList<Time> restoDosTimes = new ArrayList<>(timesAtivos);
            restoDosTimes.remove(timeDoCoracao);
            restoDosTimes.remove(oponenteEscolhido);

            Collections.shuffle(restoDosTimes);
            for (int i = 0; i < restoDosTimes.size(); i += 2) {
                if (i + 1 < restoDosTimes.size()) {
                    partidas.add(new Partida(restoDosTimes.get(i), restoDosTimes.get(i + 1)));
                } else {
                    Time folga = restoDosTimes.get(i);
                    classificados.add(folga);
                    System.out.println("\n" + folga.getNome() + " passou direto de folga.\n");
                }
            }
        } else {
            // Se o time do coração já foi eliminado, o campeonato segue 100% automático, sem apostas nem nada
            Collections.shuffle(timesAtivos);
            for (int i = 0; i < timesAtivos.size(); i += 2) {
                if (i + 1 < timesAtivos.size()) {
                    partidas.add(new Partida(timesAtivos.get(i), timesAtivos.get(i + 1)));
                } else {
                    classificados.add(timesAtivos.get(i));
                }
            }
        }
>>>>>>> 7103edad8eb8f7a7a49072ae19f5022ad690a5b5
    }

    public void adicionarTime(Time time){

<<<<<<< HEAD
        times.add(time);
    }

    public void partidaJogador(){

    }

    public void simularOutrasPartidas(){

    }

    public void verificarCampeao(){

    }

    public void mostrarClassificados(){

    }
}
=======
        timesAtivos.add(time);
        System.out.println("\nTime " + time.getNome() + " adicionado com sucesso!\n");
    }

    public void realizarAposta(Time t1, Time t2) {

        System.out.println("\n--- FAÇA SUA APOSTA ---");
        System.out.println("De qual time você vai ditar o destino? ");
        System.out.println("1 - " + t1.getNome());
        System.out.println("2 - " + t2.getNome());
        int escolhaTime = scanner.nextInt();
        Time timeApostado = (escolhaTime == 1) ? t1 : t2;

        System.out.println("\n +* Qual o seu palpite? *+ \n");
        System.out.println("\n1 - Vitória de " + timeApostado.getNome());
        System.out.println("\n2 - Derrota de " + timeApostado.getNome());
        System.out.println("3 - Empate");
        int palpite = scanner.nextInt();
        
        String tipo = "VITÓRIA";
        if (palpite == 2) tipo = "DERROTA";
        if (palpite == 3) tipo = "EMPATE";

        System.out.print("\nQuantos pontos quer apostar? \n");
        int pontos = scanner.nextInt();

        this.apostaAtual = new Aposta(timeApostado, tipo, pontos);
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

            realizarAposta(time1, time2); 

            partidaAlvo.gerarResultado();

            partidaAlvo.relatorio();      

            Time vencedor = partidaAlvo.verificarVencedor(); 

            if (vencedor != null) {
                classificados.add(vencedor);
            } else {
                System.out.println("Aviso: Empate detectado no mata-mata. Certifique-se de tratar os pênaltis!");
            }

            if (apostaAtual != null) {
                Time timeApostado = apostaAtual.getTimeEscolhido();
                String palpite = apostaAtual.getTipoAposta();
                int valor = apostaAtual.getPontosApostados();
                boolean acertou = false;

                if (vencedor == null) { // Foi empate
                    if (palpite.equals("EMPATE")) acertou = true;
                } else if (vencedor == timeApostado) {
                    if (palpite.equals("VITÓRIA")) acertou = true;
                } else { // O time apostado perdeu
                    if (palpite.equals("DERROTA")) acertou = true;
                }

                if (acertou) {
                    saldoPontos += valor;
                    System.out.println("\n*+*+* Você ACERTOU a aposta! Ganhou " + valor + " pontos. Saldo atual: " + saldoPontos);
                } else {
                    saldoPontos -= valor;
                    System.out.println("\nx°x Você ERROU a aposta! Perdeu " + valor + " pontos. Saldo atual: " + saldoPontos);
                }
                apostaAtual = null; // Limpa o palpite da rodada
            }

            partidas.remove(partidaAlvo); 


            if (partidas.isEmpty()) {
                timesAtivos = new ArrayList<>(classificados);
                System.out.println("\n ⚡︎ ⋆｡°⚽︎⋆｡° ⚡︎ Todas as partidas desta fase foram concluídas! 'Times Ativos' atualizados. ⚡︎ ⋆｡°⚽︎⋆｡° ⚡︎");
            }

        } else {
            System.out.println("\nEssa partida não pertence à rodada atual ou já foi jogada.\n");
        }
    }




    public void simularOutrasPartidas(){
        // Agora simula APENAS as partidas que ainda restam na lista... tbm considerando o time de folga
        // caso o numero de partidas doa rodada seja ímpar,
        //  o time que teve folga já foi adicionado aos classificados e não precisa ser simulado.
        if (partidas.isEmpty()) {
            System.out.println("\nNenhuma partida gerada para simular.\n");
            return;
        }

        for (int i = 0; i < partidas.size(); i++) {
            Partida partida = partidas.get(i);
            partida.gerarResultado();
                
            Time vencedor = partida.verificarVencedor();
            if (vencedor != null) {
                classificados.add(vencedor);
            }
        }

        timesAtivos = new ArrayList<>(classificados);
        partidas.clear(); // Limpa as partidas processadas
            
        System.out.println("\n✴︎˚｡ Todas as partidas restantes foram simuladas com sucesso! ✴︎˚｡\n");
    }

    public void verificarCampeao(){
        if (timesAtivos.size() == 1) {
            campeao = timesAtivos.get(0);
            System.out.println("\n⋆✴︎˚｡⋆ O grande campeão é: " + campeao.getNome() + " !!!!!!! ⋆✴︎˚｡⋆\n");
        } else {
            System.out.println("\nAinda restam " + timesAtivos.size() + " times ativos. A Copa não acabou.\n");
        }
    }

    public void mostrarClassificados(){
        if (!timesAtivos.isEmpty()) {
            System.out.println("\n⚡︎ ⋆｡𖦹°⚽︎⋆｡𖦹° ⚡︎ Times Ativos / Classificados ⚡︎ ⋆｡𖦹°⚽︎⋆｡𖦹° ⚡︎");
            for (Time time : timesAtivos) {
                System.out.println("- " + time.getNome());
            }
        } else {
            System.out.println("\nNão há times cadastrados ou ativos.\n");
        }
    }
}
>>>>>>> 7103edad8eb8f7a7a49072ae19f5022ad690a5b5
