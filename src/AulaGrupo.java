/**
 * Aula de grupo com modalidade e capacidade próprias.
 */
public class AulaGrupo extends Atividade {
    /**
     * Cria uma aula de grupo.
     */
    public AulaGrupo(int numero, String nome, Instrutor instrutor, String data,
                     String hora, int duracaoMinutos, String modalidade,
                     int capacidadeMaxima, double custoBase) {
        super(numero, nome, instrutor, data, hora, duracaoMinutos,
                "Aula de grupo", modalidade, capacidadeMaxima, custoBase);
    }
}
