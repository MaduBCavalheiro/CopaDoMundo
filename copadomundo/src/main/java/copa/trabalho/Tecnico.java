package copa.trabalho;

public class Tecnico {

    private String estrategia;

    public Tecnico(String estrategia) {
        try {
            this.estrategia = estrategia;
        } catch (Exception e) {
            System.out.println("Erro ao criar técnico: " + e.getMessage());
        }
    }

    public String getEstrategia() {
        return estrategia;
    }

    public void setEstrategia(String estrategia) {
        try {
            this.estrategia = estrategia;
        } catch (Exception e) {
            System.out.println("Erro ao definir estratégia: " + e.getMessage());
        }
    }

    public void exibirTecnico() {
        try {
            System.out.println("Estratégia: " + estrategia);
        } catch (Exception e) {
            System.out.println("Erro ao exibir técnico: " + e.getMessage());
        }
    }
}
