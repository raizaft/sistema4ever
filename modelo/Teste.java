package modelo;

public class Teste {
	public static void main(String[] args) {
        Participante p = new Participante("0123", "06/07/1993");
        // Participante p2 = new Participante("1234", "06/07/1999");
        Evento e1 = new Evento(1, "06/12/2023", "Festa", 10, 5.50);
        Ingresso i = new Ingresso("123", "0839999", e1, p);
        try {
            e1.adicionarIngresso(i);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(e1.totalArrecadado());
        System.out.println(e1);
        System.out.println(i);
    }
}