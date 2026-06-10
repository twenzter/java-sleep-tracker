package ru.yandex.practicum.sleeptracker;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

public class UserSleepingType implements Function<List<SleepingSession>, SleepAnalysisResult> {
    private static final int MORNING_TIME = 6;
    private static final int NOON_TIME = 12;

    private static final int OWL_START_SLEEPING_TIME = 23;
    private static final int OWL_END_SLEEPING_TIME = 9;
    private static final int LARK_START_SLEEPING_TIME = 22;
    private static final int LARK_END_SLEEPING_TIME = 7;

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {

        List<String> sleepingTypes = sessions.stream()
                .filter(session ->
                        (session.getStartSleepingTime().getHour() >= NOON_TIME
                                && session.getStartSleepingTime().getDayOfYear()
                                != session.getEndSleepingTime().getDayOfYear()
                                || session.getStartSleepingTime().getHour() < MORNING_TIME))
                .map(session ->
                                ((session.getStartSleepingTime().getHour() == OWL_START_SLEEPING_TIME
                                || session.getStartSleepingTime().getHour() < NOON_TIME)
                                && session.getEndSleepingTime().getHour() >= OWL_END_SLEEPING_TIME) ? "Сова" :
                                (session.getStartSleepingTime().getHour() < LARK_START_SLEEPING_TIME
                                && session.getStartSleepingTime().getHour() > NOON_TIME
                                && session.getEndSleepingTime().getHour() < LARK_END_SLEEPING_TIME) ? "Жаворонок" :
                                        "Голубь")
                .toList();


        String finalSleepingType = sleepingTypes.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("Не найдено");


        return new SleepAnalysisResult(finalSleepingType,"Ваш тип сна: %s");
    }
}
