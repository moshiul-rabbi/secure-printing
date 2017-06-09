package chiper;

/**
 * Created by HP on 31-May-17.
 */
public class GeometricCalculation {
    Util util = new Util();

    public boolean insideCircle(double center_x, double center_y, double point_x, double point_y, int radius){
        Double radiusCalculated =  getDistance(point_x, point_y, center_x, center_y);
//        System.out.println(radiusCalculated);
        if(radiusCalculated < radius)
            return true;
        else
            return false;
    }

    public double[] generateXY(int center_x, int center_y, int radius, int angleInDegree){
        double[] xy = new double[2];
        xy[0] = getXCoordinate(center_x, radius, angleInDegree);
        xy[1] = getYCoordinate(center_y,radius,angleInDegree);

        return xy;
    }

    public double[] generateXYCompressed(int center_x, int center_y, int radius, int angleInDegree, int compressorX, int compressorY){
        double[] xy = new double[2];
//        xy[0] = getXCoordinate(center_x, radius, angleInDegree) / compressorX;
//        xy[1] = getYCoordinate(center_y, radius, angleInDegree) / compressorY;

        xy[0] = getXCoordinate(center_x, radius/8, angleInDegree);
        xy[1] = getYCoordinate(center_y, radius/8, angleInDegree);

//        System.out.println("original: " + (xy[0]*8) + " , " + (xy[1]*8));
//        System.out.println("compressed: " + (xy[0]) + " , " + (xy[1]));

        return xy;
    }

    public double[] generateXYCompressed2(int center_x, int center_y, int radius, int angleInDegree, int compressor){
        double[] xy = new double[2];
        xy[0] = getXCoordinate(center_x, radius/compressor, angleInDegree);
        xy[1] = getYCoordinate(center_y, radius/compressor, angleInDegree);

//        System.out.println("original: " + (xy[0]*8) + " , " + (xy[1]*8));
//        System.out.println("compressed: " + (xy[0]) + " , " + (xy[1]));

        return xy;
    }

    public double getXCoordinate(int center_x, int radius, int angleInDegree){
        double angleInRadian = Math.toRadians(angleInDegree);
        return util.roundTwoDecimalPoints(center_x + radius*Math.cos(angleInRadian));
    }

    public double getYCoordinate(int center_y, int radius, int angleInDegree){
        double angleInRadian = Math.toRadians(angleInDegree);
        return util.roundTwoDecimalPoints(center_y + radius*Math.sin(angleInRadian));
    }

    public double getDistance(double point01_x, double point01_y, double point02_x, double point02_y){
        return Math.sqrt(Math.pow((point01_x - point02_x), 2) + Math.pow((point01_y - point02_y), 2));
    }

    public double getRadiusWithCompression(double point01_x, double point01_y, double point02_x, double point02_y, int compressor){
        return Math.sqrt(Math.pow(compressor, 2)*(Math.pow((point01_x - point02_x), 2) + Math.pow((point01_y - point02_y), 2)));
    }
}
