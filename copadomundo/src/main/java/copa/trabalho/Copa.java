package copa.trabalho;

import java.util.ArrayList;
import java.util.Collections;


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
        System.out.println("3 - Adicionar Time");
        System.out.println("4 - Partida Jogador");
        System.out.println("5 - Simular Outras Partidas");
        System.out.println("6 - Verificar Campeão");
        System.out.println("7 - Mostrar Classificados");
    }

    public void iniciarCopa(){

        System.out.println("Copa iniciada!");

        gerarRodada();
    }

    public void gerarRodada(){

        Collections.shuffle(times);

        for(int i = 0; i < times.size(); i += 2){

            Time t1 = times.get(i);
            Time t2 = times.get(i + 1);

            System.out.println(t1.getNome() + " x " + t2.getNome());

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
