package com.example.pedro.sinodam;

public class Palabras {
    private String palabra;
    private String sin1;
    private String sin2;

    public Palabras(String palabra, String sin1, String sin2) {
        this.palabra = palabra;
        this.sin1 = sin1;
        this.sin2 = sin2;
    }

    public String getPalabra() {
        return palabra;
    }

    public String getSin1() {
        return sin1;
    }

    public String getSin2() {
        return sin2;
    }
}
