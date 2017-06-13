package com.cipher;

import java.util.ArrayList;

/**
 * Created by HP on 05-Jun-17.
 */
public class GridCipher {
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

    public GridCipher(){
        CharMap map01 = new CharMap(10,6,15,'a');
        charMaps.add(map01);
        CharMap map02 = new CharMap(20,16,25,'b');
        charMaps.add(map02);
        CharMap map03 = new CharMap(30,26,35,'c');
        charMaps.add(map03);
        CharMap map04 = new CharMap(40,36,45,'d');
        charMaps.add(map04);
        CharMap map05 = new CharMap(50,46,55,'e');
        charMaps.add(map05);
        CharMap map06 = new CharMap(60,56,65,'f');
        charMaps.add(map06);
        CharMap map07 = new CharMap(70,66,75,'g');
        charMaps.add(map07);
        CharMap map08 = new CharMap(80,76,85,'h');
        charMaps.add(map08);
        CharMap map09 = new CharMap(90,86,95,'i');
        charMaps.add(map09);
        CharMap map10 = new CharMap(100,96,105,'j');
        charMaps.add(map10);
        CharMap map11 = new CharMap(110,106,115,'k');
        charMaps.add(map11);
        CharMap map12 = new CharMap(120,116,125,'l');
        charMaps.add(map12);
        CharMap map13 = new CharMap(130,126,135,'m');
        charMaps.add(map13);
        CharMap map14 = new CharMap(140,136,145,'n');
        charMaps.add(map14);
        CharMap map15 = new CharMap(150,146,155,'o');
        charMaps.add(map15);
        CharMap map16 = new CharMap(160,156,165,'p');
        charMaps.add(map16);
        CharMap map17 = new CharMap(170,166,175,'q');
        charMaps.add(map17);
        CharMap map18 = new CharMap(180,176,185,'r');
        charMaps.add(map18);
        CharMap map19 = new CharMap(190,186,195,'s');
        charMaps.add(map19);
        CharMap map20 = new CharMap(200,196,205,'t');
        charMaps.add(map20);
        CharMap map21 = new CharMap(210,206,215,'u');
        charMaps.add(map21);
        CharMap map22 = new CharMap(220,216,225,'v');
        charMaps.add(map22);
        CharMap map23 = new CharMap(230,226,235,'w');
        charMaps.add(map23);
        CharMap map24 = new CharMap(240,236,245,'x');
        charMaps.add(map24);
        CharMap map25 = new CharMap(250,246,255,'y');
        charMaps.add(map25);
        CharMap map26 = new CharMap(260,256,265,'z');
        charMaps.add(map26);
        CharMap map27 = new CharMap(270,266,275,'-');
        charMaps.add(map27);
        CharMap map28 = new CharMap(280,276,285,'0');
        charMaps.add(map28);
        CharMap map29 = new CharMap(290,286,295,'1');
        charMaps.add(map29);
        CharMap map30 = new CharMap(300,296,305,'2');
        charMaps.add(map30);
        CharMap map31 = new CharMap(310,306,315,'3');
        charMaps.add(map31);
        CharMap map32 = new CharMap(320,316,325,'4');
        charMaps.add(map32);
        CharMap map33 = new CharMap(330,326,335,'5');
        charMaps.add(map33);
        CharMap map34 = new CharMap(340,336,345,'6');
        charMaps.add(map34);
        CharMap map35 = new CharMap(350,346,355,'7');
        charMaps.add(map35);
        CharMap map36 = new CharMap(360,356,365,'8');
        charMaps.add(map36);
        CharMap map37 = new CharMap(370,366,375,'9');
        charMaps.add(map37);

    }

    public int getAbsoluteValue(char data){
        for (int i = 0; i < charMaps.size() ; i++) {
            if(charMaps.get(i).getData() == data)
                return charMaps.get(i).getDefaultValue();
        }
        return 0;
    }

    public char getCharWithRelativeValue(int value){
        for (int i = 0; i < charMaps.size() ; i++) {
            if(value >= charMaps.get(i).getMin() && value <= charMaps.get(i).getMax())
                return charMaps.get(i).getData();
        }
        return '0';
    }

}
