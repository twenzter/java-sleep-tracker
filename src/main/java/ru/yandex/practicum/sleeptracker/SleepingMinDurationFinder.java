package ru.yandex.practicum.sleeptracker;
import java.time.Duration;
import java.util.*;
import java.util.function.Function;

public class SleepingMinDurationFinder implements Function<List<SleepingSession>,SleepAnalysisResult> {

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        Optional<Duration> minDuration = sessions.stream()
                .map(session -> Duration.between(session.getStartSleepingTime(),
                        session.getEndSleepingTime()))
                .min(Duration::compareTo);
        if (minDuration.isPresent()) {
            return new SleepAnalysisResult(minDuration.get().toMinutes(),
                    "Минимальная продолжительность сессии %d минут");
        }
        return new SleepAnalysisResult("Минимальная продолжительность не найдена");

    }
}
