import java.util.Scanner; 
import java.text.*;
import java.io.*;
import java.awt.*;
/**
 * Write a description of class CountingStarsRecursive here.
 *
 * @author A
 * @version (a version number or a date)
 * 
 */
public class CountingStarsRecursive
{
    public static void main(String[] args) throws IOException {
        Scanner read = new Scanner(new FileReader("./txt/stars.txt"));
        int y, x;
        while (true) {
            //read dimensions
            try {
                y=read.nextInt();
                x=read.nextInt();
            } catch (Exception e) {
                //System.out.println(e);
                System.out.println("END");
                break;
            }
            read.nextLine();
            String[] row = new String[y];
            int[][] map = new int[y][x];
            //read map as String rows
            for (int i=0; i<y; i++) {
                row[i] = read.nextLine();
            }
            //seperate into matrix of 1 and 0
            for (int i=0; i<y; i++) {
                for (int j=0; j<x; j++) {
                    if (row[i].charAt(j)=='*') {
                        map[i][j] = 1;
                    } else {
                        map[i][j] = 0;
                    }
                }
            }
            //print map as 1s and 0s
            System.out.println("map[][]\t");
            PrintMap(map);
            /*
             * Count Stars:
             */
            int starCount=0;
            for (int i=0; i<y; i++) {
                for (int j=0; j<x; j++) {
                    if (map[i][j] == 1) {
                        starCount++;
                        map = RemoveStar(map, i, j);
                    }
                }
            }
            System.out.println("Number of stars:\t"+starCount);
        }
    }
    
    static int[][] RemoveStar(int[][] map, int y, int x) {
    /*
     * Remove Stars:
     *
     *  Run through all values {FOR LOOP}
     *      if 1 add 1 to starCount and
     *
     *          check if 1 if true turn to 0, and
     *          run ^ on surrounding values
     */
        if (map[y][x] == 1) {
            //change to 0 , must be a map[][] change
            map[y][x]=0;
            //run RemoveStar() on surrounding , must not be out of bounds
            //check bounds with map.length (y) and map[0].length (x)
            if (-1<y&&y<map.length-1) //0-19 go below if 0-18
                map=RemoveStar(map, y+1, x); //below
            if (-1<x&&x<map[0].length-1) //0-19 go right if 0-18
                map=RemoveStar(map, y, x+1); //right
            if (0<y&&y<map.length) //0-19 go above if 1-19
                map=RemoveStar(map, y-1, x); //above
            if (0<x&&x<map[0].length) //0-19 go left if 1-19
                map=RemoveStar(map, y, x-1); //left
        }
        return map;
    }

    static void PrintMap(int[][] map) {
        for (int i=0; i<map.length; i++) {
            for (int j=0; j<map[0].length; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }