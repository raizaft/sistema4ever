package regras_negocio;

import Modelos.Evento;
import Modelos.Participante;
import repositorio.Repositorio;

public class Fachada {
	
	private static Repositorio repositorio = new Repositorio();

	public static void criarEvento(String data, String descricao,int capacidade, double preco) throws Exception{
		if (capacidade < 2)
			throw new Exception("A capacidade deve ser no minimo 2");
		if(preco < 0)						
			throw new Exception("O preço do evento deve ser maior ou igual a 0");
		
		int id = repositorio.gerarId();
		Evento evento = new Evento (id, data, descricao, capacidade, preco);  // data e descrição obrigatoria?
		repositorio.adicionar(evento);
	};
	
	public static void criarParticipante(String cpf,String nascimento) throws Exception {
		
		Participante partic = repositorio.localizarParticipante(cpf);
		if(partic.getCpf().equals(cpf))
			throw new Exception("Participante ja existe");
		
		Participante participante = new Participante(cpf, nascimento);
		repositorio.adicionar(participante);
	};
	
	public static void criarConvidado( String cpf, String nascimento, String empresa) {
		
	};
	
	public static void criarIngresso(int id, String cpf, String telefone) throws Exception {
		//if (Evento.lotado())
			//throw new Exception("A capacidade do evento atingiu o limite");
	 
		//id = id
		//Ingresso i = new Ingresso (cpf,telefone);
		
		// não estou sabendo criar ingresso kkk socorro!
		};
	
	public static void apagarEvento(int id) {
		
	};
	
	public static void apagarParticipante(String cpf) {
		
	};

	public static void apagarIngresso(String codigo) {
		
	};


}
