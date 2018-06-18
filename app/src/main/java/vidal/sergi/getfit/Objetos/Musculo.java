package vidal.sergi.getfit.Objetos;

import android.media.Image;

import java.util.List;

/**
 * Created by alu2011543 on 23/03/2018.
 */

public class Musculo {

    private String nombre;
    private List<Ejercicio> ejercicioList;

//    public Musculo(List<Ejercicio> ejercicioList) {
//        this.ejercicioList = ejercicioList;
//    }

    public Musculo(String nombre, List<Ejercicio> ejercicioList) {
        this.nombre = nombre;
        this.ejercicioList = ejercicioList;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Ejercicio> getEjercicioList() {
        return ejercicioList;
    }

    public void setEjercicioList(List<Ejercicio> ejercicioList) {
        this.ejercicioList = ejercicioList;
    }

//    @Override
//    public String toString() {
//        return "Musculo{" +
//                "ejercicioList=" + ejercicioList +
//                '}';
//    }

        @Override
    public String toString() {
        return "Musculo{" +
                "nombre='" + nombre + '\'' +
                ", ejercicioList=" + ejercicioList +
                '}';
    }

    public Ejercicio getEjercicio(int pos){
        return this.ejercicioList.get(pos);
    }
}
