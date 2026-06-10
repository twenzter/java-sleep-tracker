package ru.yandex.practicum.sleeptracker;

public class SleepAnalysisResult {

    private final String answer;

    public SleepAnalysisResult(Object value, String message) {
        this.answer = String.format(message, value);
    }

    public SleepAnalysisResult(String message) {
        this.answer = message;
    }


    public String getAnswer() {
        return answer;
    }

}
