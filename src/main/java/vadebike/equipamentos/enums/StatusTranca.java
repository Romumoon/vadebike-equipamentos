package vadebike.equipamentos.enums;

public enum StatusTranca {
    LIVRE("LIVRE"),
    OCUPADA("OCUPADA"),
    NOVA("NOVA"),
    APOSENTADA("APOSENTADA"),
    EM_REPARO("EM_REPARO");

    private String status;

    StatusTranca(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
