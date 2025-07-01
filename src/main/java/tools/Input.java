package tools;

import java.util.Scanner;

public class Input implements ValidationsUtils {
    private Scanner scanner;
    public Input() {
        scanner = new Scanner(System.in);
    }

    public String getString(String msg, String pattern, String errorMsg, boolean allowEmpty) {
        System.out.print(msg);
        String input = scanner.nextLine();

        if (input.isEmpty()) {
            if (allowEmpty)
                return "";
            else {
                System.out.println(errorMsg);
                return "N/A";
            }
        }

        if (isValid(input, pattern)) return input;
        return "N/A";
    }

    public int getInt(String msg, String pattern, String errorMsg, boolean allowEmpty) {
        System.out.print(msg);
        String input = scanner.nextLine();
        if (input.isEmpty()) {
            if (allowEmpty)
                return 0;
            else {
                System.out.println(errorMsg);
                return -1;
            }
        }
        if (isValid(input, pattern)) return Integer.parseInt(input);
        return -1;
    }


    @Override
    public boolean isValid(String s, String regex) {
        if (s==null || regex==null) {
            System.out.println("Invalid input");
            return false;
        }
        return s.matches(regex);
    }
}
