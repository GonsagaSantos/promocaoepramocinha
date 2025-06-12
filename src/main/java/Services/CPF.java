package Services;

public class CPF {
    private String cpf;

    public CPF(String document) {
        validarCpf(document);
        concatCpf(document);
        this.cpf = document;
    }

    public void validarCpf(String cpf) {
        String regex = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$";
        if (!cpf.matches(regex)) {
            throw new IllegalArgumentException("Erro: CPF invalido!");
        }
    }

    public void concatCpf(String input) {
        if (input.contains(".") && input.contains("-")) {
            this.cpf = input.replaceAll("[^0-9]", "");
        }
    }

    public String getCpfConcatenado() {
        concatCpf(this.cpf);
        String parteUm = this.cpf.substring(0,3);
        String parteDois = this.cpf.substring(3,6);
        String parteTres = this.cpf.substring(6,9);
        String parteQuatro = this.cpf.substring(9);

        return parteUm + "." + parteDois + "." + parteTres + "-" + parteQuatro;
    }

    public String getCpfSomenteNumeros() {
        return this.cpf;
    }
}
