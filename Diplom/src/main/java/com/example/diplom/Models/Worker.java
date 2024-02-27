package com.example.diplom.Models;

import com.example.diplom.DbConnect;

import java.time.LocalDate;

public class Worker extends DbConnect {
    int id;
     String imie;
     String nazwisko;
     Double wynagrodzenie;
     LocalDate dataPrzyjecia;
     String telefon;

    public Worker(int id, String imie, String nazwisko, Double wynagrodzenie, LocalDate dataPrzyjecia, String telefon) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.wynagrodzenie = wynagrodzenie;
        this.dataPrzyjecia = dataPrzyjecia;
        this.telefon = telefon;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setWynagrodzenie(Double wynagrodzenie) {
        this.wynagrodzenie = wynagrodzenie;
    }

    public void setDataPrzyjecia(LocalDate dataPrzyjecia) {
        this.dataPrzyjecia = dataPrzyjecia;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public Double getWynagrodzenie() {
        return wynagrodzenie;
    }

    public LocalDate getDataPrzyjecia() {
        return dataPrzyjecia;
    }

    public String getTelefon() {
        return telefon;
    }

    public int getId() {
        return id;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void addWorker(String imie, String nazwisko){

    }
}
