package com.cipher;

import com.huffman.Encoding;
import com.huffman.EncodingMap;
import com.huffman.HuffmanEncoder;

import java.math.BigDecimal;
import java.math.BigInteger;
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
        int[][] stars = new int[Constants.NUMBER_OF_STAR][17];

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
//        Star 07
        stars[6][0] = Constants.STAR_07_X;
        stars[6][1] = Constants.STAR_07_Y;
//        Star 08
        stars[7][0] = Constants.STAR_08_X;
        stars[7][1] = Constants.STAR_08_Y;
//        Star 09
        stars[8][0] = Constants.STAR_09_X;
        stars[8][1] = Constants.STAR_09_Y;
//        Star 10
        stars[9][0] = Constants.STAR_10_X;
        stars[9][1] = Constants.STAR_10_Y;
//        Star 11
        stars[10][0] = Constants.STAR_11_X;
        stars[10][1] = Constants.STAR_11_Y;
//        Star 12
        stars[11][0] = Constants.STAR_12_X;
        stars[11][1] = Constants.STAR_12_Y;
//        Star 13
        stars[12][0] = Constants.STAR_13_X;
        stars[12][1] = Constants.STAR_13_Y;
//        Star 14
        stars[13][0] = Constants.STAR_14_X;
        stars[13][1] = Constants.STAR_14_Y;
//        Star 15
        stars[14][0] = Constants.STAR_15_X;
        stars[14][1] = Constants.STAR_15_Y;
//        Star 16
        stars[15][0] = Constants.STAR_16_X;
        stars[15][1] = Constants.STAR_16_Y;
//        Star 17
        stars[16][0] = Constants.STAR_17_X;
        stars[16][1] = Constants.STAR_17_Y;
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
            map.put(i, input.charAt(i));
        }

        return map;
    }

    public String getCipherText(String text){
        StringBuffer cipherText = new StringBuffer();
        for (int i = 0; i < text.length() ; i++) {
            cipherText.append(String.valueOf((int) text.charAt(i)));
            if(i < (text.length()-1)){
                cipherText.append('-');
            }
        }
        return cipherText.toString();
    }

    public String getHuffmanEncodedvalue(String text){
        try {
            EncodingMap<Character> map = new EncodingMap<Character>(
                    toCharacterList(Constants.HUFFMAN_MAP_ATTRIBUTE));
            HuffmanEncoder<Character> huffmanEncoder = new HuffmanEncoder<Character>(map);
            String encoded = Encoding.toString(huffmanEncoder.encode(toCharacterList(text)));
            int numberOfStartingZero = 0;
            for (int i = 0; i < encoded.length() ; i++) {
                if(encoded.charAt(i) == '0') {
                    numberOfStartingZero = i + 1;
                }
                else
                    break;
            }
            String encodedValueDecimal = numberOfStartingZero + String.valueOf(new BigInteger(encoded, 2));

            System.out.println("encoded value: " + encoded);
            System.out.println("encoded value in decimal: " + encodedValueDecimal);

            return encodedValueDecimal;
        }catch (Exception e){
            throw e;
        }
    }

    public String getHuffmanDecodedvalue(String text){
        try {
            EncodingMap<Character> map = new EncodingMap<Character>(
                    toCharacterList(Constants.HUFFMAN_MAP_ATTRIBUTE));
            HuffmanEncoder<Character> huffmanEncoder = new HuffmanEncoder<Character>(map);
            StringBuffer dataBuffer = new StringBuffer();
            int count = Integer.parseInt(text.substring(0,1));
            String data = new BigInteger(text.substring(1)).toString(2);
            for (int i = 0; i < count ; i++) {
                dataBuffer.append("0");
            }
            dataBuffer.append(data);

            System.out.println(dataBuffer);
            List<Character> decoded = huffmanEncoder.decode(dataBuffer.toString());
            return charactersToString(decoded);
        }catch (Exception e){
            throw e;
        }
    }

    public static List<Character> toCharacterList(String s) {
        List<Character> ret = new ArrayList<Character>(s.length());
        for (char c : s.toCharArray()) {
            ret.add(c);
        }
        return ret;
    }

    public String charactersToString(List<Character> chars) {
        StringBuilder sb = new StringBuilder();
        for (char c : chars)
            sb.append(c);
        return sb.toString();
    }
    
}
