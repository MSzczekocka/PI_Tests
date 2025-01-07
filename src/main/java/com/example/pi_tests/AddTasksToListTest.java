package com.example.pi_tests;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class AddTasksToListTest {
    public static void sendTasksQueries() {
        List<String> taskQueries = List.of(
                "Dodaj spotkanie z koleżanką jutro o godzinie 11",
                "Posprzątać kuchnię",
                "Umówić wizytę u lekarza",
                "Dodaj wizytę u lekarza w czwartek o 15",
                "Zaplanować zakupy na sobotę",
                "Zadzwonić do rodziców wieczorem",
                "Ustawić przypomnienie o spotkaniu zespołu",
                "Zamówić książki do nauki języka",
                "Przygotować prezentację na poniedziałek",
                "Dopisz do listy zadań, że muszę zarezerwować bilety na koncert",
                "Dopisz do listy zadań, że muszę wysłać e-mail z raportem do szefa",
                "Odebrać pranie z pralni",
                "Przygotować listę rzeczy do pakowania na wyjazd",
                "Dopisz, że muszę sprawdzić wyniki badań online",
                "Muszę się umówić wizytę u dentysty. Dodaj to do listy zadań",
                "Zorganizować spotkanie z przyjaciółmi",
                "Dopisz do listy zadań, że muszę napisać recenzję ostatnio przeczytanej książki",
                "Zaplanować budżet na kolejny miesiąc",
                "Mam zadanie sprawdzić harmonogram zajęć na uczelni. Dodaj do listy zadań ",
                "Zamówić nowe opony do samochodu",
                "Przygotować posiłki na cały tydzień",
                "Muszę zarejestrować się na kurs online. Zapisz na liście.",
                "Poprawić prezentację przed ważnym spotkaniem",
                "Przypomnieć o urodzinach kolegi",
                "Zorganizować dokumenty potrzebne do rozliczenia podatkowego",
                "Zgłosić się na wolontariat w lokalnej organizacji",
                "Zapisać się na badania kontrolne",
                "Przygotować dekoracje na przyjęcie",
                "Dodaj do listy zadań, że muszę wybrać zdjęcia do albumu rodzinnego",
                "Dodaj zadanie do listy :zaplanować trasę podróży",
                "Dziś wieczorem muszę sprawdzić terminy dostępności sali konferencyjnej. Dodaj do listy zadań",
                "Zrobić porządek w skrzynce mailowej",
                "Dokończyć artykuł do publikacji",
                "Dodaj do listy zadań, że muszę sprawdzić oferty pracy w branży",
                "Mam zadanie, że muszę zaktualizować profil zawodowy na LinkedIn. Dodaj do listy.",
                "Zaplanować aktywności na najbliższy weekend",
                "Chcę zorganizować zbiórkę na cel charytatywny. Dodaj do listy zadań",
                "Zarezerwować termin wizyty u fryzjera",
                "Dodaj do listy zadań, że muszę napisać list do przyjaciela",
                "Przygotować listę filmów do obejrzenia",
                "Dodaj do listy zadań, że muszę zaprojektować zaproszenia na ślub",
                "Uzupełnić brakujące dane w projekcie",
                "Przypomnieć zespołowi o nadchodzącym terminie projektu",
                "Posprzątać garaż",
                "Przygotować prezent na urodziny mamy",
                "Wybrać nowe przepisy na obiad, Dodaj do listy zadań",
                "Umówić się na spotkanie networkingowe",
                "Zapisz, że muszę sprawdzić stan techniczny roweru",
                "Zarezerwować wycieczkę do muzeum. Dodaj do listy zadań",
                "Zapisz na liście zadań, żę muszę rzygotować plan treningowy na kolejny tydzień"
        );

        String urlString = "";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("responsesAddTasksToList.txt", true))) {
            for (int i = 0; i < 50; i++) {
                String query = taskQueries.get(i % taskQueries.size());
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
