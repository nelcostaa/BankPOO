import java.util.Scanner;

public class Principal {
  private static Scanner teclado = new Scanner(System.in);
  private static Banco banco = new Banco("Banco do Bradesco");

  public static void main(String[] args) {
    Conta contaCorrente1 = new Corrente(12345, "Nelson", 123456789);
    Conta poupancaConta1 = new Poupanca(54321, "Filhos Faculdade", 990039102);

    banco.adicionarConta(poupancaConta1);
    banco.adicionarConta(contaCorrente1);

    poupancaConta1.depositarDinheiro(20000);
    contaCorrente1.depositarDinheiro(1000);

    int opcao;

    do {
      System.out.println("\n--- Menu Principal ---");
      System.out.println("0 - Sair");
      System.out.println("1 - Criar Conta Corrente");
      System.out.println("2 - Criar Conta Poupança");
      System.out.println("3 - Efetuar Depósito");
      System.out.println("4 - Efetuar Saque");
      System.out.println("5 - Aplicar Correção");
      System.out.println("6 - Cadastrar PIX");
      System.out.println("7 - Efetuar PIX");
      System.out.println("8 - Consultar Extrato");
      System.out.print("\nEscolha uma opção: ");
      opcao = lerInt();

      switch (opcao) {
        case 0:
          System.out.println("Obrigado por usar o sistema bancário!");
          break;
        case 1:
          criarContaCorrente();
          break;
        case 2:
          criarContaPoupanca();
          break;
        case 3:
          efetuarDeposito();
          break;
        case 4:
          efetuarSaque();
          break;
        case 5:
          aplicarCorrecao();
          break;
        case 6:
          cadastrarPIX();
          break;
        case 7:
          efetuarPIX();
          break;
        case 8:
          consultarExtrato();
          break;
        default:
          System.out.println("Opção inválida, tente novamente.");
      }
    } while (opcao != 0);

    teclado.close();
  }

  private static void criarContaCorrente() {
    System.out.println("Digite o nome do correntista: ");
    String nome = teclado.nextLine();
    System.out.println("Digite o CPF do correntista: ");
    int cpf = lerInt();
    System.out.println("Digite o numero da conta do correntista: ");
    int numero = lerInt();

    Conta conta = new Corrente(numero, nome, cpf);
    banco.adicionarConta(conta);
    System.out.println("Conta Corrente criada com sucesso!");
  }

  private static void criarContaPoupanca() {
    System.out.println("Digite o nome da poupanca: ");
    String nome = teclado.nextLine();
    System.out.println("Digite o CPF da poupanca: ");
    int cpf = lerInt();
    System.out.println("Digite o numero da poupanca: ");
    int numero = lerInt();

    Conta conta = new Poupanca(numero, nome, cpf);
    banco.adicionarConta(conta);
    System.out.println("Conta Poupança criada com sucesso!");
  }

  private static void efetuarDeposito() {
    System.out.println("Digite o número da conta:");
    int numero = lerInt();
    System.out.println("Digite o valor do depósito:");
    double valor = lerDouble();

    for (Conta conta : banco.getContas()) {
      if (conta.getContaPeloNumero(numero)) {
        conta.depositarDinheiro(valor);
        conta.getSaldo();
      }
    }

  }

  private static void efetuarSaque() {
    System.out.println("Digite o número da conta:");
    int numero = lerInt();
    System.out.println("Digite o valor do depósito:");
    double valor = lerDouble();

    for (Conta conta : banco.getContas()) {
      if (conta.getContaPeloNumero(numero)) {
        conta.sacarDinheiro(valor);
      }
    }
  }

  private static void aplicarCorrecao() {
    System.out.println("Digite a taxa de correção (em %):");
    double taxa = lerDouble();

    for (Conta conta : banco.getContas()) {
      if (conta instanceof Poupanca) {
        ((Poupanca) conta).aplicarTaxaCorrecao(taxa);
      }
    }
  }

  private static void cadastrarPIX() {
    System.out.println("Digite o CPF para cadastrar no sistema PIX:");
    int cpf = lerInt();

    for (Conta conta : banco.getContas()) {
      if (conta instanceof Corrente && conta.getCpfCorrentista() == cpf) {
        ((Corrente) conta).cadastrarCPF(cpf);
      }
    }
  }

  private static void efetuarPIX() {
    System.out.println("Digite o numero da conta que vai fazer o pix:");
    int numeroContaEnviando = lerInt();
    System.out.println("Digite o CPF do destinatário:");
    int cpfContaDestino = lerInt();
    System.out.println("Digite o valor do PIX:");
    double valor = lerDouble();

    Conta contaEnviandoPix = null;

    for (Conta conta : banco.getContas()) {
      if (conta.getContaPeloNumero(numeroContaEnviando)) {
        contaEnviandoPix = conta;
      }
    }

    for (int cpfCheck : Corrente.getCpfsPix()) {
      if (cpfCheck == cpfContaDestino) {
        ((Corrente) contaEnviandoPix).fazerPix(cpfContaDestino, valor);
      }
    }
  }

  private static void consultarExtrato() {
    System.out.println("Digite o número da conta:");
    int numero = lerInt();

    banco.consultarExtrato(numero);
  }

  private static int lerInt() {
    while (true) {
      try {
        return Integer.parseInt(teclado.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("Entrada inválida. Por favor, insira um número inteiro.");
      }
    }
  }

  private static double lerDouble() {
    while (true) {
      try {
        return Double.parseDouble(teclado.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("Entrada inválida. Por favor, insira um número válido.");
      }
    }
  }
}
