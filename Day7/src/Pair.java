/**
 * Pair Class, stores a Key and a Value for that key.
 * @param <K> Type of Key
 * @param <V> Type of Value
 */
public class Pair<K,V> {

    private K data1;
    private V data2;

    /**
     * Constructs the Pair class.
     * @param data1 The data to be stored in the key.
     * @param data2 The data to be stored in the value.
     */
    public Pair(K data1, V data2){
        this.data1 = data1;
        this.data2 = data2;
    }

    /**
     * Gets the key for this given pair.
     * @return the key
     */
    public K getKey(){
        return data1;
    }

    /**
     * Gets the value for this pair
     * @return the value
     */
    public V getValue(){
        return data2;
    }

    /**
     * Prints the pair to the command line - used for testing.
     */
    public void printPair(){
        System.out.println(data1.toString() + ":" + data2.toString());
    }
}
