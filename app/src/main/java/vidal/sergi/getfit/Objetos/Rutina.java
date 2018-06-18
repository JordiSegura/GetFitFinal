package vidal.sergi.getfit.Objetos;

import java.util.List;

public class Rutina {

    private int id;
    private int img;
    private String nombre;
    private List<Musculo> musculoList;

    public Rutina(int id, int img, String nombre, List<Musculo> musculoList) {
        this.id = id;
        this.img = img;
        this.nombre = nombre;
        this.musculoList = musculoList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Musculo> getMusculoList() {
        return musculoList;
    }

    public void setMusculoList(List<Musculo> musculoList) {
        this.musculoList = musculoList;
    }

    public Musculo getMusculo(int pos){
        return this.musculoList.get(pos);
    }
}
