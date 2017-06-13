import com.cipher.CipherOperation;
import com.cipher.Constants;
import com.ocr.reader.Cordinate;
import com.printer.util.PrinterOperation;
import com.cipher.Util;
import com.itextpdf.text.*;

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
        String scannedDst = "D:\\Development\\Temporary\\RemovableFiles\\test file\\Sample6\\CCF13017_0003.pdf";

        String textToChiper = "Moshiul Huq Rabbi";
        StringBuffer cipherText = new StringBuffer(util.getHuffmanEncodedvalue(textToChiper));

        System.out.println("Cipher Text: " + cipherText.toString());

        for (int i = 0; i < 1 ; i++) {
            System.out.println("count: " + i);
//            printerOperation.writeToPdfFromExisting(src, dst, cipherText.toString());
        }

        try {
            String output = "";

//            For testing
//            char[] index = new char[1];
//            index[0] = 0;
            String[] index = "14:3:4:22:26:0:29:11:17:16:20:23:13:9:7:6:10:19:5:27:25:18:2:21:1:24:12:28:15:8".split(":");

            ArrayList<Cordinate> cordinates_soft = printerOperation.readCordinatesFromPDF(dst);
            output = new CipherOperation().getCharacterUsingIndex(cordinates_soft, index);
            System.out.println("Verified Text: " + output);

            ArrayList<Cordinate> cordinates = printerOperation.readCordinatesFromScannedPDF(scannedDst,4.15, 116, 3635,
                    Constants.BASE_X,Constants.BASE_Y);
            output = new CipherOperation().getCharacterUsingIndex(cordinates, index);

            System.out.println("Verified Text: " + output);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(new Date());

    }

}
