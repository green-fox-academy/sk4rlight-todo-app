import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PrintBasedOnArgument {

    public void printTheArgument(String[] argument) {

        if (argument.length == 0) {
            System.out.println(getUsageInformation());
        } else if (argument[0].equals("-l")) {
            System.out.println(getToDoList());
        } else if (argument[0].equals("-a")) {
            addNewTask(argument[1]);
        } else if (argument[0].equals("-r")) {
            removeTask(Integer.valueOf(argument[1]));
        }

    }

    private String getUsageInformation() {
        Path instructionPath = Paths.get("../data/instructions.txt");
        List<String> content = new ArrayList<>();

        try {
            content = Files.readAllLines(instructionPath);
        } catch (IOException e) {
            System.out.println("Oops, there are no instructions for this program :)");
        }
        StringBuilder contentToString = new StringBuilder();
        for (String list : content) {
            contentToString.append(list);
            contentToString.append("\n");
        }
        return contentToString.toString();
    }

    private String getToDoList() {
        Path toDoPath = Paths.get("../data/todotasks.txt");
        List<String> content = new ArrayList<>();

        try {
            content = Files.readAllLines(toDoPath);
        } catch (IOException e) {
            System.out.println("Oops, there are no tasks for this argument :)");
        }
        StringBuilder contentToString = new StringBuilder();
        if (content.isEmpty()) {
            return "There are no tasks for today! :)";
        }
        contentToString.append("$todo -l \n \n");

        for (int i = 0; i < content.size(); i++) {
            contentToString.append(i + 1 + ". " + content.get(i));
            contentToString.append("\n");
        }
        return contentToString.toString();
    }

    private void addNewTask(String argumentText) {
        Path toDoPath = Paths.get("../data/todotasks.txt");
        List<String> content = new ArrayList<>();

        try {
            content = Files.readAllLines(toDoPath);
        } catch (IOException e) {
            System.out.println("Oops, cannot access todo file.");
        }
        content.add(argumentText);

        try {
            Files.write(toDoPath, content);
        } catch (IOException e) {
            System.out.println("Can't add new task.");
        }
    }

    private void removeTask(int argumentNumber) {
        Path toDoPath = Paths.get("../data/todotasks.txt");
        List<String> content = new ArrayList<>();

        try {
            content = Files.readAllLines(toDoPath);
        } catch (IOException e) {
            System.out.println("Oops, cannot access todo file.");
        }
        content.remove(argumentNumber);

        try {
            Files.write(toDoPath, content);
        } catch (IOException e) {
            System.out.println("Can't add new task.");
        }
    }
}

