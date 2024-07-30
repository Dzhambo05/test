public enum Form {
    CART("Карта"),
    CASH("Наличные"),
    TRANSFER("Перевод");


    private String value;

    Form(String value) {
        this.value = value;
    }

}
