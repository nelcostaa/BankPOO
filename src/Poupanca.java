import java.time.LocalDate;

public final class Poupanca extends Conta implements Remunerada {

  public Poupanca(int numeroConta, String nomeCorrentista,
      int cpfCorrentista) {
    super(numeroConta, nomeCorrentista, cpfCorrentista);
  }

  public void aplicarTaxaCorrecao(double taxaCorrecao) {
    System.out.println("Saldo anterior = " + this.getSaldo());

    double novoSaldo = this.getSaldo() * (1 + taxaCorrecao);
    this.setSaldo(novoSaldo);

    Operacao taxa = new Operacao(LocalDate.now(), novoSaldo, Tipo.CORRECAO);
    this.adicionarOperacao(taxa);

    System.out.println("---------------");
    System.out.println("Saldo atualizado = " + this.getSaldo());

  }
}