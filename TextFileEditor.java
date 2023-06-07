import java.io.*;

public class TextFileEditor {
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
        StringBuilder editedContent = new StringBuilder();
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                editedContent.append(line).append("\n");
            }

            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("An error occurred while opening the file.");
            e.printStackTrace();
            return;
        }

        System.out.println("Start editing. Press 'q' to quit without saving or 's' to save the changes.");
        System.out.println(editedContent.toString());

        String userInput;
        while (true) {
            try {
                userInput = readLine(reader);
            } catch (IOException e) {
                System.out.println("An error occurred while reading user input.");
                e.printStackTrace();
                break;
            }

            if (userInput.equalsIgnoreCase("q")) {
                System.out.println("Editing canceled. Changes not saved.");
                break;
            } else if (userInput.equalsIgnoreCase("s")) {
                try {
                    writeFile(file.getPath(), editedContent.toString());
                    System.out.println("File saved successfully.");
                } catch (IOException e) {
                    System.out.println("An error occurred while saving the file.");
                    e.printStackTrace();
                }
                break;
            } else {
                editedContent = new StringBuilder(userInput).append("\n");
                System.out.println("Text modified. Press 'q' to quit without saving or 's' to save the changes.");
            }
        }
    }

    private static String readLine(BufferedReader reader) throws IOException {
        String line = reader.readLine();
        if (line != null) {
            return line.trim();
        }
        return "";
    }

    private static void writeFile(String filename, String text) throws IOException {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(filename);
            fileWriter.write(text);
        } catch (IOException e) {
            throw e;
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    System.out.println("An error occurred while closing the file writer.");
                    e.printStackTrace();
                }
            }
        }
    }
}
