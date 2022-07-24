import java.util.Arrays;

/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {
        int length = 0;
        for (String item : asciis) {
            length = Math.max(length, item.length());
        }
        String[] sorted = Arrays.copyOf(asciis, asciis.length);
        for (int i = 0; i < length; i++) {
            sorted = sortHelperLSD(sorted, i, length);
        }
        return sorted;

    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     *
     * @param asciis Input array of Strings
     * @param index  The position to sort the Strings on.
     */
    private static String[] sortHelperLSD(String[] asciis, int index, int length) {
        int[] counts = new int[257];
        int longest = length - 1 - index;
        for (String i : asciis) {
            if (i.length() <= longest) {
                counts[0]++;//0 represent the null
            } else {
                counts[(int) i.charAt(longest) + 1]++;
            }
        }
        int[] starts = new int[257];
        int pos = 0;
        for (int i = 0; i < starts.length; i++) {
            starts[i] = pos;
            pos += counts[i];
        }
        String[] sorted = new String[asciis.length];
        int pad = 0;
        for (int i = 0; i < asciis.length; i++) {
            String item = asciis[i];
            if (item.length() <= longest) {
                int place = starts[pad];
                sorted[place] = item;
                starts[pad] += 1;
            } else {
                char c = item.charAt(longest);
                int place = starts[(int) c + 1];
                sorted[place] = item;
                starts[(int) c + 1] += 1;
            }

        }
        return sorted;

    }


    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start  int for where to start sorting in this method (includes String at start)
     * @param end    int for where to end sorting in this method (does not include String at end)
     * @param index  the index of the character the method is currently sorting on
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }
}
