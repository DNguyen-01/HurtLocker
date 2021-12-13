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
        //pattern matcher to search for "Hamlet"
//        int matchCounter = 0;
        Pattern pattern = Pattern.compile("NAME");
        //find all matches in hamletData
        Matcher matcher = pattern.matcher(groceryList);
//        matchCounter += matcher.groupCount();
        //replace all the matcher w/ Leon
        groceryList = matcher.replaceAll("name");

        //searches for all uppercase
        pattern = Pattern.compile("naMe");
        matcher = pattern.matcher(groceryList);
//        matchCounter += matcher.groupCount();
        //replace with all uppercase
        groceryList = matcher.replaceAll("name");

        pattern = Pattern.compile("naME");
        matcher = pattern.matcher(groceryList);
//        matchCounter += matcher.groupCount();
        //replace with all uppercase
        groceryList = matcher.replaceAll("name");

        pattern = Pattern.compile("NAMe");
        matcher = pattern.matcher(groceryList);
//        matchCounter += matcher.groupCount();
        //replace with all uppercase
        groceryList = matcher.replaceAll("name");





        String output = groceryList;
        System.out.println(output);

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
