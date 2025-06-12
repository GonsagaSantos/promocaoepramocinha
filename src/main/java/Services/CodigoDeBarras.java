package Services;

public class CodigoDeBarras {

    private String codigo;

    public CodigoDeBarras(String codigoDocumento) {
        String codigoLimpo = codigoDocumento.replaceAll("[^0-9]", "");
        validarCodigoDeBarras(codigoLimpo);
        this.codigo = codigoLimpo;
    }

    public CodigoDeBarras() {}

    private void validarCodigoDeBarras(String codigo) {
        if (codigo == null || codigo.length() != 13) {
            throw new IllegalArgumentException("Erro: O código de barras deve ter 13 dígitos!");
        }
        int[] digitos = new int[13];
        for (int i = 0; i < 13; i++) {
            digitos[i] = Character.getNumericValue(codigo.charAt(i));
        }
        int numVerificadorEsperado = digitos[12];
        int numVerificadorCalculado = calcularDigitoVerificador(digitos);
        if (numVerificadorCalculado != numVerificadorEsperado) {
            throw new IllegalArgumentException("Erro: Código de barras inválido (dígito verificador)!");
        }
    }

    private int calcularDigitoVerificador(int[] digitos) {
        int soma = 0;
        for (int i = 0; i < 12; i++) {
            if ((i + 1) % 2 == 0) {
                soma += digitos[i] * 3;
            } else {
                soma += digitos[i] * 1;
            }
        }
        int resto = soma % 10;
        return (resto == 0) ? 0 : (10 - resto);
    }

    public String getCodigoFormatado() {
        if (this.codigo == null || this.codigo.length() != 13) {
            return this.codigo;
        }
        return this.codigo.substring(0, 1) + " " +
                this.codigo.substring(1, 7) + " " +
                this.codigo.substring(7, 12) + " " +
                this.codigo.substring(12, 13);
    }

    public String getCodigoApenasNumeros() {
        return this.codigo;
    }
}