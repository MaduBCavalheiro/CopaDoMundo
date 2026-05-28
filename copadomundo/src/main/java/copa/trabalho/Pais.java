package copa.trabalho;

import java.util.ArrayList;
import java.util.List;

public class Pais {

    private String nome;
    private List<Estadio> listaEstadios;

    public Pais(String nome) {
        try {
            this.nome = nome;
            this.listaEstadios = new ArrayList<>();
        } catch (Exception e) {
            System.out.println("Erro ao criar país: " + e.getMessage());
        }
    }

    public String getNome() {
        return nome;
    }

    public List<Estadio> getListaEstadios() {
        return listaEstadios;
    }

    public void setNome(String nome) {
        try {
            this.nome = nome;
        } catch (Exception e) {
            System.out.println("Erro ao definir nome do país: " + e.getMessage());
        }
    }

    public void adicionarEstadio(Estadio estadio) {
        try {
            listaEstadios.add(estadio);
            System.out.println("Estádio adicionado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao adicionar estádio: " + e.getMessage());
        }
    }

    public void exibirEstadios() {
        try {
            for (Estadio e : listaEstadios) {
                System.out.println(e.getNome());
            }
        } catch (Exception e) {
            System.out.println("Erro ao exibir estádios: " + e.getMessage());
        }
    }
}