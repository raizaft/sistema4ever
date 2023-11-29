package regras_negocio;

import Modelos.Evento;
import repositorio.Repositorio;

public class Fachada {
	
	private static Repositorio repositorio = new Repositorio();

	public static void criarEvento(String data, String descricao,int capacidade, double preco) throws Exception{
		
		int id = repositorio.gerarId();
		Evento e = new Evento (id, data, descricao, capacidade, preco);
		repositorio.adicionar(e);
	};
	
	public static void criarParticipante(String cpf,String nascimento) {
		
	};
	
	public static void criarConvidado( String cpf, String nascimento, String empresa) {
		
	};
	
	public static void criarIngresso(String id, String cpf, String telefone) {
		
	};
	
	public static void apagarEvento(String id) {
		
	};
	
	public static void apagarParticipante(String cpf) {
		
	};

	public static void apagarIngresso(String codigo) {
		
	};


}
