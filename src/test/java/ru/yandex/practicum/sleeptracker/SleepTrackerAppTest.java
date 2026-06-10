package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;

public class SleepTrackerAppTest {

    final static List<Function<List<SleepingSession>,SleepAnalysisResult>> functions =
            List.of(new AmountSleepingSessions(), new SleepingMinDurationFinder(), new SleepingMaxDurationFinder(),
                    new SleepingAverageDurationFinder(), new AmountBadSleepingSessions(),
                    new SleeplessSessionsFinder(), new UserSleepingType());

    final static List<SleepingSession> sessionsList = List.of(new SleepingSession(
            LocalDateTime.of(2025,10,20,16,20),
            LocalDateTime.of(2025,10,20,16,30),
            SleepQuality.BAD), new SleepingSession(
            LocalDateTime.of(2025,11,12,23,20),
            LocalDateTime.of(2025,11,13,6,50),
            SleepQuality.GOOD));

    final static List<SleepingSession> emptySessionsList = List.of();


    @Test
    void amountSleepingSessionWithFilledList() {
        Assertions.assertEquals("Всего 2 сессий сна", functions.get(0).apply(sessionsList).getAnswer());
    }

    @Test
    void amountSleepingSessionWithEmptyList() {
        Assertions.assertEquals("Всего 0 сессий сна", functions.get(0).apply(emptySessionsList).getAnswer());
    }

    @Test
    void sleepingMinDurationFinderFilledList() {
        Assertions.assertEquals("Минимальная продолжительность сессии 10 минут",
                functions.get(1).apply(sessionsList).getAnswer());
    }

    @Test
    void sleepingMinDurationFinderEmptyList() {
        Assertions.assertEquals("Минимальная продолжительность не найдена",
                functions.get(1).apply(emptySessionsList).getAnswer());
    }

    @Test
    void sleepingMaxDurationFinderFilledList() {
        Assertions.assertEquals("Максимальная продолжительность сессии 450 минут",
                functions.get(2).apply(sessionsList).getAnswer());
    }

    @Test
    void sleepingMaxDurationFinderEmptyList() {
        Assertions.assertEquals("Максимальная продолжительность не найдена",
                functions.get(2).apply(emptySessionsList).getAnswer());
    }

    @Test
    void sleepingAverageDurationFinderFilledList() {
        Assertions.assertEquals("Средняя продолжительность сессии 230,00 минут",
                functions.get(3).apply(sessionsList).getAnswer());
    }

    @Test
    void sleepingAverageDurationFinderEmptyList() {
        Assertions.assertEquals("Средняя продолжительность сессии 0,00 минут",
                functions.get(3).apply(emptySessionsList).getAnswer());
    }

    @Test
    void amountBadSleepingSessionsFilledList() {
        Assertions.assertEquals("Всего 1 сессий с плохим качеством сна",
                functions.get(4).apply(sessionsList).getAnswer());
    }

    @Test
    void amountBadSleepingSessionsEmptyList() {
        Assertions.assertEquals("Всего 0 сессий с плохим качеством сна",
                functions.get(4).apply(emptySessionsList).getAnswer());
    }

    @Test
    void sleeplessSessionsFinderFrom6To23() {
        Assertions.assertEquals("Всего 1 бессонных ночей",functions.get(5).apply(List.of(new SleepingSession(
                LocalDateTime.of(2025,10,20,8,0),
                LocalDateTime.of(2025,10,20,23,59),
                SleepQuality.BAD))).getAnswer());
    }

    @Test
    void sleeplessSessionsFinderFrom16To0() {
        Assertions.assertEquals("Всего 0 бессонных ночей",functions.get(5).apply(List.of(new SleepingSession(
                LocalDateTime.of(2025,10,20,16,0),
                LocalDateTime.of(2025,10,21,0,0),
                SleepQuality.BAD))).getAnswer());
    }

    @Test
    void sleeplessSessionsFinderFrom23To3() {
        Assertions.assertEquals("Всего 0 бессонных ночей",functions.get(5).apply(List.of(new SleepingSession(
                LocalDateTime.of(2025,10,20,23,0),
                LocalDateTime.of(2025,10,21,3,0),
                SleepQuality.BAD))).getAnswer());
    }


    @Test
    void sleeplessSessionsFinderFrom2To5() {
        Assertions.assertEquals("Всего 0 бессонных ночей",functions.get(5).apply(List.of(new SleepingSession(
                LocalDateTime.of(2025,10,21,2,0),
                LocalDateTime.of(2025,10,21,5,0),
                SleepQuality.BAD))).getAnswer());
    }

    @Test
    void sleeplessSessionsFinderFrom5To10() {
        Assertions.assertEquals("Всего 0 бессонных ночей",functions.get(5).apply(List.of(new SleepingSession(
                LocalDateTime.of(2025,10,21,5,0),
                LocalDateTime.of(2025,10,21,10,0),
                SleepQuality.BAD))).getAnswer());
    }

    @Test
    void sleeplessSessionsFinderFrom23To7() {
        Assertions.assertEquals("Всего 0 бессонных ночей",functions.get(5).apply(List.of(new SleepingSession(
                LocalDateTime.of(2025,10,20,23,0),
                LocalDateTime.of(2025,10,21,7,0),
                SleepQuality.BAD))).getAnswer());
    }

    @Test
    void sleeplessSessionsFinderFrom6To10() {
        Assertions.assertEquals("Всего 1 бессонных ночей",functions.get(5).apply(List.of(new SleepingSession(
                LocalDateTime.of(2025,10,21,6,0),
                LocalDateTime.of(2025,10,21,10,0),
                SleepQuality.BAD))).getAnswer());
    }


    @Test
    void userSleepingTypeForOwl() {
        Assertions.assertEquals("Ваш тип сна: Сова",functions.get(6).apply(List.of(new SleepingSession(
                LocalDateTime.of(2025,10,21,0,0),
                LocalDateTime.of(2025,10,21,10,0),
                SleepQuality.BAD))).getAnswer());
    }

    @Test
    void userSleepingTypeForLark() {
        Assertions.assertEquals("Ваш тип сна: Жаворонок",functions.get(6).apply(List.of(new SleepingSession(
                LocalDateTime.of(2025,10,21,21,0),
                LocalDateTime.of(2025,10,22,6,0),
                SleepQuality.BAD))).getAnswer());
    }

    @Test
    void userSleepingTypeForPigeon() {
        Assertions.assertEquals("Ваш тип сна: Голубь",functions.get(6).apply(List.of(new SleepingSession(
                LocalDateTime.of(2025,10,21,0,0),
                LocalDateTime.of(2025,10,21,6,0),
                SleepQuality.BAD))).getAnswer());
    }
}