/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author AitorBM
 */
public class Pregunta {

    private int id;
    private String texto;

    private List<Respuesta> respuestas = new ArrayList<>();
    private Categoria categoria;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the texto
     */
    public String getTexto() {
        return texto;
    }

    /**
     * @param texto the texto to set
     */
    public void setTexto(String texto) {
        this.texto = texto;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    /**
     * @return the categoria
     */
    public Categoria getCategoria() {
        return categoria;
    }

    /**
     * @return the respuestas
     */
    public List<Respuesta> getRespuestas() {
        return respuestas;
    }

    /**
     * @param respuesta the respuestas to set
     */
    public void addRespuesta(Respuesta respuesta) {
        this.respuestas.add(respuesta);
    }

}
