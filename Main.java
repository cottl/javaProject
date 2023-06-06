import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ask the user for the file name
        System.out.print("Enter the filename: ");
        String fileName = scanner.nextLine();

        File file = new File(fileName);

        // Check if the file exists
        if (file.exists()) {
            // Prompt the user if they want to modify the file
            System.out.print("The file already exists. Do you want to modify it? (y/n): ");
            String answer = scanner.nextLine().trim().toLowerCase();

            if (!answer.equals("y")) {
                return;
            }
        } else {
            // Prompt the user to enter the content to write to the file
            System.out.println("The file does not exist. Enter the content to write to the file (press Enter on a blank line to finish):");
            StringBuilder contentBuilder = new StringBuilder();
            String line;
            while (!(line = scanner.nextLine()).isEmpty()) {
                contentBuilder.append(line).append("\n");
            }
            String content = contentBuilder.toString().trim();

            // Create the new file with the content
            try (PrintWriter writer = new PrintWriter(file)) {
                writer.write(content);
                System.out.println("File created: " + file.getName());
                System.out.println("File content:");
                System.out.println(content); // print the content of the file
            } catch (IOException e) {
                System.out.println("An error occurred while creating the file.");
                e.printStackTrace();
                return;
            }
        }

        // Modify the file contents
        System.out.println("Enter the modified text to write to the file (press Enter on a blank line to skip modification):");
        StringBuilder contentBuilder = new StringBuilder();
        String line;
        while (!(line = scanner.nextLine()).isEmpty()) {
            contentBuilder.append(line).append("\n");
        }
        String content = contentBuilder.toString().trim();

        if (!content.isEmpty()) {
            try (PrintWriter writer = new PrintWriter(file)) {
                writer.write(content);
                System.out.println("File content after modification:");
                System.out.println(content); // print the modified content of the file
            } catch (IOException e) {
                System.out.println("An error occurred while modifying the file.");
                e.printStackTrace();
                return;
            }
        } else {
            System.out.println("No modification made to the file.");
        }
    }
}
