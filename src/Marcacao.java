/**
 * Liga um sócio a uma actividade marcada.
 */
public class Marcacao {
    private Socio socio;
    private Atividade atividade;

    /**
     * Cria uma marcação entre um sócio e uma actividade.
     */
    public Marcacao(Socio socio, Atividade atividade) {
        this.socio = socio;
        this.atividade = atividade;
    }

    /** Devolve o sócio associado à marcação. */
    public Socio getSocio() {
        return socio;
    }

    /** Devolve a actividade associada à marcação. */
    public Atividade getAtividade() {
        return atividade;
    }

    /** Devolve o custo final depois do desconto. */
    public double getCustoFinal() {
        return socio.calcularPrecoFinal(atividade.getCustoBase());
    }

    /** Devolve o valor poupado nesta marcação. */
    public double getPoupanca() {
        return socio.calcularPoupanca(atividade.getCustoBase());
    }
}
