package christmas.view;

import christmas.domain.MenuOrder;

public class NoEventOutput {
    private static final int ZERO = 0;
    private final Output output = new Output();

    public void check(MenuOrder menu) {
        noneGift();
        noneDiscount();
        noneDiscountPrice();
        totalPrice(menu);
        noneEventBadge();
    }

    private void noneEventBadge() {
        System.out.printf(String.format(output.EVENT_BADGE, output.MONTH) + "\n");
        output.showNone();
    }

    private void totalPrice(MenuOrder menu) {
        System.out.println(output.AFTER_DISCOUNT_PRICE);
        System.out.print(menu.getTotalPrice() + output.WON);
        System.out.println(output.LINE);
    }

    private void noneDiscountPrice() {
        output.showTotalDiscountPrice();
        System.out.print(ZERO + output.WON);
        System.out.println(output.LINE);
    }

    private void noneDiscount() {
        System.out.println(output.DISCOUNT_RECORD);
        output.showNone();
        System.out.print(output.LINE);
    }

    public void noneGift() {
        System.out.println(output.GIFT_MENU);
        output.showNone();
        System.out.print(output.LINE);
    }
}
