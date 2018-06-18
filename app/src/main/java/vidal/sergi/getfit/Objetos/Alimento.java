package vidal.sergi.getfit.Objetos;

public class Alimento {
    private String nombre;
    private int calorias;
    private int gramos;

    public Alimento() {
    }

    public Alimento(String nombre, int calorias) {
        this.nombre = nombre;
        this.calorias = calorias;
    }

    public Alimento(String nombre, int calorias, int gramos) {
        this.nombre = nombre;
        this.calorias = calorias;
        this.gramos = gramos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCalorias() {
        return calorias;
    }

    public void setCalorias(int calorias) {
        this.calorias = calorias;
    }

    public int getGramos() {
        return gramos;
    }

    public void setGramos(int gramos) {
        this.gramos = gramos;
    }

    @Override
    public String toString() {
        return "Alimento{" +
                "nombre='" + nombre + '\'' +
                ", calorias=" + calorias +
                ", gramos=" + gramos +
                '}';
    }
}
