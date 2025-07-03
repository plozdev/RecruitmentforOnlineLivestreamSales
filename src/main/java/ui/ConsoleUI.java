package ui;

import model.kol;
import tools.Input;

public class ConsoleUI {
    private final String menuOptions ="1. New Registration\n" +
            "2. Update Registration Information\n" +
            "3. Display Registered List\n" +
            "4. Delete Registration Information\n" +
            "5. Search KOLs by Name\n" +
            "6. Filter Data by Category\n" +
            "7. Statistics of Registration Numbers by Platform\n" +
            "8. Save Data to File\n" +
            "9. Exit the Program\n";
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

            //TODO: FATAL
            short choice = (short) input.getLong("Enter choice (1-9): ","Invalid format!",false);

            switch (choice) {
                case 1:
                    //TODO: Register
                    kol newRegistration = input.getKol(false);
                    System.out.println(newRegistration);
                    break;
                case 2:
                    //TODO: Update
                    break;
                case 3:
                    //TODO: Display
                    break;
                case 4:
                    //TODO: Delete
                    break;
                case 5:
                    //TODO: Search
                    break;
                case 6:
                    //TODO: Filter data
                    break;
                case 7:
                    //TODO: Statistics
                    break;
                case 8:
                    //TODO: Update
                    break;
                case 9:
                    isRunning = false;
                    break;
                default:
                    System.out.println("Please enter a number from 1 to 9!");
            }
        }
        System.out.println("Goodbye...");
        input.close();
    }
}
