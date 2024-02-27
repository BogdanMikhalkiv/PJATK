package com.example.diplom.Models;

import java.time.LocalDate;

public class Wyplata {
    int idWyplata;
    int idPracownik;
    String imie;
    String nazwisko;
    Double kwota;
    LocalDate data;

    public Wyplata(int idWyplata, int idPracownik, String imie, String nazwisko, Double kwota, LocalDate data) {

        this.idWyplata = idWyplata;
        this.idPracownik = idPracownik;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.kwota = kwota;
        this.data = data;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }



    public void setIdWyplata(int idWyplata) {
        this.idWyplata = idWyplata;
    }

    public void setIdPracownik(int idPracownik) {
        this.idPracownik = idPracownik;
    }

    public int getIdWyplata() {
        return idWyplata;
    }

    public int getIdPracownik() {
        return idPracownik;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public Double getKwota() {
        return kwota;
    }

    public LocalDate getData() {
        return data;
    }
}
