package christmas.view;

public class Output {
    private static final String EVENT_PLANNER = "안녕하세요! 우테코 식당 %d월 이벤트 플래너입니다.\n";
    private static final int MONTH = 12;
    private static final String RESERVATION_DAY = "%d월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String ERROR_FORMAT = "[ERROR]";
    private static final String INVALID_DAY_FORMAT = " 유효하지 않은 날짜입니다. 다시 입력해 주세요.";

    public void showEventPlanner() {
        String eventMessage = String.format(EVENT_PLANNER, MONTH);
        String reservationMessage = String.format(RESERVATION_DAY, MONTH);
        System.out.println(eventMessage + reservationMessage);
    }

    public void showError() {
        System.out.println(ERROR_FORMAT+INVALID_DAY_FORMAT);
    }
}
