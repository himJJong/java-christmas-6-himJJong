package christmas.domain;

import christmas.view.Output;

public class Day {
    private final int date;
    private static final int FIRST_DATE = 1;
    private static final int LAST_DATE = 31;
    private final Output output = new Output();

    public Day(int date){
        validateDay(date);
        this.date = date;
    }

    private void validateDay(int number) {
        if(checkInvalidArea(number)){
            throw new IllegalArgumentException();
        }
    }

    private boolean checkInvalidArea(int number) {
        return number < FIRST_DATE || number > LAST_DATE;
    }
}
