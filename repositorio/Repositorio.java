package repositorio;

import java.util.ArrayList;

import Modelos.Evento;
import Modelos.Ingresso;
import Modelos.Participante;

public class Repositorio {
	private ArrayList<Evento> eventos = new ArrayList<>();
	private ArrayList<Ingresso> ingressos = new ArrayList<>();
	private ArrayList<Participante> participantes = new ArrayList<>();
	
	
	public int gerarId() {
		if (eventos.isEmpty())
			return 1;
		else {
			Evento ultimo = eventos.get(eventos.size()-1);
			return ultimo.getId() + 1;
		}
	}
	
	public void adicionar(Evento e) {
		eventos.add(e);
	}

	public void remover(Evento e) {
		eventos.remove(e);
	}
	public Evento localizarEvento(int id) {
		for(Evento e : eventos)
			if (e.getId() == id)	
				return e;
		return null;
	}
	
	
	public void adicionar(Ingresso i) {
		ingressos.add(i);
	}

	public void remover(Ingresso i) {
		ingressos.remove(i);
	}
	
	public void adicionar(Participante p) {
		participantes.add(p);
	}

	public void remover(Participante p) {
		participantes.remove(p);
	}
	
	public Participante localizarParticipante (String cpf) {
		for(Participante p: participantes)
			if(p.getCpf() == cpf)
				return p;
		return null;
	}
	
	
	
}
