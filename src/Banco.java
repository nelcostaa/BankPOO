import java.util.ArrayList;

public abstract class Banco {
  private ArrayList<Conta> contas;
  private String nomeConta;
  private ArrayList<Conta> listaPix;

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
}
