package com.example.pi_tests;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class RagQuestionPositive {
    public static void ragPositiveQueries() {
        List<String> ragQueries = List.of(
                "Mam pytanie do pracy inżynierskiej. Czym są autoregresywne duże modele językowe?",
                "Mam pytanie do pracy inżynierskiej. Jaki jest problem etyczny z ChatGPT?",
                "Mam pytanie do pracy inżynierskiej. Czym jest bag-of-words? ",
                "Mam pytanie do pracy inżynierskiej. Czym są Intelligent Cognitive Assistants? ",
                "Mam pytanie do pracy inżynierskiej. Jak można przyspieszyć trening modelu?"
        );

        String urlString = "https://hook.eu2.make.com/mja2hg4iy6dsajbagpg3cj96h7mpffby";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("responsesRagQuestionPositive.txt", true))) {
            for (int i = 0; i < 10; i++) {
                String query = ragQueries.get(i % ragQueries.size());
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
