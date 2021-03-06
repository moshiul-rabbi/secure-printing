package chiper;

import com.ocr.reader.Cordinate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by HP on 31-May-17.
 */
public class ChiperOperation {
    GeometricCalculation geometricCalculation = new GeometricCalculation();
    Util util = new Util();
    GridChiper gridChiper = new GridChiper();

    public ArrayList<CharacterInfo> getCordinatesFromText(String text) throws Exception {
        int length = text.length();
        int tempStar = -1;
        ArrayList<CharacterInfo> characterInfos = new ArrayList<CharacterInfo>();

        System.out.print("Provided Text: " + text);
        System.out.println();

        for(int i=0; i<text.length();i++) {

//            int radius = (int)text.charAt(i);
            int radius = gridChiper.getAbsoluteValue(text.charAt(i));

            int randomStar = util.generateRandomValue((Constants.NUMBER_OF_STAR - 1), 0);
            System.out.println("random star: " + randomStar);
            if(Constants.NUMBER_OF_STAR == 0)
                throw new Exception("At least one star is required");

            int[][] stars = util.loadStars();
            double[] xy = generateMinDistanceXY(radius, randomStar, stars, Constants.COMPRESS_X, Constants.COMPRESS_Y,
                    Constants.BOUNDARY_RIGHT, Constants.BOUNDARY_Y_TOP, Constants.BOUNDARY_Y_BOTTOM);
//            System.out.println("(x,y): ("+ xy[0] + "," + xy[1] +")");

            CharacterInfo characterInfo = new CharacterInfo();
            characterInfo.setX(xy[0]);
            characterInfo.setY(xy[1]);
            characterInfo.setIndex(i);
            characterInfo.setData(text.charAt(i));

            characterInfos.add(characterInfo);
        }

        // Sorting based on Y
        /*java.util.Arrays.sort(characterInfo, new java.util.Comparator<double[]>() {
            public int compare(double[] a, double[] b) {
                return (-1)*Double.compare(a[1], b[1]);
            }
        });
*/
        Collections.sort(characterInfos, new Comparator<CharacterInfo>() {
            public int compare(CharacterInfo characterInfo1, CharacterInfo characterInfo2) {
                if (characterInfo1.getY() == characterInfo2.getY())
                    return 0;
                return characterInfo1.getY() < characterInfo2.getY() ? 1 : -1;
            }
        });

        for (final CharacterInfo s : characterInfos) {
            System.out.println(s.getIndex() + ": " + s.getData() + " x:" + s.getX() + " y:" + s.getY());
        }

        return characterInfos;
    }

    public double[] generateMinDistanceXY(int radius, int star, int[][] stars, int compressX, int compressY,
                                          int boundaryRight, int boundaryTop, int boundaryBottom) {
        int angleInDegree = util.generateRandomValue(Constants.MAX_ANGLE_IN_DEGREE, Constants.MIN_ANGLE_IN_DEGREE);
        double[] xy = geometricCalculation.generateXYCompressed(stars[star][0], stars[star][1], radius, angleInDegree, compressX, compressY);
        for(int i=0; i<Constants.NUMBER_OF_STAR; i++) {
            if (i == star)
                continue;

            double distance = geometricCalculation.getDistance(xy[0], xy[1], stars[i][0], stars[i][1]);
            boolean insideBlackHole = insideBlackHole(xy[0], xy[1]);

            double x_whole = xy[0]+Constants.BASE_X;
            double y_whole = xy[1]+Constants.BASE_Y;

            System.out.println("angle: " + angleInDegree + " distance: " + distance + " radius: " + (double)(radius/8) + " Star: " + i + " x_whole: " + x_whole
                    + " boundaryRight: " + boundaryRight + " y_whole:  " + y_whole + " boundaryBottom " + boundaryBottom + " ,top " + boundaryTop);

            if (distance < (double)(radius/8) || insideBlackHole || x_whole > boundaryRight ||
                    y_whole < boundaryBottom || y_whole > boundaryTop) {
                return generateMinDistanceXY(radius, star, stars, compressX, compressY, boundaryRight, boundaryTop, boundaryBottom);
            }
        }
        return xy;
    }

    public boolean insideBlackHole(double x, double y){
        int[][] blackHoles = util.loadBlackHoles();
        for(int i=0; i<Constants.NUMBER_OF_BLACK_HOLE; i++){
            boolean insideHole = geometricCalculation.insideCircle(blackHoles[i][0], blackHoles[i][1], x, y, blackHoles[i][2]);
            if(insideHole)
                return true;
        }
        return false;
    }

    public String getCharacter(ArrayList<Cordinate> cordinates) throws Exception {
        if(Constants.NUMBER_OF_STAR == 0)
            throw new Exception("At least one star is required");

        int[][] stars = util.loadStars();
        StringBuffer verifiedText = new StringBuffer();

        for(int i=0; i<cordinates.size(); i++){
            double x = cordinates.get(i).getX();
            double y = cordinates.get(i).getY();

            if(insideBlackHole(x,y))
                continue;

            double[] starPosition = getMinDistanceStar(x,y,stars);
            int radius = (int)Math.round(geometricCalculation.getDistance(x,y,starPosition[0],starPosition[1]));
            System.out.println(radius);
            verifiedText.append(gridChiper.getCharWithRelativeValue(radius));
        }
        return verifiedText.toString();
    }

    public String getCharacterFromScannedDocument(ArrayList<Cordinate> cordinates) throws Exception {
        if(Constants.NUMBER_OF_STAR == 0)
            throw new Exception("At least one star is required");

        String dataIndecies = cordinates.get(cordinates.size() - 1).getData();
        String[] index = dataIndecies.split("#");

        int[][] stars = util.loadStars();
        char[] text = new char[cordinates.size()];

        for(int i=0; i<cordinates.size()-1; i++){
            double x = cordinates.get(i).getX();
            double y = cordinates.get(i).getY();

            if(insideBlackHole(x,y))
                continue;

            double[] starPosition = getMinDistanceStar(x,y,stars);
            int radius = (int)Math.round(geometricCalculation.getDistance(x,y,starPosition[0],starPosition[1]));
            System.out.println(radius);

            if(i < index.length)
                text[Integer.parseInt(index[i])] =  gridChiper.getCharWithRelativeValue(radius);

//            text[i] =  gridChiper.getCharWithRelativeValue(radius);
        }
        return String.valueOf(text);
    }

    public String getCharacterUsingIndex(ArrayList<Cordinate> cordinates, char[] index) throws Exception {
        if(Constants.NUMBER_OF_STAR == 0)
            throw new Exception("At least one star is required");

        int[][] stars = util.loadStars();
        char[] text = new char[cordinates.size()];

        for(int i=0; i<cordinates.size(); i++){
            double x = cordinates.get(i).getX();
            double y = cordinates.get(i).getY();

            if(insideBlackHole(x,y))
                continue;

            double[] starPosition = getMinDistanceStar(x,y,stars);
            int radius = (int)Math.round(geometricCalculation.getDistance(x,y,starPosition[0],starPosition[1])) * Constants.COMPRESSOR;
            System.out.println("//// star(x,y): " + starPosition[0] + "," + starPosition[1] );
//            verifiedText.append(gridChiper.getCharWithRelativeValue(radius));

            text[index[i]] =  gridChiper.getCharWithRelativeValue(radius);
        }
//        return verifiedText.toString();
        return String.valueOf(text);
    }

    public double[] getMinDistanceStar(double x, double y, int[][] stars) {
        double[] star = new double[2];

        double min = geometricCalculation.getDistance(x, y, stars[0][0], stars[0][1]);
        star[0] = stars[0][0];
        star[1] = stars[0][1];

        for(int i=1; i<Constants.NUMBER_OF_STAR; i++) {
            double distance = geometricCalculation.getDistance(x, y, stars[i][0], stars[i][1]);
            if(distance < min){
                min = distance;
                star[0] = stars[i][0];
                star[1] = stars[i][1];
            }
        }
        return star;
    }

    public double[] generateNoiceText(){
        int radius = util.generateRandomValue(Constants.BLACKHOLE_01_R,0);
        int angle = util.generateRandomValue(Constants.MAX_ANGLE_IN_DEGREE, Constants.MIN_ANGLE_IN_DEGREE);
        int[][] blackHoles = util.loadBlackHoles();
//        int randomHole = util.generateRandomValue(Constants.NUMBER_OF_BLACK_HOLE, 1);

        return geometricCalculation.generateXY(blackHoles[0][0], blackHoles[0][1], radius, angle);
    }
}
