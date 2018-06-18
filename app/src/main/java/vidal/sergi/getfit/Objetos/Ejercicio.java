package vidal.sergi.getfit.Objetos;

/**
 * Created by alu2011543 on 23/03/2018.
 */

public class Ejercicio {
    private String nombre;
    private int series;
    private int repeticiones;
    private double descanso;
    private int imagen;

    public Ejercicio() {
    }

    public Ejercicio(String nombre, int series, int repeticiones, double descanso) {
        this.nombre = nombre;
        this.series = series;
        this.repeticiones = repeticiones;
        this.descanso = descanso;

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public int getRepeticiones() {
        return repeticiones;
    }

    public void setRepeticiones(int repeticiones) {
        this.repeticiones = repeticiones;
    }

    public double getDescanso() {
        return descanso;
    }

    public void setDescanso(double descanso) {
        this.descanso = descanso;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return "Ejercicio{" +
                "nombre='" + nombre + '\'' +
                ", series=" + series +
                ", repeticiones=" + repeticiones +
                ", descanso=" + descanso +
                '}';
    }
}
