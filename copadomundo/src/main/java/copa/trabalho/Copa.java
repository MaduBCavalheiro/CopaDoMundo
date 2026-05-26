package copa.trabalho;

import java.sql.Time;
import java.util.ArrayList;

public class Copa {

    private String nome;
    private String sede;
    private Time campeao;

    private ArrayList<Time> times;
    private ArrayList<Partida> partidas;
    private ArrayList<Time> empatados;


    public Copa(String nome, String sede) {

        this.nome = nome;
        this.sede = sede;

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
    }

    public void iniciarCopa(){

        System.out.println("Copa iniciada!");

        gerarRodada();
    }

    public void gerarRodada(){

    }

    public void adicionarTime(Time time){

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
