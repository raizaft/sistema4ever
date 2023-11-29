package Modelos;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.Period;

public class Participante {
    private String cpf;
    private String nascimento;
    private ArrayList<Ingresso> ingressos = new ArrayList<>();

    public Participante(String cpf, String nascimento) {
        this.cpf = cpf;
        this.nascimento = nascimento;
    } 

    public String getCpf() {
		return cpf;
	}

	public String getNascimento() {
		return nascimento;
	}

	public int calcularIdade() {
        String[] partes = nascimento.split("/");
        int dia = Integer.parseInt(partes[0]);
        int mes = Integer.parseInt(partes[1]);
        int ano = Integer.parseInt(partes[2]);
        
        LocalDate dataNascimento = LocalDate.of(ano, mes, dia);
        LocalDate dataAtual = LocalDate.now();
        Period periodo = Period.between(dataNascimento, dataAtual);

        int anos = periodo.getYears();
        return anos;
    }
	
	public ArrayList<Ingresso> getIngressos() {
		return ingressos;
	}

}