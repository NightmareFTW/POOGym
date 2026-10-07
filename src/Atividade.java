/**
 * Guarda os dados comuns a uma actividade do ginásio.
 */
public class Atividade {
    private int numero;
    private String nome;
    private Instrutor instrutor;
    private String data;
    private String hora;
    private int duracaoMinutos;
    private String categoria;
    private String detalhe;
    private int capacidadeMaxima;
    private double custoBase;

    /**
     * Cria uma actividade com os seus dados e custo base.
     */
    public Atividade(int numero, String nome, Instrutor instrutor, String data,
                     String hora, int duracaoMinutos, String categoria,
                     String detalhe, int capacidadeMaxima, double custoBase) {
        this.numero = numero;
        this.nome = nome;
        this.instrutor = instrutor;
        this.data = data;
        this.hora = hora;
        this.duracaoMinutos = duracaoMinutos;
        this.categoria = categoria;
        this.detalhe = detalhe;
        this.capacidadeMaxima = capacidadeMaxima;
        this.custoBase = custoBase;
    }

    /** Devolve o número da actividade. */
    public int getNumero() {
        return numero;
    }

    /** Devolve o nome da actividade. */
    public String getNome() {
        return nome;
    }

    /** Devolve o instrutor responsável. */
    public Instrutor getInstrutor() {
        return instrutor;
    }

    /** Devolve a data da actividade. */
    public String getData() {
        return data;
    }

    /** Devolve a hora de início. */
    public String getHora() {
        return hora;
    }

    /** Devolve a duração em minutos. */
    public int getDuracaoMinutos() {
        return duracaoMinutos;
    }

    /** Devolve a categoria da actividade. */
    public String getCategoria() {
        return categoria;
    }

    /** Devolve a modalidade ou o nível da actividade. */
    public String getDetalhe() {
        return detalhe;
    }

    /** Devolve a capacidade máxima da actividade. */
    public int getCapacidadeMaxima() {
        return capacidadeMaxima;
    }

    /** Devolve o custo base da actividade. */
    public double getCustoBase() {
        return custoBase;
    }
}
