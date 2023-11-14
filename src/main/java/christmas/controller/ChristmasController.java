package christmas.controller;

import christmas.domain.Day;
import christmas.domain.MenuOrder;
import christmas.view.Input;
import christmas.view.Output;

public class ChristmasController {
    private final Input input = new Input();
    private final Output output = new Output();

    public void run() {
        output.EventPlanner();
        Day day = reserveDay();
        output.Menu();
        MenuOrder menu = orderMenu();
        output.EventPreview(day);
        result(menu, day);
    }

    private void result(MenuOrder menu, Day day) {
        output.MenuOrder(menu);
        output.BeforeDiscountPrice(menu);
        output.Gift(menu);
        /*
        output.BenefitRecord(menu);
        output.TotalBenefit(menu);
        output.AfterDiscountPrice(menu);
        output.Badge(menu);
         */

    }

    private MenuOrder orderMenu() {
        try {
            return new MenuOrder(input.read());
        } catch (IllegalArgumentException e) {
            output.MenuError();
            return orderMenu();
        }
    }

    private Day reserveDay() {
        try {
            return new Day(toInt(input.read()));
        } catch (IllegalArgumentException e) {
            output.DayError();
            return reserveDay();
        }
    }

    private int toInt(String input) {
        return Integer.parseInt(input);
    }
}
