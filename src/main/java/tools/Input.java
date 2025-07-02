package tools;

import model.kol;

import java.util.Scanner;
import java.util.function.Function;

public class Input implements ValidationsUtils{
    private final Scanner scanner;
    public Input() {
        scanner = new Scanner(System.in);
    }
    public void close() { scanner.close(); }

    /*
    public String getString(String msg, String pattern, String errorMsg, boolean allowEmpty) {
        System.out.print(msg);
        String input = scanner.nextLine();

        if (input.isEmpty()) {
            if (allowEmpty)
                return "";
            else {
                System.out.println(errorMsg);
            }
        }

        return validateWithCallback(input, data->isValid(data,pattern));
    }

    public int getInt(String msg, String pattern, String errorMsg, boolean allowEmpty) {
        System.out.print(msg);
        String input = scanner.nextLine();
        if (input.isEmpty()) {
            if (allowEmpty)
                return 0;
            else {
                System.out.println(errorMsg);
            }
        }
        try {
            return Integer.parseInt(validateWithCallback(input, data->isValid(data,pattern)));
        } catch (NumberFormatException e) {
            System.err.println(errorMsg);
            return -1;
        }
    }
    */
    public kol getKol(boolean isUpdate) {
        System.out.print("Enter KOL information");
        if (isUpdate) {
            System.out.println(" (leave blank to keep old data)");
        }

        String id = readValidInput("Enter KOL Id: ",
                s->isValid(s,ValidationsUtils.ID),
                Function.identity(),
                isUpdate); //No need to convert

        String name = readValidInput("Enter KOL Name: ",
                s->isValid(s,ValidationsUtils.NAME),
                Function.identity(),
                isUpdate);

        String phone  = readValidInput("Enter KOL Id: ",
                s->isValid(s,ValidationsUtils.PHONE),
                Function.identity(),
                isUpdate);

        String email = readValidInput("Enter KOL Email: ",
                s->isValid(s,ValidationsUtils.EMAIL),
                Function.identity(),
                isUpdate);

        String code = ""; //TODO: Code platform Logic

        long followersCnt = readValidInput("Enter KOL Name: ",
                n-> n > 0,
                Long::parseUnsignedLong,
                isUpdate);

        return new kol(id,name,phone,email,code,followersCnt);
    }

    ///////////////////////
    /// Call back interface
    /// Validator data
    /// //////////////////
    public interface Validator <T> {
        boolean validate(T t);
    }
    public <T> T readValidInput(String prompt, Validator<T> validator, Function<String, T> converter, boolean allowEmpty) {
        int attempts = 0;
        final int MAX_ATTEMPTS = 4;

        while (attempts < MAX_ATTEMPTS) {
            System.out.print(prompt);
            String raw = scanner.nextLine();

            /*
            if (raw.equalsIgnoreCase("q")) {
                System.out.println("Returning to the menu...");
                return null;
            }

             */

            if (raw.isEmpty() && allowEmpty) {
                return null;
            }

            try {
                T converted = converter.apply(raw);
                if (validator.validate(converted)) {
                    return converted;
                }
            } catch (Exception e) {
                // TODO: Catch parsing errors
            }

            System.out.println("❌ Invalid format. Please try again!");
            attempts++;
        }

        System.out.println("⚠️ You enter too many times.");
        return null;
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
