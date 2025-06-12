package Services;

import java.util.Random;

public class GerarCodigoBarras {
    private GerarCodigoBarras() {}

    public static String gerarNovo() {
        Random random = new Random();
        StringBuilder codigo = new StringBuilder();
        for (int i = 0; i < 12; i++) {
            codigo.append(random.nextInt(10));
        }
        int digitoVerificador = calcularDigitoVerificador(codigo.toString());
        return codigo.toString() + digitoVerificador;
    }

    private static int calcularDigitoVerificador(String codigo12Digitos) {
        int soma = 0;
        for (int i = 0; i < 12; i++) {
            int digito = Character.getNumericValue(codigo12Digitos.charAt(i));
            if ((i + 1) % 2 == 0) { soma += digito * 3; }
            else { soma += digito * 1; }
        }
        int resto = soma % 10;
        return (resto == 0) ? 0 : (10 - resto);
    }
}
