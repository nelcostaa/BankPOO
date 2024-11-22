import java.util.ArrayList;
import java.time.LocalDate;

public final class Corrente extends Conta implements Pix {
  // Lista para armazenar os CPFs cadastrados no Pix
  private static ArrayList<Integer> cpfsPix = new ArrayList<>();

  public Corrente(String nomeConta, int numeroConta, String nomeCorrentista, int cpfCorrentista) {
    super(nomeConta, numeroConta, nomeCorrentista, cpfCorrentista);
  }

  // Implementação do método fazerPix da interface Pix
  @Override
  public void fazerPix(int cpfDestino, double valorPix) {
    if (valorPix <= 0) {
      System.out.println("Valor de PIX deve ser positivo.");
      return;
    }

    // Usa o método getAccountByCpf da classe Banco
    Conta desConta = super.getAccountByCpf(cpfDestino);
    if (desConta == null) {
      System.out.println("Erro: Nenhuma conta encontrada com o CPF destino.");
      return;
    }

    if (this.getSaldo() >= valorPix) {
      // Deduz o valor da conta origem
      Operacao pixOut = new Operacao(LocalDate.now(), valorPix, Tipo.PIX_OUT);
      this.sacarDinheiro(valorPix);
      this.adicionarOperacao(pixOut);

      // Adiciona o valor à conta destino
      desConta.depositarDinheiro(valorPix);
      Operacao pixIn = new Operacao(LocalDate.now(), valorPix, Tipo.PIX_IN);
      desConta.adicionarOperacao(pixIn);

      System.out.println("PIX realizado com sucesso!");
    } else {
      System.out.println("Erro ao realizar o PIX: saldo insuficiente.");
    }
  }

  public static ArrayList<Integer> getCpfsPix() {
    return cpfsPix;
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
    this.adicionarOperacao(pixIn);
    System.out.println("PIX recebido com sucesso!");
  }

  // Implementação simplificada do método cadastrarCPF
  @Override
  public void cadastrarCPF(int cpf) {
    if (cpfsPix.contains(cpf)) {
      System.out.println("CPF já cadastrado no Pix.");
    } else {
      cpfsPix.add(cpf); // Adiciona o CPF ao ArrayList
      System.out.println("CPF cadastrado no Pix com sucesso!");
    }
  }

  // Método para exibir todos os CPFs cadastrados no Pix
  public static void listarCpfsPix() {
    System.out.println("CPFs cadastrados no Pix:");
    for (int cpf : cpfsPix) {
      System.out.println(cpf);
    }
  }
}
