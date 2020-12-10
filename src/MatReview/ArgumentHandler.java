package MatReview;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ArgumentHandler {

    public void handleArgument(String[] arguments) {
        if (arguments.length == 0) {
            System.out.println(getInstructions());
        } else {
            switch (arguments[0]) {
                case "-U" :
                case " --uppercase" :
                {
                    System.out.println(arguments[1].toUpperCase());
                    break;
                }
                case "-L":
                case " --lowercase" :{
                    System.out.println(arguments[1].toLowerCase());
                    break;
                }
                default:{
                    System.out.println("invalid arguments");
                    break;
                }
            }
        }
    }

    private String getInstructions() {
        Path instructionPath = Paths.get("data/instructions");
        List<String> content = new ArrayList<>();
        try {
            content = Files.readAllLines(instructionPath);
        } catch (IOException e) {
            System.out.println("Sry, instructions not available, have fun experimenting");
        }
        StringBuilder contentAsString = new StringBuilder();
        for (String line: content) {
            contentAsString.append(line);
            contentAsString.append("\n");
        }
        return contentAsString.toString();

    }

}