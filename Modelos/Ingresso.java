package Modelos;


public class Ingresso {
    private String codigo;
    private String telefone;
    private Evento evento;
    private Participante participante;

    public Ingresso(String codigo, String telefone) {
        this.codigo = codigo;
        this.telefone = telefone;
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
    	if (participante.calcularIdade() < 18)
    		return evento.getPreco() * 0.9;
    	else if (participante.calcularIdade() >= 18 && participante.calcularIdade() < 60 )
    		return evento.getPreco();
    	else
    		return evento.getPreco() * 0.8;
    }
    
    public Evento getEvento()    {
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
}
