/**
 * Guarda os dados comuns a todos os sócios.
 */
public class Socio {
    private String nome;
    private String numero;
    private String email;
    private double desconto;
    private String tipo;
    private String informacaoAdicional;

    /**
     * Cria um sócio com os dados comuns.
     */
    public Socio(String nome, String numero, String email, double desconto,
                 String tipo, String informacaoAdicional) {
        this.nome = nome;
        this.numero = numero;
        this.email = email;
        this.desconto = desconto;
        this.tipo = tipo;
        this.informacaoAdicional = informacaoAdicional;
    }

    /** Devolve o nome do sócio. */
    public String getNome() {
        return nome;
    }

    /** Devolve o número único do sócio. */
    public String getNumero() {
        return numero;
    }

    /** Devolve o e-mail do sócio. */
    public String getEmail() {
        return email;
    }

    /** Devolve a percentagem de desconto do sócio. */
    public double getDesconto() {
        return desconto;
    }

    /** Devolve o tipo do sócio. */
    public String getTipo() {
        return tipo;
    }

    /** Devolve os dados próprios do tipo de sócio. */
    public String getInformacaoAdicional() {
        return informacaoAdicional;
    }

    /** Calcula o preço depois de aplicar o desconto. */
    public double calcularPrecoFinal(double precoBase) {
        return arredondar(precoBase * (1 - desconto));
    }

    /** Calcula o valor poupado numa marcação. */
    public double calcularPoupanca(double precoBase) {
        return arredondar(precoBase - calcularPrecoFinal(precoBase));
    }

    private double arredondar(double valor) {
        return Math.round(valor * 100.0) / 100.0;
    }
}
