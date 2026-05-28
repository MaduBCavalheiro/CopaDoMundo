package copa.trabalho;

public class Estadio {

    private String nome;
    private Pais pais;
    private double capacidade;

    public Estadio(String nome, Pais pais, double capacidade) {
        try {
            this.nome = nome;
            this.pais = pais;
            this.capacidade = capacidade;
        } catch (Exception e) {
            System.out.println("Erro ao criar estádio: " + e.getMessage());
        }
    }

    public String getNome() {
        return nome;
    }

    public Pais getPais() {
        return pais;
    }

    public double getCapacidade() {
        return capacidade;
    }

    public void setNome(String nome) {
        try {
            this.nome = nome;
        } catch (Exception e) {
            System.out.println("Erro ao definir nome: " + e.getMessage());
        }
    }

    public void setPais(Pais pais) {
        try {
            this.pais = pais;
        } catch (Exception e) {
            System.out.println("Erro ao definir país: " + e.getMessage());
        }
    }

    public void setCapacidade(double capacidade) {
        try {
            this.capacidade = capacidade;
        } catch (Exception e) {
            System.out.println("Erro ao definir capacidade: " + e.getMessage());
        }
    }

    public void exibirEstadio() {
        try {
            System.out.println("Nome: " + nome);
            System.out.println("País: " + pais.getNome());
            System.out.println("Capacidade: " + capacidade);
        } catch (Exception e) {
            System.out.println("Erro ao exibir estádio: " + e.getMessage());
        }
    }
}
