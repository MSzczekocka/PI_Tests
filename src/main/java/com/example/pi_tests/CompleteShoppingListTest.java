package com.example.pi_tests;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class CompleteShoppingListTest {
    public static void completeShoppingQueries() {
        List<String> shoppingQueries = List.of(
                "Usuń chleb z listy zakupów",
                "Usuń mleko z listy zakupów",
                "Usuń płyn do naczyń z listy zakupów",
                "Usuń ser z listy zakupów",
                "Usuń makaron z listy zakupów",
                "Usuń masło z listy zakupów",
                "Usuń kawę z listy zakupów",
                "Usuń herbatę z listy zakupów",
                "Usuń cukier z listy zakupów",
                "Usuń jajka z listy zakupów",
                "Usuń ziemniaki z listy zakupów",
                "Usuń marchew z listy zakupów",
                "Usuń cebulę z listy zakupów",
                "Usuń owoce z listy zakupów",
                "Usuń jogurt naturalny z listy zakupów",
                "Usuń wodę mineralną z listy zakupów",
                "Usuń soki owocowe z listy zakupów",
                "Usuń syrop z listy zakupów",
                "Usuń szampon z listy zakupów",
                "Usuń pastę do zębów z listy zakupów",
                "Usuń żel pod prysznic z listy zakupów",
                "Usuń proszek do prania z listy zakupów",
                "Usuń wino z listy zakupów",
                "Usuń piwo z listy zakupów",
                "Usuń napoje gazowane z listy zakupów",
                "Usuń chipsy z listy zakupów",
                "Usuń czekoladę z listy zakupów",
                "Usuń ciastka z listy zakupów",
                "Usuń miód z listy zakupów",
                "Usuń oliwę z oliwek z listy zakupów",
                "Usuń sos pomidorowy z listy zakupów",
                "Usuń ketchup z listy zakupów",
                "Usuń musztardę z listy zakupów",
                "Usuń majonez z listy zakupów",
                "Usuń pieprz z listy zakupów",
                "Usuń przyprawy z listy zakupów",
                "Usuń mrożonki z listy zakupów",
                "Usuń ryż z listy zakupów",
                "Usuń chusteczki higieniczne z listy zakupów",
                "Usuń produkty do pieczenia z listy zakupów",
                "Usuń pieczywo chrupkie z listy zakupów",
                "Usuń ryby z listy zakupów",
                "Usuń konserwy z listy zakupów",
                "Usuń mięso mielone z listy zakupów",
                "Usuń kiełbasę z listy zakupów",
                "Usuń ser żółty z listy zakupów",
                "Usuń śmietanę z listy zakupów",
                "Usuń warzywa z listy zakupów",
                "Usuń tabletki do zmywarki z listy zakupów"
        );

        String urlString = "https://hook.eu2.make.com/mja2hg4iy6dsajbagpg3cj96h7mpffby";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("responsesCompleteShoppingList.txt", true))) {
            for (int i = 0; i < 50; i++) {
                String query = shoppingQueries.get(i % shoppingQueries.size());
                sendPostRequest(urlString, query, writer);
            }
        } catch (Exception e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    private static void sendPostRequest(String urlString, String query, BufferedWriter writer) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setDoOutput(true);

            String postData = "query=" + query;

            try (OutputStream os = connection.getOutputStream()) {
                os.write(postData.getBytes());
                os.flush();
            }

            int responseCode = connection.getResponseCode();
            StringBuilder responseBody = new StringBuilder();

            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                while ((line = br.readLine()) != null) {
                    responseBody.append(line);
                }
            }

            writer.write("Sent query: " + query + ", Response Code: " + responseCode + ", Response Body: " + responseBody);
            writer.newLine();
            System.out.println("Sent query: " + query);
            System.out.println("Response Code: " + responseCode);
            System.out.println("Response Body: " + responseBody);
        } catch (Exception e) {
            try {
                writer.write("Error sending query: " + query + ", Error: " + e.getMessage());
                writer.newLine();
            } catch (Exception ex) {
                System.err.println("Error writing error to file: " + ex.getMessage());
            }
            System.err.println("Error sending request: " + e.getMessage());
        }
    }
}

