package christmas.view;

import christmas.domain.Day;
import christmas.domain.MenuOrder;

public class Output {

    private static final String EVENT_PLANNER = "안녕하세요! 우테코 식당 %d월 이벤트 플래너입니다.\n";
    public static final int MONTH = 12;
    private static final String RESERVATION_DAY = "%d월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String ERROR_FORMAT = "[ERROR]";
    private static final String INVALID_DAY_FORMAT = " 유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    private static final String MENU = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";
    private static final String INVALID_MENU_ORDER = " 유효하지 않은 주문입니다. 다시 입력해 주세요.";
    private static final String EVENT_PREVIEW = "%d월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String CHRISTMAS_DISCOUNT = "크리스마스 디데이 할인: -%d";
    private static final String SPECIAL_DISCOUNT = "특별 할인: -%d";
    private static final String GIFT_EVENT = "증정 이벤트: -";
    public static final String GIFT_MENU = "<증정 메뉴>";
    public static final String DISCOUNT_RECORD = "<혜택 내역>";
    private static final String TOTAL_DISCOUNT = "<총혜택 금액>";
    public static final String AFTER_DISCOUNT_PRICE = "<할인 후 예상 결제 금액>";
    private static final String NONE = "없음";
    public static final int GIFT_LIMIT = 120000;
    private static final String GIFT = "샴페인";
    public static final int GIFT_PRICE = 25000;
    private static final String ONE = "1";
    private static final String PER = "개";
    public static final String WON = "원";
    private static final String COMPLETED_MENU_ORDER = "<주문 메뉴>";
    private static final String BEFORE_DISCOUNT_PRICE = "<할인 전 총주문 금액>";
    public static final String LINE = "\n";
    public static final String EVENT_BADGE = "<%d월 이벤트 배지>";

    public void showEventPlanner() {
        String eventMessage = String.format(EVENT_PLANNER, MONTH);
        String reservationMessage = String.format(RESERVATION_DAY, MONTH);
        System.out.println(eventMessage + reservationMessage);
    }

    public void showDayError() {
        System.out.println(ERROR_FORMAT + INVALID_DAY_FORMAT);
    }

    public void showMenu() {
        System.out.println(MENU);
    }

    public void showMenuError() {
        System.out.println(ERROR_FORMAT + INVALID_MENU_ORDER);
    }

    public void showMenuOrder(MenuOrder menu) {
        System.out.println(COMPLETED_MENU_ORDER);
        menu.getOrderedItems().forEach((key, value) -> System.out.println(key + " " + value + PER));
        System.out.print(LINE);
    }

    public void showBeforeDiscountPrice(MenuOrder menu) {
        System.out.println(BEFORE_DISCOUNT_PRICE);
        System.out.println(String.format("%,d", menu.getTotalPrice()) + WON);
        System.out.print(LINE);
    }

    public void showEventPreview(Day day) {
        System.out.printf(EVENT_PREVIEW, MONTH, day.getDate());
        System.out.print(LINE);
    }


    public void showChristmasDiscount(int christmasDiscount) {
        System.out.print(CHRISTMAS_DISCOUNT);
        System.out.printf(String.format("%,d", christmasDiscount) + WON);
    }

    public void showNone() {
        System.out.println(NONE);
    }

    public void showSpecialDiscount(int specialDiscountUnit) {
        System.out.print(SPECIAL_DISCOUNT);
        System.out.printf(String.format("%,d", specialDiscountUnit) + WON);
    }

    public void showGiftEvent() {
        System.out.print(GIFT_EVENT);
        System.out.printf(String.format("%,d", GIFT_PRICE) + WON);
        System.out.println(LINE);
    }

    public void showTotalDiscountPrice() {
        System.out.println(TOTAL_DISCOUNT);
    }

    public void showTotalDiscountPriceWon( int discountPrice) {
        System.out.println("-" + String.format("%,d", discountPrice) + WON);
        System.out.println(LINE);
    }

    public void showAfterDiscountPrice(MenuOrder menu, int discountPrice) {
        System.out.println(AFTER_DISCOUNT_PRICE);
        System.out.printf(String.format("%,d", menu.getTotalPrice() - discountPrice) + WON);
        System.out.println(LINE);
    }
}