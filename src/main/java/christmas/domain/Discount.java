package christmas.domain;

import christmas.view.Output;

public class Discount {
    private final int ZERO = 0;
    private final int ONE = 1;
    private final int TWO = 2;
    private final int CHRISTMAS_DATE = 25;
    private final int CHRISTMAS_DISCOUNT_UNIT = 100;
    private final int THOUSAND = 1000;
    private final int SEVEN = 7;
    private final int SPECIAL_DATE = 3;
    private final int FIVE_THOUSAND = 5000;
    private final int TEN_THOUSAND = 10000;
    private final int TWENTY_THOUSAND = 20000;
    private final int THIS_YEAR = 2023;
    private final Output output = new Output();
    private int totalDiscountPrice = 0;

    public void check(MenuOrder menu, Day day) {
        int discountPrice = 0;
        int date = day.getDate();

        if (menu.getTotalPrice() >= output.GIFT_LIMIT) {
            output.showGiftMenu();
        }
        output.showEventRecord();
        discountPrice += christmasDiscount(date);
        discountPrice += dailyDiscount(menu, date);
        discountPrice += specialDiscount(date);
        giftDiscount(menu, discountPrice);
        afterDiscountPrice(menu, discountPrice);
        eventBadge();
    }

    private void eventBadge() {
        System.out.printf(String.format(output.EVENT_BADGE, output.MONTH) + "\n");

        if (totalDiscountPrice >= FIVE_THOUSAND && totalDiscountPrice < TEN_THOUSAND) {
            output.showBadgeStar();
        } else if (totalDiscountPrice >= TEN_THOUSAND && totalDiscountPrice < TWENTY_THOUSAND) {
            output.showBadgeTree();
        } else if (totalDiscountPrice >= TWENTY_THOUSAND) {
            output.showBadgeSanta();
        }
    }

    private void afterDiscountPrice(MenuOrder menu, int discountPrice) {
        output.showAfterDiscountPrice(menu, discountPrice);
    }

    private void giftDiscount(MenuOrder menu, int discountPrice) {
        if (menu.getTotalPrice() >= output.GIFT_LIMIT) {
            output.showGiftEvent();
            totalDiscountPrice += discountPrice + output.GIFT_PRICE;
        } else if (menu.getTotalPrice() < output.GIFT_LIMIT && discountPrice == ZERO) {
            output.showNoExist();
        } else if (menu.getTotalPrice() < output.GIFT_LIMIT) {
            totalDiscountPrice += discountPrice;
        }
        output.showTotalDiscountPrice();
        output.showTotalDiscountPriceWon(totalDiscountPrice);
    }

    private int specialDiscount(int date) {
        if (date % SEVEN == SPECIAL_DATE || date == CHRISTMAS_DATE) {
            output.showSpecialDiscount(THOUSAND);
            return THOUSAND;
        }

        return ZERO;
    }

    private int dailyDiscount(MenuOrder menu, int date) {
        if (date % SEVEN == ONE || date % SEVEN == TWO) {
            output.showWeekendDiscount(menu.getDessertCount() * THIS_YEAR);
            return menu.getDessertCount() * THIS_YEAR;
        }
        output.showWeekdayDiscount(menu.getMainCount() * THIS_YEAR);

        return menu.getMainCount() * THIS_YEAR;
    }

    private int christmasDiscount(int date) {
        int discountPrice = 0;

        if (date >= ONE && date <= CHRISTMAS_DATE) {
            discountPrice = calculatedChristmasDiscount(date);
            output.showChristmasDiscount(discountPrice);
            return discountPrice;
        }

        return ZERO;
    }

    private int calculatedChristmasDiscount(int date) {
        return ((date - ONE) * CHRISTMAS_DISCOUNT_UNIT) + THOUSAND;
    }
}
