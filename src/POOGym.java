import java.util.ArrayList;
import java.util.Calendar;

/**
 * Gere as colecções de sócios, instrutores, actividades e marcações.
 */
public class POOGym {
    private ArrayList<Socio> socios;
    private ArrayList<Instrutor> instrutores;
    private ArrayList<Atividade> atividades;
    private ArrayList<Marcacao> marcacoes;

    /** Cria um ginásio sem registos. */
    public POOGym() {
        socios = new ArrayList<Socio>();
        instrutores = new ArrayList<Instrutor>();
        atividades = new ArrayList<Atividade>();
        marcacoes = new ArrayList<Marcacao>();
    }

    /** Adiciona um sócio se o número ainda não estiver registado. */
    public boolean adicionarSocio(Socio socio) {
        if (obterSocio(socio.getNumero()) != null) {
            return false;
        }
        socios.add(socio);
        return true;
    }

    /** Adiciona um instrutor se o número ainda não estiver registado. */
    public boolean adicionarInstrutor(Instrutor instrutor) {
        if (obterInstrutor(instrutor.getNumero()) != null) {
            return false;
        }
        instrutores.add(instrutor);
        return true;
    }

    /** Adiciona uma actividade à colecção do ginásio. */
    public void adicionarAtividade(Atividade atividade) {
        atividades.add(atividade);
    }

    /** Cria uma marcação se houver lugares e não existir uma igual. */
    public boolean criarMarcacao(String numeroSocio, int numeroAtividade) {
        Socio socio = obterSocio(numeroSocio);
        Atividade atividade = obterAtividade(numeroAtividade);

        if (socio == null || atividade == null) {
            return false;
        }

        for (int i = 0; i < marcacoes.size(); i++) {
            Marcacao marcacao = marcacoes.get(i);
            if (marcacao.getSocio().getNumero().equals(numeroSocio)
                    && marcacao.getAtividade().getNumero() == numeroAtividade) {
                return false;
            }
        }

        if (contarMarcacoes(atividade) >= atividade.getCapacidadeMaxima()) {
            return false;
        }

        marcacoes.add(new Marcacao(socio, atividade));
        return true;
    }

    /** Procura um sócio pelo número único. */
    public Socio obterSocio(String numero) {
        for (int i = 0; i < socios.size(); i++) {
            if (socios.get(i).getNumero().equals(numero)) {
                return socios.get(i);
            }
        }
        return null;
    }

    /** Procura um instrutor pelo número. */
    public Instrutor obterInstrutor(int numero) {
        for (int i = 0; i < instrutores.size(); i++) {
            if (instrutores.get(i).getNumero() == numero) {
                return instrutores.get(i);
            }
        }
        return null;
    }

    /** Procura uma actividade pelo número. */
    public Atividade obterAtividade(int numero) {
        for (int i = 0; i < atividades.size(); i++) {
            if (atividades.get(i).getNumero() == numero) {
                return atividades.get(i);
            }
        }
        return null;
    }

    /** Devolve uma cópia da lista de sócios. */
    public ArrayList<Socio> getSocios() {
        return new ArrayList<Socio>(socios);
    }

    /** Devolve uma cópia da lista de instrutores. */
    public ArrayList<Instrutor> getInstrutores() {
        return new ArrayList<Instrutor>(instrutores);
    }

    /** Devolve uma cópia da lista de actividades. */
    public ArrayList<Atividade> getAtividades() {
        return new ArrayList<Atividade>(atividades);
    }

    /** Devolve uma cópia da lista de marcações. */
    public ArrayList<Marcacao> getMarcacoes() {
        return new ArrayList<Marcacao>(marcacoes);
    }

    /** Devolve o total facturado em todas as marcações. */
    public double calcularTotalFaturado() {
        double total = 0;
        for (int i = 0; i < marcacoes.size(); i++) {
            total += marcacoes.get(i).getCustoFinal();
        }
        return arredondar(total);
    }

    /** Conta marcações de uma categoria de actividade. */
    public int contarAtividades(String categoria) {
        int total = 0;
        for (int i = 0; i < atividades.size(); i++) {
            if (atividades.get(i).getCategoria().equals(categoria)) {
                total++;
            }
        }
        return total;
    }

    /** Devolve a actividade com mais marcações ou null se não houver nenhuma. */
    public Atividade obterAtividadeMaisPopular() {
        Atividade maisPopular = null;
        int maiorNumeroMarcacoes = 0;

        for (int i = 0; i < atividades.size(); i++) {
            Atividade atividade = atividades.get(i);
            int numeroMarcacoes = contarMarcacoes(atividade);
            if (numeroMarcacoes > maiorNumeroMarcacoes) {
                maiorNumeroMarcacoes = numeroMarcacoes;
                maisPopular = atividade;
            }
        }
        return maisPopular;
    }

    /** Devolve o total gasto por um sócio. */
    public double calcularTotalGasto(String numeroSocio) {
        double total = 0;
        for (int i = 0; i < marcacoes.size(); i++) {
            Marcacao marcacao = marcacoes.get(i);
            if (marcacao.getSocio().getNumero().equals(numeroSocio)) {
                total += marcacao.getCustoFinal();
            }
        }
        return arredondar(total);
    }

    /** Devolve o total poupado por um sócio. */
    public double calcularTotalPoupado(String numeroSocio) {
        double total = 0;
        for (int i = 0; i < marcacoes.size(); i++) {
            Marcacao marcacao = marcacoes.get(i);
            if (marcacao.getSocio().getNumero().equals(numeroSocio)) {
                total += marcacao.getPoupanca();
            }
        }
        return arredondar(total);
    }

    private int contarMarcacoes(Atividade atividade) {
        int total = 0;
        for (int i = 0; i < marcacoes.size(); i++) {
            if (marcacoes.get(i).getAtividade().getNumero() == atividade.getNumero()) {
                total++;
            }
        }
        return total;
    }

    private double arredondar(double valor) {
        return Math.round(valor * 100.0) / 100.0;
    }

    /**
     * Cria os registos fixos pedidos para a demonstração da Fase 1.
     */
    public void carregarDadosDemonstracao() {
        int anoActual = Calendar.getInstance().get(Calendar.YEAR);

        adicionarSocio(new SocioIndividual("Ana Silva", "S001", "ana@example.pt", anoActual - 17));
        adicionarSocio(new SocioIndividual("Bruno Costa", "S002", "bruno@example.pt", anoActual - 30));
        adicionarSocio(new SocioIndividual("Carla Reis", "S003", "carla@example.pt", anoActual - 66));
        adicionarSocio(new SocioEmpresa("Diogo Lopes", "S004", "diogo@example.pt", "Coimbra Tech", "501234567"));
        adicionarSocio(new SocioEmpresa("Eva Martins", "S005", "eva@example.pt", "Saúde Centro", "502345678"));
        adicionarSocio(new SocioEmpresa("Filipe Rocha", "S006", "filipe@example.pt", "Lusitânia", "503456789"));

        adicionarInstrutor(new Instrutor(1, "Marta Alves", "Feminino", "Yoga e pilates"));
        adicionarInstrutor(new Instrutor(2, "Rui Mendes", "Masculino", "Fitness e ciclismo"));
        adicionarInstrutor(new Instrutor(3, "Sara Pinto", "Feminino", "Treino personalizado"));

        adicionarAtividade(new AulaGrupo(1, "Yoga da manhã", instrutores.get(0), "20/10/2026", "09:00", 60, "Yoga", 12, 8.00));
        adicionarAtividade(new AulaGrupo(2, "Spinning 1", instrutores.get(1), "20/10/2026", "10:00", 45, "Spinning", 10, 9.00));
        adicionarAtividade(new AulaGrupo(3, "Zumba 1", instrutores.get(0), "20/10/2026", "11:00", 50, "Zumba", 15, 8.50));
        adicionarAtividade(new AulaGrupo(4, "CrossFit 1", instrutores.get(1), "20/10/2026", "12:00", 60, "CrossFit", 8, 12.00));
        adicionarAtividade(new SessaoPersonalTraining(5, "PT iniciação", instrutores.get(2), "20/10/2026", "14:00", 45, "Iniciação", 18.00));
        adicionarAtividade(new SessaoPersonalTraining(6, "PT intermédio", instrutores.get(2), "20/10/2026", "15:00", 45, "Intermédio", 22.00));
        adicionarAtividade(new SessaoPersonalTraining(7, "PT avançado", instrutores.get(1), "20/10/2026", "16:00", 60, "Avançado", 28.00));
        adicionarAtividade(new SessaoPersonalTraining(8, "PT iniciação 2", instrutores.get(2), "21/10/2026", "09:00", 45, "Iniciação", 18.00));

        criarMarcacao("S001", 1);
        criarMarcacao("S002", 1);
        criarMarcacao("S003", 2);
        criarMarcacao("S004", 3);
        criarMarcacao("S005", 5);
        criarMarcacao("S006", 6);
    }
}
