package models;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

public class UserSecurity {
    public static String sha256(String text) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
        byte[] shaByteArr = mDigest.digest(text.getBytes(StandardCharsets.UTF_8));
        StringBuilder hexStrBuilder = new StringBuilder();
        for (byte b : shaByteArr) {
            hexStrBuilder.append(String.format("%02x", b));
        }
        return hexStrBuilder.toString();
    }
    public static String checkName(String name){
        if (name.isEmpty())
            return "Nome não pode ser vazio.";
        else if(name.length() > 128)
            return "Nome excede o número máximo de caracteres.";
        return "valid name";
    }
    public static String checkSurname(String surname){
        if (surname.isEmpty())
            return "Sobrenome não pode ser vazio.";
        else if(surname.length() > 128)
            return "Sobrenome excede o número máximo de caracteres.";
        return "valid surname";
    }
    public static String checkPassword(String password){
        if (password.length() < 8 || password.length() > 24) {
            return "Senha deve ter entre 8 e 12 caracteres.";
        }
        String passwordRegex = "[^a-zA-Z0-9]";
        Pattern pattern = Pattern.compile(passwordRegex);
        if (!pattern.matcher(password).find()) {
            return "Senha deve contar no mínimo um caractere especial";
        }
        return "valid password";
    }
    public static String checkCPF(String cpf){
        if(cpf.length() != 11){
            return "Caixa de cpf vazia ou incompleta.";
        }
        cpf = cpf.replace( ".", "");
        cpf = cpf.replace("-","");
        int weight = 1;
        int sum = 0;
        for(int index = 0; index < 9; index++){
            sum += (Integer.parseInt(String.valueOf(cpf.charAt(index))) * weight++);
        }
        int firstVerifierDigit = sum % 11;
        if(firstVerifierDigit == 10) firstVerifierDigit = 0;
        if(Integer.parseInt(String.valueOf(cpf.charAt(9))) != firstVerifierDigit){
            return "Cpf inválido";
        }
        weight = 0;
        sum = 0;
        for(int index = 0; index < 10; index++){
            sum += (Integer.parseInt(String.valueOf(cpf.charAt(index))) * weight++);
        }
        int secondVerifierDigit = sum % 11;
        if(secondVerifierDigit == 10) secondVerifierDigit = 0;
        if(Integer.parseInt(String.valueOf(cpf.charAt(10))) != secondVerifierDigit){
            return "Cpf inválido";
        }
        return "valid cpf";
    }
    public static String checkEmail(String email){
        String emailRegex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        if(email == null || email.isEmpty()){
            return "Caixa de email vazia.";
        }else if(email.length() > 256){
            return "Email excede o número máximo de caracteres.";
        }
        Pattern pattern = Pattern.compile(emailRegex);
        if(!pattern.matcher(email).matches()){
            return "Padrão de email inválido";
        }
        return "valid email";
    }

}
