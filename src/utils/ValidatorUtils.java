package utils;

public class ValidatorUtils {
    public static boolean todosCharsIguais(String string) {
        char c = string.charAt(0);
        for (int i = 1; i < string.length(); i++) {
            if (c != string.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static boolean digitosCpfValidos(String cpf) {
        return cpf.charAt(9) - '0' == calculaDigitoCPF(cpf, 0) && cpf.charAt(10) - '0' == calculaDigitoCPF(cpf, 1);
    }

    public static boolean digitosCnpjValidos(String cnpj) {
        return cnpj.charAt(9) - '0' == calculaDigitoCNPJ(cnpj, 0)
                && cnpj.charAt(10) - '0' == calculaDigitoCNPJ(cnpj, 1);
    }

    public static String formatarCPF(String cpf) {
        return cpf.replaceAll("\\.|-", "").trim();
    }

    public static String formatarCNPJ(String cnpj) {
        return cnpj.replaceAll("\\.|-|/", "").trim();
    }

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

    private static int calculaDigitoCPF(String cpf, int dig) {
        int digito = 0, resto;
        for (int i = 0; i < 9 + dig; i++) {
            int d = cpf.charAt(i) - '0';
            digito += d * (10 + dig - i);
        }
        resto = digito % 11;
        return 11 - resto;
    }
}
