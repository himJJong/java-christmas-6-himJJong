package christmas.output;

import christmas.domain.MenuOrder;
import christmas.view.Output;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OutputTest {
    @Test
    @DisplayName("주문 메뉴가 맞는지 확인하는 성공 테스트")
    void testOrder() {
        // Given
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        MenuOrder menuOrder = new MenuOrder("아이스크림-2,초코케이크-2");
        menuOrder.getOrderedItems().put("아이스크림", 2);
        menuOrder.getOrderedItems().put("초코케이크", 2);

        // When
        Output output = new Output();
        output.MenuOrder(menuOrder);

        // Then
        String expectedOutput = "<주문 메뉴>\n" +
                "아이스크림 2개\n" +
                "초코케이크 2개\n" +
                "\n";
        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }
}
