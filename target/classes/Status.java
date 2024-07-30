public enum Status {

    INPROCESS("В процессе"),
    READY("Готов"),
    SENT("Отправлено"),
    DELIVERED("Доставлено");


    private String value;


    Status(String value) {
        this.value = value;
    }
}
