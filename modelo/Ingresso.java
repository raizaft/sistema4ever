package modelo;

public class Ingresso {
    private String codigo;
    private String telefone;
    private Evento evento;
    private Participante participante;

    public Ingresso(String codigo, String telefone, Evento evento, Participante participante) {
        this.codigo = codigo;
        this.telefone = telefone;
        this.evento = evento;
        this.participante = participante;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public double calcularPreco() {
        double valorIngresso;
        if (participante.calcularIdade() < 18)
            valorIngresso = evento.getPreco() * 0.9;
        else if (participante.calcularIdade() >= 18 && participante.calcularIdade() < 60)
            valorIngresso = evento.getPreco();
        else
            valorIngresso = evento.getPreco() * 0.8;

        if (participante instanceof Convidado) {
            return valorIngresso * 0.5;
        } else {
            return valorIngresso;
        }
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento e) {
        evento = e;
    }

    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante p) {
        participante = p;
    }

    @Override
    public String toString() {
        return "Ingresso{" +
                "codigo='" + codigo + '\'' +
                ", telefone='" + telefone + '\'' +
                ", evento=" + evento.getId() +
                ", participante=" + participante.getCpf() +
                '}';
    }
}