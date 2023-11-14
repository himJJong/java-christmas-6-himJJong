package christmas.domain;

public class Day {
    private final int date;
    private static final int FIRST_DATE = 1;
    private static final int LAST_DATE = 31;

    public Day(int date) {
        validateDay(date);
        this.date = date;
    }

    private void validateDay(int number) {
        if (checkInvalidArea(number)) {
            throw new IllegalArgumentException();
        }
    }

    public int getDate() {
        return date;
    }

    private boolean checkInvalidArea(int number) {
        return number < FIRST_DATE || number > LAST_DATE;
    }
}
