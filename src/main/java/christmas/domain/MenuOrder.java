package christmas.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MenuOrder {
    private final Map<String, Integer> orderedItems;
    private int totalPrice = 0;
    private int dessertCount = 0;
    private int mainCount = 0;

    public MenuOrder(String foods, int dessertCount, int mainCount) {
        this.orderedItems = new HashMap<>();
        this.dessertCount = dessertCount;
        this.mainCount = mainCount;
        this.totalPrice = validate(foods);
    }

    private int validate(String foods) {
        checkBlankOrder(foods);
        checkFormat(foods);
        checkNoMenuInFoods(foods);
        checkTotalOrderLimit();
        checkIfOnlyBeverageOrdered();

        return calculateTotalPrice();
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

    public void checkTotalOrderLimit() {
        int totalQuantity = orderedItems.values().stream().mapToInt(Integer::intValue).sum();

        if (totalQuantity > 20) {
            throw new IllegalArgumentException();
        }
    }

    private void checkIfOnlyBeverageOrdered() {
        boolean onlyBeverageOrdered = orderedItems.keySet().stream()
                .allMatch(this::isValidBeverage);

        if (onlyBeverageOrdered) {
            throw new IllegalArgumentException();
        }
    }

    private int calculateTotalPrice() {
        int calculatedTotalPrice = 0;

        for (Map.Entry<String, Integer> menu : orderedItems.entrySet()) {
            String menuName = menu.getKey();

            int quantity = menu.getValue();
            int menuPrice = getMenuPrice(menuName);

            calculatedTotalPrice += (menuPrice * quantity);
        }

        return calculatedTotalPrice;
    }

    private int getMenuPrice(String menuName) {
        int price = findAppetizer(menuName);

        if (price == 0) {
            price = findMainCourse(menuName);
        }
        if (price == 0) {
            price = findDessert(menuName);
        }
        if (price == 0) {
            price = findBeverage(menuName);
        }

        return price;
    }


    private int findBeverage(String menuName) {
        int price = 0;

        for (MenuBoard.Beverage beverage : MenuBoard.Beverage.values()) {
            if (beverage.name().equalsIgnoreCase(menuName)) {
                price = beverage.getPrice();
                break;
            }
        }

        return price;
    }

    private int findDessert(String menuName) {
        int price = 0;
        for (MenuBoard.Dessert dessert : MenuBoard.Dessert.values()) {
            if (dessert.name().equalsIgnoreCase(menuName)) {
                price = dessert.getPrice();
                break;
            }
        }

        return price;
    }

    private int findMainCourse(String menuName) {
        int price = 0;

        for (MenuBoard.MainCourse mainCourse : MenuBoard.MainCourse.values()) {
            if (mainCourse.name().equalsIgnoreCase(menuName)) {
                price = mainCourse.getPrice();
                break;
            }
        }

        return price;
    }

    private int findAppetizer(String menuName) {
        int price = 0;

        for (MenuBoard.Appetizer appetizer : MenuBoard.Appetizer.values()) {
            if (appetizer.name().equalsIgnoreCase(menuName)) {
                price = appetizer.getPrice();
                break;
            }
        }

        return price;
    }

    public Map<String, Integer> getOrderedItems() {
        return orderedItems;
    }


    private void checkResultFormat(boolean isValidOrder) {
        if (!isValidOrder) {
            throw new IllegalArgumentException();
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

    private boolean isValidAppetizer(String menuName) {
        try {
            MenuBoard.Appetizer.valueOf(menuName);
            return true;
        } catch (IllegalArgumentException ignored) {
            return false;
        }
    }

    private boolean isValidMainCourse(String menuName) {
        try {
            MenuBoard.MainCourse.valueOf(menuName);
            mainCount++;
            return true;
        } catch (IllegalArgumentException ignored) {
            return false;
        }
    }

    private boolean isValidDessert(String menuName) {
        try {
            MenuBoard.Dessert.valueOf(menuName);
            dessertCount++;
            return true;
        } catch (IllegalArgumentException ignored) {
            return false;
        }
    }

    private boolean isValidBeverage(String menuName) {
        try {
            MenuBoard.Beverage.valueOf(menuName);
            return true;
        } catch (IllegalArgumentException ignored) {
            return false;
        }
    }

    private void checkMenuValidity(String menuName) {
        boolean isValidMenu = isValidAppetizer(menuName) ||
                isValidMainCourse(menuName) ||
                isValidDessert(menuName) ||
                isValidBeverage(menuName);

        if (!isValidMenu) {
            throw new IllegalArgumentException();
        }
    }


    public int getTotalPrice() {
        return totalPrice;
    }

    public int getDessertCount() {
        return dessertCount;
    }

    public int getMainCount() {
        return mainCount;
    }
}
