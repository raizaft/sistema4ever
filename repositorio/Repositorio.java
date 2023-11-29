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
}
