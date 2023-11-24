package Modelos;
public class Ingresso {
    private String codigo;
    private String telefone;
    private Evento evento;
    private Participante participante;

    public Ingresso(String c, String t, Evento e, Participante p) {
        this.codigo = c;
        this.telefone = t;
        this.evento = e;
        this.participante = p;
    }
}
