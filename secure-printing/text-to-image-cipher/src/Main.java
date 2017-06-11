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
        String scannedDst = "D:\\Development\\Temporary\\RemovableFiles\\test file\\Sample6\\CCF11017_0002.png";

        String textToChiper = "rabbi";
        String cipherText = util.getCipherText(textToChiper);
        System.out.println("Cipher Text: " + cipherText);
//        printerOperation.writeToPdfFromExisting(src, dst, cipherText);

        try {
            String output = "";

//            For testing
//            char[] index = new char[1];
//            index[0] = 0;
            char[] index = new char[16];
            index[0] = 9;
            index[1] = 3;
            index[2] = 11;
            index[3] = 12;
            index[4] = 10;
            index[5] = 1;
            index[6] = 14;
            index[7] = 5;
            index[8] = 0;
            index[9] = 15;
            index[10] = 8;
            index[11] = 7;
            index[12] = 13;
            index[13] = 2;
            index[14] = 4;
            index[15] = 6;

//            ArrayList<Cordinate> cordinates = printerOperation.readCordinatesFromPDF(dst);
//            output = new ChiperOperation().getCharacterUsingIndex(cordinates, index);

            ArrayList<Cordinate> cordinates = printerOperation.readCordinatesFromScannedPDF(scannedDst,4.15, 116, 3635,
                    Constants.BASE_X,Constants.BASE_Y);
            output = new ChiperOperation().getCharacterUsingIndex(cordinates, index);

            System.out.println("Verified Text: " + output);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(new Date());

    }

}
