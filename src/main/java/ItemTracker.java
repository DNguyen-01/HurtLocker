import java.util.HashMap;
import java.util.Map;

public class ItemTracker {

    private final String name;
    private Map<String, Integer> priceFrequency;
    private int count;

    public ItemTracker(String name) {
        StringBuilder sb = new StringBuilder();
        if(name.length() > 0) { //uppercase the first letter
            sb.append(Character.toUpperCase(name.charAt(0)));
            for (int i = 1; i < name.length(); i++) { //to lowercase every character after index 0
                sb.append(Character.toLowerCase(name.charAt(i)));
            }
        }

        this.name = sb.toString();

        this.priceFrequency = new HashMap<>();
        this.count = 0;

    }

    public void incrementCount(){

        count++;
    }

    public int getCount(){
        return count;
    }

    public Map<String, Integer> getPriceFrequency() {
        return priceFrequency;
    }

    public String getName() {
        return name;
    }

    public void addPrice(String price){

        priceFrequency.put(price, priceFrequency.getOrDefault(price, 0)+1);
    }

    @Override
    public boolean equals(Object o){

        if(!(o instanceof ItemTracker)){

            return false;
        }
        ItemTracker item = (ItemTracker) o;

        return name.equals(item.getName());
    }

    @Override
    public int hashCode(){ //using this in a map or set, therefore we need a hashcode to identify

        return name.hashCode();
    }
}
