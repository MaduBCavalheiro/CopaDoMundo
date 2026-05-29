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

    // Método auxiliar para limpar a tela visualmente
    private void limparTela() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    public void menu(){
        System.out.println("=================================================");
        System.out.println("               MINI COPA DO MUNDO                ");
        System.out.println("=================================================");
        System.out.println("1 - Adicionar Time (Cadastrar)");
        System.out.println("2 - Escolher Time do Coração");
        System.out.println("3 - JOGAR COPA DO MUNDO");
        System.out.println("7 - Mostrar Status dos Times Ativos");
        System.out.println("0 - Sair");
        System.out.println("=================================================");
        System.out.print("Escolha uma opção: ");

        int escolha = scanner.nextInt();
        scanner.nextLine();

        switch(escolha) {
            case 1:
                limparTela();
                System.out.print("Digite o nome do time: ");
                String nomeTime = scanner.nextLine();  
                adicionarTime(new Time(nomeTime));
                break;

            case 2:
                limparTela();
                if (timesAtivos.isEmpty()) {
                    System.out.println("\n[ERRO] Cadastre os times primeiro!\n");
                } else {
                    escolherTimeDoCoracao();
                }
                break;

            case 3:
                limparTela();
                iniciarEExecutarCopa(); 
                break;

            case 7:
                limparTela();
                mostrarClassificados();
                break;
                
            case 0:
                limparTela();
                System.out.println("\nObrigado por jogar! Até a próxima. *\n");
                System.exit(0); // CORREÇÃO: Fecha o programa de verdade, quebrando o loop da Main
                break;

            default:
                System.out.println("\n== Opção inválida. ==\n");
        }
    }

    public void escolherTimeDoCoracao() {
        System.out.println("========================================");
        System.out.println("       ESCOLHA SEU TIME DO CORAÇÃO       ");
        System.out.println("========================================");
        for (int i = 0; i < timesAtivos.size(); i++) {
            System.out.println(i + " - " + timesAtivos.get(i).getNome());
        }
        System.out.print("Digite o número do seu time: ");
        int indice = scanner.nextInt();
        scanner.nextLine(); 
        
        if (indice >= 0 && indice < timesAtivos.size()) {
            this.timeDoCoracao = timesAtivos.get(indice);
            System.out.println("\n[OK] Você agora torce para: " + timeDoCoracao.getNome() + "!\n");
        } else {
            System.out.println("\n[ERRO] Índice inválido! Seleção cancelada.\n");
        }
    }

    public void iniciarEExecutarCopa() {
        if (timesAtivos.size() < 2) {
            System.out.println("\nNão há times suficientes para iniciar a copa!\n");
            return;
        }

        if (this.timeDoCoracao == null) {
            System.out.println("\n[AVISO] Você não escolheu um time do coração anteriormente.");
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
                System.out.println("\n-> Chegou a hora do seu grande confronto!");
                partidaJogador(partidaDoJogador.getT1(), partidaDoJogador.getT2());
            } else if (timesAtivos.contains(timeDoCoracao)) {
                System.out.println("\n[INFO] Seu time está de folga nesta rodada e assistirá aos demais.");
            } else {
                System.out.println("\n[INFO] Seu time foi eliminado. Simulando o restante da competição...");
            }

            if (!partidas.isEmpty()) {
                simularOutrasPartidas();
            }

            System.out.println("\nPressione ENTER para avançar para a próxima etapa da Copa...");
            scanner.nextLine();
            limparTela(); // Limpa a tela para a próxima fase do torneio
        }
        
        verificarCampeao();
    }

    public void gerarRodada() {
        partidas.clear();
        classificados.clear(); 

        if (timesAtivos.contains(timeDoCoracao)) {
            System.out.println("\n* Seu time (" + timeDoCoracao.getNome() + ") está vivo na disputa!");
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

        System.out.println("\n+* Qual o seu palpite? *+");
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
                    System.out.println("\n[POSTA] Você ACERTOU! Ganhou " + valor + " pontos. Saldo: " + saldoPontos);
                } else {
                    saldoPontos -= valor;
                    System.out.println("\n[APOSTA] Você ERROU! Perdeu " + valor + " pontos. Saldo: " + saldoPontos);
                }
                apostaAtual = null; 
            }

            partidas.remove(partidaAlvo); 
            verificarFimDaRodada();

        } else {
            System.out.println("\nEssa partida não pertence à rodada atual.\n");
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
        System.out.println("\n[SISTEMA] Todas as outras partidas foram simuladas nos bastidores!");
        verificarFimDaRodada();
    }

    private void verificarFimDaRodada() {
        if (partidas.isEmpty()) {
            timesAtivos = new ArrayList<>(classificados);
            System.out.println("\n--- Rodada concluída! Lista de times ativos atualizada. ---");
        }
    }

    public void verificarCampeao(){
        if (timesAtivos.size() == 1) {
            campeao = timesAtivos.get(0);
            System.out.println("\n=================================================");
            System.out.println("  O GRANDE CAMPEÃO DA COPA É: " + campeao.getNome().toUpperCase());
            System.out.println("=================================================\n");
        }
    }

    public void mostrarClassificados(){
        if (!timesAtivos.isEmpty()) {
            System.out.println("========================================");
            System.out.println("      TIMES ATIVOS NA COMPETIÇÃO       ");
            System.out.println("========================================");
            for (Time time : timesAtivos) {
                System.out.println("- " + time.getNome());
            }
            System.out.println("========================================");
        } else {
            System.out.println("\n-- Não há times cadastrados ou ativos. --\n");
        }
    }
}