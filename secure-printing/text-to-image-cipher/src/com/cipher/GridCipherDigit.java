package com.cipher;

import java.util.ArrayList;

/**
 * Created by HP on 05-Jun-17.
 */
public class GridCipherDigit {
    class CharMap{
        int defaultValue;
        int min;
        int max;
        char data;
        CharMap(int defaultValue, int min, int max,char data){
            this.defaultValue = defaultValue;
            this.min = min;
            this.max = max;
            this.data = data;
        }

        public int getDefaultValue() {
            return defaultValue;
        }
        public void setDefaultValue(int defaultValue) {
            this.defaultValue = defaultValue;
        }
        public int getMin() {
            return min;
        }
        public void setMin(int min) {
            this.min = min;
        }
        public int getMax() {
            return max;
        }
        public void setMax(int max) {
            this.max = max;
        }
        public char getData() {
            return data;
        }
        public void setData(char data) {
            this.data = data;
        }
    }

    private ArrayList<CharMap> charMaps = new ArrayList<CharMap>();

    public GridCipherDigit(){
        CharMap map1 = new CharMap(25,0,49,'0');
        charMaps.add(map1);
        CharMap map2 = new CharMap(75,50,99,'1');
        charMaps.add(map2);
        CharMap map3 = new CharMap(125,100,149,'2');
        charMaps.add(map3);
        CharMap map4 = new CharMap(175,150,199,'3');
        charMaps.add(map4);
        CharMap map5 = new CharMap(225,200,249,'4');
        charMaps.add(map5);
        CharMap map6 = new CharMap(275,250,299,'5');
        charMaps.add(map6);
        CharMap map7 = new CharMap(325,300,349,'6');
        charMaps.add(map7);
        CharMap map8 = new CharMap(375,350,399,'7');
        charMaps.add(map8);
        CharMap map9 = new CharMap(425,400,449,'8');
        charMaps.add(map9);
        CharMap map10 = new CharMap(475,450,499,'9');
        charMaps.add(map10);
    }

    public int getAbsoluteValue(char data){
        for (int i = 0; i < charMaps.size() ; i++) {
            if(charMaps.get(i).getData() == data)
                return charMaps.get(i).getDefaultValue();
        }
        return 0;
    }

    public char getCharWithRelativeValue(int value) throws Exception {
        for (int i = 0; i < charMaps.size() ; i++) {
            if(value >= charMaps.get(i).getMin() && value <= charMaps.get(i).getMax()) {
//                System.out.println("Value: " + value);
                return charMaps.get(i).getData();
            }
        }

        return charMaps.get(charMaps.size()-1).getData();
    }

    public String getOriginalText(String value){
        StringBuffer originalText = new StringBuffer();
        String[] charValue = value.split("-");
        for (int i = 0; i < charValue.length ; i++) {
            originalText.append((char)Integer.parseInt(charValue[i]));
        }
        return originalText.toString();
    }

}
