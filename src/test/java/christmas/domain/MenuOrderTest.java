package christmas.domain;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MenuOrderTest {

    @Test
    @DisplayName("메뉴 주문의 형식이 잘못되었을 때 예외 테스트")
    public void testInvalidFormat() {
        String invalidFormat = "InvalidFormat";
        assertThrows(IllegalArgumentException.class, () -> new MenuOrder(invalidFormat));
    }

    @Test
    @DisplayName("같은 메뉴 중복일 때 예외 테스트")
    public void testDuplicateMenu() {
        String duplicateMenu = "양송이수프-2,양송이수프-1";
        assertThrows(IllegalArgumentException.class, () -> new MenuOrder(duplicateMenu));
    }

    @Test
    @DisplayName("주문 예외 테스트")
    void testWrongOrderMenu() {
        String wrongOrderMenu = "3,제로콜라-a";
        assertThrows(IllegalArgumentException.class, () -> new MenuOrder(wrongOrderMenu));
    }

    @Test
    @DisplayName("메뉴판에 없는 메뉴 주문 했을 때 예외 테스트")
    public void testInvalidMenu() {
        String invalidMenu = "InvalidMenu-1";
        assertThrows(IllegalArgumentException.class, () -> new MenuOrder(invalidMenu));
    }

    @Test
    @DisplayName("각 메뉴 수량 개수가 0개일 때 예외 테스트")
    public void testZeroQuantity() {
        String zeroQuantity = "양송이수프-0,초코케이크-0";
        assertThrows(IllegalArgumentException.class, () -> new MenuOrder(zeroQuantity));
    }

    @Test
    @DisplayName("총 수량이 20개를 넘었을 때 예외 테스트")
    public void testTotalOrderLimitExceeded() {
        String orderExceedLimit = "양송이수프-1,바비큐립-2,초코케이크-3,제로콜라-15";
        assertThrows(IllegalArgumentException.class, () -> new MenuOrder(orderExceedLimit));
    }

    @Test
    @DisplayName("주문을 1개만 했을 때 통과 테스트")
    public void testOneOrder() {
        String orderOneLimit = "초코케이크-1";
        assertDoesNotThrow(() -> new MenuOrder(orderOneLimit));
    }

    @Test
    @DisplayName("주문시 공백이 있을 때 예외 테스트")
    public void testBlankOrder() {
        String orderOneLimit = "초코케이크-1, 아이스크림-5";
        assertThrows(IllegalArgumentException.class, () -> new MenuOrder(orderOneLimit));
    }

    @Test
    @DisplayName("주문을 20개 했을 때 통과 테스트")
    public void testTwentyOrders() {
        String orderTwentyLimit = "아이스크림-20";
        MenuOrder menuOrder = new MenuOrder(orderTwentyLimit);
        assertDoesNotThrow(menuOrder::checkTotalOrderLimit);
    }

    @Test
    @DisplayName("주문을 15개 했을 때 통과 테스트")
    public void testFifteenOrders() {
        String orderFifteenLimit = "아이스크림-5,초코케이크-10,제로콜라-4";
        MenuOrder menuOrder = new MenuOrder(orderFifteenLimit);
        assertDoesNotThrow(menuOrder::checkTotalOrderLimit);
    }
}