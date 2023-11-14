package christmas.view;

import christmas.domain.MenuOrder;

import java.util.Map;

public class Output {
    private static final String EVENT_PLANNER = "안녕하세요! 우테코 식당 %d월 이벤트 플래너입니다.\n";
    private static final int MONTH = 12;
    private static final String RESERVATION_DAY = "%d월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String ERROR_FORMAT = "[ERROR]";
    private static final String INVALID_DAY_FORMAT = " 유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    private static final String MENU = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";
    private static final String INVALID_MENU_ORDER = " 유효하지 않은 주문입니다. 다시 입력해 주세요.";
    private static final String PER = "개";
    private static final String COMPLETED_MENU_ORDER = "<주문 메뉴>";
    public void EventPlanner() {
        String eventMessage = String.format(EVENT_PLANNER, MONTH);
        String reservationMessage = String.format(RESERVATION_DAY, MONTH);
        System.out.println(eventMessage + reservationMessage);
    }

    public void DayError() {
        System.out.println(ERROR_FORMAT+INVALID_DAY_FORMAT);
    }

    public void Menu() {
        System.out.println(MENU);
    }

    public void MenuError(){
        System.out.println(ERROR_FORMAT+INVALID_MENU_ORDER);
    }

    public void Order(MenuOrder menu) {
        System.out.println(COMPLETED_MENU_ORDER);
        menu.getOrderedItems().forEach((key, value) -> System.out.println(key + " " + value + "개"));
    }
}
