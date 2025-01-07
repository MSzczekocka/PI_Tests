package com.example.pi_tests;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class MailGeneratorTest {
    public static void mailGeneratorQueries() {
        List<String> mailQueries = List.of(

                "Wygeneruj e-mail z podziękowaniem za ostatnie spotkanie",
                "Stwórz e-mail z prośbą o przesłanie materiałów z konferencji",
                "Przygotuj e-mail informujący o zmianie terminu spotkania",
                "Wygeneruj e-mail z zaproszeniem na urodziny",
                "Stwórz e-mail z ofertą współpracy biznesowej",
                "Przygotuj e-mail z potwierdzeniem udziału w szkoleniu",
                "Wygeneruj e-mail z życzeniami urodzinowymi dla klienta",
                "Stwórz e-mail z przypomnieniem o niezapłaconej fakturze",
                "Przygotuj e-mail z podsumowaniem projektu",
                "Wygeneruj e-mail z pytaniem o dostępność produktu",
                "Stwórz e-mail z propozycją zmiany harmonogramu",
                "Przygotuj e-mail z zaproszeniem na spotkanie zespołowe",
                "Wygeneruj e-mail z gratulacjami z okazji awansu",
                "Stwórz e-mail z potwierdzeniem zamówienia",
                "Przygotuj e-mail z zaproszeniem na webinar",
                "Wygeneruj e-mail z informacją o aktualizacji polityki prywatności",
                "Stwórz e-mail z prośbą o opinię na temat produktu",
                "Przygotuj e-mail z przypomnieniem o ważnym terminie",
                "Wygeneruj e-mail z informacją o awarii systemu",
                "Stwórz e-mail z zaproszeniem na prezentację produktu",
                "Przygotuj e-mail z życzeniami świątecznymi",
                "Wygeneruj e-mail z potwierdzeniem rezerwacji hotelu",
                "Stwórz e-mail z podziękowaniem za zakup produktu",
                "Przygotuj e-mail z informacją o dostępnych promocjach",
                "Wygeneruj e-mail z pytaniem o opinie po spotkaniu",
                "Stwórz e-mail z zapytaniem o dostępność sali konferencyjnej",
                "Przygotuj e-mail z przypomnieniem o uzupełnieniu dokumentów",
                "Wygeneruj e-mail z zaproszeniem na targi branżowe",
                "Stwórz e-mail z podziękowaniem za wsparcie projektu",
                "Przygotuj e-mail z informacją o planowanych zmianach w grafiku",
                "Wygeneruj e-mail z propozycją spotkania w przyszłym tygodniu",
                "Stwórz e-mail z ofertą promocyjną dla stałych klientów",
                "Przygotuj e-mail z prośbą o przesłanie brakujących danych",
                "Wygeneruj e-mail z informacją o planowanych pracach serwisowych",
                "Stwórz e-mail z zaproszeniem na szkolenie online",
                "Przygotuj e-mail z podziękowaniem za zgłoszenie reklamacji",
                "Wygeneruj e-mail z przypomnieniem o terminie płatności",
                "Stwórz e-mail z gratulacjami z okazji rocznicy współpracy",
                "Przygotuj e-mail z zaproszeniem na kolację biznesową",
                "Wygeneruj e-mail z pytaniem o szczegóły oferty",
                "Stwórz e-mail z informacją o wynikach ankiety",
                "Przygotuj e-mail z zaproszeniem na spotkanie zarządu",
                "Wygeneruj e-mail z informacją o zmianach w regulaminie",
                "Stwórz e-mail z przypomnieniem o wymaganym szkoleniu",
                "Przygotuj e-mail z podziękowaniem za uczestnictwo w wydarzeniu",
                "Wygeneruj e-mail z informacją o nowych możliwościach współpracy",
                "Stwórz e-mail z prośbą o zatwierdzenie projektu",
                "Przygotuj e-mail z zaproszeniem na wieczór networkingowy",
                "Wygeneruj e-mail z informacją o dostępności miejsc na warsztaty"
        );

        String urlString = "";

        try (
                BufferedWriter writer = new BufferedWriter(new FileWriter("responsesMailGenerator.txt", true))) {
            for (int i = 0; i < 50; i++) {
                String query = mailQueries.get(i % mailQueries.size());
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
