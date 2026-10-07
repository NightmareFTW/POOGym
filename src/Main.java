import java.util.ArrayList;
import java.util.Calendar;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * Apresenta o menu de consola do POOGym.
 */
public class Main {
    private Scanner teclado;
    private POOGym ginasio;

    private Main() {
        teclado = new Scanner(System.in, StandardCharsets.UTF_8);
        ginasio = new POOGym();
        ginasio.carregarDadosDemonstracao();
    }

    /**
     * Inicia a aplicação.
     */
    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        Main aplicacao = new Main();
        aplicacao.executar();
    }

    private void executar() {
        int opcao;
        do {
            apresentarMenu();
            opcao = lerInteiro("Opção: ");
            executarOpcao(opcao);
        } while (opcao != 0);
        System.out.println("Até breve.");
    }

    private void apresentarMenu() {
        System.out.println();
        System.out.println("===== POOGym =====");
        System.out.println("1 - Registar sócio");
        System.out.println("2 - Registar instrutor");
        System.out.println("3 - Registar actividade");
        System.out.println("4 - Listar sócios");
        System.out.println("5 - Listar actividades");
        System.out.println("6 - Criar marcação");
        System.out.println("7 - Listar marcações e facturação");
        System.out.println("8 - Resumo de registos");
        System.out.println("9 - Actividade mais popular");
        System.out.println("10 - Valores de um sócio");
        System.out.println("0 - Sair");
    }

    private void executarOpcao(int opcao) {
        if (opcao == 1) {
            registarSocio();
        } else if (opcao == 2) {
            registarInstrutor();
        } else if (opcao == 3) {
            registarAtividade();
        } else if (opcao == 4) {
            listarSocios();
        } else if (opcao == 5) {
            listarAtividades();
        } else if (opcao == 6) {
            criarMarcacao();
        } else if (opcao == 7) {
            listarMarcacoes();
        } else if (opcao == 8) {
            apresentarResumo();
        } else if (opcao == 9) {
            apresentarMaisPopular();
        } else if (opcao == 10) {
            apresentarValoresSocio();
        } else if (opcao != 0) {
            System.out.println("Opção inválida.");
        }
    }

    private void registarSocio() {
        System.out.println("1 - Individual");
        System.out.println("2 - Empresa");
        int tipo = lerInteiroEntre("Tipo de sócio: ", 1, 2);
        String nome = lerTexto("Nome: ");
        String numero = lerTexto("Número único: ");
        String email = lerTexto("E-mail: ");
        Socio socio;

        if (tipo == 1) {
            int anoNascimento = lerAnoNascimento();
            socio = new SocioIndividual(nome, numero, email, anoNascimento);
        } else {
            String nomeEmpresa = lerTexto("Nome da empresa: ");
            String nifEmpresa = lerTexto("NIF da empresa: ");
            socio = new SocioEmpresa(nome, numero, email, nomeEmpresa, nifEmpresa);
        }

        if (ginasio.adicionarSocio(socio)) {
            System.out.println("Sócio registado. Desconto: "
                    + formatarDinheiro(socio.getDesconto() * 100) + "%");
        } else {
            System.out.println("Já existe um sócio com esse número.");
        }
    }

    private void registarInstrutor() {
        int numero = proximoNumeroInstrutor();
        String nome = lerTexto("Nome: ");
        String genero = lerTexto("Género: ");
        String formacao = lerTexto("Formação: ");
        Instrutor instrutor = new Instrutor(numero, nome, genero, formacao);
        ginasio.adicionarInstrutor(instrutor);
        System.out.println("Instrutor registado com o número " + numero + ".");
    }

    private void registarAtividade() {
        if (ginasio.getInstrutores().isEmpty()) {
            System.out.println("Registe primeiro um instrutor.");
            return;
        }

        System.out.println("1 - Aula de grupo");
        System.out.println("2 - Personal training");
        int tipo = lerInteiroEntre("Tipo de actividade: ", 1, 2);
        int numero = proximoNumeroAtividade();
        String nome = lerTexto("Nome: ");
        listarInstrutores();
        int numeroInstrutor = lerInteiro("Número do instrutor: ");
        Instrutor instrutor = ginasio.obterInstrutor(numeroInstrutor);
        while (instrutor == null) {
            System.out.println("Não existe um instrutor com esse número.");
            numeroInstrutor = lerInteiro("Número do instrutor: ");
            instrutor = ginasio.obterInstrutor(numeroInstrutor);
        }

        String data = lerTexto("Data (por exemplo, 25/10/2026): ");
        String hora = lerTexto("Hora de início (por exemplo, 10:30): ");
        int duracao = lerInteiroPositivo("Duração em minutos: ");
        Atividade atividade;

        if (tipo == 1) {
            String modalidade = lerModalidade();
            int capacidade = lerInteiroPositivo("Capacidade máxima: ");
            double custoBase = obterCustoGrupo(modalidade);
            atividade = new AulaGrupo(numero, nome, instrutor, data, hora,
                    duracao, modalidade, capacidade, custoBase);
        } else {
            String nivel = lerNivel();
            double custoBase = obterCustoPersonalTraining(nivel);
            atividade = new SessaoPersonalTraining(numero, nome, instrutor,
                    data, hora, duracao, nivel, custoBase);
        }

        ginasio.adicionarAtividade(atividade);
        System.out.println("Actividade registada com o número " + numero + ".");
    }

    private void listarSocios() {
        ArrayList<Socio> socios = ginasio.getSocios();
        if (socios.isEmpty()) {
            System.out.println("Ainda não existem sócios.");
            return;
        }

        for (int i = 0; i < socios.size(); i++) {
            Socio socio = socios.get(i);
            System.out.print(socio.getNumero() + " - " + socio.getNome()
                    + " | " + socio.getEmail() + " | desconto "
                    + formatarDinheiro(socio.getDesconto() * 100) + "%");
            System.out.println(" | " + socio.getTipo() + " | "
                    + socio.getInformacaoAdicional());
        }
    }

    private void listarInstrutores() {
        ArrayList<Instrutor> instrutores = ginasio.getInstrutores();
        for (int i = 0; i < instrutores.size(); i++) {
            Instrutor instrutor = instrutores.get(i);
            System.out.println(instrutor.getNumero() + " - " + instrutor.getNome()
                    + " (" + instrutor.getGenero() + ")");
        }
    }

    private void listarAtividades() {
        ArrayList<Atividade> atividades = ginasio.getAtividades();
        if (atividades.isEmpty()) {
            System.out.println("Ainda não existem actividades.");
            return;
        }

        for (int i = 0; i < atividades.size(); i++) {
            Atividade atividade = atividades.get(i);
            System.out.println(atividade.getNumero() + " - " + atividade.getNome()
                    + " | " + atividade.getCategoria() + ": " + atividade.getDetalhe()
                    + " | instrutor " + atividade.getInstrutor().getNome()
                    + " | " + atividade.getData() + " às " + atividade.getHora()
                    + " | " + atividade.getDuracaoMinutos() + " min"
                    + " | capacidade " + atividade.getCapacidadeMaxima()
                    + " | base " + formatarDinheiro(atividade.getCustoBase()));
        }
    }

    private void criarMarcacao() {
        listarSocios();
        String numeroSocio = lerTexto("Número do sócio: ");
        listarAtividades();
        int numeroAtividade = lerInteiro("Número da actividade: ");

        if (ginasio.criarMarcacao(numeroSocio, numeroAtividade)) {
            Socio socio = ginasio.obterSocio(numeroSocio);
            Atividade atividade = ginasio.obterAtividade(numeroAtividade);
            double preco = socio.calcularPrecoFinal(atividade.getCustoBase());
            System.out.println("Marcação criada para " + atividade.getNome()
                    + " (" + atividade.getData() + " às " + atividade.getHora()
                    + "). Custo final: " + formatarDinheiro(preco) + " €.");
        } else {
            System.out.println("Não foi possível criar a marcação. Verifique os números, "
                    + "lugares disponíveis e marcações existentes.");
        }
    }

    private void listarMarcacoes() {
        ArrayList<Marcacao> marcacoes = ginasio.getMarcacoes();
        if (marcacoes.isEmpty()) {
            System.out.println("Ainda não existem marcações.");
        } else {
            for (int i = 0; i < marcacoes.size(); i++) {
                Marcacao marcacao = marcacoes.get(i);
                Atividade atividade = marcacao.getAtividade();
                System.out.println(marcacao.getSocio().getNome() + " -> "
                        + atividade.getNome() + " | " + atividade.getData()
                        + " às " + atividade.getHora() + " | custo "
                        + formatarDinheiro(marcacao.getCustoFinal()) + " €"
                        + " | poupou " + formatarDinheiro(marcacao.getPoupanca()) + " €");
            }
        }
        System.out.println("Total facturado: "
                + formatarDinheiro(ginasio.calcularTotalFaturado()) + " €");
    }

    private void apresentarResumo() {
        int individuais = 0;
        int empresas = 0;
        ArrayList<Socio> socios = ginasio.getSocios();

        for (int i = 0; i < socios.size(); i++) {
            if (socios.get(i).getTipo().equals("Individual")) {
                individuais++;
            } else {
                empresas++;
            }
        }

        System.out.println("Sócios individuais: " + individuais
                + ", sócios empresa: " + empresas);
        System.out.println("Aulas de grupo: " + ginasio.contarAtividades("Aula de grupo")
                + ", sessões de PT: " + ginasio.contarAtividades("Personal training"));
    }

    private void apresentarMaisPopular() {
        Atividade atividade = ginasio.obterAtividadeMaisPopular();
        if (atividade == null) {
            System.out.println("Ainda não existem actividades com marcações.");
        } else {
            System.out.println("Actividade mais popular: " + atividade.getNome()
                    + " (" + atividade.getCategoria() + "), com "
                    + contarMarcacoesAtividade(atividade.getNumero()) + " marcações.");
        }
    }

    private void apresentarValoresSocio() {
        String numero = lerTexto("Número do sócio: ");
        Socio socio = ginasio.obterSocio(numero);
        if (socio == null) {
            System.out.println("Não existe um sócio com esse número.");
            return;
        }

        System.out.println(socio.getNome() + " gastou "
                + formatarDinheiro(ginasio.calcularTotalGasto(numero)) + " € e poupou "
                + formatarDinheiro(ginasio.calcularTotalPoupado(numero)) + " €.");
    }

    private int contarMarcacoesAtividade(int numeroAtividade) {
        int total = 0;
        ArrayList<Marcacao> marcacoes = ginasio.getMarcacoes();
        for (int i = 0; i < marcacoes.size(); i++) {
            if (marcacoes.get(i).getAtividade().getNumero() == numeroAtividade) {
                total++;
            }
        }
        return total;
    }

    private int proximoNumeroInstrutor() {
        int proximo = 1;
        ArrayList<Instrutor> instrutores = ginasio.getInstrutores();
        for (int i = 0; i < instrutores.size(); i++) {
            if (instrutores.get(i).getNumero() >= proximo) {
                proximo = instrutores.get(i).getNumero() + 1;
            }
        }
        return proximo;
    }

    private int proximoNumeroAtividade() {
        int proximo = 1;
        ArrayList<Atividade> atividades = ginasio.getAtividades();
        for (int i = 0; i < atividades.size(); i++) {
            if (atividades.get(i).getNumero() >= proximo) {
                proximo = atividades.get(i).getNumero() + 1;
            }
        }
        return proximo;
    }

    private String lerModalidade() {
        System.out.println("1 - Yoga | 2 - Spinning | 3 - Zumba | 4 - CrossFit");
        int opcao = lerInteiroEntre("Modalidade: ", 1, 4);
        if (opcao == 1) {
            return "Yoga";
        } else if (opcao == 2) {
            return "Spinning";
        } else if (opcao == 3) {
            return "Zumba";
        }
        return "CrossFit";
    }

    private String lerNivel() {
        System.out.println("1 - Iniciação | 2 - Intermédio | 3 - Avançado");
        int opcao = lerInteiroEntre("Nível: ", 1, 3);
        if (opcao == 1) {
            return "Iniciação";
        } else if (opcao == 2) {
            return "Intermédio";
        }
        return "Avançado";
    }

    private double obterCustoGrupo(String modalidade) {
        if (modalidade.equals("Yoga")) {
            return 8.00;
        } else if (modalidade.equals("Spinning")) {
            return 9.00;
        } else if (modalidade.equals("Zumba")) {
            return 8.50;
        }
        return 12.00;
    }

    private double obterCustoPersonalTraining(String nivel) {
        if (nivel.equals("Iniciação")) {
            return 18.00;
        } else if (nivel.equals("Intermédio")) {
            return 22.00;
        }
        return 28.00;
    }

    private String lerTexto(String pergunta) {
        String texto;
        do {
            System.out.print(pergunta);
            texto = teclado.nextLine().trim();
            if (texto.isEmpty()) {
                System.out.println("O valor não pode ficar vazio.");
            }
        } while (texto.isEmpty());
        return texto;
    }

    private int lerInteiro(String pergunta) {
        while (true) {
            System.out.print(pergunta);
            String texto = teclado.nextLine().trim();
            try {
                return Integer.parseInt(texto);
            } catch (NumberFormatException erro) {
                System.out.println("Introduza um número inteiro.");
            }
        }
    }

    private int lerInteiroPositivo(String pergunta) {
        int valor = lerInteiro(pergunta);
        while (valor <= 0) {
            System.out.println("O valor tem de ser positivo.");
            valor = lerInteiro(pergunta);
        }
        return valor;
    }

    private int lerAnoNascimento() {
        int anoActual = Calendar.getInstance().get(Calendar.YEAR);
        int ano = lerInteiro("Ano de nascimento: ");
        while (ano < 1900 || ano > anoActual) {
            System.out.println("Introduza um ano entre 1900 e " + anoActual + ".");
            ano = lerInteiro("Ano de nascimento: ");
        }
        return ano;
    }

    private int lerInteiroEntre(String pergunta, int minimo, int maximo) {
        int valor = lerInteiro(pergunta);
        while (valor < minimo || valor > maximo) {
            System.out.println("Escolha um valor entre " + minimo + " e " + maximo + ".");
            valor = lerInteiro(pergunta);
        }
        return valor;
    }

    private String formatarDinheiro(double valor) {
        return String.format(java.util.Locale.forLanguageTag("pt-PT"), "%.2f", valor);
    }
}
