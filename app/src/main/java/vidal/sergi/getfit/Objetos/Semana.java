package vidal.sergi.getfit.Objetos;

import java.util.List;

public class Semana {
    private List<Dia> diaList;

    public Semana(List<Dia> diaList) {
        this.diaList = diaList;
    }

    public List<Dia> getDiaList() {
        return diaList;
    }

    public void setDiaList(List<Dia> diaList) {
        this.diaList = diaList;
    }

    @Override
    public String toString() {
        return "Semana{" +
                "diaList=" + diaList +
                '}';
    }
}
