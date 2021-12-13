import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class  PatternMatcher {

    //create a class loader to import the RawData.txt into a map

    private static String groceryList;
    private HashMap<String, Integer> occurrencesOfFood = new HashMap<String,Integer>();


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


    static String changeAllInstancesOfLabels() {

        Pattern pattern = Pattern.compile("(?i)name");
        Matcher matcher = pattern.matcher(groceryList);
        String name = matcher.replaceAll("\nname");

        Pattern pattern1 = Pattern.compile("(?i)milk");
        Matcher matcher1 = pattern1.matcher(groceryList);
        String milk = matcher1.replaceAll("Milk");


        Pattern pattern2 = Pattern.compile("(?i)apples");
        Matcher matcher2 = pattern2.matcher(groceryList);
        String apples = matcher2.replaceAll("Apples");


        Pattern pattern3 = Pattern.compile("(?i)bread");
        Matcher matcher3 = pattern3.matcher(groceryList);
        String bread = matcher3.replaceAll("Bread");


        Pattern pattern4 = Pattern.compile("(?i)cookies");
        Matcher matcher4 = pattern4.matcher(groceryList);
        String cookie = matcher4.replaceAll("Cookies");

        Pattern pattern5 = Pattern.compile("(?i)price");
        Matcher matcher5 = pattern5.matcher(groceryList);
        String price = matcher5.replaceAll("\nPrice");

        Pattern pattern6 = Pattern.compile("(?i)foodexpiration");
        Matcher matcher6 = pattern6.matcher(groceryList);
        String foodExpiration = matcher6.replaceAll("\nFoodExpiration");

        Pattern pattern7 = Pattern.compile("[#;%^!@]");
        Matcher matcher7 = pattern7.matcher(groceryList);
        groceryList = matcher7.replaceAll("");

        Pattern pattern8 = Pattern.compile("(?i)type");
        Matcher matcher8 = pattern8.matcher(groceryList);
        groceryList = matcher8.replaceAll("\n");

        return groceryList;

    }


    public int getCountOfStuff() {

        int milkCounter = 0;
        Pattern milkPattern = Pattern.compile("(?i)milk");
        Matcher milkMatcher = milkPattern.matcher(groceryList);
        while (milkMatcher.find()) {
            milkCounter++;
        }

        int breadCounter = 0;
        Pattern breadPattern = Pattern.compile("(?i)bread");
        Matcher breadMatcher = milkPattern.matcher(groceryList);
        while (breadMatcher.find()) {
            breadCounter++;
        }

        int cookieCounter = 0;
        Pattern cookiePattern = Pattern.compile("(?i)cookies");
        Matcher cookieMatcher = milkPattern.matcher(groceryList);
        while (cookieMatcher.find()) {
            cookieCounter++;
        }

        int appleCounter = 0;
        Pattern applePattern = Pattern.compile("(?i)apples");
        Matcher appleMatcher = applePattern.matcher(groceryList);
        while (appleMatcher.find()) {
            appleCounter++;
        }

        int priceCounter = 0;
        Pattern pricePattern = Pattern.compile("3.23");
        Matcher priceMatcher = pricePattern.matcher(groceryList);
        while ((priceMatcher.find())){
            priceCounter++;
        }

        return priceCounter;
    }


    //get the frequency of food appearing in the groceryList
    //idea behind this is to create a Key and value everytime it encounters a new word
//    public void foodCount(String groceryList) {
//
//        String[] strArray = new String[groceryList.length()];
//
//        for (String food : strArray) {
//            if (occurrencesOfFood.containsKey(food)) {
//                occurrencesOfFood.put(food, occurrencesOfFood.get(food) + 1);
//            } else {
//                occurrencesOfFood.put(food, 1);
//            }
//
//
//        }
//    }

}



