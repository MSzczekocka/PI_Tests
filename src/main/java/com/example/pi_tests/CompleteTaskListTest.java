package com.example.pi_tests;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class CompleteTaskListTest {
    public static void completeTaskQueries() {
        List<String> shoppingQueries = List.of(
                "Usuń spotkanie z koleżanką jutro o godzinie 11 z listy zadań",
                "Usuń sprzątanie kuchni z listy zadań",
                "Usuń wizytę u lekarza z listy zadań",
                "Usuń wizytę u lekarza w czwartek o 15 z listy zadań",
                "Usuń zaplanowanie zakupów na sobotę z listy zadań",
                "Usuń zadanie: zadzwonić do rodziców wieczorem",
                "Usuń przypomnienie o spotkaniu zespołu z listy zadań",
                "Usuń zamówienie książek do nauki języka z listy zadań",
                "Usuń przygotowanie prezentacji na poniedziałek z listy zadań",
                "Usuń rezerwację biletów na koncert z listy zadań",
                "Usuń wysłanie e-maila z raportem do szefa z listy zadań",
                "Usuń odbiór prania z pralni z listy zadań",
                "Usuń przygotowanie listy rzeczy do pakowania na wyjazd z listy zadań",
                "Usuń sprawdzenie wyników badań online z listy zadań",
                "Usuń umówienie wizyty u dentysty z listy zadań",
                "Usuń zorganizowanie spotkania z przyjaciółmi z listy zadań",
                "Usuń napisanie recenzji ostatnio przeczytanej książki z listy zadań",
                "Usuń zaplanowanie budżetu na kolejny miesiąc z listy zadań",
                "Usuń sprawdzenie harmonogramu zajęć na uczelni z listy zadań",
                "Usuń zamówienie nowych opon do samochodu z listy zadań",
                "Usuń przygotowanie posiłków na cały tydzień z listy zadań",
                "Usuń rejestrację na kurs online z listy zadań",
                "Usuń poprawienie prezentacji przed ważnym spotkaniem z listy zadań",
                "Usuń przypomnienie o urodzinach kolegi z listy zadań",
                "Usuń zorganizowanie dokumentów potrzebnych do rozliczenia podatkowego z listy zadań",
                "Usuń zgłoszenie się na wolontariat w lokalnej organizacji z listy zadań",
                "Usuń zapisanie się na badania kontrolne z listy zadań",
                "Usuń przygotowanie dekoracji na przyjęcie z listy zadań",
                "Usuń wybranie zdjęć do albumu rodzinnego z listy zadań",
                "Usuń zaplanowanie trasy podróży z listy zadań",
                "Usuń sprawdzenie terminu dostępności sali konferencyjnej z listy zadań",
                "Usuń zrobienie porządku w skrzynce mailowej z listy zadań",
                "Usuń dokończenie artykułu do publikacji z listy zadań",
                "Usuń sprawdzenie ofert pracy w branży z listy zadań",
                "Usuń aktualizację profilu zawodowego na LinkedIn z listy zadań",
                "Usuń zaplanowanie aktywności na najbliższy weekend z listy zadań",
                "Usuń zorganizowanie zbiórki na cel charytatywny z listy zadań",
                "Usuń rezerwację terminu wizyty u fryzjera z listy zadań",
                "Usuń napisanie listu do przyjaciela z listy zadań",
                "Usuń przygotowanie listy filmów do obejrzenia z listy zadań",
                "Usuń zaprojektowanie zaproszeń na ślub z listy zadań",
                "Usuń uzupełnienie brakujących danych w projekcie z listy zadań",
                "Usuń przypomnienie zespołowi o nadchodzącym terminie projektu z listy zadań",
                "Usuń posprzątanie garażu z listy zadań",
                "Usuń przygotowanie prezentu na urodziny mamy z listy zadań",
                "Usuń wybranie nowych przepisów na obiad z listy zadań",
                "Usuń umówienie się na spotkanie networkingowe z listy zadań",
                "Usuń sprawdzenie stanu technicznego roweru z listy zadań",
                "Usuń rezerwację wycieczki do muzeum z listy zadań",
                "Usuń przygotowanie planu treningowego na kolejny tydzień z listy zadań"
    );

    String urlString = "https://hook.eu2.make.com/mja2hg4iy6dsajbagpg3cj96h7mpffby";

        try (
    BufferedWriter writer = new BufferedWriter(new FileWriter("responsesCompleteTaskList.txt", true))) {
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
