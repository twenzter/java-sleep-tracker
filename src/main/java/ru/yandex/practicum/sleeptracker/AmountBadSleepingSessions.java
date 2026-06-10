package ru.yandex.practicum.sleeptracker;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AmountBadSleepingSessions implements Function<List<SleepingSession>,SleepAnalysisResult> {

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        sessions = sessions.stream()
                .filter(session -> session.getSleepQuality().equals(SleepQuality.BAD))
                .collect(Collectors.toList());
        return new SleepAnalysisResult(sessions.size(),"Всего %d сессий с плохим качеством сна");
    }
}
