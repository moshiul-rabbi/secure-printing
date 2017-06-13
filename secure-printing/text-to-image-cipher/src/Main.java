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
        String scannedDst = "D:\\Development\\Temporary\\RemovableFiles\\test file\\Sample6\\CCF13017_0024.pdf";

        String textToChiper = "DCP-T700W 1497340211704 A8-1E-84-08-5B-D5";
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
            String[] index = ("54:43:56:11:57:47:16:9:21:59:25:58:66:53:26:2:67:32:73:34:0:28:3:17:65:39:70:23:62:45:13:8:72:18:52:61:44:33:24:1:19:64:71:15:20:36:27:51:46:60:42:50:4:31:41:48:55:29:22:7:14:5:49:40:38:30:35:68:10:69:12:63:6:37").split(":");

//            ArrayList<Cordinate> cordinates_soft = printerOperation.readCordinatesFromPDF(dst);
//            output = new CipherOperation().getCharacterUsingIndex(cordinates_soft, index);

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
