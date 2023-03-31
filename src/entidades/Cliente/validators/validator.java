package entidades.Cliente.validators;

public class validator {
    public static boolean todosCharsIguais(String string) {
        char c = string.charAt(0);
        for (int i = 1; i < string.length(); i++) {
            if (c != string.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}
