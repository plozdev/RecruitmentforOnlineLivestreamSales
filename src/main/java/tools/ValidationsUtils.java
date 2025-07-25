package tools;

public interface ValidationsUtils {
    String ID = "^(bt|fs|bc|gm|tl)\\d{6}$";
    String CATEGORY = "(bt|fs|bc|gm|tl)";
    String NAME = "^[\\p{L} ]{5,30}$";
    String PHONE = "^0[98753]\\d{8}$";
    String EMAIL = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
    String POSITIVE_INT = "\\d+";

    boolean isValid(String s, String regex);
}
