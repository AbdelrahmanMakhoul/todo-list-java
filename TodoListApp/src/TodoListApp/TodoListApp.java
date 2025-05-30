package TodoListApp;

import java.util.*;
import java.io.*;

public class TodoListApp {
    static List<String> tasks = new ArrayList<>();
    static final String FILE_NAME = "tasks.txt";

    public static void main(String[] args) {
        loadTasks();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n---To-do list---");
            System.out.println("1. View tasks");
            System.out.println("2. Add task");
            System.out.println("3. Delete task");
            System.out.println("4. Save and exit");
            System.out.print("Choose option:");
            choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1 -> showTasks();
                case 2 -> addTask(scanner);
                case 3 -> deleteTask(scanner);
                case 4 -> saveTasks();
                default -> System.out.println("Invalid option.5");
            }

        } while (choice != 4);
    }

    static void showTasks() {
        if (tasks.isEmpty()) {
            System.out.println("There are no tasks.");
            return;
        }
        System.out.println("Tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    static void addTask(Scanner scanner) {
        System.out.print("Enter the task: ");
        String task = scanner.nextLine();
        tasks.add(task);
        System.out.println("Added.");
    }

    static void deleteTask(Scanner scanner) {
        showTasks();
        System.out.print("Enter the task number to delete: ");
        int index = scanner.nextInt() - 1;
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
            System.out.println("deleted");
        } else {
            System.out.println("invalid");
        }
    }

    static void saveTasks() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (String task : tasks) {
                writer.println(task);
            }
            System.out.println("saved");
        } catch (IOException e) {
            System.out.println("failed");
        }
    }

    static void loadTasks() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;
        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                tasks.add(fileScanner.nextLine());
            }
        } catch (IOException e) {
            System.out.println("Failed to load tasks.");
        }
    }
}
