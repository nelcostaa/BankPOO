import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Conta extends Banco {
  private int numeroConta;
  private String nomeCorrentista;
  private int cpfCorrentista;
  private ArrayList<Operacao> operacoes;
  private double saldo;

  public Conta(int numeroConta, String nomeCorrentista,
      int cpfCorrentista) {
    super(nomeCorrentista);
    this.numeroConta = numeroConta;
    this.nomeCorrentista = nomeCorrentista;
    this.cpfCorrentista = cpfCorrentista;
    this.operacoes = new ArrayList<>();
    this.saldo = 0.0; // Inicializando o saldo com 0
  }

  public void setSaldo(double saldo) {
    this.saldo = saldo;
  }

  public double getSaldo() {
    return saldo;
  }

  public void adicionarOperacao(Operacao operacao) {
    this.operacoes.add(operacao);
  }

  public boolean sacarDinheiro(double qntdDinheiro) {
    if (qntdDinheiro <= 0) {
      System.out.println("Valor de saque deve ser positivo.");
      return false;
    }
    if (this.getSaldo() < qntdDinheiro) {
      System.out.println("Saldo insuficiente para o saque.");
      return false;
    }

    this.saldo -= qntdDinheiro;
    Operacao saque = new Operacao(LocalDate.now(), qntdDinheiro, Tipo.SAQUE);
    adicionarOperacao(saque); // Registra a operação de saque

    System.out.println();
    System.out.println("Saldo atual: " + this.saldo);

    return true;
  }

  public void depositarDinheiro(double qntdDinheiro) {
    if (qntdDinheiro <= 0) {
      System.out.println("Valor de depósito deve ser positivo.");
      return;
    }
    this.saldo += qntdDinheiro;
    Operacao deposito = new Operacao(LocalDate.now(), qntdDinheiro, Tipo.DEPOSITO);
    adicionarOperacao(deposito); // Registra a operação de depósito

    System.out.println();
    System.out.println("Saldo atual: " + this.saldo);
  }

  public int getNumeroConta() {
    return numeroConta;
  }

  public String getNomeCorrentista() {
    return nomeCorrentista;
  }

  public int getCpfCorrentista() {
    return cpfCorrentista;
  }

  public void setNumeroConta(int numeroConta) {
    this.numeroConta = numeroConta;
  }

  public ArrayList<Operacao> getOperacoes() {
    return operacoes;
  }

  public void setOperacoes(ArrayList<Operacao> operacoes) {
    this.operacoes = operacoes;
  }

  public void setNomeCorrentista(String nomeCorrentista) {
    this.nomeCorrentista = nomeCorrentista;
  }

  public void setCpfCorrentista(int cpfCorrentista) {
    this.cpfCorrentista = cpfCorrentista;
  }

  public boolean getContaPeloNumero(int numero) {
    if (this.getNumeroConta() == numero) {
      return true;
    }
    return false;
  }

  @Override
  public String toString() {
    return "Conta{"
        + "numeroConta=" + numeroConta + ", nomeCorrentista='" +
        nomeCorrentista + '\'' + ", cpfCorrentista=" + cpfCorrentista +
        ", saldo=" + saldo + '}';
  }
}
