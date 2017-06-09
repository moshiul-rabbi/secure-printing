package chiper;

import com.ocr.reader.Cordinate;

import java.util.ArrayList;

/**
 * Created by HP on 31-May-17.
 */
public class ChiperOperationtemp2 {
    GeometricCalculation geometricCalculation = new GeometricCalculation();
    Util util = new Util();
    GridChiper gridChiper = new GridChiper();

    public double[][] getCordinatesFromText(String text) throws Exception {
        int length = text.length();

        double[][] cordinates = new double[length][2];

        System.out.print("Provided Text: " + text);
        System.out.println();

        for(int i=0,j=0; i<text.length();j++) {
//            Noise add in even position
           /* if(j%2 == 0){
                double[] noise = generateNoiceText();
                cordinates[j][0] = noise[0];
                cordinates[j][1] = noise[1];
                continue;
            }*/
//            int radius = (int)text.charAt(i);

            int radius = gridChiper.getAbsoluteValue(text.charAt(i));

            int randomStar = util.generateRandomValue((Constants.NUMBER_OF_STAR - 1), 0);
            if(Constants.NUMBER_OF_STAR == 0)
                throw new Exception("At least one star is required");

            int[][] stars = util.loadStars();
            double[] xy = generateMinDistanceXY(radius, randomStar, stars);
//            System.out.println("(x,y): ("+ xy[0] + "," + xy[1] +")");
            cordinates[j][0] = xy[0];
            cordinates[j][1] = xy[1];
            i++;
        }

        // Sorting based on Y
        java.util.Arrays.sort(cordinates, new java.util.Comparator<double[]>() {
            public int compare(double[] a, double[] b) {
                return (-1)*Double.compare(a[1], b[1]);
            }
        });

        for (final double[] s : cordinates) {
            System.out.println(s[0] + " " + s[1]);
        }

        return cordinates;
    }

    public double[] generateMinDistanceXY(int radius, int star, int[][] stars) {
        int angleInDegree = util.generateRandomValue(Constants.MAX_ANGLE_IN_DEGREE, Constants.MIN_ANGLE_IN_DEGREE);
        double[] xy = geometricCalculation.generateXY(stars[star][0], stars[star][1], radius, angleInDegree);
        for(int i=0; i<Constants.NUMBER_OF_STAR; i++) {
            if (i == star)
                continue;

            double distance = geometricCalculation.getDistance(xy[0], xy[1], stars[i][0], stars[i][1]);
            boolean insideBlackHole = insideBlackHole(xy[0],xy[1]);
            if (distance < radius || insideBlackHole) {
                return generateMinDistanceXY(radius, star, stars);
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
