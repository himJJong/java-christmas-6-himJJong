package christmas.domain;

public enum Number {
    ZERO(0),
    ONE(1),
    TWO(2),
    SEVEN(7),
    THOUSAND(1000),
    FIVE_THOUSAND(5000),
    TEN_THOUSAND(10000),
    TWENTY_THOUSAND(20000),
    CHRISTMAS_DATE(25),
    MONTH(12),
    FIRST_DATE(1),
    LAST_DATE(31),
    SPECIAL_DATE(3),
    THIS_YEAR(2023),
    CHRISTMAS_DISCOUNT_UNIT(100),
    APPLY_EVENT_LIMIT(10000),
    GIFT_LIMIT(120000),
    GIFT_PRICE(25000);

    private final int value;

    Number(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
