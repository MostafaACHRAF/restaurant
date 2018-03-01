package AppConfiguration;

public interface WaiterConfig {
    String SAME_ORDER = "Same";
    String ORDER_FOR_KEY_WORD = "for";
    int ORDER_FOR_PARTS_COUNT = 3;
    int ORDER_FOR_CONTENT_INDEX = 0;
    int ORDER_FOR_KEY_WORD_INDEX = 1;
    int ORDER_FOR_EXPECTED_CUSTOMERS_INDEX = 2;
    String CUSTOMER_ORDER_SEPARATOR = ": ";
    String ORDER_FOR_SEPARATOR = " ";
    int ORDER_CONTENT_INDEX = 1;
    int CUSTOMER_NAME_INDEX = 0;
    String emptyCustomerName = "";
    String emptyOrderContent = "";
}
