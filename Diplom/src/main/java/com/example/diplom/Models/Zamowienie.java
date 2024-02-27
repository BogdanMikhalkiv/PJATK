package com.example.diplom.Models;

import java.time.LocalDate;

public class Zamowienie {
    private int idKlient;
    private int idPracownik;
    private int idZamowienie;
    private double suma;
    private String numer;
    private LocalDate data;

    public Zamowienie(int idKlient, int idPracownik, int idZamowienie,String numer, double suma, LocalDate data) {
        this.idKlient = idKlient;
        this.idPracownik = idPracownik;
        this.idZamowienie = idZamowienie;
        this.suma = suma;
        this.data = data;
        this.numer = numer;
    }

    public String getNumer() {
        return numer;
    }

    public void setNumer(String numer) {
        this.numer = numer;
    }

    public void setIdKlient(int idKlient) {
        this.idKlient = idKlient;
    }

    public void setIdPracownik(int idPracownik) {
        this.idPracownik = idPracownik;
    }

    public void setIdZamowienie(int idZamowienie) {
        this.idZamowienie = idZamowienie;
    }

    public void setSuma(double suma) {
        this.suma = suma;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public int getIdKlient() {
        return idKlient;
    }

    public int getIdPracownik() {
        return idPracownik;
    }

    public int getIdZamowienie() {
        return idZamowienie;
    }

    public double getSuma() {
        return suma;
    }

    public LocalDate getData() {
        return data;
    }
}
