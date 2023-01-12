package Unit;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class ValidateUtils {
    public static final String NAME_PATTERN = "^[A-Z]([A-Z]*[a-z0-9]*[ ]?)+$";

    public static boolean validateProductName(String nameProduct) {
        return Pattern.compile(NAME_PATTERN).matcher(nameProduct).matches();
    }

    public static String removeAccent(String s) {
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("");
    }

    public static boolean isPasswordValid(String password) {
        return Pattern.compile(PASSWORD_PATTERN).matcher(password).matches();
    }

    public static boolean isUsernameValid(String password) {
        return Pattern.compile(USERNAME_PATTERN).matcher(password).matches();
    }

    public static final String USERNAME_PATTERN = "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$";
    public static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[-_!@#&()[{}]:;',?/*~$^+=<>\\.]).{8,20}$";

    public static boolean isNameValid(String name) {
        return Pattern.compile(NAME_REGEX).matcher(name).matches();
    }

    public static final String NAME_REGEX = "^([A-Z0-9ÀẢÃÁẠĂẰẲẴẮẶÂẦẨẪẤẬĐÈẺẼÉẸÊỀỂỄẾỆÌỈĨÍỊÒỎÕÓỌÔỒỔỖỐỘƠỜỞỠỚỢÙỦŨÚỤƯỪỬỮỨỰỲỶỸÝỴ][a-z0-9àảãáạăằẳẵắặâầẩẫấậđèẻẽéẹêềểễếệiìỉĩíịòỏõóọôồổỗốộơờởỡớợùủũúụỤưừửữứựỳỷỹýỵ]{0,6} ?)*$";

    public static final String PHONE_REGEX = "^[0][1-9][0-9]{8,9}$";

    public static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static boolean isPhoneValid(String number) {
        return Pattern.compile(PHONE_REGEX).matcher(number).matches();
    }

    public static boolean isEmailValid(String email) {
        return Pattern.compile(EMAIL_REGEX).matcher(email).matches();
    }
}
