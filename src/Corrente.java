import java.time.LocalDate;

public final class Corrente extends Conta implements Pix {

  public Corrente(String nomeConta, int numeroConta, String nomeCorrentista,
      int cpfCorrentista) {
    super(nomeConta, numeroConta, nomeCorrentista, cpfCorrentista);
  }

  // Implementação do método fazerPix da interface Pix
  @Override
  public void fazerPix(Conta desConta, double valorPix) {
    if (valorPix <= 0) {
      System.out.println("Valor de PIX deve ser positivo.");
      return;
    }
    if (this.getSaldo() >= valorPix) { // Realiza o saque da conta origem
      // tira o dinheiro da conta origem e registra a operação
      Operacao pixOut = new Operacao(LocalDate.now(), valorPix, Tipo.PIX_OUT);
      this.sacarDinheiro(valorPix);
      this.adicionarOperacao(pixOut);

      // deposita o dinheiro na conta destino e registra a operação
      desConta.depositarDinheiro(valorPix); // Deposita na conta destino
      Operacao pixIn = new Operacao(LocalDate.now(), valorPix, Tipo.PIX_IN);
      desConta.adicionarOperacao(pixIn);

      System.out.println("PIX realizado com sucesso!");
    } else {
      System.out.println("Erro ao realizar o PIX: saldo insuficiente.");
    }
  }

  // Implementação do método receberPix da interface Pix
  @Override
  public void receberPix(double valorPix) {
    if (valorPix <= 0) {
      System.out.println("Valor de PIX recebido deve ser positivo.");
      return;
    }

    this.depositarDinheiro(valorPix); // Realiza o depósito da conta corrente
    Operacao pixIn = new Operacao(LocalDate.now(), valorPix, Tipo.PIX_IN);
    this.adicionarOperacao(pixIn); // Registra a operação(pixIn);
    System.out.println("PIX recebido com sucesso!");
  }

  @Override
  public void cadastrarCPF() {
    getCpfCorrentista();
  }
}
