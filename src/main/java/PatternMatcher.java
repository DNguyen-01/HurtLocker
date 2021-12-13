import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.security.PublicKey;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class  PatternMatcher {

    //create a class loader to import the RawData.txt into a map

    private String groceryList;
    public PatternMatcher patternMatcher;

    public PatternMatcher() {
        this.groceryList = loadFile();
        PatternMatcher patternMatcher = new PatternMatcher();
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

        int matchCounter = 0;
        Pattern pattern = Pattern.compile("(?i)name");
        Matcher matcher = pattern.matcher(groceryList);
//      matchCounter += matcher.groupCount();
        groceryList = matcher.replaceAll("\nname");

        return groceryList;


    }

    public String changeAllInstancesOfFoodType() {

        int matchCounterMilk = 0;
        Pattern pattern1 = Pattern.compile("(?i)milk");
        Matcher matcher1 = pattern1.matcher(groceryList);
//      matchCounter += matcher.groupCount();
        groceryList = matcher1.replaceAll("Milk");

        int matchCounterApples = 0;
        Pattern pattern2 = Pattern.compile("(?i)apples");
        Matcher matcher2 = pattern2.matcher(groceryList);
//      matchCounter += matcher.groupCount();
        groceryList = matcher2.replaceAll("Apples");

        int matchCounterBread = 0;
        Pattern pattern3 = Pattern.compile("(?i)bread");
        Matcher matcher3 = pattern3.matcher(groceryList);
//      matchCounter += matcher.groupCount();
        groceryList = matcher3.replaceAll("Bread");

        int matchCounterCookies = 0;
        Pattern pattern4 = Pattern.compile("(?i)cookies");
        Matcher matcher4 = pattern4.matcher(groceryList);
//      matchCounter += matcher.groupCount();
        groceryList = matcher4.replaceAll("Cookie");

        return groceryList;

    }

//    public String changeAllInstancesOfApples() {
//
//        int matchCounter = 0;
//        Pattern pattern = Pattern.compile("(?i)apples");
//        Matcher matcher = pattern.matcher(groceryList);
////      matchCounter += matcher.groupCount();
//        groceryList = matcher.replaceAll("Apples");
//
//        return groceryList;
//
//    }

//    public String changeAllInstancesOfBread() {
//
//
//        Pattern pattern = Pattern.compile("(?i)bread");
//        Matcher matcher = pattern.matcher(groceryList);
////      matchCounter += matcher.groupCount();
//        groceryList = matcher.replaceAll("Bread");
//
//        return groceryList;
//    }
//
//    public String changeAllInstancesOfCookies() {
//
//
//        Pattern pattern = Pattern.compile("(?i)cookies");
//        Matcher matcher = pattern.matcher(groceryList);
////      matchCounter += matcher.groupCount();
//        groceryList = matcher.replaceAll("Cookie");
//
//        return groceryList;
//
//    }

    public String changeAllInstancesOfPrices() {


        Pattern pattern = Pattern.compile("(?i)prices");
        Matcher matcher = pattern.matcher(groceryList);
//      matchCounter += matcher.groupCount();
        groceryList = matcher.replaceAll("\nPrices");

        return groceryList;

    }

    public String removeAllHashTags (){
        Pattern pattern = Pattern.compile("[#;]");
        Matcher matcher = pattern.matcher(groceryList);
//      matchCounter += matcher.groupCount();
        groceryList = matcher.replaceAll("");

        return groceryList;
    }



}



