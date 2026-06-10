package ru.yandex.practicum.sleeptracker;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Function;

public class SleeplessSessionsFinder implements Function<List<SleepingSession>,SleepAnalysisResult> {
    private static final int MORNING_TIME = 6;
    private static final int NOON_TIME = 12;

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        LocalDate startPeriod = sessions.getFirst().getEndSleepingTime().toLocalDate();
        LocalDate endPeriod = sessions.getLast().getEndSleepingTime().toLocalDate();
        List<LocalDate> allDatesOfPeriod = startPeriod.datesUntil(endPeriod.plusDays(1)).toList();

        List<LocalDate> sleepingNights = sessions.stream()
                .filter(session ->
                        (session.getStartSleepingTime().getHour() >= NOON_TIME
                        && session.getStartSleepingTime().getDayOfYear() != session.getEndSleepingTime().getDayOfYear()
                        || session.getStartSleepingTime().getHour() < MORNING_TIME))
                .map(session ->
                        session.getStartSleepingTime().getHour() >= NOON_TIME ?
                        session.getEndSleepingTime().toLocalDate() :
                        session.getStartSleepingTime().toLocalDate())
                .toList();

        List<LocalDate> sleeplessNights = allDatesOfPeriod.stream()
                .filter(date -> !sleepingNights.contains(date))
                .toList();

        return new SleepAnalysisResult(sleeplessNights.size(),"Всего %d бессонных ночей");
    }
}
