package vidal.sergi.getfit.Objetos;

import java.util.List;

public class Comida {

    private String nombre;
    private List<Alimento> alimentoList;

    public Comida(String nombre, List<Alimento> alimentoList) {
        this.nombre = nombre;
        this.alimentoList = alimentoList;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Alimento> getAlimentoList() {
        return alimentoList;
    }

    public void setAlimentoList(List<Alimento> alimentoList) {
        this.alimentoList = alimentoList;
    }

    @Override
    public String toString() {
        return "Comida{" +
                "nombre='" + nombre + '\'' +
                ", alimentoList=" + alimentoList +
                '}';
    }

    public Alimento getAlimento(int pos){
        return this.alimentoList.get(pos);
    }
}