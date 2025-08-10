package com.alura.conversormoneda;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ConversorApp {
    public static void main(String[] args) {
        Conversor conversor = new Conversor();
        Scanner teclado = new Scanner(System.in);

        // Clave de API
        String claveApi = "f06b0945e47731bfd95b6123";

        // Configuración de Gson para deserializar el JSON
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        int opcion = 0;
        while (opcion != 7) {
            try {
                // El conversor solo se encarga de mostrar el menú y pedir la opción
                opcion = conversor.seleccionarMoneda(teclado);

                if (opcion >= 1 && opcion <= 6) {
                    System.out.print("Ingrese el monto que desee convertir: ");
                    double monto = teclado.nextDouble();

                    // Construcción de la URL de la API usando la moneda base seleccionada
                    String url = "https://v6.exchangerate-api.com/v6/" + claveApi + "/latest/" + conversor.getMonedaBase();

                    // Realiza la llamada HTTP a la URL y obtener el JSON
                    //Esta estructura nunca cambia
                    HttpClient client = HttpClient.newHttpClient();
                    HttpRequest request = HttpRequest.newBuilder()
                            .uri(URI.create(url))                     //url fue la que construimos
                            .build();
                    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                    String json = response.body();

                    // Deserializar el JSON en un objeto ConversionRates
                    ConversionRates tasas = gson.fromJson(json, ConversionRates.class);

                    // Obtener la tasa de la moneda de destino
                    double tasaDeConversion = tasas.getConversionRates().get(conversor.getMonedaDestino());

                    // Realizar el cálculo
                    double resultado = monto * tasaDeConversion;

                    // Mostrar el resultado
                    System.out.println("\nEl valor " + String.format("%.2f", monto) + " [" + conversor.getMonedaBase() + "] " +
                            "corresponde al valor final de ==> " + String.format("%.2f", resultado) + " [" + conversor.getMonedaDestino() + "]\n");
                } else if (opcion == 7) {
                    System.out.println("Saliendo del conversor. ¡Gracias por usarlo!");
                } else {
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Error: Por favor, ingresa un número válido para la opción y el monto.");
                teclado.next(); // Limpiar el buffer del scanner
            } catch (IOException | InterruptedException e) {
                System.out.println("Ocurrió un error al conectar con la API: " + e.getMessage());
            } catch (NullPointerException e) {
                System.out.println("Error: No se pudo obtener la tasa de conversión para la moneda de destino.");
            }
        }
        teclado.close();
    }
}
