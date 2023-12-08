package modelo;

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

    public int getCapacidade() {
        return capacidade;
    }

    public boolean lotado() {
        return ingressos.size() >= getCapacidade();
    }

    public int quantidadeIngressos() {
        return ingressos.size();
    }

    public double totalArrecadado() {
        double valorTotal = 0;
        for (Ingresso i : ingressos) {
            valorTotal += i.calcularValor();
        }
        return valorTotal;
    }

    public void adicionarIngresso(Ingresso ingresso) {
        ingressos.add(ingresso);
    }

    public void removerIngresso(Ingresso ingresso) {
        ingressos.remove(ingresso);
    }

    public void apagarTodos() {
        ingressos.clear();
    }

    public Ingresso localizarIngresso(String codigo) {
        for (Ingresso i : ingressos)
            if (i.getCodigo().equals(codigo))
                return i;
        return null;
    }

    public ArrayList<Ingresso> getIngressos() {
        return ingressos;
    }

    @Override
    public String toString() {
        return  "ID: " + id +
                " | Data: " + data +
                " | Descricao: " + descricao +
                " | Capacidade: " + capacidade +
                " | Preco: " + preco +
                " | Ingressos: " + ingressos.size();
    }
}
