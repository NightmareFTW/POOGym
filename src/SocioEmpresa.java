/**
 * Sócio abrangido por um protocolo com uma empresa.
 */
public class SocioEmpresa extends Socio {
    private String nomeEmpresa;
    private String nifEmpresa;

    /**
     * Cria um sócio empresa com o desconto fixo previsto no enunciado.
     */
    public SocioEmpresa(String nome, String numero, String email,
                        String nomeEmpresa, String nifEmpresa) {
        super(nome, numero, email, 0.20, "Empresa",
                "empresa " + nomeEmpresa + " | NIF " + nifEmpresa);
        this.nomeEmpresa = nomeEmpresa;
        this.nifEmpresa = nifEmpresa;
    }

    /** Devolve o nome da empresa parceira. */
    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    /** Devolve o NIF da empresa parceira. */
    public String getNifEmpresa() {
        return nifEmpresa;
    }
}
