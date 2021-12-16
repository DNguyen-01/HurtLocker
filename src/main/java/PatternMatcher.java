import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class  PatternMatcher {

    //create a class loader to import the RawData.txt into a map

    private static String groceryList;
    private HashMap<String, Integer> occurrencesOfFood = new HashMap<String, Integer>();


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


    public Matcher getPrice() {

        Pattern pricePattern = Pattern.compile("\\d[.]\\d\\d");
        Matcher priceMatcher = pricePattern.matcher(groceryList);
        while (priceMatcher.find()) {

            return priceMatcher;

        }
        return null;
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
//
//            int priceCounter = 0;
//            Pattern pricePattern = Pattern.compile("3.23");
//            Matcher priceMatcher = pricePattern.matcher(groceryList);
//            while ((priceMatcher.find())) {
//                priceCounter++;

        return 0;
    }
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





