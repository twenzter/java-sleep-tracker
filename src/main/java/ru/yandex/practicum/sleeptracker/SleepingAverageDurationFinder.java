package ru.yandex.practicum.sleeptracker;
import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class SleepingAverageDurationFinder implements Function<List<SleepingSession>,SleepAnalysisResult> {

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        double averageDuration = sessions.stream()
                .map(session -> Duration.between(session.getStartSleepingTime(),
                        session.getEndSleepingTime()))
                .mapToLong(Duration::toMinutes)
                .average()
                .orElse(0.0);

        return new SleepAnalysisResult(averageDuration, "Средняя продолжительность сессии %.2f минут");
    }
}
