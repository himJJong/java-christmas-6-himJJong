package christmas.controller;

import christmas.domain.Day;
import christmas.domain.MenuOrder;
import christmas.view.Input;
import christmas.view.Output;

public class ChristmasController {
    private final Input input = new Input();
    private final Output output = new Output();
    public void run() {
        output.showEventPlanner();
        Day day = reserveDay();
        output.showMenu();
        MenuOrder menuOrder = orderMenu();
    }

    private MenuOrder orderMenu() {
        try {
            return new MenuOrder(input.read());
        } catch (IllegalArgumentException e){
            output.showMenuError();
            return orderMenu();
        }
    }

    private Day reserveDay() {
        try{
            return new Day(toInt(input.read()));
        } catch (IllegalArgumentException e){
            output.showDayError();
            return reserveDay();
        }
    }

    private int toInt(String input){
        return Integer.parseInt(input);
    }
}
