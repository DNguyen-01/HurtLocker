import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private static String groceryList;

    public String readRawDataToString() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        String result = IOUtils.toString(classLoader.getResourceAsStream("RawData.txt"));
        return result;
    }

    public static void main(String[] args) throws Exception {

        /* TODO
        -> filter out the characters and weird symbols
        -> consolidate the multiple occurrence of the words
        -> get count (food, price)
        -> errors
        -> modify for printing out  -> add line for expiration date
         */


//        Pattern pattern = Pattern.compile("(?i)name");
//        Matcher matcher = pattern.matcher(groceryList);
//        String name = matcher.replaceAll("\nname");
//
//        Pattern pattern1 = Pattern.compile("(?i)milk");
//        Matcher matcher1 = pattern1.matcher(groceryList);
//        String milk = matcher1.replaceAll("Milk");
//
//
//        Pattern pattern2 = Pattern.compile("(?i)apples");
//        Matcher matcher2 = pattern2.matcher(groceryList);
//        String apples = matcher2.replaceAll("Apples");
//
//
//        Pattern pattern3 = Pattern.compile("(?i)bread");
//        Matcher matcher3 = pattern3.matcher(groceryList);
//        String bread = matcher3.replaceAll("Bread");
//
//
//        Pattern pattern4 = Pattern.compile("(?i)cookies");
//        Matcher matcher4 = pattern4.matcher(groceryList);
//        String cookie = matcher4.replaceAll("Cookies");
//
//        Pattern pattern5 = Pattern.compile("(?i)price");
//        Matcher matcher5 = pattern5.matcher(groceryList);
//        String price = matcher5.replaceAll("\nPrice");
//
//        Pattern pattern6 = Pattern.compile("(?i)foodexpiration");
//        Matcher matcher6 = pattern6.matcher(groceryList);
//        String foodExpiration = matcher6.replaceAll("\nFoodExpiration");
//
//        Pattern pattern7 = Pattern.compile("[#;%^!@]");
//        Matcher matcher7 = pattern7.matcher(groceryList);
//        groceryList = matcher7.replaceAll("");
//
//        Pattern pattern8 = Pattern.compile("(?i)type");
//        Matcher matcher8 = pattern8.matcher(groceryList);
//        groceryList = matcher8.replaceAll("\n");
//
//        int milkCounter = 0;
//        Pattern milkPattern = Pattern.compile("(?i)milk");
//        Matcher milkMatcher = milkPattern.matcher(groceryList);
//        while (milkMatcher.find()) {
//            milkCounter++;
//        }
//
//        int breadCounter = 0;
//        Pattern breadPattern = Pattern.compile("(?i)bread");
//        Matcher breadMatcher = milkPattern.matcher(groceryList);
//        while (breadMatcher.find()) {
//            breadCounter++;
//        }
//
//        int cookieCounter = 0;
//        Pattern cookiePattern = Pattern.compile("(?i)cookies");
//        Matcher cookieMatcher = milkPattern.matcher(groceryList);
//        while (cookieMatcher.find()) {
//            cookieCounter++;
//        }
//
//        int appleCounter = 0;
//        Pattern applePattern = Pattern.compile("(?i)apples");
//        Matcher appleMatcher = applePattern.matcher(groceryList);
//        while (appleMatcher.find()) {
//            appleCounter++;
//        }
//
//        int priceCounter = 0;
//        Pattern pricePattern = Pattern.compile("3.23");
//        Matcher priceMatcher = pricePattern.matcher(groceryList);
//        while ((priceMatcher.find())) {
//            priceCounter++;
//        }


        /* TODO build out the pattern */

        groceryList = loadFile();

//        Pattern pattern7 = Pattern.compile("##");
//        Matcher matcher7 = pattern7.matcher(groceryList);
//        groceryList = matcher7.replaceAll("\n");
//        String[] items = groceryList.split("\n");

        Pattern pattern = Pattern.compile("([\\w\\d]*):([\\w\\d./]*)[;^%&@#]+");
        Matcher matcher = pattern.matcher(groceryList);

        int errorCounter = 0;

        List<String> orderedItems = new ArrayList<>();
        Map<String, ItemTracker> items = new HashMap<>();

        String currentName = "";
        String currentPrice = "";

        while (matcher.find()) {
            String key = matcher.group(1);
            String value = matcher.group(2);
            boolean lastKeyValue = matcher.group(0).contains("##");
            if (key.equals("") || value.equals("")) {
                errorCounter++;
            } else {
                if (key.toLowerCase().equals("name")) {
                    currentName = value.replace('0', 'o').toLowerCase();
                }
                else if(key.toLowerCase().equals("price")){
                    currentPrice = value;
                }
            }
            if (lastKeyValue) {
                if(!currentName.equals("") && !currentPrice.equals("")) {
                    if (items.get(currentName) == null) {
                        items.put(currentName, new ItemTracker(currentName));
                        orderedItems.add(currentName);
                    }
                    ItemTracker currentItem = items.get(currentName);
                    currentItem.incrementCount();
                    currentItem.addPrice(currentPrice);
                    items.put(currentItem.getName().toLowerCase(), currentItem);
                }
                currentName = "";
                currentPrice = "";
            }
//            System.out.println("key: " + key + ";Value: " + value + "; " + lastKeyValue);
//            break;
        }
        for (String item: orderedItems) {

            ItemTracker currentItemTracker = items.get(item);
            System.out.println("name:" + currentItemTracker.getName() + " seen: " + currentItemTracker.getCount());
            for (Map.Entry<String, Integer> priceFrequency : currentItemTracker.getPriceFrequency().entrySet()) {
                System.out.println("price: " + priceFrequency.getKey() + " seen: " + priceFrequency.getValue());
            }
        }
        System.out.println(errorCounter);






    }





    private static String loadFile() {
        ClassLoader classLoader = Main.class.getClassLoader();
        File file = null;
        try {
            file = new File(new URI(classLoader.getResource("RawData.txt").toString()).getPath());
        }
        catch (Exception e){

        }
        StringBuilder result = new StringBuilder("");

        try(Scanner scanner = new Scanner(file)){
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                result.append(line).append("\n");
            }

            scanner.close();
        }catch(IOException e){
            e.printStackTrace();
        }

        return result.toString();
    }





}
