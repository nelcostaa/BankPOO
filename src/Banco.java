import java.util.ArrayList;

public class Banco {
  private ArrayList<Conta> contas;
  private String nomeConta;

  public Banco(String nomeConta) {
    this.contas = new ArrayList<Conta>();
    this.nomeConta = nomeConta;
  }

  // Método para adicionar uma conta ao banco
  public void adicionarConta(Conta conta) {
    contas.add(conta);
  }

  // Método para listar todas as contas
  public ArrayList<Conta> getContas() {
    return contas;
  }

  public String getNomeConta() {
    return nomeConta;
  }

  public void setNomeConta(String nomeConta) {
    this.nomeConta = nomeConta;
  }

  public Conta getAccountByCpf(int cpf) {
    for (Conta conta : getContas()) {
      if (conta.getCpfCorrentista() == cpf) {
        return conta; // Retorna a conta correspondente ao CPF
      }
    }
    return null; // Retorna null se nenhuma conta for encontrada
  }

  public void consultarExtrato(int numeroConta) {
    // Busca a conta pelo número
    Conta conta = null;
    for (Conta c : contas) {
      if (c.getNumeroConta() == numeroConta) {
        conta = c;
        break;
      }
    }

    if (conta == null) {
      System.out.println("Conta não encontrada.");
      return;
    }

    // Exibe o extrato
    System.out.println("Extrato da conta: " + numeroConta);
    System.out.println("-------------------------");
    double saldoConsolidado = 0.0;

    for (Operacao operacao : conta.getOperacoes()) {
      // Atualiza o saldo consolidado com o impacto da operação
      saldoConsolidado += operacao.calcularImpacto();

      // Exibe a operação formatada
      System.out.printf("%s | %-8s | R$ %.2f | Saldo: R$ %.2f\n",
          operacao.getData(), operacao.getTipo(), operacao.getValor(), saldoConsolidado);
    }
    System.out.println("-------------------------");
  }

}
