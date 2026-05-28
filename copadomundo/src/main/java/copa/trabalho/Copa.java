package copa.trabalho;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class Copa {

    private String nome;
    private String sede;
    private Time campeao;
    private Time timeDoCoracao;
    private int saldoPontos = 0;
    private Aposta apostaAtual;
    private Scanner scanner;
    private ArrayList<Time> timesAtivos;
    private ArrayList<Partida> partidas;
    private ArrayList<Time> classificados;

    public Copa(String nome, String sede) {
        this.nome = nome;
        this.sede = sede;
        scanner = new Scanner(System.in);
        timesAtivos = new ArrayList<>();
        partidas = new ArrayList<>();
        classificados = new ArrayList<>();
    }

    public void menu(){
        System.out.println("\n⊹ ࣪ ˖ ⋆𖦹°⚽︎⋆𖦹° ⊹ ࣪ ˖ mini COPA DO MUNDO ⊹ ࣪ ˖ ⋆𖦹°⚽︎⋆𖦹° ⊹ ࣪ ˖");
        System.out.println("1 - Adicionar Time (Cadastrar)");
        System.out.println("2 - Escolher Time do Coração");
        System.out.println("3 - JOGAR COPA DO MUNDO");
        System.out.println("7 - Mostrar Status dos Times Ativos");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");

        int escolha = scanner.nextInt();
        scanner.nextLine();

        switch(escolha) {
            case 1:
                System.out.print("Digite o nome do time: ");
                String nomeTime = scanner.nextLine();  
                adicionarTime(new Time(nomeTime));
                break;

            case 2:
                if (timesAtivos.isEmpty()) {
                    System.out.println("\n *+[Erro]+* Cadastre os times primeiro!\n");
                } else {
                    escolherTimeDoCoracao();
                }
                break;

            case 3:
                iniciarEExecutarCopa(); 
                break;

            case 7:
                mostrarClassificados();
                break;
                
            case 0:
                System.out.println("\nObrigado por jogar! Até a próxima. ⚽*+*\n");
                break;

            default:
                System.out.println("\n== Opção inválida. ==\n");
        }
    }

    public void escolherTimeDoCoracao() {
        System.out.println("\n⚽︎⋆｡𖦹 ESCOLHA SEU TIME DO CORAÇÃO ⚽︎⋆｡𖦹");
        for (int i = 0; i < timesAtivos.size(); i++) {
            System.out.println(i + " - " + timesAtivos.get(i).getNome());
        }
        System.out.print("Digite o número do seu time: ");
        int indice = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer do scanner
        
        if (indice >= 0 && indice < timesAtivos.size()) {
            this.timeDoCoracao = timesAtivos.get(indice);
            System.out.println("Você agora torce para: " + timeDoCoracao.getNome() + "!\n");
        } else {
            System.out.println("\nÍndice inválido! Seleção cancelada.\n");
        }
    }

    public void iniciarEExecutarCopa() {
        if (timesAtivos.size() < 2) {
            System.out.println("\nNão há times suficientes para iniciar a copa!\n");
            return;
        }

        if (this.timeDoCoracao == null) {
            System.out.println("\n *+[Aviso]+* Você não escolheu um time do coração anteriormente.");
            escolherTimeDoCoracao();
        }   

        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║    A COPA DO MUNDO COMEÇOU DE VERDADE! ║");
        System.out.println("╚════════════════════════════════════════╝\n");

        while (timesAtivos.size() > 1) {
            System.out.println("\n--- FASE COM " + timesAtivos.size() + " TIMES ---");
            
            gerarRodada();

            Partida partidaDoJogador = null;
            for (Partida p : partidas) {
                if (p.getT1() == timeDoCoracao || p.getT2() == timeDoCoracao) {
                    partidaDoJogador = p;
                    break;
                }
            }

            if (partidaDoJogador != null) {
                System.out.println("\nChegou a hora do seu grande confronto!");
                partidaJogador(partidaDoJogador.getT1(), partidaDoJogador.getT2());
            } else if (timesAtivos.contains(timeDoCoracao)) {
                System.out.println("\nSeu time está de folga nesta rodada e assistirá aos demais.");
            } else {
                System.out.println("\nSeu time foi eliminado. Simulando o restante da competição...");
            }

            if (!partidas.isEmpty()) {
                simularOutrasPartidas();
            }

            System.out.println("\nPressione ENTER para avançar para a próxima etapa da Copa...");
            scanner.nextLine();
        }
        
        verificarCampeao();
    }

    public void gerarRodada() {
        partidas.clear();
        classificados.clear(); 

        if (timesAtivos.contains(timeDoCoracao)) {
            System.out.println("\n★ Seu time (" + timeDoCoracao.getNome() + ") está vivo na disputa!");
            System.out.println("Escolha seu oponente para esta fase:");
            
            ArrayList<Time> possiveisOponentes = new ArrayList<>(timesAtivos);
            possiveisOponentes.remove(timeDoCoracao); 

            for (int i = 0; i < possiveisOponentes.size(); i++) {
                System.out.println(i + " - " + possiveisOponentes.get(i).getNome());
            }
            
            System.out.print("Digite o número do oponente: ");
            int escolhaOp = scanner.nextInt();
            scanner.nextLine(); 
            
            if (escolhaOp < 0 || escolhaOp >= possiveisOponentes.size()) {
                System.out.println("Opção inválida! Oponente automático selecionado.");
                escolhaOp = 0;
            }
            
            Time oponenteEscolhido = possiveisOponentes.get(escolhaOp);
            partidas.add(new Partida(timeDoCoracao, oponenteEscolhido));

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
                    System.out.println("\n* " + folga.getNome() + " passou direto por folga técnica.");
                }
            }
        } else {
            Collections.shuffle(timesAtivos);
            for (int i = 0; i < timesAtivos.size(); i += 2) {
                if (i + 1 < timesAtivos.size()) {
                    partidas.add(new Partida(timesAtivos.get(i), timesAtivos.get(i + 1)));
                } else {
                    classificados.add(timesAtivos.get(i));
                }
            }
        }
    }

    public void adicionarTime(Time time){
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
        System.out.println("1 - Vitória | 2 - Derrota | 3 - Empate");
        int palpite = scanner.nextInt();
        
        String tipo = "VITÓRIA";
        if (palpite == 2) tipo = "DERROTA";
        if (palpite == 3) tipo = "EMPATE";

        System.out.print("\nQuantos pontos quer apostar? ");
        int pontos = scanner.nextInt();
        scanner.nextLine();

        this.apostaAtual = new Aposta(timeApostado, tipo, pontos);
    }

    public void partidaJogador(Time time1, Time time2){
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

                if (vencedor == null) { 
                    if (palpite.equals("EMPATE")) acertou = true;
                } else if (vencedor == timeApostado) {
                    if (palpite.equals("VITÓRIA")) acertou = true;
                } else { 
                    if (palpite.equals("DERROTA")) acertou = true;
                }

                if (acertou) {
                    saldoPontos += valor;
                    System.out.println("\n*+*+* Você ACERTOU a aposta! Ganhou " + valor + " pontos. Saldo atual: " + saldoPontos);
                } else {
                    saldoPontos -= valor;
                    System.out.println("\nx°x Você ERROU a aposta! Perdeu " + valor + " pontos. Saldo atual: " + saldoPontos);
                }
                apostaAtual = null; 
            }

            partidas.remove(partidaAlvo); 
            verificarFimDaRodada(); 

        } else {
            System.out.println("\nEssa partida não pertence à rodada atual ou já foi jogada.\n");
        }
    }

    public void simularOutrasPartidas(){
        ArrayList<Partida> paraSimular = new ArrayList<>(partidas);

        for (Partida partida : paraSimular) {
            partida.gerarResultado();
            Time vencedor = partida.verificarVencedor();
            if (vencedor != null) {
                classificados.add(vencedor);
            }
            partidas.remove(partida); 
        }
        // CORREÇÃO: Tirada a barra invertida dupla que quebrava o print
        System.out.println("\n✴︎˚｡ Todas as outras partidas da rodada foram computadas nos bastidores!");
        verificarFimDaRodada();
    }

    private void verificarFimDaRodada() {
        if (partidas.isEmpty()) {
            timesAtivos = new ArrayList<>(classificados);
            System.out.println("\n ⚡︎ ⋆｡°⚽︎⋆｡° ⚡︎ Todas as partidas desta fase foram concluídas! 'Times Ativos' atualizados. ⚡︎ ⋆｡°⚽︎⋆｡° ⚡︎");
        }
    }

    public void verificarCampeao(){
        if (timesAtivos.size() == 1) {
            campeao = timesAtivos.get(0);
            System.out.println("\n⋆✴︎˚｡⋆ O grande campeão é: " + campeao.getNome() + " !!!!!!! ⋆✴︎˚｡⋆\n");
        }
    }

    public void mostrarClassificados(){
        if (!timesAtivos.isEmpty()) {
            System.out.println("\n⚡︎ ⋆｡𖦹°⚽︎⋆｡𖦹° ⚡︎ Times Ativos / Classificados ⚡︎ ⋆｡𖦹°⚽︎⋆｡𖦹° ⚡︎");
            for (Time time : timesAtivos) {
                System.out.println("- " + time.getNome());
            }
        } else {
            System.out.println("\n-- Não há times cadastrados ou ativos. --\n");
        }
    }
}