package tools;

import model.KOL;

import java.util.Scanner;

/**
 * Utility class for handling user input and validation.
 */
public class Input implements ValidationsUtils{
    private final Scanner scanner;

    /**
     * Initializes the input scanner.
     */
    public Input() {
        scanner = new Scanner(System.in);
    }

    /**
     * Closes the scanner resource.
     */
    public void close() { scanner.close(); }

    /**
     * Gets a validated string input from the user.
     * @param msg Prompt message
     * @param pattern Regex pattern for validation
     * @param errorMsg Error message for invalid input
     * @param allowEmpty Allow empty input or not
     * @return Validated string input
     */
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

    /**
     * Gets a validated long value from the user.
     * @param msg Prompt message
     * @param errorMsg Error message for invalid input
     * @param allowEmpty Allow empty input or not
     * @return Validated long value, or -1 if empty and allowed
     */
    public long getLong(String msg, String errorMsg, boolean allowEmpty) {
        while (true) {
            System.out.print(msg);
            String input = scanner.nextLine();
            if (input.isEmpty()) {
                if (allowEmpty)
                    return -1L;
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

    /**
     * Prompts user to input KOL information.
     * @param isUpdate If true, allows empty input to keep old data
     * @return KOL object with user input
     */
    public KOL getKol(boolean isUpdate) {
        if (isUpdate) {
            System.out.println("(leave blank to keep old data)");
        }

        String name = getString("Enter name: ",ValidationsUtils.NAME,"Invalid format!",isUpdate);
        String phone = getString("Enter phone number: ",ValidationsUtils.PHONE,"Invalid format!",isUpdate);
        String email = getString("Enter email: ",ValidationsUtils.EMAIL,"Invalid format!",isUpdate);

        String platform = "";
        boolean isExitsPlatformCode = false;

        while (!isExitsPlatformCode) {
            platform = getString("Enter platform code (YT01,TK01,FB01,IG01): ",null,"Invalid format!",isUpdate);
            if (platform.equalsIgnoreCase("YT01"))
                platform = "YouTube";
            else if (platform.equalsIgnoreCase("TK01"))
                platform = "TikTok";
            else if (platform.equalsIgnoreCase("FB01"))
                platform = "Facebook";
            else if (platform.equalsIgnoreCase("IG01"))
                platform = "Instagram";

            if (!platform.isEmpty() || platform.isEmpty() && isUpdate)
                break; //Break point

            System.out.println("The code isn't exists.");

        }

        long followersCnt = getLong("Enter followers: ","Invalid number!",isUpdate);

        return new KOL(name,phone,email,platform,followersCnt);
    }

    /**
     * Prompts user for a yes/no answer.
     * @return true if user enters 'y', false if 'n'
     */
    public boolean getYesNo() {
        String input ;

        while (true) {
            input = scanner.nextLine();
            if (!input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("n")) {
                System.out.println("Please enter 'y' or 'n'.");
                continue;
            }
            break;
        }
        return (input.equalsIgnoreCase("y"));
    }

    /////////////////////////
    ///
    ///
    ///////////////////////
    /**
     * Validates a string against a regex pattern.
     * @param s Input string
     * @param regex Regex pattern
     * @return true if valid, false otherwise
     */
    @Override
    public boolean isValid(String s, String regex) {
        if (s==null || regex==null) {
            System.out.println("Invalid input");
            return false;
        }
        return s.toLowerCase().matches(regex);
    }
}
