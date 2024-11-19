import java.time.LocalDate;

public class Operacao {

  private LocalDate data;
  private double valor;
  private Tipo tipo;

  public Operacao(LocalDate data, double valor, Tipo tipo) {
    this.data = data;
    this.valor = valor;
    this.tipo = tipo;
  }
}
