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

  public double calcularImpacto() {
    // Determina se o valor deve ser subtraído ou somado ao saldo
    if (this.tipo == Tipo.SAQUE || this.tipo == Tipo.PIX_OUT) {
      return -this.valor;
    }
    return this.valor;
  }

  public LocalDate getData() {
    return data;
  }

  public void setData(LocalDate data) {
    this.data = data;
  }

  public double getValor() {
    return valor;
  }

  public void setValor(double valor) {
    this.valor = valor;
  }

  public Tipo getTipo() {
    return tipo;
  }

  public void setTipo(Tipo tipo) {
    this.tipo = tipo;
  }

}
