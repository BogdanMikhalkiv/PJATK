package com.example.diplom.Models;

import java.time.LocalDate;

public class Panel {

    int idPanel;
    String nazwa;
    String typ;
    double cena;
    LocalDate data;
    String rozmiar;

    public Panel(int idPanel,String nazwa, String typ, double cena, LocalDate data, String rozmiar) {
        this.idPanel = idPanel;
        this.nazwa = nazwa;
        this.typ = typ;
        this.cena = cena;
        this.data = data;
        this.rozmiar = rozmiar;
    }

    public void setIdPanel(int idPanel) {
        this.idPanel = idPanel;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setRozmiar(String rozmiar) {
        this.rozmiar = rozmiar;
    }

    public int getIdPanel() {
        return idPanel;
    }

    public String getNazwa() {
        return nazwa;
    }

    public String getTyp() {
        return typ;
    }

    public double getCena() {
        return cena;
    }

    public LocalDate getData() {
        return data;
    }

    public String getRozmiar() {
        return rozmiar;
    }
}
