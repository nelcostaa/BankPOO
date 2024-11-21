public class App {
  public static void main(String[] args) {
    // Criação do banco
    Banco banco = new Banco("Banco XYZ") {
      // Banco abstrato, criando uma classe anônima
    };

    // Criação de contas
    Corrente contaCorrente1 = new Corrente("Conta Corrente 1", 12345, "João Silva", 123456789);
    Corrente contaCorrente2 = new Corrente("Conta Corrente 2", 67890, "Maria Oliveira", 987654321);
    // Adicionando contas ao banco
    banco.adicionarConta(contaCorrente1);
    banco.adicionarConta(contaCorrente2);
    // Realizando um PIX/
    contaCorrente1.depositarDinheiro(1000); // Deposita 1000 na conta de João
    contaCorrente1.fazerPix(contaCorrente2, 200); // João faz um PIX para Maria

    // Exibe as contas e saldos
    System.out.println(contaCorrente1);
    System.out.println(contaCorrente2);
  }
}
