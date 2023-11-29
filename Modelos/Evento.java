package Modelos;

import java.util.ArrayList;


public class Evento {
    private int id;
    private String data;
    private String descricao;
    private int capacidade;
    private double preco;
    private ArrayList<Ingresso> ingressos = new ArrayList<>();

    public Evento(int id, String data, String desc, int c, double preco) {
        this.id = id;
        this.data = data;
        this.descricao = desc;
        this.capacidade = c;
        this.preco = preco;
    }

    public int getId() {
		return id;
	}

	public String getData() {
		return data;
	}

	public String getDescricao() {
		return descricao;
	}

	public double getPreco() {
		return preco;
	}

	public boolean lotado() {
        return ingressos.size() >= getCapacidade();
    }

    public int quantidadeIngressos() {
        return ingressos.size();
    }

    public double totalArrecadado() {
        return ingressos.size() * preco;
    }

    public int getCapacidade() {
        return capacidade;
    }
    
    public void adicionar(Ingresso ingresso)    {
		ingressos.add(ingresso);
	}
    
	public void remover (Ingresso ingresso)  {
		ingressos.remove(ingresso);
	}

	public Ingresso localizarIngresso(String codigo) {
		for(Ingresso i: ingressos)
			if(i.getCodigo().equals(codigo)) 
				return i; 
		return null;	
	}
	
	
    public ArrayList<Ingresso> getIngressos() {
		return ingressos;
	}
    
}
