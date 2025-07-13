package ui;

import manager.KOLDisplay;
import manager.KOLManager;
import manager.Platform;
import manager.PlatformDisplay;
import tools.Input;

/**
 * UI console for the program
 */
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
    private final KOLManager kolManager;
    private final KOLDisplay kolDisplay;
    private final Platform platformManager;
    private final PlatformDisplay platformDisplay;
    private boolean isRunning = true;
    private final Input input;
    public ConsoleUI() {
        input = new Input();
        kolManager = new KOLManager();
        kolDisplay = new KOLDisplay();
        platformManager = new Platform();
        platformDisplay = new PlatformDisplay();
    }

    /**********************
     * Starting point
     */
    public void start() {
        boolean kolExists = !kolManager.isEmpty();
        boolean isSaved = true;
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
                    if (kolManager.add()) {
                        System.out.println("Registration successful!");
                        kolExists = true;
                        isSaved = false;
                    }
                    else
                        System.out.println("Registration failed! Returning to the menu...");

                    break;
                case 2:
                    //TODO: Update
                    if (kolManager.update()) {
                        System.out.println("Update registration successful!");
                        isSaved = false;
                    }
                    else
                        System.out.println("Update registration failed! Returning to the menu...");

                    break;
                case 3:
                    //TODO: Display
                    kolManager.listAll();
                    break;
                case 4:
                    //TODO: Delete
                    if (kolManager.delete()) {
                        System.out.println("Delete registration successful!");
                        isSaved = false;
                    }
                    else
                        System.out.println("Delete registration failed! Returning to the menu...");
                    break;
                case 5:
                    //TODO: Search
                    kolDisplay.displayKOLs(kolManager.searchingKOL(),"No one match the search criteria!");
                    break;
                case 6:
                    //TODO: Filter data
                    kolDisplay.displayKOLs(kolManager.filterByCategories() ,"No KOLs have registered under this category!");
                    break;
                case 7:
                    //TODO: Statistics
                    platformDisplay.stat(kolManager, platformManager);
                    break;
                case 8:
                    //TODO: Save
                    kolManager.saveData();
                    isSaved = true;
                    break;
                case 9:
                    if (!isSaved) {
                        //TODO: Warning to user
                        System.out.println("Do you want to save the change before exiting? (Y/N)");
                        if (input.getYesNo()) {
                            kolManager.saveData();
                            isSaved = true;
                        }
                    }
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
