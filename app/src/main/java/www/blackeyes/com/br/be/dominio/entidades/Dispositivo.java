package www.blackeyes.com.br.be.dominio.entidades;

import java.io.Serializable;

/**
 * Created by jeriel on 11/7/15.
 */
public class Dispositivo implements Serializable{

    public static String TABELA = "DISPOSITIVOS";
    public static String ID = "_id";
    public static String NOME = "NOME";
    public static String DISPOSITIVO = "DISPOSITIVO";

    private long id;
    private String nome;
    private String dispositivo;

    public Dispositivo(){
        id = 0;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(String dispositivo) {
        this.dispositivo = dispositivo;
    }

    @Override
    public String toString(){

        return nome + " " + dispositivo;
    }
}
