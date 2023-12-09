package regras_negocio;

import modelo.Convidado;
import modelo.Evento;
import modelo.Ingresso;
import modelo.Participante;
import repositorio.Repositorio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Fachada {
    private static Repositorio repositorio = new Repositorio();

    public static void criarEvento(String data, String descricao, int capacidade, double preco) throws Exception {
        if (capacidade < 2)
            throw new Exception("A capacidade deve ser no minimo 2.");
        if (preco < 0)
            throw new Exception("O preço do evento deve ser maior ou igual a 0.");
        if (data == null || data.length() == 0){
            throw new Exception("A data do evento é obrigatória.");
        } else if (descricao == null || descricao.length() == 0){
            throw new Exception("A descrição do evento é obrigatória.");
        } else if (data.length() != 10) {
            throw new Exception("Data inválida.");
        }

        int id = repositorio.gerarId();
        Evento evento = new Evento(id, data, descricao, capacidade, preco);
        repositorio.adicionarEvento(evento);
        repositorio.salvarObjetos();
    }

    public static void criarParticipante(String cpf, String nascimento) throws Exception {
    	Participante partic = repositorio.localizarParticipante(cpf);
        if (partic != null)
            throw new Exception("Participante ja está registrado!");
        
        if (cpf.isBlank() || nascimento.isBlank()){
            throw new Exception("CPF ou data de nascimento não informado.");
        } else if (nascimento.length() != 12) {
            throw new Exception("Data de nascimento inválida.");
        }

        Participante participante = new Participante(cpf, nascimento);
        repositorio.adicionarParticipante(participante);
        repositorio.salvarObjetos();
    }

    public static void criarConvidado(String cpf, String nascimento, String empresa) throws Exception {
        if (cpf.isBlank() || nascimento.isBlank() || empresa.isBlank()){
            throw new Exception("CPF, data de nascimento ou empresa não informado.");
        }
        Participante partic = repositorio.localizarParticipante(cpf);
        if (partic != null)
            throw new Exception("Convidado ja registrado!");


        Convidado convidado = new Convidado(cpf, nascimento, empresa);
        repositorio.adicionarParticipante(convidado);
        repositorio.salvarObjetos();
    }

    public static void criarIngresso(Integer id, String cpf, String telefone) throws Exception {
        if (telefone == null || telefone.length() != 9)
            throw new Exception("Informe um telefone válido!");

        String codigo = id + "-" + cpf;

        Participante participante = repositorio.localizarParticipante(cpf);
        Evento evento = repositorio.localizarEvento(id);
        if (evento == null || participante == null){
            throw new Exception("Evento ou participante não encontrado.");
        }

        if (evento.lotado()) {
            throw new Exception("Ingresso não foi criado, pois o evento está lotado.");
        }

        if (evento.getIngressos().stream().anyMatch(ingresso -> ingresso.getParticipante().getCpf().equals(cpf))){
            throw new Exception("Participante já comprou ingreso para este evento.");
        };

        Ingresso ingresso = new Ingresso(codigo, telefone, evento, participante);
        evento.adicionarIngresso(ingresso);
        participante.adicionarIngresso(ingresso);
        repositorio.adicionarIngresso(ingresso);
        repositorio.salvarObjetos();
    }

    public static void apagarEvento(int id) throws Exception {
        Evento evento = repositorio.localizarEvento(id);
        if (evento == null){
            throw new Exception("Evento não foi encontrado.");
        } else if (!evento.getIngressos().isEmpty()){
            throw new Exception("Evento possui ingressos vendidos.");
        }
        repositorio.removerEvento(evento);
        repositorio.salvarObjetos();
    }

    public static void apagarParticipante(String cpf) throws Exception {
        Participante participante = repositorio.localizarParticipante(cpf);
        if (participante == null) {
            throw new Exception("Participante não encontrado");
        }

        List<Ingresso> ingressos = participante.getIngressos();

        for (int i = 0; i < ingressos.size(); i++) {
            Ingresso ingresso = ingressos.get(i);
            String dataEventoStr = ingresso.getEvento().getData();
            
            if (dataEventoStr != null && !dataEventoStr.isEmpty()) {
                LocalDate dataEvento = LocalDate.parse(dataEventoStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                if (dataEvento.isAfter(LocalDate.now())) {
                    throw new RuntimeException("Participante possui ingressos não expirados.");
                }
            }

            ingresso.getEvento().removerIngresso(ingresso);
            repositorio.removerIngresso(ingresso);
        }

        repositorio.removerParticipante(participante);
        repositorio.salvarObjetos();
    }

    public static void apagarIngresso(String codigo) throws Exception {
        Ingresso ingresso = repositorio.localizarIngresso(codigo);
        if (ingresso == null){
            throw new Exception("Ingresso não encontrado");
        }
        repositorio.removerIngresso(ingresso);
        repositorio.salvarObjetos();
    }

    public static ArrayList<Evento> listarEventos(){
        return repositorio.getEventos();
    }

    public static ArrayList<Participante> listarParticipantes(){
        return repositorio.getParticipantes();
    }

    public static ArrayList<Ingresso> listarIngressos(){
        return repositorio.getIngressos();
    }
    
    public static ArrayList<Ingresso> listarIngressosEvento(int codigoEvento) {
        ArrayList<Ingresso> ingressosDoEvento = new ArrayList<>();

        try {
            ArrayList<Ingresso> todosIngressos = repositorio.getIngressos();

            for (Ingresso ingresso : todosIngressos) {
                if (ingresso.getEvento().getId() == codigoEvento) {
                    ingressosDoEvento.add(ingresso);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ingressosDoEvento;
    }
    
    public static ArrayList<Ingresso> listarIngressosParticipante(String cpf) {
        ArrayList<Ingresso> ingressosParticipante = new ArrayList<>();

        try {
            ArrayList<Ingresso> todosIngressos = repositorio.getIngressos();

            for (Ingresso ingresso : todosIngressos) {
                if (ingresso.getParticipante().getCpf() == cpf) {
                    ingressosParticipante.add(ingresso);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ingressosParticipante;
    }
}
