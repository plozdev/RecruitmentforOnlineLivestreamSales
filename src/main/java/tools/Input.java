package tools;

import model.KOL;

import java.util.Scanner;

public class Input implements ValidationsUtils{
    private final Scanner scanner;
    public Input() {
        scanner = new Scanner(System.in);
    }
    public void close() { scanner.close(); }

    public String getString(String msg, String pattern, String errorMsg, boolean allowEmpty) {
        while (true) {
            System.out.print(msg);
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                if (allowEmpty)
                    return "";
                else {
                    System.out.println(errorMsg);
                    continue;
                }
            }

            if (pattern != null) {
                if (!isValid(input, pattern)) {
                    System.out.println(errorMsg);
                    continue;
                }
            }

            return input;
        }
    }

    public long getLong(String msg, String errorMsg, boolean allowEmpty) {
        while (true) {
            System.out.print(msg);
            String input = scanner.nextLine();
            if (input.isEmpty()) {
                if (allowEmpty)
                    return 0L;
                else {
                    System.out.println(errorMsg);
                    continue;
                }
            }
            if (!isValid(input,ValidationsUtils.POSITIVE_INT)) {
                System.out.println(errorMsg);
                continue;
            }
            try {
                return Long.parseUnsignedLong(input);
            } catch (NumberFormatException e) {
                System.err.println("Parsing error.\n" + e.getMessage() + ". Try again!");
            }
        }
    }

    public KOL getKol(boolean isUpdate) {
        System.out.println("Enter KOL information");
        if (isUpdate) {
            System.out.println(" (leave blank to keep old data)");
        }

        String name = getString("Enter name: ",ValidationsUtils.NAME,"Invalid format!",false);
        String phone = getString("Enter phone number: ",ValidationsUtils.PHONE,"Invalid format!",false);
        String email = getString("Enter email: ",ValidationsUtils.EMAIL,"Invalid format!",false);
        String code = getString("Enter platform code: ",null,null,false);
        long followersCnt = getLong("Enter followers: ","Invalid number!",false);

        return new KOL(name,phone,email,code,followersCnt);
    }

    /////////////////////////
    ///
    ///
    ///////////////////////
    @Override
    public boolean isValid(String s, String regex) {
        if (s==null || regex==null) {
            System.out.println("Invalid input");
            return false;
        }
        return s.matches(regex);
    }
}
