package com.example.pi_tests;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class RagQuestionNegative {
    public static void ragNegativeQueries() {
        List<String> ragQueries = List.of(
                "Mam pytanie do pracy inżynierskiej. Czym są związki aromatyczne?",
                "Mam pytanie do pracy inżynierskiej. Jaki jest masa atomowa tlenu?",
                "Mam pytanie do pracy inżynierskiej. Czym zajmuje się podolog?",
                "Mam pytanie do pracy inżynierskiej. Czym jest metoda nakładania powłok ALD? ",
                "Mam pytanie do pracy inżynierskiej. Czym są katalizatory w chemii?"
        );

        String urlString = "";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("responsesRagQuestionNegative.txt", true))) {
            for (int i = 0; i < 5; i++) {
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
