package ru.yandex.practicum.sleeptracker;
import java.time.LocalDateTime;

public class SleepingSession {
    private final LocalDateTime startSleepingTime;
    private final LocalDateTime endSleepingTime;
    private final SleepQuality sleepQuality;

    SleepingSession(LocalDateTime startSleepingTime, LocalDateTime endSleepingTime, SleepQuality sleepQuality) {
        this.startSleepingTime = startSleepingTime;
        this.endSleepingTime = endSleepingTime;
        this.sleepQuality = sleepQuality;
    }

    @Override
    public String toString() {
        return "SleepingSession{" +
                "startSleepingTime=" + startSleepingTime +
                ", endSleepingTime=" + endSleepingTime +
                ", sleepQuality='" + sleepQuality + '\'' +
                '}';
    }

    public LocalDateTime getStartSleepingTime() {
        return startSleepingTime;
    }

    public LocalDateTime getEndSleepingTime() {
        return endSleepingTime;
    }

    public SleepQuality getSleepQuality() {
        return sleepQuality;
    }
}
