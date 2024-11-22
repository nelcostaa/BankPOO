import java.util.Scanner;

public class App {
  public static void main(String[] args) {
    // Criação do banco
    Banco bradesco = new Banco("Banco do Bradesco");

    // Criação de contas
    Conta corrente1 = new Corrente("Conta Corrente 1", 12345, "João Silva", 123456789);
    Conta poupanca1 = new Poupanca("Conta Poupança 1", 22464, "John", 1495672345);

    // Adicionando contas ao banco
    bradesco.adicionarConta(corrente1);
    bradesco.adicionarConta(poupanca1);

    // Efetua um depósito nos dois tipos de contas
    corrente1.depositarDinheiro(500);
    poupanca1.depositarDinheiro(200);

    System.out.println("Saldo após depósito:");
    System.out.println("Conta Corrente: R$ " + corrente1.getSaldo());
    System.out.println("Conta Poupança: R$ " + poupanca1.getSaldo());

    corrente1.sacarDinheiro(200);
    poupanca1.sacarDinheiro(100);

    System.out.println("Saldo após saque:");
    System.out.println("Conta Corrente: R$ " + corrente1.getSaldo());
    System.out.println("Conta Poupança: R$ " + poupanca1.getSaldo());

    try (Scanner teclado = new Scanner(System.in)) {
      // Pede a taxa de correção para contas poupança
      double taxa = lerDouble("Qual taxa você deseja aplicar?", teclado);
      System.out.println("Taxa registrada: " + taxa);

      for (Conta conta : bradesco.getContas()) {
        if (conta instanceof Poupanca) {
          ((Poupanca) conta).aplicarTaxaCorrecao(taxa);
        }
      }

      // Cadastra CPF no sistema PIX
      int cpf = lerInt("Qual CPF você deseja adicionar ao sistema PIX?", teclado);
      System.out.println("CPF registrado: " + cpf);

      for (Conta conta : bradesco.getContas()) {
        if (conta instanceof Corrente && conta.getCpfCorrentista() == cpf) {
          ((Corrente) conta).cadastrarCPF(cpf);
        }
      }

      // Realiza um PIX
      int cpfDestino = lerInt("Para qual CPF você deseja enviar um PIX?", teclado);
      double valorPix = lerDouble("Qual o valor do PIX?", teclado);

      for (int cpfCheck : Corrente.getCpfsPix()) {
        if (cpfDestino == cpfCheck) {
          for (Conta conta : bradesco.getContas()) {
            if (conta instanceof Corrente) {
              ((Corrente) conta).fazerPix(cpfDestino, valorPix);
            }
          }
        }
      }

      // Consulta extrato de uma conta
      int contaExtrato = lerInt("Qual conta você deseja consultar o extrato?", teclado);
      bradesco.consultarExtrato(contaExtrato);
    }
  }

  // Métodos auxiliares para leitura com validação
  private static double lerDouble(String mensagem, Scanner teclado) {
    Double valor = null;
    while (valor == null) {
      System.out.println(mensagem);
      try {
        valor = Double.parseDouble(teclado.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("Entrada inválida. Por favor, insira um número válido.");
      }
    }
    return valor;
  }

  private static int lerInt(String mensagem, Scanner teclado) {
    Integer valor = null;
    while (valor == null) {
      System.out.println(mensagem);
      try {
        valor = Integer.parseInt(teclado.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("Entrada inválida. Por favor, insira um número inteiro.");
      }
    }
    return valor;
  }
}
