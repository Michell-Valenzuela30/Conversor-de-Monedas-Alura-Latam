package com.alura.conversormoneda;

import java.util.Map;

public class ConversionRates {
    private String base_code;
    private Map<String, Double> conversion_rates;

    // Getter necesario para acceder al mapa de tasas
    public Map<String, Double> getConversionRates() {
        return conversion_rates;
    }
}


/*

LA ESTRUTURA DEL JSON ERA LA SIGUIENTE, POR ELLO SE CREÓ UN MAP PARA QUE
ALCANZARA LOS OTROS VALORES
QUE ASEMEJABAN A UN ARRAY, O LISTA..
  {
    "result":"success",
     "documentation":"https://www.exchangerate-api.com/docs",
     "conversion_rates":{
          "USD":1,
          "AED":3.6725,
          "AFN":68.8756,
          "ALL":83.9673,
          "AMD":383.5649,
          "ANG":1.7900,
          "AOA":918.4549,
          "ARS":1333.6700,
          ...
}*/
