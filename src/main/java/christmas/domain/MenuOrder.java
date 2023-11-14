package christmas.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MenuOrder {
    private final Map<String, Integer> orderedItems;
    private int totalPrice = 0;

    public int getTotalPrice() {
        return totalPrice;
    }

    public MenuOrder(String foods) {
        this.orderedItems = new HashMap<>();
        this.totalPrice = validate(foods);
    }

    private int validate(String foods) {
        checkBlankOrder(foods);
        checkFormat(foods);
        checkNoMenuInFoods(foods);
        checkTotalOrderLimit();

        return calculateTotalPrice();
    }

    private int calculateTotalPrice() {
        int calculatedTotalPrice = 0;

        for (Map.Entry<String, Integer> entry : orderedItems.entrySet()) {
            String menuName = entry.getKey();

            int quantity = entry.getValue();
            int menuPrice = getMenuPrice(menuName);

            calculatedTotalPrice += (menuPrice * quantity);
        }

        return calculatedTotalPrice;
    }

    private int getMenuPrice(String menuName) {
        return MenuBoard.valueOf(menuName).getPrice();
    }

    private void checkMenuValidity(String menuName) {
        boolean menuFound = Arrays.stream(MenuBoard.values())
                .anyMatch(menu -> menu.name().equalsIgnoreCase(menuName));

        if (!menuFound) {
            throw new IllegalArgumentException();
        }
    }

    public Map<String, Integer> getOrderedItems() {
        return orderedItems;
    }

    private void checkBlankOrder(String foods) {
        if (foods.contains(" ")) {
            throw new IllegalArgumentException();
        }
    }

    private void checkFormat(String foods) {
        String[] menuItems = foods.split(",");

        boolean isValidOrder = Arrays.stream(menuItems)
                .allMatch(item -> {
                    String[] parts = item.split("-");
                    if (parts.length == 2) {
                        String menuName = parts[0].trim();
                        String quantity = parts[1].trim();
                        return menuName.matches("[\\w가-힣]+") && quantity.matches("[1-9]|1[0-9]|20");
                    }
                    return false;
                });

        checkResultFormat(isValidOrder);
    }

    private void checkResultFormat(boolean isValidOrder) {
        if (!isValidOrder) {
            throw new IllegalArgumentException();
        }
    }

    private void checkNoMenuInFoods(String foods) {
        String[] menuItems = foods.split(",");
        if (menuItems.length == 1) {
            orderMenus(menuItems[0]);
        } else {
            Arrays.stream(menuItems)
                    .map(String::trim)
                    .forEach(this::orderMenus);
        }
    }

    private void orderMenus(String menuItem) {
        String[] menuAndQuantity = menuItem.split("-");
        String menuName = menuAndQuantity[0];
        int quantity = Integer.parseInt(menuAndQuantity[1]);

        checkDuplicateMenu(menuName);
        checkMenuValidity(menuName);
        orderedItems.put(menuName, quantity);
    }

    private void checkDuplicateMenu(String menuName) {
        if (orderedItems.containsKey(menuName)) {
            throw new IllegalArgumentException();
        }
    }


    public void checkTotalOrderLimit() {
        int totalQuantity = orderedItems.values().stream().mapToInt(Integer::intValue).sum();

        if (totalQuantity > 20) {
            throw new IllegalArgumentException();
        }
    }
}
