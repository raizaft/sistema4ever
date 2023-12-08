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

    public String getTelefone() {
        return telefone;
    }

    public Evento getEvento() {
        return evento;
    }

    public Participante getParticipante() {
        return participante;
    }

    public double calcularValor() {
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

    @Override
    public String toString() {
        return  "Código: " + codigo +
                " | Telefone: " + telefone +
                " | Evento: " + evento.getId() +
                " | Participante: " + participante.getCpf();
    }
}
