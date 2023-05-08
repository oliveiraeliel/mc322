package utils;

public final class Validacao {
    /**
     * Verifica se o cpf dado é válido.
     * 
     * @param cpf CPF
     * @return boolean
     */
    public static boolean validaCPF(String cpf) {
        cpf = ValidatorUtils.formatarCPF(cpf);
        return cpf.length() == 11 && !ValidatorUtils.todosCharsIguais(cpf) && ValidatorUtils.digitosCpfValidos(cpf);
    }

    /**
     * Verifica se o cnpj dado é válido.
     * 
     * @param cnpj CNPJ
     * @return boolean
     */
    public static boolean validaCNPJ(String cnpj) {
        cnpj = ValidatorUtils.formatarCNPJ(cnpj);
        return cnpj.length() == 14 && !ValidatorUtils.todosCharsIguais(cnpj) && ValidatorUtils.digitosCnpjValidos(cnpj);
    }

    public static boolean validaNome(String nome) {
        return nome.matches("[a-zA-Z\\s]+$");
    }

}
