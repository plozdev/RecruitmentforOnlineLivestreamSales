# KOL Recruitment Management System

A console-based application developed in Java for managing the recruitment information of Key Opinion Leaders (KOLs) for an online livestream sales campaign[cite: 3, 13]. [cite\_start]This project is an academic assignment (J1.L.P0031) that emphasizes the principles of Object-Oriented Programming (OOP)[cite: 1, 8, 20].

## About The Project

This program enables a marketing team to manage the entire lifecycle of KOL recruitment for a strategic campaign titled "Digital Influencers Drive Sales"[cite: 12, 13]. It handles new registrations, updates, data filtering, and statistical reporting, all through a user-friendly command-line interface. The system persists data by saving it to a binary file.

## Features

[cite\_start]The application provides the following core functionalities[cite: 23]:

  * **New Registration**: Add a new KOL to the system with input validation[cite: 24].
  * **Update Information**: Modify the details of an existing KOL[cite: 25]. [cite\_start]The system intelligently keeps old data if no new input is provided[cite: 61].
  * **Display List**: Show a formatted list of all registered KOLs[cite: 26].
  * **Delete Registration**: Remove a KOL's record from the system[cite: 27].
  * **Search by Name**: Find KOLs based on a full or partial name search[cite: 28].
  * **Filter by Category**: Display KOLs belonging to a specific category (e.g., Beauty, Fashion, Gaming)[cite: 29].
  * **Platform Statistics**: Generate and display statistics on the number of KOLs registered for each social media platform (e.g., TikTok, Shopee)[cite: 30, 31].
  * **Save to File**: Persist all registration data to a file named `kol_registrations.dat`[cite: 32].
  * **Exit**: Terminate the program, with a safety prompt to save any unsaved changes[cite: 33].

## Built With

  * **Java (JDK 8 or higher)**
  * **Object-Oriented Programming (OOP) Principles**

## Getting Started

To get a local copy up and running, follow these simple steps.

### Prerequisites

Make sure you have a recent version of the Java Development Kit (JDK) installed on your machine (e.g., JDK 11, 17, or 21).

### Installation & Running

1.  **Clone the repository** or download the source code.
2.  **Open the project** in your favorite Java IDE (e.g., IntelliJ IDEA, Eclipse, VS Code).
3.  **Place the data file**: Ensure the `KOLList.csv` file is located in the project's resources folder (`src/main/resources/` or a similar path configured in your IDE's classpath)[cite: 14]. This file is essential for providing the available KOL categories and platforms.
4.  **Run the application**: Locate the `Main.java` file in the `org` package and run it.
5.  The application will start in your console.

## Usage

Once the program is running, you will be presented with a menu of 9 options. Simply type the number corresponding to the action you wish to perform and press Enter. Follow the on-screen prompts to interact with the system.

Data will be loaded from `kol_registrations.dat` on startup if the file exists. All changes can be saved to this file using option 8.

## Project Structure

The project is organized into several packages to ensure a clean architecture and separation of concerns:

```
src
├── model/        # Data classes (POJOs) like KOL, Platform, StatObject.
├── manager/      # Business logic layer (KOLManager, PlatformManager).
├── ui/           # User interface layer (ConsoleUI).
├── tools/        # Utility classes for input, file operations, and validation.
└── org/          # Main entry point of the application.
```

## Author

*Hoang Mai Anh - plozdev*

## Acknowledgments

  * This project is an assignment for the LAB211 course at FPT University.
  * Project specifications and requirements provided in the assignment `J1.L.P0031`.
  * Resources link: https://drive.google.com/drive/folders/1V23m0MfEcHM2sqODM0o1-RbL_BsLdQV4?usp=sharing
