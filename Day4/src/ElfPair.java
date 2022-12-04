/**
 * Elf pair class. Stores the cleaning assignment for a pair of elves.
 */
public class ElfPair {

    /**
     * The first assignment in a pair
     **/
    private String elf1;

    /**
     * The second assignment in a pair
     **/
    private String elf2;

    /**
     * Constructs the Elf Pair from two given strings containing the assignment
     *
     * @param elf1 the first assignment
     * @param elf2 the second assignment
     */
    public ElfPair(String elf1, String elf2) {
        this.elf1 = elf1;
        this.elf2 = elf2;
    }

    /**
     * Gets the assignment for the first elf in the pair
     *
     * @return the assignment for elf1
     */
    public String getElf1() {
        return elf1;
    }

    /**
     * Gets the assignment for the second elf in the pair
     *
     * @return the assignment for elf2
     */
    public String getElf2() {
        return elf2;
    }
}
