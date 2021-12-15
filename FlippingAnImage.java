import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


/**
 *
 */
 public class FlippingAnImage {


    /**
     * Given an n x n binary matrix image, 
     * flip the image horizontally, 
     * then invert it, 
     * and return the resulting image.
     * 
     * Execution: O(N) - Space: O(1)

     * 82 / 82 test cases passed.
     * Status: Accepted
     * Runtime: 1 ms
     * Memory Usage: 41.8 MB
     */
    static public int[][] flipAndInvertImage0(int[][] image) {

        // **** initialization ****
        int n = image.length;

        // **** sanity checks ****
        if (n == 1) {
            image[0][0] = (image[0][0] == 0) ? 1 : 0;
            return image;
        }

        // **** flip image horizontally - O(n) ****
        for (int i = 0; i < n; i++)
            flip(image[i]);

        // **** invert image - O(n) ****
        invert(image);

        // **** return processed image ****
        return image;
    }


    /**
     * Flip the contents of the specified array.
     */
    static private void flip(int[] arr) {

        // **** initialization ****
        int n = arr.length;
        int i = 0;
        int j = n - 1;

        // **** flip array contents ****
        while (i < j) {

            // **** flip elements ****
            int tmp = arr[i];
            arr[i]  = arr[j];
            arr[j]  = tmp;

            // **** update indices ****
            i++;
            j--;
        }
    }


    /**
     * Invert the values of the specified matrix.
     */
    static private void invert(int[][] matrix) {

        // **** initialization ****
        int n = matrix.length;

        // **** invert values ****
        for (int r = 0; r < n; r++)
            for (int c = 0; c < n; c++)
                matrix[r][c] = (matrix[r][c] == 0) ? 1 : 0;
    }


    /**
     * Given an n x n binary matrix image, 
     * flip the image horizontally, 
     * then invert it, 
     * and return the resulting image.
     * 
     * Execution: O(N) - Space: O(N)
     * 
     * 82 / 82 test cases passed.
     * Status: Accepted
     * Runtime: 1 ms
     * Memory Usage: 42 MB
     */
    static public int[][] flipAndInvertImage1(int[][] image) {
        
        // **** sanity checks ****
        if (image.length == 1) {
            image[0][0] = (image[0][0] == 0) ? 1 : 0;
            return image;
        }

        // **** initialization ****
        int n           = image.length;
        int[][] matrix  = new int[n][n];

        // **** copy cell values from image to matrix - O(N) ****
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                matrix[r][c] = (image[r][n - 1 - c] == 0) ? 1 : 0;
            }

            // ???? ????
            // System.out.println("<<< matrix[" + r + "]: " + Arrays.toString(matrix[r]));
        }

        // **** return matrix ****
        return matrix;
    }


    /**
     * Given an n x n binary matrix image, 
     * flip the image horizontally, 
     * then invert it, 
     * and return the resulting image.
     * 
     * Execution: O(N) - Space: O(1)
     * 
     * Runtime: 0 ms, faster than 100.00% of Java online submissions.
     * Memory Usage: 41.7 MB, less than 8.71% of Java online submissions.
     */
    static public int[][] flipAndInvertImage(int[][] image) {
        
        // **** initialization ****
        int n = image.length;
    
        // **** sanity checks ****
        if (n == 1) {
            image[0][0] = (image[0][0] == 0) ? 1 : 0;
            return image;
        }

        // **** flip and invert cells one row at a time - O(N) ****
        for (int[] row : image) {

            // **** flip and invert cells - O(n) ****
            for (int i = 0; i * 2 < n; i++)
                if (row[i] == row[n - 1 - i])
                    row[i] = row[n - 1 - i] ^= 1;
        }

        // **** return processed image ****
        return image;
    }


    /**
     * Test scaffold.
     * 
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        
        // **** open buffered reader ****
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // **** read first input line ****
        int[] arr = Arrays.stream(br.readLine().trim().split(","))
                            .mapToInt(Integer::parseInt)
                            .toArray();

        // **** determine n ****
        int n = arr.length;

        // **** declare and populate image ****
        int[][] image = new int[n][];
        image[0] = arr;

        for (int i = 1; i < n; i++) {
            image[i] = Arrays.stream(br.readLine().trim().split(","))
                        .mapToInt(Integer::parseInt)
                        .toArray();
        }

        // **** close buffered reader ****
        br.close();

        // ???? ????
        System.out.println("main <<< n: " + n);
        System.out.println("main <<< image: ");
        for (int i = 0; i < n; i++)
            System.out.println(Arrays.toString(image[i]));

        // **** clone image ****
        // int[][] cloned = image.clone();
        int[][] cloned = new int[n][n];
        for (int r = 0; r < n; r++)
            for (int c = 0; c < n; c++)
                cloned[r][c] = image[r][c];

        // **** call function of interest ****
        image = flipAndInvertImage0(image);

        // **** display output image ****
        System.out.println("main <<< flipAndInvertImage0: ");
        for (int i = 0; i < n; i++)
            System.out.println(Arrays.toString(image[i]));

        // **** restore image ****
        image = cloned.clone();

        // **** call function of interest ****
        image = flipAndInvertImage1(image);

        // **** display output image ****
        System.out.println("main <<< flipAndInvertImage1: ");
        for (int i = 0; i < n; i++)
            System.out.println(Arrays.toString(image[i]));

        // **** restore image ****
        image = cloned.clone();

        // **** call function of interest ****
        image = flipAndInvertImage(image);

        // **** display output image ****
        System.out.println("main <<< flipAndInvertImage: ");
        for (int i = 0; i < n; i++)
            System.out.println(Arrays.toString(image[i]));
    }
 }