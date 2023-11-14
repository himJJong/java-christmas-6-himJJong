package christmas.domain;

import christmas.view.Output;

public class Discount {
    private final int ZERO = 0;
    private final int ONE = 1;
    private final int CHRISTMAS_DATE = 25;
    private final int CHRISTMAS_DISCOUNT_UNIT = 100;
    private final int SPECIAL_DISCOUNT_UNIT = 1000;
    private final int SEVEN = 7;
    private final int SPECIAL_DATE = 3;
    private final Output output = new Output();

    public void check(MenuOrder menu, Day day) {
        int discountPrice = 0;
        int date = day.getDate();

        discountPrice += christmasDiscount(date); // 날짜  체크 1~25 (완)
        discountPrice += dailyDiscount(menu, date);     // 평일 주말 체크
        discountPrice += specialDiscount(date);   // 별체크 (완)
        giftDiscount(menu, discountPrice);         // 증정 이벤트 (완), 총혜택 금액
        afterDiscountPrice(menu, discountPrice);
    }

    private void afterDiscountPrice(MenuOrder menu, int discountPrice) {
        output.showAfterDiscountPrice(menu, discountPrice);
    }

    private void giftDiscount(MenuOrder menu, int discountPrice) {
        if(menu.getTotalPrice() >= output.GIFT_LIMIT){
            output.showGiftEvent();
        }
        else if(menu.getTotalPrice() < output.GIFT_LIMIT && discountPrice == ZERO){
            output.showNone();
        }
        output.showTotalDiscount(discountPrice);
    }

    private int specialDiscount(int date) {
        if(date % SEVEN == SPECIAL_DATE || date == CHRISTMAS_DATE){
            output.showSpecialDiscount(SPECIAL_DISCOUNT_UNIT);
            return SPECIAL_DISCOUNT_UNIT;
        }

        return ZERO;
    }

    private int dailyDiscount(MenuOrder menu, int date) {

        return 0;
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
        return (date - ONE) * CHRISTMAS_DISCOUNT_UNIT;
    }
}
