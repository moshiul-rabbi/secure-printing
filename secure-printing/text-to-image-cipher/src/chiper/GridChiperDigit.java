package chiper;

import java.util.ArrayList;

/**
 * Created by HP on 05-Jun-17.
 */
public class GridChiperDigit {
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

    public GridChiperDigit(){
        CharMap map1 = new CharMap(20,0,39,'0');
        charMaps.add(map1);
        CharMap map2 = new CharMap(60,40,79,'1');
        charMaps.add(map2);
        CharMap map3 = new CharMap(100,80,119,'2');
        charMaps.add(map3);
        CharMap map4 = new CharMap(140,120,159,'3');
        charMaps.add(map4);
        CharMap map5 = new CharMap(180,160,199,'4');
        charMaps.add(map5);
        CharMap map6 = new CharMap(220,200,239,'5');
        charMaps.add(map6);
        CharMap map7 = new CharMap(260,240,279,'6');
        charMaps.add(map7);
        CharMap map8 = new CharMap(300,280,319,'7');
        charMaps.add(map8);
        CharMap map9 = new CharMap(340,320,359,'8');
        charMaps.add(map9);
        CharMap map10 = new CharMap(380,360,399,'9');
        charMaps.add(map10);
        CharMap map11 = new CharMap(420,400,439,'-');
        charMaps.add(map11);

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

    public String getOriginalText(String value){
        StringBuffer originalText = new StringBuffer();
        String[] charValue = value.split("-");
        for (int i = 0; i < charValue.length ; i++) {
            originalText.append((char)Integer.parseInt(charValue[i]));
        }
        return originalText.toString();
    }

}
