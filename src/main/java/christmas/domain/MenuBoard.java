package christmas.domain;

public class MenuBoard {
    public enum Appetizer {
        양송이수프(6_000), 타파스(5_500), 시저샐러드(8_000);

        private final int price;

        Appetizer(int price) {
            this.price = price;
        }

        public int getPrice() {
            return price;
        }
    }

    public enum MainDish {
        티본스테이크(55_000), 바비큐립(54_000), 해산물파스타(35_000), 크리스마스파스타(25_000);

        private final int price;

        MainDish(int price) {
            this.price = price;
        }

        public int getPrice() {
            return price;
        }
    }

    public enum Dessert {
        초코케이크(15_000), 아이스크림(5_000);

        private final int price;

        Dessert(int price) {
            this.price = price;
        }

        public int getPrice() {
            return price;
        }
    }

    public enum Beverage {
        제로콜라(3_000), 레드와인(60_000), 샴페인(25_000);

        private final int price;

        Beverage(int price) {
            this.price = price;
        }

        public int getPrice() {
            return price;
        }
    }
}
