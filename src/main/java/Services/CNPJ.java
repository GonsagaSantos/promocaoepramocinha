package Services;

public class CNPJ {
    private String cnpj;

    public CNPJ(String documento) {
        String cnpjLimpo = documento.replaceAll("[^0-9]", "");
        validarCnpj(cnpjLimpo);
        this.cnpj = cnpjLimpo;
    }

    private void validarCnpj(String cnpj) {
        if (cnpj == null || cnpj.length() != 14) {
            throw new IllegalArgumentException("Erro: CNPJ deve ter 14 dígitos!");
        }
        if (cnpj.matches("(\\d)\\1{13}")) {
            throw new IllegalArgumentException("Erro: CNPJ com todos os dígitos iguais é inválido!");
        }
        int[] digitos = new int[14];
        for (int i = 0; i < 14; i++) {
            digitos[i] = Character.getNumericValue(cnpj.charAt(i));
        }
        int digitoCalculado1 = calcularDigitoCnpj(digitos, 12);
        if (digitoCalculado1 != digitos[12]) {
            throw new IllegalArgumentException("Erro: CNPJ inválido (primeiro dígito verificador)!");
        }
        int digitoCalculado2 = calcularDigitoCnpj(digitos, 13);
        if (digitoCalculado2 != digitos[13]) {
            throw new IllegalArgumentException("Erro: CNPJ inválido (segundo dígito verificador)!");
        }
    }

    private int calcularDigitoCnpj(int[] digitosCnpj, int tamanho) {
        int soma = 0;
        int pesoAtual = 2;
        for (int i = tamanho - 1; i >= 0; i--) {
            soma += digitosCnpj[i] * pesoAtual;
            pesoAtual++;
            if (pesoAtual > 9) { pesoAtual = 2; }
        }
        int resto = soma % 11;
        return (resto < 2) ? 0 : (11 - resto);
    }

    public String getrCnpjFormatado() {
        if (this.cnpj == null || this.cnpj.length() != 14) {
            return this.cnpj;
        }
        return this.cnpj.substring(0, 2) + "." +
                this.cnpj.substring(2, 5) + "." +
                this.cnpj.substring(5, 8) + "/" +
                this.cnpj.substring(8, 12) + "-" +
                this.cnpj.substring(12, 14);
    }

    public String getCnpjApenasNumeros() {
        return this.cnpj;
    }
}