package entidades.Cliente.validators;

// Classe com métodos de verificacão do cpf
public class CPFvalidator {
    // verifica se todos os caracteres de uma string são iguais
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
        return cpf.charAt(9) - '0' == calculaDigitoCPF(cpf, 0) && cpf.charAt(10) - '0' == calculaDigitoCPF(cpf, 1);
    }

    // cálcula o primeiro digito verificador do cpf
    private static int calculaDigitoCPF(String cpf, int dig) {
        int digito = 0, resto;
        for (int i = 0; i < 9 + dig; i++) {
            int d = cpf.charAt(i) - '0';
            digito += d * (10 + dig - i);
        }
        resto = digito % 11;
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
