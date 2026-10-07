/**
 * Representa um instrutor ou personal trainer.
 */
public class Instrutor {
    private int numero;
    private String nome;
    private String genero;
    private String formacao;

    /**
     * Cria um instrutor.
     */
    public Instrutor(int numero, String nome, String genero, String formacao) {
        this.numero = numero;
        this.nome = nome;
        this.genero = genero;
        this.formacao = formacao;
    }

    /** Devolve o número do instrutor. */
    public int getNumero() {
        return numero;
    }

    /** Devolve o nome do instrutor. */
    public String getNome() {
        return nome;
    }

    /** Devolve o género do instrutor. */
    public String getGenero() {
        return genero;
    }

    /** Devolve a formação do instrutor. */
    public String getFormacao() {
        return formacao;
    }
}
