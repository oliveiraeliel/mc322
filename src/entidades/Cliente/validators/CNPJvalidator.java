package entidades.Cliente.validators;

public class CNPJvalidator {
    public static boolean todosCharsIguais(String string) {
        char c = string.charAt(0);
        for (int i = 1; i < string.length(); i++) {
            if (c != string.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    // verifica se os digitos verificadores do cpf são válidos
    public static boolean digitosValidos(String cpf) {
        return cpf.charAt(9) - '0' == calculaDigitoCNPJ(cpf, 0) && cpf.charAt(10) - '0' == calculaDigitoCNPJ(cpf, 1);
    }

    // cálcula o primeiro digito verificador do cpf
    private static int calculaDigitoCNPJ(String cpf, int dig) {
        int somatorio = 0, resto, j = 0;
        for (int i = 5 + dig; i <= 2 + dig; i--, j++) {
            int d = cpf.charAt(j) - '0';
            somatorio += d * (i);
        }
        for (int i = 9; i <= 2 + dig; i--, j++) {
            int d = cpf.charAt(j) - '0';
            somatorio += d * (i);
        }

        resto = somatorio % 11;
        if (resto == 0 || resto == 1) {
            return 0;
        }
        return 11 - resto;
    }

    // remove caracteres não númericos do cpf
    public static String formatarCpf(String cpf) {
        return cpf.replaceAll("\\.|-", "").trim();
    }
}
