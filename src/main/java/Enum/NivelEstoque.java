package Enum;

public enum NivelEstoque {
    ESTOQUE_ALTO("Estoque Alto"),
    DISPONIVEL("Disponivel"),
    QUANTIDADE_MINIMA("Quantidade Mínima"),
    ESTOQUE_BAIXO("Estoque Baixo"),
    INDISPONIVEL("Indisponível");

    private final String dbValue;

    NivelEstoque(String dbValue) {
        this.dbValue = dbValue;
    }

    public String getDbValue() {
        return dbValue;
    }

    public static NivelEstoque fromDbValue(String dbValue) {
        for (NivelEstoque nivel : NivelEstoque.values()) {
            if (nivel.getDbValue().equalsIgnoreCase(dbValue)) {
                return nivel;
            }
        }
        throw new IllegalArgumentException("Valor de Nível de Estoque inválido do banco de dados: " + dbValue);
    }
}