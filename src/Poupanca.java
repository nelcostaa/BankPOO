public final class Poupanca extends Conta implements Remunerada {

  public Poupanca(String nomeConta, int numeroConta, String nomeCorrentista,
                  int cpfCorrentista) {
    super(nomeConta, numeroConta, nomeCorrentista, cpfCorrentista);
  }

  public void aplicarTaxaCorrecao(double taxaCorrecao) {
    double novoSaldo = this.getSaldo() * (1 + taxaCorrecao);
    this.setSaldo(novoSaldo);
  }
}