package com.example.pi_tests;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class AddToShoppingListTest {
    public static void sendShoppingQueries() {
        List<String> shoppingQueries = List.of(
                "Potrzebuję kupić chleb",
                "Na liście zakupów znajdź miejsce na mleko",
                "Dodaj płyn do naczyń do mojej listy",
                "Chcę mieć ser na liście zakupów",
                "Na obiad brakuje makaronu - dodaj go",
                "Zapomniałem o maśle, dodaj proszę",
                "Dodaj kawę do listy zakupów",
                "Herbata musi być na liście zakupów",
                "Brakuje cukru, dodaj go na listę",
                "Zapisz jajka na mojej liście zakupów",
                "Dodaj ziemniaki - są potrzebne",
                "Marchew powinna znaleźć się na liście",
                "Brakuje cebuli, dopisz ją",
                "Na deser potrzebuję owoców",
                "Jogurt naturalny jest konieczny, dopisz go",
                "Woda mineralna - dorzuć do listy",
                "Soki owocowe, dodaj proszę",
                "Wpisz syrop na listę zakupów",
                "Szampon powinien być na liście zakupów",
                "Pasta do zębów, zapisz to",
                "Brakuje żelu pod prysznic, dopisz",
                "Proszek do prania musi być na liście",
                "Wino na kolację - zapisz",
                "Dodaj piwo na listę",
                "Napoje gazowane będą potrzebne, dopisz je",
                "Na przekąskę chipsy - zapisz to",
                "Czekolada jest potrzebna, dopisz ją",
                "Dodaj ciastka do listy",
                "Miód do herbaty, zapisz to",
                "Oliwa z oliwek - koniecznie dopisz",
                "Sos pomidorowy jest niezbędny, dopisz go",
                "Ketchup - zapisz to",
                "Musztarda jest potrzebna, dopisz ją",
                "Majonez musi być na liście",
                "Pieprz - dopisz do listy",
                "Dodaj przyprawy do listy",
                "Brakuje mrożonek, zapisz je",
                "Ryż na obiad, zapisz to",
                "Chusteczki higieniczne muszą być na liście",
                "Dodaj produkty do pieczenia",
                "Pieczywo chrupkie, zapisz to",
                "Ryby są potrzebne, dopisz je",
                "Konserwy, koniecznie zapisz",
                "Mięso mielone musi być na liście",
                "Dodaj kiełbasę do zakupów",
                "Ser żółty, zapisz to",
                "Śmietana jest potrzebna, dopisz ją",
                "Warzywa do zupy, zapisz to",
                "Tabletki do zmywarki - dopisz je"
        );

        String urlString = "https://hook.eu2.make.com/mja2hg4iy6dsajbagpg3cj96h7mpffby";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("responsesAddToShoppingList.txt", true))) {
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
