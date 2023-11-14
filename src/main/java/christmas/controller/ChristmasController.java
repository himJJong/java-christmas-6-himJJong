package christmas.controller;

import christmas.domain.Day;
import christmas.domain.Discount;
import christmas.domain.MenuOrder;
import christmas.view.Input;
import christmas.view.Output;

public class ChristmasController {
    private final Input input = new Input();
    private final Output output = new Output();
    private final Discount discount = new Discount();

    public void run() {
        output.showEventPlanner();
        Day day = reserveDay();
        output.showMenu();
        MenuOrder menu = orderMenu();
        output.showEventPreview(day);
        result(menu, day);
    }

    private void result(MenuOrder menu, Day day) {
        output.showMenuOrder(menu);
        output.showBeforeDiscountPrice(menu);
        output.showGift(menu);
        discount.check(menu, day);

        /*

        output.Badge(menu);
        */
    }

    private MenuOrder orderMenu() {
        try {
            return new MenuOrder(input.read());
        } catch (IllegalArgumentException e) {
            output.showMenuError();
            return orderMenu();
        }
    }

    private Day reserveDay() {
        try {
            return new Day(toInt(input.read()));
        } catch (IllegalArgumentException e) {
            output.showDayError();
            return reserveDay();
        }
    }

    private int toInt(String input) {
        return Integer.parseInt(input);
    }
}
