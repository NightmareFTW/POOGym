import java.util.Calendar;

/**
 * Sócio cujo desconto depende da idade.
 */
public class SocioIndividual extends Socio {
    private int anoNascimento;

    /**
     * Cria um sócio individual e determina o desconto pela idade actual.
     */
    public SocioIndividual(String nome, String numero, String email, int anoNascimento) {
        super(nome, numero, email, calcularDesconto(anoNascimento),
                "Individual", "nascimento: " + anoNascimento);
        this.anoNascimento = anoNascimento;
    }

    /** Devolve o ano de nascimento. */
    public int getAnoNascimento() {
        return anoNascimento;
    }

    private static double calcularDesconto(int anoNascimento) {
        int anoActual = Calendar.getInstance().get(Calendar.YEAR);
        int idade = anoActual - anoNascimento;

        if (idade < 18) {
            return 0.10;
        }
        if (idade >= 65) {
            return 0.15;
        }
        return 0.0;
    }
}
