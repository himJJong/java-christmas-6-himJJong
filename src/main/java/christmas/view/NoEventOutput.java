package christmas.view;

import christmas.domain.MenuOrder;
import christmas.domain.Number;

public class NoEventOutput {
    private final Output output = new Output();

    public void check(MenuOrder menu) {
        noneGift();
        noneDiscount();
        noneDiscountPrice();
        totalPrice(menu);
        noneEventBadge();
    }

    private void noneEventBadge() {
        System.out.printf(String.format(output.EVENT_BADGE, numberToInt(Number.MONTH)) + output.SKIP_LINE);
        output.showNoExist();
    }

    private void totalPrice(MenuOrder menu) {
        System.out.println(output.AFTER_DISCOUNT_PRICE_SENTENCE);
        System.out.println(menu.getTotalPrice() + output.WON + output.SKIP_LINE);
    }

    private void noneDiscountPrice() {
        output.showTotalDiscountPrice();
        System.out.println(numberToInt(Number.ZERO) + output.WON + output.SKIP_LINE);
    }

    private void noneDiscount() {
        System.out.println(output.DISCOUNT_RECORD_SENTENCE);
        output.showNoExist();
        System.out.print(output.SKIP_LINE);
    }

    public void noneGift() {
        System.out.println(output.GIFT_MENU_SENTENCE);
        output.showNoExist();
        System.out.print(output.SKIP_LINE);
    }

    private int numberToInt(Number date) {
        return date.getValue();
    }
}
