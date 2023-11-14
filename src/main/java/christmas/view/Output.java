package christmas.view;

import christmas.domain.Day;
import christmas.domain.MenuOrder;

public class Output {
    private static final String EVENT_PLANNER_SENTENCE = "안녕하세요! 우테코 식당 %d월 이벤트 플래너입니다.\n";
    private static final String RESERVATION_DAY_SENTENCE = "%d월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String MENU_SENTENCE = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";
    private static final String EVENT_PREVIEW_SENTENCE = "%d월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String COMPLETED_MENU_ORDER = "<주문 메뉴>";
    private static final String BEFORE_DISCOUNT_PRICE = "<할인 전 총주문 금액>";
    public static final String GIFT_MENU_SENTENCE = "<증정 메뉴>";
    public static final String DISCOUNT_RECORD_SENTENCE = "<혜택 내역>";
    private static final String CHRISTMAS_DISCOUNT_SENTENCE = "크리스마스 디데이 할인: -";
    private static final String WEEKDAY = "평일 할인: -";
    private static final String WEEKEND = "주말 할인: -";
    private static final String SPECIAL_DISCOUNT_SENTENCE = "특별 할인: -";
    private static final String GIFT_EVENT_SENTENCE = "증정 이벤트: -";
    private static final String TOTAL_DISCOUNT_SENTENCE = "<총혜택 금액>";
    public static final String AFTER_DISCOUNT_PRICE_SENTENCE = "<할인 후 예상 결제 금액>";
    public static final String EVENT_BADGE = "<%d월 이벤트 배지>";
    private static final String ERROR_FORMAT = "[ERROR]";
    private static final String INVALID_DAY_FORMAT = " 유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    private static final String INVALID_MENU_FORMAT = " 유효하지 않은 주문입니다. 다시 입력해 주세요.";
    private static final String NONE = "없음";
    private static final String STAR = "별";
    private static final String TREE = "트리";
    private static final String SANTA = "산타";
    private static final String GIFT = "샴페인";
    public static final int GIFT_LIMIT = 120000;
    public static final int GIFT_PRICE = 25000;
    public static final int ZERO = 0;
    private static final String ONE = "1";
    public static final int MONTH = 12;
    private static final String PER = "개";
    public static final String WON = "원";
    public static final String SKIP_LINE = "\n";

    public void showEventPlanner() {
        String eventMessage = String.format(EVENT_PLANNER_SENTENCE, MONTH);
        String reservationMessage = String.format(RESERVATION_DAY_SENTENCE, MONTH);
        System.out.println(eventMessage + reservationMessage);
    }

    public void showDayError() {
        System.out.println(ERROR_FORMAT + INVALID_DAY_FORMAT);
    }

    public void showMenu() {
        System.out.println(MENU_SENTENCE);
    }

    public void showMenuError() {
        System.out.println(ERROR_FORMAT + INVALID_MENU_FORMAT);
    }

    public void showMenuOrder(MenuOrder menu) {
        System.out.println(COMPLETED_MENU_ORDER);
        menu.getOrderedItems().forEach((key, value) -> System.out.println(key + " " + value + PER));
        System.out.print(SKIP_LINE);
    }

    public void showBeforeDiscountPrice(MenuOrder menu) {
        System.out.println(BEFORE_DISCOUNT_PRICE);
        System.out.println(String.format("%,d", menu.getTotalPrice()) + WON);
        System.out.print(SKIP_LINE);
    }

    public void showEventPreview(Day day) {
        System.out.printf(EVENT_PREVIEW_SENTENCE, MONTH, day.getDate());
        System.out.print(SKIP_LINE);
    }


    public void showChristmasDiscount(int christmasDiscount) {
        System.out.print(CHRISTMAS_DISCOUNT_SENTENCE);
        System.out.printf(String.format("%,d", christmasDiscount) + WON + "\n");
    }

    public void showNoExist() {
        System.out.println(NONE);
    }

    public void showSpecialDiscount(int specialDiscountUnit) {
        System.out.print(SPECIAL_DISCOUNT_SENTENCE);
        System.out.printf(String.format("%,d", specialDiscountUnit) + WON + SKIP_LINE);
    }

    public void showGiftEvent() {
        System.out.print(GIFT_EVENT_SENTENCE);
        System.out.printf(String.format("%,d", GIFT_PRICE) + WON);
        System.out.print(SKIP_LINE);
    }

    public void showTotalDiscountPrice() {
        System.out.println(TOTAL_DISCOUNT_SENTENCE);
    }

    public void showTotalDiscountPriceWon(int discountPrice) {
        System.out.println("-" + String.format("%,d", discountPrice) + WON);
        System.out.println(SKIP_LINE);
    }

    public void showAfterDiscountPrice(MenuOrder menu, int discountPrice) {
        System.out.println(AFTER_DISCOUNT_PRICE_SENTENCE);
        System.out.printf(String.format("%,d", menu.getTotalPrice() - discountPrice) + WON);
        System.out.println(SKIP_LINE);
    }

    public void showWeekdayDiscount(int discountPrice) {
        System.out.print(WEEKDAY);
        System.out.printf(String.format("%,d", discountPrice) + WON + SKIP_LINE);
    }

    public void showWeekendDiscount(int discountPrice) {
        System.out.print(WEEKEND);
        System.out.printf(String.format("%,d", discountPrice) + WON + SKIP_LINE);
    }

    public void showGiftMenu() {
        System.out.println(GIFT_MENU_SENTENCE);
        System.out.println(GIFT + " " + ONE + PER + SKIP_LINE);
    }

    public void showEventRecord() {
        System.out.println(DISCOUNT_RECORD_SENTENCE);
    }

    public void showBadgeSanta() {
        System.out.println(SANTA);
    }

    public void showBadgeStar() {
        System.out.println(STAR);
    }

    public void showBadgeTree() {
        System.out.println(TREE);
    }
}