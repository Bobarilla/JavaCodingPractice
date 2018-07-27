import java.util.Scanner;
import java.text.*;
import java.io.*;
import java.awt.*;
/**
 * Write a description of class ArrayFinder here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ArrayFinder
{
    public static void main(String[] args) throws Exception {
        /*
         * https://www.toptal.com/developers/sorting-algorithms
         * https://www.geeksforgeeks.org/insertion-sort/
         */
        Scanner sc = new Scanner(System.in);
        Scanner fr = new Scanner(new FileReader("./txt/ArrayData.txt"));
        DecimalFormat df = new DecimalFormat("");//TBD
        PrintWriter pw = new PrintWriter("./txt/ArrayData.txt");
        //int[] array = {0,4,3,6,8,2,1,9,5,7};
        System.out.print("How many numbers should be generated?\t");
        int n, temp, temp2;
        n = sc.nextInt(); sc.nextLine();

        for (int i=0; i<n; i++) {
            pw.println((int) (Math.random()*10));
        }
        pw.close();
        int[] array = new int[n];
        for (int i=0; i<array.length; i++) {
            array[i] = fr.nextInt(); fr.nextLine();
        }
        boolean containsInt=false;
        System.out.println("Unsorted");
        PrintArray(array);

        System.out.println("High to Low");
        //Bubble Sort
        //BubbleSortHToL(array);
        //Selection sort
        SelectionSortHToL(array);
        //Insertion Sort
        //InsertionSortHToL(array);
        //Shell Sort
        //ShellSortHToL(array);
        PrintArray(array);

        System.out.println("Low to High");
        //Bubble Sort
        BubbleSortLToH(array);
        PrintArray(array);

        int max, min, intAmount=0;
        System.out.println("Maximum\n"+array[array.length-1]);
        System.out.println("Minimum\n"+array[0]);
        System.out.print("Int to search for:\t");
        temp = sc.nextInt(); sc.nextLine();
        for (int i=0; i<array.length; i++) {
            if (array[i] == temp) {
                containsInt = true;
                intAmount++;
                System.out.println(temp+" found at location "+i);
            }
        }
        System.out.println("Contains this number:\t"+containsInt+"\nAmount:\t"+intAmount);
    }

    static void PrintArray(int[] array) {
        for (int i=0; i<array.length; i++) {
            System.out.println(array[i]);
        }
    }

    static void BubbleSortHToL(int[] array) {
        int temp;
        for (int i=0; i<array.length; i++) {//passes
            for (int j=0; j<array.length; j++) {//position
                if (j!=(array.length-1)) {
                    if (array[j+1]>array[j]) {
                        temp = array[j]; //stores lower
                        array[j] = array[j+1]; //lower changes to higher
                        array[j+1] = temp; //higher changes to lower
                    } else {
                        //System.out.println("Nope");
                    }
                }
            }
        }
    }

    static void BubbleSortLToH(int[] array) {
        int temp,x=0;
        for (int i=0; i<array.length; i++) {//passes
            for (int j=0; j<array.length; j++) {//position
                if (j!=(array.length-1)) {
                    if (array[j+1]<array[j]) {
                        temp = array[j]; //stores lower
                        array[j] = array[j+1]; //lower changes to higher
                        array[j+1] = temp; //higher changes to lower
                    }
                }
            }
        }
    }

    static void SelectionSortHToL(int[] array) {
        int temp, temp2;
        for (int i=0; i<array.length; i++) {
            temp = i;//start position
            for (int j=i; j<array.length; j++) {//go through others
                if (array[temp]<array[j]) {
                    temp=j;
                }
            }
            temp2=array[i];
            array[i]=array[temp];
            array[temp]=temp2;
        }
    }

    static void SelectionSortLToH(int[] array) {
        int temp, temp2;
        for (int i=0; i<array.length; i++) {
            temp=i;//start position
            for (int j=i; j<array.length; j++) {//go through others
                if (array[temp]>array[j]) {
                    temp=j;
                }
            }
            temp2=array[i];
            array[i]=array[temp];
            array[temp]=temp2;
        }
    }

    static void InsertionSortHToL(int[] array) {
        /*
         * Takes Value
         * Compares to previous values, if it finds smaller, it places it in that position and moves all after up by 1
         */
        for (int i=1; i<array.length; i++) //[f,t,t,t,t,t,t,t] ->
        {
            int temp = array[i];
            int j = i-1;
            // Move elements of array[0..i-1 OR j], that are greater than temp, to one position ahead of their current position
            while (j>=0 && array[j] < temp)//not i<=1 //going backwards [f(j.f),t,t,t,t,t,t,t(j.i),f(i),f,f,f,f] <- //Shifts values ->
            {
                array[j+1] = array[j];
                j--;
            }
            //System.out.println("Run");
            array[j+1] = temp;//[]
        }
        //return array;
    }

    static void InsertionSortLToH(int[] array) {
        for (int i=1; i<array.length; i++)
        {
            int temp = array[i];
            int j = i-1;
            while (j>=0 && array[j] > temp)
            {
                array[j+1] = array[j];
                j--;
            }
            array[j+1] = temp;
        }
    }
    
    static void ShellSortHToL(int[] array) {//INCOMPLETE
        for (int gap = array.length/2; gap > 0; gap /= 2)
        {
            for (int i = gap; i < array.length; i += 1)
            {
                int temp = array[i]; //SAVED i
                int j;
                for (j = i; j >= gap && array[j - gap] < temp; j -= gap) { //[f,f,f,f,f,f,f,f,f,f,f,f,f,f,t,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f](30)Gap3
                    array[j] = array[j - gap]; //REPLACES j
                }
                array[j] = temp;
            }
        }
    }
}
