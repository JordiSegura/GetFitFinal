package vidal.sergi.getfit.Objetos;

import java.util.List;

public class Seguimiento {
    public String semana;
    private List<Semana> semanaList;


    public Seguimiento(String semana, List<Semana> semanaList) {
        this.semana = semana;
        this.semanaList = semanaList;
    }

    public Seguimiento() {
    }

    public String getSemana() {
        return semana;
    }

    public void setSemana(String semana) {
        this.semana = semana;
    }

    public List<Semana> getSemanaList() {
        return semanaList;
    }

    public void setSemanaList(List<Semana> semanaList) {
        this.semanaList = semanaList;
    }

    @Override
    public String toString() {
        return "Seguimiento{" +
                "semana='" + semana + '\'' +
                ", semanaList=" + semanaList +
                '}';
    }
}
