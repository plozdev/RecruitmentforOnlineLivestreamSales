package ui;

import manager.KOLManager;
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
    private final KOLManager controller;
    private boolean isRunning = true;
    private final Input input;
    public ConsoleUI() {
        input = new Input();
        controller = new KOLManager();
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
                    if (controller.add())
                        System.out.println("Registration successful!");
                    else
                        System.out.println("Registration failed! Returning to the menu...");

                    break;
                case 2:
                    //TODO: Update
                    if (controller.isEmptyKOL()) {
                        System.out.println("No KOLs have registered yet. Please try later...");
                        break;
                    }
                    if (controller.update())
                        System.out.println("Update registration successful!");
                    else
                        System.out.println("Update registration failed! Returning to the menu...");

                    break;
                case 3:
                    //TODO: Display
                    controller.listAllKOLs();
                    break;
                case 4:
                    //TODO: Delete
                    if (controller.delete())
                        System.out.println("Delete registration successful!");
                    else
                        System.out.println("Delete registration failed! Returning to the menu...");
                    break;
                case 5:
                    //TODO: Search
                    controller.searchingKOL();
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
