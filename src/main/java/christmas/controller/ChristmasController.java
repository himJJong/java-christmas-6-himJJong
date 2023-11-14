package christmas.controller;

import christmas.domain.Day;
import christmas.domain.Discount;
import christmas.domain.MenuOrder;
import christmas.domain.Number;
import christmas.view.Input;
import christmas.view.NoEventOutput;
import christmas.view.Output;

public class ChristmasController {
    private final Input input = new Input();
    private final Output output = new Output();
    private final NoEventOutput noEventOutput = new NoEventOutput();
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
        int beforeDiscountPrice = menu.getTotalPrice();

        output.showMenuOrder(menu);
        output.showBeforeDiscountPrice(menu);
        applyEvent(beforeDiscountPrice, menu, day);
    }

    private void applyEvent(int beforeDiscountPrice, MenuOrder menu, Day day) {
        if (beforeDiscountPrice >= numberToInt(Number.APPLY_EVENT_LIMIT)) {
            discount.check(menu, day);
        }

        if (beforeDiscountPrice < numberToInt(Number.APPLY_EVENT_LIMIT)) {
            noEventOutput.check(menu);
        }
    }

    private MenuOrder orderMenu() {
        try {
            return new MenuOrder(input.read(), numberToInt(Number.ZERO), numberToInt(Number.ZERO));
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

    private int numberToInt(Number date) {
        return date.getValue();
    }
}