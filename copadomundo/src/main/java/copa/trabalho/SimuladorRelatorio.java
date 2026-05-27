package copa.trabalho;
// Esta classe serve como exemplo de como seria aplicada a probabilidade de 
// geração de evento no relatório. Pode ser descartada se necessário.
import java.util.Random;

public class SimuladorRelatorio {
    public static void main(String[] args) {
        String time1 = "Time A";
        String time2 = "Time B";

        System.out.println("=== GERANDO RELATÓRIO DA PARTIDA ===");
        
        Evento eventoSorteado = sortearEvento(time1, time2);
        eventoSorteado.exibirEvento();
    }

    public static Evento sortearEvento(String t1, String t2) {
        Random random = new Random();
        int chance = random.nextInt(100);

        if (chance < 60) {
            return new EventoAbsurdo();
        } 
        else if (chance < 90) {
            return new EventoGenerico();
        } 
        else {
            return new EventoRivalidade(t1, t2);
        }
    }
}
