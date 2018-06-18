package vidal.sergi.getfit.Objetos;

/**
 * Created by Sergi on 01/03/2018.
 */

public class Usuario {
    int idRutina;
    int idDieta;
    String nombre;
    String password;
    String apellidos;
    int edad;
    String sexo;
    double peso;
    int altura;
    double imc;
    String diferenciaDias,diferenciaDias2,finDieta,finRutina;
    public Usuario() {
    }

    public Usuario(String nombre, String apellidos, int edad, String sexo, double peso, int altura,String diferenciaDias,String diferenciaDias2,String finDieta,String finRutina) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.sexo = sexo;
        this.peso = peso;
        this.altura = altura;
        this.diferenciaDias = diferenciaDias;
        this.diferenciaDias2 = diferenciaDias2;
        this.finDieta = finDieta;
        this.finRutina = finRutina;


    }

    public Usuario(int idDieta, int idRutina, String nombre, String apellidos, int edad, String sexo, double peso, int altura) {
        this.idDieta = idDieta;
        this.idRutina = idRutina;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.sexo = sexo;
        this.peso = peso;
        this.altura = altura;
    }

    public String getDiferenciaDias() {
        return diferenciaDias;
    }

    public void setDiferenciaDias(String diferenciaDias) {
        this.diferenciaDias = diferenciaDias;
    }

    public String getDiferenciaDias2() {
        return diferenciaDias2;
    }

    public void setDiferenciaDias2(String diferenciaDias2) {
        this.diferenciaDias2 = diferenciaDias2;
    }

    public String getFinDieta() {
        return finDieta;
    }

    public void setFinDieta(String finDieta) {
        this.finDieta = finDieta;
    }

    public String getFinRutina() {
        return finRutina;
    }

    public void setFinRutina(String finRutina) {
        this.finRutina = finRutina;
    }

    public int getIdRutina() {
        return idRutina;
    }

    public void setIdRutina(int idRutina) {
        this.idRutina = idRutina;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public double getImc() {
        return imc;
    }

    public void setImc(double imc) {
        this.imc = imc;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idRutina=" + idRutina +
                ", password='" + password + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", edad=" + edad +
                ", sexo='" + sexo + '\'' +
                ", peso=" + peso +
                ", altura=" + altura +
                ", imc=" + imc +
                '}';
    }
}
