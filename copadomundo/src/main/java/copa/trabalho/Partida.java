package copa.trabalho;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Partida {

    private Time t1;
    private Time t2;

    private int golsT1;
    private int golsT2;

    private double duracao;
    private LocalDateTime data;
    private DateTimeFormatter formatador;

    private int cartoesAmarelos;
    private int cartoesVermelhos;

    private boolean encerrada;
    private Time vencedor;

    private Estadio estadio;

    // Evento especial da partida
    private Evento eventoPartida;

    public Partida(Time t1, Time t2, Estadio estadio) {
        this.t1 = t1;
        this.t2 = t2;

        this.golsT1 = 0;
        this.golsT2 = 0;

        this.duracao = 90.0;
        this.data = LocalDateTime.now();
        this.formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        this.cartoesAmarelos = 0;
        this.cartoesVermelhos = 0;

        this.encerrada = false;
        this.vencedor = null;

        this.eventoPartida = null;

        this.estadio = estadio;
    }

    // GETTERS
    public Time getT1() {
        return t1;
    }

    public Time getT2() {
        return t2;
    }

    public Time getVencedor() {
        return vencedor;
    }

    public boolean isEncerrada() {
        return encerrada;
    }

    // RESULTADO DA PARTIDA
    public void gerarResultado() {
        // Evita gerar resultado duas vezes
        if (encerrada) {
            return;
        }

        Random random = new Random();

        golsT1 = random.nextInt(6);
        golsT2 = random.nextInt(6);

        gerarCartoesRandomicamente();
        gerarEventoPartida();

        // Define vencedor
        if (golsT1 > golsT2) {
            vencedor = t1;
        }
        else if (golsT2 > golsT1) {
            vencedor = t2;
        }
        else {
            // Mata-mata não permite empate
            int min = 5;
            int max = 10;
            if (random.nextBoolean()) {
                vencedor = t1;
                vencedor.AdicionarPontos(random.nextInt((max-min) + 1) + min);
                t2.AdicionarPontos(random.nextInt(min+1));
            }
            else {
                vencedor = t2;
                vencedor.AdicionarPontos(random.nextInt((max-min) + 1) + min);
                t1.AdicionarPontos(random.nextInt(min+1));
            }

            System.out.println("\n# Empate no tempo normal!");
            System.out.println("# Decisão nos pênaltis...\n");

            System.out.println("Devido ao empate, a quantidade acumulada de pontos de cada time foi comparada.");
            System.out.println(t1.getNome() + ": " + t1.getPontos() + " pontos.");
            System.out.println(t2.getNome() + ": " + t2.getPontos() + " pontos.");
            System.out.println("Por possuir uma quantidade maior de pontos, " + vencedor.getNome() + " passa adiante.");

            System.out.println(
                "Vencedor nos pênaltis: " +
                vencedor.getNome()
            );
        }

        encerrada = true;
    }


    public void gerarCartoesRandomicamente() {
        Random random = new Random();
        cartoesAmarelos = random.nextInt(8);
        cartoesVermelhos = random.nextInt(3);
    }


    public void gerarEventoPartida() {
        Random random = new Random();
        int chance = random.nextInt(100);


        if (chance < 60) {
            eventoPartida = new EventoAbsurdo();
        }

        else if (chance < 90) {
            eventoPartida = new EventoGenerico();
        }

        else {
      
            if (t1.getNome() != null && t2.getNome() != null && !t1.getNome().isEmpty()) {
                eventoPartida = new EventoRivalidade(t1.getNome(), t2.getNome());
            } else {
                eventoPartida = new EventoGenerico();
            }
        }
    }

   
    public Time verificarVencedor() {
        if (!encerrada) {
            gerarResultado();
        }
        return vencedor;
    }

    
    public void relatorio() {
        System.out.println("\n══════════════════════════════════");
        System.out.println("      # RELATÓRIO DA PARTIDA #");
        System.out.println("══════════════════════════════════");

        System.out.println(
            "\n" +
            t1.getNome() +
            " " +
            golsT1 +
            " x " +
            golsT2 +
            " " +
            t2.getNome()
        );

        if (golsT1 == golsT2) {
            System.out.println("\nEmpate no tempo normal.");

            System.out.println("Devido ao empate, a quantidade acumulada de pontos de cada time foi comparada.");
            System.out.println(t1.getNome() + ": " + t1.getPontos() + " pontos.");
            System.out.println(t2.getNome() + ": " + t2.getPontos() + " pontos.");
            System.out.println("Por possuir uma quantidade maior de pontos, " + vencedor.getNome() + " passa adiante.");

            System.out.println(
                "Vencedor nos pênaltis: " +
                vencedor.getNome()
            );
        }
        else {
            System.out.println(
                "\nVencedor: " +
                vencedor.getNome()
            );
        }

        System.out.println(
            "\nDuração: " +
            duracao +
            " minutos"
        );

        System.out.println(
            "Data: " +
            data.format(formatador)
        );

        System.out.println(
            "Cartões amarelos: " +
            cartoesAmarelos
        );

        System.out.println(
            "Cartões vermelhos: " +
            cartoesVermelhos
        );

        System.out.println(
            "Técnico do "+ t1.getNome() +": " +
            t1.getTecnico()
        );

        System.out.println(
            "Técnico do "+ t2.getNome() +": " +
            t2.getTecnico()
        );

        System.out.println(
            "Estádio: "+ estadio.getNome() +" | " +
            estadio.getPais()
        );
      
        if (eventoPartida != null) {
            System.out.println(
                "\n════ EVENTO DA PARTIDA ════"
            );
            eventoPartida.exibirEvento();
        }

        System.out.println(
            "══════════════════════════════════\n"
        );
    }
}