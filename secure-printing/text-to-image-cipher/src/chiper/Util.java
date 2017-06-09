package chiper;

import java.util.*;

/**
 * Created by HP on 31-May-17.
 */
public class Util {
    public int getCharValue(Character character){
        return (int)character;
    }

    public int generateRandomValue(int max, int min){
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public int[] generateRandomValuesDescended(int min, int max, int size){
        final Random random = new Random();
        final List<Integer> list = new ArrayList<>();
        while (list.size() < size) {
            list.add(random.nextInt(max) + min);
        }
        Collections.sort(list, Collections.reverseOrder());
        final int[] ints = new int[list.size()];
        final Iterator<Integer> iter = list.iterator();
        for (int i = 0; iter.hasNext(); ++i) {
            ints[i] = iter.next();
        }
        return ints;
    }

    public double roundTwoDecimalPoints(double value){
        return (double)Math.round(value * 100) / 100;
    }

    public int[][] loadStars(){
        int[][] stars = new int[Constants.NUMBER_OF_STAR][6];

//        Star 01
        stars[0][0] = Constants.STAR_01_X;
        stars[0][1] = Constants.STAR_01_Y;
//        Star 02
        stars[1][0] = Constants.STAR_02_X;
        stars[1][1] = Constants.STAR_02_Y;
//        Star 03
        stars[2][0] = Constants.STAR_03_X;
        stars[2][1] = Constants.STAR_03_Y;
//        Star 04
        stars[3][0] = Constants.STAR_04_X;
        stars[3][1] = Constants.STAR_04_Y;
//        Star 05
        stars[4][0] = Constants.STAR_05_X;
        stars[4][1] = Constants.STAR_05_Y;
//        Star 06
        stars[5][0] = Constants.STAR_06_X;
        stars[5][1] = Constants.STAR_06_Y;

        return stars;

    }

    public int[][] loadBlackHoles(){
        int[][] holes = new int[Constants.NUMBER_OF_BLACK_HOLE][3];

//        Hole 01
        holes[0][0] = Constants.BLACKHOLE_01_X;
        holes[0][1] = Constants.BLACKHOLE_01_Y;
        holes[0][2] = Constants.BLACKHOLE_01_R;

        return holes;

    }

    public HashMap<Integer,Character> mapInputText(String input){
        HashMap<Integer,Character> map = new HashMap<Integer,Character>();
        for (int i = 0; i < input.length(); i++) {
            map.put(i,input.charAt(i));
        }

        return map;
    }
}
