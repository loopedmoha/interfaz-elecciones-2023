package com.mycompany.elecciones2023datos.services;

import com.mycompany.elecciones2023datos.model.Circunscripcion;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class LlamadasCliente {
    String url = "localhost";

    /*public List<Circunscripcion> getAllAutonomiasAuto() {
        HttpURLConnection connection = null;
        String endpoint = "/autonomicas/circunscripciones/autonomias";
        try {
            URL urlObj = new URL("http://" + url + ":9090" + endpoint);
            //System.out.println("Conectando a: " + urlObj);
            connection = (HttpURLConnection) urlObj.openConnection();
            connection = (HttpURLConnection) urlObj.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(2000);
            int responseCode = connection.getResponseCode();
            return (responseCode == HttpURLConnection.HTTP_OK);
        } catch (IOException e) {
            return false;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }*/
}
