package com.iesvirgendelcarmen.socialtech;

import java.io.Serializable;
import java.util.List;

public class Alumno implements Serializable {
    private String nombre;
    private String apellidos;
    private int edad;
    private String sexo;
    private String telefono;
    private String email;
    private String formacion;
    private String provincia;



    public Alumno()  {
    }

    public Alumno(String nombre, String apellidos, int edad, String telefono, String email) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.telefono = telefono;
        this.email = email;
        this.formacion = formacion;
        this.provincia = provincia;
    }

    public Alumno(String nombre, String apellidos, String email) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
    }

    public Alumno(String nombre, String apellidos, int edad, String sexo, String telefono, String email, String formacion, String provincia) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.sexo = sexo;
        this.telefono = telefono;
        this.email = email;
        this.formacion = formacion;
        this.provincia = provincia;
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

    public String getTelefono() {

        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEdad() {
        return edad+"";
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFormacion() {
        return formacion;
    }

    public void setFormacion(String formacion) {
        this.formacion = formacion;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    @Override
    public String toString() {
        return getNombre() +" "+getApellidos()+" "+getEmail();
    }
}


