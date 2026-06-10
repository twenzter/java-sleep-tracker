package ru.yandex.practicum.sleeptracker;
import java.time.Duration;
import java.util.*;
import java.util.function.Function;

public class SleepingMaxDurationFinder implements Function<List<SleepingSession>,SleepAnalysisResult> {

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        Optional<Duration> maxDuration = sessions.stream()
                .map(session -> Duration.between(session.getStartSleepingTime(),
                        session.getEndSleepingTime()))
                .max(Duration::compareTo);
        if (maxDuration.isPresent()) {
            return new SleepAnalysisResult(maxDuration.get().toMinutes(),
                    "Максимальная продолжительность сессии %d минут");
        }
        return new SleepAnalysisResult("Максимальная продолжительность не найдена");


    }
}
