package regras_negocio;

import modelo.Convidado;
import modelo.Evento;
import modelo.Ingresso;
import modelo.Participante;
import repositorio.Repositorio;

import java.time.LocalDate;
import java.util.ArrayList;

public class Fachada {

    private static Repositorio repositorio = new Repositorio();

    public static void criarEvento(String data, String descricao, int capacidade, double preco) throws Exception {
        if (capacidade < 2)
            throw new Exception("A capacidade deve ser no minimo 2");
        if (preco < 0)
            throw new Exception("O preço do evento deve ser maior ou igual a 0");
        if (data == null || data.length() == 0){
            throw new Exception("A data do evento é obrigatória");
        } else if (descricao == null || descricao.length() == 0){
            throw new Exception("A descrição do evento é obrigatória");
        }

        int id = repositorio.gerarId();
        Evento evento = new Evento(id, data, descricao, capacidade, preco);  // data e descrição obrigatoria?
        repositorio.adicionar(evento);
    }

    public static void criarParticipante(String cpf, String nascimento) throws Exception {
        if (cpf.isBlank() || nascimento.isBlank()){
            throw new Exception("CPF ou Nascimento não informado");
        }
        Participante partic = repositorio.localizarParticipante(cpf);
        if (partic != null)
            throw new Exception("Participante ja existe");

        Participante participante = new Participante(cpf, nascimento);
        repositorio.adicionar(participante);
    }

    public static void criarConvidado(String cpf, String nascimento, String empresa) throws Exception {
        if (cpf.isBlank() || nascimento.isBlank() || empresa.isBlank()){
            throw new Exception("CPF, data de nascimento ou empresa não informado");
        }
        Participante partic = repositorio.localizarParticipante(cpf);
        if (partic != null)
            throw new Exception("Convidado ja existe");


        Convidado convidado = new Convidado(cpf, nascimento, empresa);
        repositorio.adicionar(convidado);
    }

    public static void criarIngresso(Integer id, String cpf, String telefone) throws Exception {
        if (telefone == null || telefone.length() != 9)
            throw new Exception("O telefone precisa ser válido");

        String codigo = id + "-" + cpf;

        Participante participante = repositorio.localizarParticipante(cpf);
        Evento evento = repositorio.localizarEvento(id);
        if (evento == null || participante == null){
            throw new Exception("Evento ou participante não encontrados");
        }

        if (evento.lotado()) {
            throw new Exception("Evento está lotado");
        }

        if (evento.getIngressos().stream().anyMatch(ingresso -> ingresso.getParticipante().getCpf().equals(cpf))){
            throw new Exception("Participante já comprou ingreso para evento");
        };

        Ingresso ingresso = new Ingresso(codigo, telefone, evento, participante);
        evento.adicionarIngresso(ingresso);
        participante.adicionarIngresso(ingresso);
        repositorio.adicionar(ingresso);
    }

    public static void apagarEvento(int id) throws Exception {
        Evento evento = repositorio.localizarEvento(id);
        if (evento == null){
            throw new Exception("Evento não encontrado");
        } else if (!evento.getIngressos().isEmpty()){
            throw new Exception("Evento com ingressos vendidos");
        }
        repositorio.remover(evento);
    }

    public static void apagarParticipante(String cpf) throws Exception {
        Participante participante = repositorio.localizarParticipante(cpf);
        if (participante == null){
            throw new Exception("Participante não encontrado");
        }
        participante.getIngressos().forEach(ingresso -> {
            if (LocalDate.parse(ingresso.getEvento().getData()).isAfter(LocalDate.now())){
                throw new RuntimeException("Participante possui ingressos não expirados");
            }
        });

        participante.getIngressos().forEach(ingresso -> {
            ingresso.getEvento().remover(ingresso);
            repositorio.remover(ingresso);
        });

        repositorio.remover(participante);
    }

    public static void apagarIngresso(String codigo) throws Exception {
        Ingresso ingresso = repositorio.localizarIngresso(codigo);
        if (ingresso == null){
            throw new Exception("Ingresso não encontrado");
        }
        repositorio.remover(ingresso);
    }

    public static ArrayList<Evento> listarEventos(){
        return repositorio.listarEventos();
    }

    public static ArrayList<Participante> listarParticipantes(){
        return repositorio.listarParticipantes();
    }

    public static ArrayList<Ingresso> listarIngressos(){
        return repositorio.listarIngressos();
    }
}
