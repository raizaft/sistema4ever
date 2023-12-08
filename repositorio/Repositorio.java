package repositorio;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import modelo.Convidado;
import modelo.Evento;
import modelo.Ingresso;
import modelo.Participante;

public class Repositorio {
	private ArrayList<Evento> eventos = new ArrayList<>();
	private ArrayList<Ingresso> ingressos = new ArrayList<>();
	private ArrayList<Participante> participantes = new ArrayList<>();

	public Repositorio() {
		carregarObjetos();
	}

	public ArrayList<Evento> getEventos() {
		return eventos;
	}

	public ArrayList<Ingresso> getIngressos() {
		return ingressos;
	}

	public ArrayList<Participante> getParticipantes() {
		return participantes;
	}

	public int gerarId() {
		if (eventos.isEmpty())
			return 1;
		else {
			Evento ultimo = eventos.get(eventos.size()-1);
			return ultimo.getId() + 1;
		}
	}
	
	public void adicionarEvento(Evento e) {
		eventos.add(e);
	}

	public void removerEvento(Evento e) {
		eventos.remove(e);
	}

	public Evento localizarEvento(int id) {
		for(Evento e : eventos)
			if (e.getId() == id)
				return e;
		return null;
	}
	
	public void adicionarIngresso(Ingresso i) {
		ingressos.add(i);
	}

	public void removerIngresso(Ingresso i) {
		ingressos.remove(i);
	}

	public Ingresso localizarIngresso(String codigo) {
		for(Ingresso i : ingressos)
			if(i.getCodigo().equals(codigo))
				return i;
		return null;
	}

	public void adicionarParticipante(Participante p) {
		participantes.add(p);
	}

	public void removerParticipante(Participante p) {
		participantes.remove(p);
		List<Ingresso> ingressosParaRemover = ingressos.stream().filter(ingresso -> ingresso.getParticipante().equals(p)).toList();

		ingressosParaRemover.forEach(ingresso -> {
			ingresso.getEvento().getIngressos().remove(ingresso);
			ingresso.getParticipante().getIngressos().remove(ingresso);
		});
		ingressos.removeAll(ingressosParaRemover);
	}
	
	public Participante localizarParticipante (String cpf) {
		for(Participante p: participantes) {
			if(p.getCpf().equals(cpf)){
				return p;
			}
		}
		return null;
	}

	public void carregarObjetos() {
		try {
			File f1 = new File( new File(".\\eventos.csv").getCanonicalPath() ) ; 
			File f2 = new File( new File(".\\participantes.csv").getCanonicalPath() ) ; 
			File f3 = new File( new File(".\\ingressos.csv").getCanonicalPath() ) ; 
			if (!f1.exists() || !f2.exists() || !f3.exists()) {
				FileWriter arquivo1 = new FileWriter(f1); arquivo1.close();
				FileWriter arquivo2 = new FileWriter(f2); arquivo2.close();
				FileWriter arquivo3 = new FileWriter(f3); arquivo3.close();
				return;
			}
		}
		catch(Exception ex)		{
			throw new RuntimeException("criacao dos arquivos vazios:"+ex.getMessage());
		}

		String linha;	
		String[] partes;	
		Evento evento;
		Participante participante;

		try	{
			String data, descricao, id, capacidade, preco;
			File f = new File( new File(".\\eventos.csv").getCanonicalPath())  ;
			Scanner arquivo1 = new Scanner(f);
			while(arquivo1.hasNextLine()) {
				linha = arquivo1.nextLine().trim();		
				partes = linha.split(";");	
				id = partes[0];
				data = partes[1];
				descricao = partes[2];
				capacidade = partes[3];
				preco = partes[4];
				evento = new Evento(Integer.parseInt(id), data, descricao,
						Integer.parseInt(capacidade), Double.parseDouble(preco));
				this.adicionarEvento(evento);
			} 
			arquivo1.close();
		}
		catch(Exception ex)		{
			throw new RuntimeException("leitura arquivo de eventos:"+ex.getMessage());
		}

		try	{
			String cpf, nascimento, empresa;
			File f = new File( new File(".\\participantes.csv").getCanonicalPath());
			Scanner arquivo2 = new Scanner(f);
			while(arquivo2.hasNextLine()) 	{
				linha = arquivo2.nextLine().trim();	
				partes = linha.split(";");
				cpf = partes[0];
				nascimento = partes[1];
				if(partes.length==2) {
					participante = new Participante(cpf,nascimento);
					this.adicionarParticipante(participante);
				}
				else {
					empresa = partes[2];
					participante = new Convidado(cpf,nascimento,empresa);
					this.adicionarParticipante(participante);
				}

			}
			arquivo2.close();
		}
		catch(Exception ex)		{
			throw new RuntimeException("leitura arquivo de participantes:"+ex.getMessage());
		}
		
		try	{
			String codigo, telefone, cpf;
			int id;
			Ingresso ingresso;
			Evento evento1;
			Participante participante1;
			File f = new File( new File(".\\ingressos.csv").getCanonicalPath())  ;
			Scanner arquivo3 = new Scanner(f);
			while(arquivo3.hasNextLine()) 	{
				linha = arquivo3.nextLine().trim();	
				partes = linha.split(";");
				codigo = partes[0];
				telefone = partes[1];
				id = Integer.parseInt(codigo.split("-")[0]);
				cpf = codigo.split("-")[1];
				evento1 = this.localizarEvento(id);
				participante1 = this.localizarParticipante(cpf);
				
				ingresso = new Ingresso(codigo,telefone,evento1,participante1);
				evento1.adicionarIngresso(ingresso);
				participante1.adicionarIngresso(ingresso);
				this.adicionarIngresso(ingresso);
			}
			arquivo3.close();
		}
		catch(Exception ex)		{
			throw new RuntimeException("leitura arquivo de participantes:"+ex.getMessage());
		}
	}

	public void	salvarObjetos() {
		try	{
			File f = new File( new File(".\\eventos.csv").getCanonicalPath());
			FileWriter arquivo1 = new FileWriter(f); 
			for(Evento e : eventos) 	{
				arquivo1.write(e.getId()+";"+e.getData()+";"+e.getDescricao()+";"+e.getCapacidade()+";"+e.getPreco()+"\n");	
			} 
			arquivo1.close();
		}
		catch(Exception e){
			throw new RuntimeException("problema na criação do arquivo  eventos "+e.getMessage());
		}

		try	{
			File f = new File( new File(".\\participantes.csv").getCanonicalPath())  ;
			FileWriter arquivo2 = new FileWriter(f) ; 
			for(Participante p : participantes) {
				if(p instanceof Convidado c )
					arquivo2.write(p.getCpf() +";" + p.getNascimento() +";" + c.getEmpresa()+"\n");	
				else
					arquivo2.write(p.getCpf() +";" + p.getNascimento() +"\n");	

			} 
			arquivo2.close();
		}
		catch (Exception e) {
			throw new RuntimeException("problema na criação do arquivo  participantes "+e.getMessage());
		}
		try	{
			File f = new File( new File(".\\ingressos.csv").getCanonicalPath())  ;
			FileWriter arquivo3 = new FileWriter(f) ; 
			for(Ingresso i : this.getIngressos()) {
					arquivo3.write(i.getCodigo() +";" + i.getTelefone()+"\n");	

			} 
			arquivo3.close();
		}
		catch (Exception e) {
			throw new RuntimeException("problema na criação do arquivo  participantes "+e.getMessage());
		}
	}
}
