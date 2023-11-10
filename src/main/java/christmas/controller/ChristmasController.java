package christmas.controller;

import christmas.domain.Day;
import christmas.view.Input;
import christmas.view.Output;

public class ChristmasController {
    private final Input input = new Input();
    private final Output output = new Output();
    public void run() {
        output.showEventPlanner();
        Day day = reserveDay();
    }

    private Day reserveDay() {
        try{
            return new Day(toInt(input.read()));
        } catch (IllegalArgumentException e){
            output.showError();
            return reserveDay();
        }
    }

    private int toInt(String input){
        return Integer.parseInt(input);
    }
}
