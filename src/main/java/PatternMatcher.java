import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternMatcher {

    //create a class loader to import the RawData.txt into a map

    private String groceryList;

    public PatternMatcher() {
        this.groceryList = loadFile();
    }

    private String loadFile() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = null;
        try {
            file = new File(new URI(classLoader.getResource("rawData.txt").toString()).getPath());
        } catch (Exception e) {

        }
        StringBuilder result = new StringBuilder("");

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                result.append(line).append("\n");
            }

            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result.toString();
    }

    public String changeAllInstancesOfName() {

//        int matchCounter = 0;
        Pattern pattern = Pattern.compile("(?i)NAME");
        Matcher matcher = pattern.matcher(groceryList);
//        matchCounter += matcher.groupCount();
        groceryList = matcher.replaceAll("name");

//        //searches for all uppercase
//        pattern = Pattern.compile("naMe");
//        matcher = pattern.matcher(groceryList);
//        matchCounter += matcher.groupCount();
//        //replace with all uppercase
//        groceryList = matcher.replaceAll("name");
//
//        pattern = Pattern.compile("naME");
//        matcher = pattern.matcher(groceryList);
//        matchCounter += matcher.groupCount();
//        //replace with all uppercase
//        groceryList = matcher.replaceAll("name");
//
//        pattern = Pattern.compile("NAMe");
//        matcher = pattern.matcher(groceryList);
//        matchCounter += matcher.groupCount();
//        //replace with all uppercase
//        groceryList = matcher.replaceAll("name");
        return groceryList;
    }


}
