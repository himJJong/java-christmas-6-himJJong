package christmas.domain;

import christmas.view.Output;

public class Discount {
    private final Output output = new Output();
    private int totalDiscountPrice = 0;

    public void check(MenuOrder menu, Day day) {
        int discountPrice = 0;
        int date = day.date();

        if (menu.getTotalPrice() >= Number.GIFT_LIMIT.getValue()) {
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
        System.out.printf(String.format(output.EVENT_BADGE, numberToInt(Number.MONTH)) + output.SKIP_LINE);

        if (totalDiscountPrice >= numberToInt(Number.FIVE_THOUSAND) && totalDiscountPrice < numberToInt(Number.TEN_THOUSAND)) {
            output.showBadgeStar();
        } else if (totalDiscountPrice >= numberToInt(Number.TEN_THOUSAND) && totalDiscountPrice < numberToInt(Number.TWENTY_THOUSAND)) {
            output.showBadgeTree();
        } else if (totalDiscountPrice >= numberToInt(Number.TWENTY_THOUSAND)) {
            output.showBadgeSanta();
        }
    }

    private void afterDiscountPrice(MenuOrder menu, int discountPrice) {
        output.showAfterDiscountPrice(menu, discountPrice);
    }

    private void giftDiscount(MenuOrder menu, int discountPrice) {
        if (menu.getTotalPrice() >= numberToInt(Number.GIFT_LIMIT)) {
            output.showGiftEvent();
            totalDiscountPrice += discountPrice + numberToInt(Number.GIFT_PRICE);
        } else if (menu.getTotalPrice() < numberToInt(Number.GIFT_LIMIT) && discountPrice == numberToInt(Number.ZERO)) {
            output.showNoExist();
        } else if (menu.getTotalPrice() < numberToInt(Number.GIFT_LIMIT)) {
            totalDiscountPrice += discountPrice;
        }
        output.showTotalDiscountPrice();
        output.showTotalDiscountPriceWon(totalDiscountPrice);
    }

    private int specialDiscount(int date) {
        if (date % numberToInt(Number.SEVEN) == numberToInt(Number.SPECIAL_DATE) || date == numberToInt(Number.CHRISTMAS_DATE)) {
            output.showSpecialDiscount(numberToInt(Number.THOUSAND));
            return numberToInt(Number.THOUSAND);
        }

        return numberToInt(Number.ZERO);
    }

    private int dailyDiscount(MenuOrder menu, int date) {
        if (date % numberToInt(Number.SEVEN) == numberToInt(Number.ONE) || date % numberToInt(Number.SEVEN) == numberToInt(Number.TWO)) {
            output.showWeekendDiscount(menu.getDessertCount() * numberToInt(Number.THIS_YEAR));
            return menu.getDessertCount() * numberToInt(Number.THIS_YEAR);
        }
        output.showWeekdayDiscount(menu.getMainCount() * numberToInt(Number.THIS_YEAR));

        return menu.getMainCount() * numberToInt(Number.THIS_YEAR);
    }

    private int christmasDiscount(int date) {
        int discountPrice = 0;

        if (date >= numberToInt(Number.ONE) && date <= numberToInt(Number.CHRISTMAS_DATE)) {
            discountPrice = calculatedChristmasDiscount(date);
            output.showChristmasDiscount(discountPrice);
            return discountPrice;
        }

        return numberToInt(Number.ZERO);
    }

    private int calculatedChristmasDiscount(int date) {
        return ((date - numberToInt(Number.ONE)) * numberToInt(Number.CHRISTMAS_DISCOUNT_UNIT)) + numberToInt(Number.THOUSAND);
    }

    private int numberToInt(Number date) {
        return date.getValue();
    }
}
