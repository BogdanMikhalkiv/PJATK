package com.example.diplom.Models;

import java.time.LocalDate;

public class Klient  {
    int id;
    String imie;
    String nazwisko;
    String telefon;
    LocalDate dataRejestracji;

    public Klient(int id, String imie, String nazwisko, String telefon, LocalDate dataRejestracji) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.telefon = telefon;
        this.dataRejestracji = dataRejestracji;
    }

    public int getId() {
        return id;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public String getTelefon() {
        return telefon;
    }

    public LocalDate getDataRejestracji() {
        return dataRejestracji;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public void setDataRejestracji(LocalDate dataRejestracji) {
        this.dataRejestracji = dataRejestracji;
    }
}
