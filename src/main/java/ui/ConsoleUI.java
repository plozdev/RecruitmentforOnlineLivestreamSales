package ui;

import manager.KOLDisplay;
import manager.KOLManager;
import manager.PlatformDisplay;
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
    private final KOLDisplay display;
    private final PlatformDisplay platformDisplay;
    private boolean isRunning = true;
    private final Input input;
    public ConsoleUI() {
        input = new Input();
        controller = new KOLManager();
        display = new KOLDisplay();
        platformDisplay = new PlatformDisplay();
    }

    /**********************
     * Starting point
     */
    public void start() {
        boolean kolExists = !controller.isEmpty();
        while (isRunning) {
            System.out.println(menuOptions);

            //TODO: FATAL
            short choice = (short) input.getLong("Enter choice (1-9): ","Invalid format!",false);
            if (choice != 1 && !kolExists && choice != 9) {
                System.out.println("No KOLs have registered yet. Please try later...");
                continue;
            }
            switch (choice) {
                case 1:
                    //TODO: Register
                    if (controller.add()) {
                        System.out.println("Registration successful!");
                        kolExists = true;
                    }
                    else
                        System.out.println("Registration failed! Returning to the menu...");

                    break;
                case 2:
                    //TODO: Update
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
                    display.displayKOLs(controller.searchingKOL(),"No one match the search criteria!");
                    break;
                case 6:
                    //TODO: Filter data
                    display.displayKOLs(controller.filterByCategories() ,"No KOLs have registered under this category!");
                    break;
                case 7:
                    //TODO: Statistics
                    platformDisplay.stat(controller);
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
