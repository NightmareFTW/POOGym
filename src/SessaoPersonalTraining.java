/**
 * Sessão de personal training com um nível de treino.
 */
public class SessaoPersonalTraining extends Atividade {
    /**
     * Cria uma sessão individual de personal training.
     */
    public SessaoPersonalTraining(int numero, String nome, Instrutor instrutor,
                                  String data, String hora, int duracaoMinutos,
                                  String nivel, double custoBase) {
        super(numero, nome, instrutor, data, hora, duracaoMinutos,
                "Personal training", nivel, 1, custoBase);
    }
}
