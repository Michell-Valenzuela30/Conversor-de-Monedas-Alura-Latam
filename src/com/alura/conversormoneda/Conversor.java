package com.alura.conversormoneda;

import java.util.Scanner;

public class Conversor {
    private String monedaBase;
    private String monedaDestino;

    public void mensajeInicial(){
        System.out.println("*******************************************");
        System.out.println("Sea Bienvenido/a al Conversor de Moneda :]\n");
        System.out.println("1) Dólar           ==> Peso Argentino");
        System.out.println("2) Peso Argentino  ==> Dólar");
        System.out.println("3) Dólar           ==> Real Brasileño");
        System.out.println("4) Real Brasileño  ==> Dólar");
        System.out.println("5) Dólar           ==> Peso Colombiano");
        System.out.println("6) Peso Colombiano ==> Dólar");
        System.out.println("7) Salir");
        System.out.println("-------------------------------------------");
    }

    public String getMonedaBase() {
        return monedaBase;
    }

    public String getMonedaDestino() {
        return monedaDestino;
    }

    public int seleccionarMoneda(Scanner teclado){
        mensajeInicial();
        System.out.print("Elige una opción: ");
        int opcion = teclado.nextInt();

        switch (opcion) {
            case 1:
                this.monedaBase = "USD";
                this.monedaDestino = "ARS";
                break;
            case 2:
                this.monedaBase = "ARS";
                this.monedaDestino = "USD";
                break;
            case 3:
                this.monedaBase = "USD";
                this.monedaDestino = "BRL";
                break;
            case 4:
                this.monedaBase = "BRL";
                this.monedaDestino = "USD";
                break;
            case 5:
                this.monedaBase = "USD";
                this.monedaDestino = "COP";
                break;
            case 6:
                this.monedaBase = "COP";
                this.monedaDestino = "USD";
                break;
        }
        return opcion;
    }
}
