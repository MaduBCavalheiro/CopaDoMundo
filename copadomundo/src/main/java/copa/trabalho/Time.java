package copa.trabalho;

public class Time{
    private String Nome;
    private Tecnico tecnico;
    private int pontos;
    private int SaldoGols;
    private int CartoesAmarelos;
    private int CartoesVermelhos;
    

    public Time(String nome){
      this.tecnico = null;
      this.pontos = 0;
      this.SaldoGols = 0;
      this.CartoesAmarelos = 0;
      this.CartoesVermelhos = 0;
}
          
     
     public void AdicionarPontos(int pontos){
        this.pontos += pontos;
     }
     
     public void AtualizarSaldosGols(int gols){
      this.SaldoGols += gols;
     }

     public void AdicionarCartaoAmarelo(){
      CartoesAmarelos++;
     }

     public void AdicionarCartaoVermelho(){
      CartoesVermelhos++;
     }

      public String getNome(){
      return Nome;
       }

       public Tecnico getTecnico(){
         return tecnico;
       }

       public int getPontos(){
         return pontos;
       }

       public int getSaldoGols(){
         return SaldoGols;
       }

       public int getCartoesAmarelos(){
         return CartoesAmarelos;
       }

       public int getCartoesVermelhos(){
         return CartoesVermelhos;
       }
}