package christmas.domain;

import java.util.stream.IntStream;

public record Day(int date) {
    public Day {
        validateDay(date);
    }

    private void validateDay(int date) {
        if (checkInvalidArea(date)) {
            throw new IllegalArgumentException("Invalid date.");
        }
    }

    private boolean checkInvalidArea(int number) {
        return IntStream
                .rangeClosed(numberToInt(Number.FIRST_DATE), numberToInt(Number.LAST_DATE))
                .noneMatch(i -> i == number);
    }

    private int numberToInt(Number date) {
        return date.getValue();
    }
}