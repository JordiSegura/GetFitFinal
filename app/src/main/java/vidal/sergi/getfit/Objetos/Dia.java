package vidal.sergi.getfit.Objetos;

public class Dia {
    public boolean desayuno;
    public boolean almuerzo;
    public boolean comida;
    public boolean merienda;
    public boolean cena;
    public boolean rutina;

    public Dia(boolean desayuno, boolean almuerzo, boolean comida, boolean merienda, boolean cena, boolean rutina) {
        this.desayuno = desayuno;
        this.almuerzo = almuerzo;
        this.comida = comida;
        this.merienda = merienda;
        this.cena = cena;
        this.rutina = rutina;
    }

    public Dia() {
    }

    public boolean isDesayuno(boolean b) {
        return desayuno;
    }

    public void setDesayuno(boolean desayuno) {
        this.desayuno = desayuno;
    }

    public boolean isAlmuerzo() {
        return almuerzo;
    }

    public void setAlmuerzo(boolean almuerzo) {
        this.almuerzo = almuerzo;
    }

    public boolean isComida() {
        return comida;
    }

    public void setComida(boolean comida) {
        this.comida = comida;
    }

    public boolean isMerienda() {
        return merienda;
    }

    public void setMerienda(boolean merienda) {
        this.merienda = merienda;
    }

    public boolean isCena() {
        return cena;
    }

    public void setCena(boolean cena) {
        this.cena = cena;
    }

    public boolean isRutina() {
        return rutina;
    }

    public void setRutina(boolean rutina) {
        this.rutina = rutina;
    }

    public void desayuno(boolean desayuno) {
    }
}
