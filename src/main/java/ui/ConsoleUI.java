package ui;

import tools.Input;
import tools.ValidationsUtils;

public class ConsoleUI {
    private final String menuOptions ="1. New Registration\n" +
            "2. Update Registration Information\n" +
            "3. Display Registered List\n" +
            "3. Display Registered List\n" +
            "4. Delete Registration Information\n" +
            "5. Search KOLs by Name\n" +
            "6. Filter Data by Category\n" +
            "7. Statistics of Registration Numbers by Platform\n" +
            "8. Save Data to File\n" +
            "9. Exit the Program\n" +
            "0. Exit";
    private boolean isRunning = true;
    private final Input input;
    public ConsoleUI() {
        input = new Input();
    }

    /**********************
     * Starting point
     */
    public void start() {
        while (isRunning) {
            System.out.println(menuOptions);
            int choice = input.getInt("Please enter a number (0-9): ", ValidationsUtils.POSITIVE_INT,"Invalid number!",false);
            switch (choice) {
                case 0:
                    isRunning = false;
                    break;
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 6:

                    break;
                case 7:

                    break;
                case 8:

                    break;
                case 9:

                    break;
                default:
                    System.out.println("Please enter a number from 0 to 9!");
                    break;
            }
        }
        System.out.println("Goodbye...");
        input.close();
    }
}
