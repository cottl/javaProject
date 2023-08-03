import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName;

        while (true) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Create a new file");
            System.out.println("2. Open an existing file and edit");
            System.out.println("3. Exit");

            int choice;
            try {
                choice = Integer.parseInt(reader.readLine());
            } catch (IOException | NumberFormatException e) {
                System.out.println("Invalid input. Please try again.");
                continue;
            }

            switch (choice) {
                case 1:
                    // Create a new file
                    System.out.print("Enter the file name: ");
                    try {
                        fileName = readLine(reader);
                    } catch (IOException e) {
                        System.out.println("An error occurred while reading user input.");
                        e.printStackTrace();
                        continue;
                    }

                    File file = new File(fileName);

                    try {
                        if (file.createNewFile()) {
                            System.out.println("File created: " + file.getName());
                            System.out.print("Do you want to modify the file? (y/n): ");
                            String input = readLine(reader);
                            if (input.equalsIgnoreCase("y")) {
                                modifyFile(file, reader);
                            }
                        } else {
                            System.out.println("File already exists.");
                        }
                    } catch (IOException e) {
                        System.out.println("An error occurred while creating the file.");
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    // Open an existing file and edit
                    System.out.print("Enter the file name: ");
                    try {
                        fileName = readLine(reader);
                    } catch (IOException e) {
                        System.out.println("An error occurred while reading user input.");
                        e.printStackTrace();
                        continue;
                    }

                    File editFile = new File(fileName);

                    modifyFile(editFile, reader);
                    break;
                case 3:
                    // Exit the program
                    System.out.println("Exiting...");
                    try {
                        reader.close();
                    } catch (IOException e) {
                        System.out.println("An error occurred while closing the reader.");
                        e.printStackTrace();
                    }
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void modifyFile(File file, BufferedReader reader) {
        try {
            List<String> lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);

            System.out.println("Start editing. Press 'q' to quit without saving or 's' to save the changes.");
            for (String line : lines) {
                System.out.println(line);
            }

            String userInput;
            while (true) {
                userInput = readLine(reader);

                if (userInput.equalsIgnoreCase("q")) {
                    System.out.println("Editing canceled. Changes not saved.");
                    return;
                } else if (userInput.equalsIgnoreCase("s")) {
                    try {
                        Files.write(file.toPath(), lines, StandardCharsets.UTF_8);
                        System.out.println("File saved successfully.");
                    } catch (IOException e) {
                        System.out.println("An error occurred while saving the file.");
                        e.printStackTrace();
                    }
                    return;
                } else {
                    lines.add(userInput);
                    System.out.println("Text modified. Press 'q' to quit without saving or 's' to save the changes.");
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while opening or reading the file.");
            e.printStackTrace();
        }
    }

    private static String readLine(BufferedReader reader) throws IOException {
        String line = reader.readLine();
        if (line != null) {
            return line.trim();
        }
        return "";
    }
}
