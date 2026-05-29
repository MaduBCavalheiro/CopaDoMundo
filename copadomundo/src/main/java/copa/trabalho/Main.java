package copa.trabalho;

public class Main {
    public static void main(String[] args) {
        
        Copa miniCopa = new Copa("Mini Copa do Mundo FAI", "Brasil");

        miniCopa.adicionarTime(new Time("Brasil"));
        miniCopa.adicionarTime(new Time("Argentina"));
        miniCopa.adicionarTime(new Time("França"));
        miniCopa.adicionarTime(new Time("Alemanha"));
        miniCopa.adicionarTime(new Time("Japão"));
        miniCopa.adicionarTime(new Time("Marrocos"));
        miniCopa.adicionarTime(new Time("Itália"));
        miniCopa.adicionarTime(new Time("Espanha"));

        System.out.println("\n--- Configuração Inicial Concluída com Sucesso! ---");


        int opcao = -1;
        
        while (true) {
            miniCopa.menu();
        }

    }
}