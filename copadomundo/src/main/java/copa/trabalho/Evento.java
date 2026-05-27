package copa.trabalho;

import java.util.Random;

public abstract class Evento {
    protected boolean raridade;
    protected String descricao;
    protected Random random = new Random();

    public abstract void gerarDescricao();

    public void exibirEvento() {
        System.out.println("----------------------------------------");
        System.out.println("[EVENTO] Raro: " + (raridade ? "Sim" : "Não"));
        System.out.println("Descrição: " + descricao);
        System.out.println("----------------------------------------");
    }

    public boolean isRaridade() {
        return raridade;
    }

    public String getDescricao() {
        return descricao;
    }
}
