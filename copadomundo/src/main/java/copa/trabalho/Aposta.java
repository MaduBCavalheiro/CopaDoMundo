package copa.trabalho;

public class Aposta {
    
    private Time timeEscolhido;
    private String tipoAposta; 
    private int pontosApostados;


    public Aposta(Time timeEscolhido, String tipoAposta, int pontosApostados) {
        this.timeEscolhido = timeEscolhido;
        this.tipoAposta = tipoAposta;
        this.pontosApostados = pontosApostados;
    }


    public Time getTimeEscolhido() {
        return timeEscolhido;
    }

    public String getTipoAposta() {
        return tipoAposta;
    }

    public int getPontosApostados() {
        return pontosApostados;
    }


    public void setTimeEscolhido(Time timeEscolhido) {
        this.timeEscolhido = timeEscolhido;
    }

    public void setTipoAposta(String tipoAposta) {
        this.tipoAposta = tipoAposta;
    }

    public void setPontosApostados(int pontosApostados) {
        this.pontosApostados = pontosApostados;
    }
}