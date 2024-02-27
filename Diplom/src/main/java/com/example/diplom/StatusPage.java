package com.example.diplom;

public enum  StatusPage {
    PRACOWNIK(0),
    ORDER(1),
    WAREHOUSE(2);
    int index;

    StatusPage(int i) {
        index = i;
    }
}
