package com.example.diplom.Models;

import java.time.LocalDate;

public class Sklad {
    int idSklad;
    Panel panel;
    int count;

    public Sklad(int idSklad, int idPanel, String nazwa, String typ, double cena, LocalDate date, String rozmiar) {
        this.idSklad = idSklad;
        panel = new Panel(idPanel,nazwa,typ, cena,date,rozmiar);
    }

    public Sklad(int idSklad, int idPanel, String nazwa, String typ, double cena, LocalDate date, String rozmiar,int count) {
        this.idSklad = idSklad;
        panel = new Panel(idPanel,nazwa,typ, cena,date,rozmiar);
        this.count = count;
    }

    public int getIdSklad() {
        return idSklad;
    }

    public LocalDate getData() {
        return panel.getData();
    }

    public String getNazwa() {
        return panel.getNazwa();
    }

    public String getTyp() {
        return panel.getTyp();
    }

    public double getCena() {
        return panel.getCena();
    }

    public int getIdPanel(){
        return panel.getIdPanel();
    }

    public String getRozmiar() {
        return panel.getRozmiar();
    }

}
