import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import jdk.nashorn.internal.parser.JSONParser;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {



    public String readRawDataToString() throws Exception{
        ClassLoader classLoader = getClass().getClassLoader();
        String result = IOUtils.toString(classLoader.getResourceAsStream("RawData.txt"));
        return result;
    }

    public static void main(String[] args) throws Exception{

        String groceryList = loadFile();
        int matchCounter = 0;

//        String patternString1 = "(name) (?i)";
//        Pattern pattern = Pattern.compile(patternString1);
//        Matcher matcher = pattern.matcher(groceryList);
//        while (matcher.find()){
//            System.out.println("found: " + matcher.group(1) + "" + matcher.group(2));
//        }

        Pattern pattern = Pattern.compile("(?i)name");
        Matcher matcher = pattern.matcher(groceryList);
//        matchCounter += matcher.groupCount();
        groceryList = matcher.replaceAll("\nname:");
//        String name = groceryList;


        Pattern pattern2 = Pattern.compile("(?i)milk");
        Matcher matcher2 = pattern2.matcher(groceryList);
        groceryList = matcher2.replaceAll("Milk");
//        String milk = groceryList;



        System.out.println(groceryList);




//        String output = groceryList;
//        System.out.println(output);

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
