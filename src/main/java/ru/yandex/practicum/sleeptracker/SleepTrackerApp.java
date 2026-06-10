package ru.yandex.practicum.sleeptracker;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;
import java.time.format.DateTimeFormatter;

public class SleepTrackerApp {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");
    private static final List<Function<List<SleepingSession>,SleepAnalysisResult>> functions =
            List.of(new AmountSleepingSessions(), new SleepingMinDurationFinder(), new SleepingMaxDurationFinder(),
                    new SleepingAverageDurationFinder(), new AmountBadSleepingSessions(),
                    new SleeplessSessionsFinder(), new UserSleepingType());

    public static void main(String[] args) {

        try (BufferedReader br = new BufferedReader(new FileReader(args[0], StandardCharsets.UTF_8))) {
            List<SleepingSession> sleepingSessions =  br.lines()
                    .map(session -> session.split(";"))
                    .map(session -> new SleepingSession(LocalDateTime.parse(session[0], DATE_TIME_FORMATTER),
                            LocalDateTime.parse(session[1], DATE_TIME_FORMATTER),
                            SleepQuality.valueOf(session[2])))
                    .toList();
            functions.forEach(function ->
                    System.out.println(function.apply(sleepingSessions).getAnswer()));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}