public class App {
  public static void main(String[] args) {
    // Criação do banco
    Banco bradesco = new Banco("Banco do Bradesco");

    // Criação de contas
    Corrente contaCorrente1 = new Corrente("Conta Corrente 1", 12345, "João Silva", 123456789);
    Corrente contaCorrente2 = new Corrente("Conta Corrente 2", 67890, "Maria Oliveira", 987654321);
    Poupanca contaPoupanca1 = new Poupanca("Conta Poupanca 1", 22464, "John", 1495672345);

    // Efetua um deposito nos dois tipos de contas
    contaCorrente1.depositarDinheiro(500);
    contaPoupanca1.depositarDinheiro(200);

    contaCorrente1.sacarDinheiro(200);
    contaPoupanca1.sacarDinheiro(100);

    // Adicionando contas ao banco
    bradesco.adicionarConta(contaCorrente1);
    bradesco.adicionarConta(contaCorrente2);

    // Realizando um PIX/
    contaCorrente1.depositarDinheiro(1000); // Deposita 1000 na conta de João
    contaCorrente1.fazerPix(contaCorrente2, 200); // João faz um PIX para Maria
    contaCorrente1.cadastrarCPF();
    contaCorrente2.cadastrarCPF();

    // Exibe as contas e saldos
    System.out.println(contaCorrente1);
    System.out.println(contaCorrente2);
    Corrente.listarCpfsPix();
  }
}
