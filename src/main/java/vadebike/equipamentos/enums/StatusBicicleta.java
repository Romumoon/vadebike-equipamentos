package vadebike.equipamentos.enums;

public enum StatusBicicleta {
    DISPONIVEL("DISPONÍVEL"),
    EM_USO("EM_USO"),
    NOVA("NOVA"),
    APOSENTADA("APOSENTADA"),
    REPARO_SOLICITADO("REPARO_SOLICITADO"),
    EM_REPARO("EM_REPARO");

    private String status;

    StatusBicicleta(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}

