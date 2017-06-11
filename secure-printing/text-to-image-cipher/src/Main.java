import PrinterUtil.PrinterOperation;
import chiper.ChiperOperation;
import chiper.Constants;
import chiper.Util;
import com.itextpdf.text.*;
import com.ocr.reader.Cordinate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws IOException, DocumentException {
        System.out.println(new Date());
        PrinterOperation printerOperation = new PrinterOperation();
        Util util = new Util();
        HashMap<Integer,Character> map = new HashMap<Integer,Character>();

        String src = "D:\\Development\\Temporary\\RemovableFiles\\TestDocument.pdf";
        String dst = "D:\\Development\\Temporary\\RemovableFiles\\TestDocument_good.pdf";
        String scannedDst = "D:\\Development\\Temporary\\RemovableFiles\\test file\\Sample6\\CCF11017_0002.pdf";

        String textToChiper = "moshiulhuqrabbi";
        printerOperation.writeToPdfFromExisting(src, dst, textToChiper);

        try {
            String output = "";

//            For testing
//            char[] index = new char[1];
//            index[0] = 0;
            char[] index = new char[7];
            index[0] = 1;
            index[1] = 6;
            index[2] = 3;
            index[3] = 4;
            index[4] = 0;
            index[5] = 2;
            index[6] = 5;

//            ArrayList<Cordinate> cordinates = printerOperation.readCordinatesFromPDF(dst);
//            output = new ChiperOperation().getCharacterUsingIndex(cordinates, index);

            /*ArrayList<Cordinate> cordinates = printerOperation.readCordinatesFromScannedPDF(scannedDst,4.15, 116, 3635,
                    Constants.BASE_X,Constants.BASE_Y);
            output = new ChiperOperation().getCharacterUsingIndex(cordinates, index);*/

            System.out.println("Verified Text: " + output);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(new Date());

    }

}
