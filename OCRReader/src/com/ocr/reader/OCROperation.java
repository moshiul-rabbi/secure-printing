package com.ocr.reader;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Moshiul on 05-Jun-17.
 */
public class OCROperation {
    public ArrayList<Cordinate> getOcrParsedCharacterPosition(String inputFileName, double standardDeviation,
                                                              double standardScannedBaseX, double standardScannedBaseY,
                                                              double baseX, double baseY) throws TesseractException, IOException {
        ArrayList<Cordinate> cordinates = new ArrayList<Cordinate>();
        File image = new File(inputFileName);

//        OCR
        Tesseract tesseract = new Tesseract();
        tesseract.setHocr(true);
        tesseract.setTessVariable("tessedit_char_whitelist","o");
//        String htmlOutput = tesseract.doOCR(image, new Rectangle(0,0,2457,3483));
        String htmlOutput = tesseract.doOCR(image, new Rectangle(2120,670,270,1300));
        System.out.println(htmlOutput);

//        Co-ordinates from HOCR output
        Document document = Jsoup.parse(htmlOutput);
        ArrayList<Character> index = new ArrayList<Character>();

        for (Element ocrxWord : document.select(".ocrx_word")) {
            String text = ocrxWord.text();
            String title = ocrxWord.attr("title"); // bbox 250 192 1606 375; x_wconf 70, etc
            System.out.println("text: " + text + " position: " + title);

            Cordinate cordinate = getCordinatesFromOcrOutput(text, title, standardDeviation, standardScannedBaseX, standardScannedBaseY, baseX, baseY);
            System.out.println(cordinate.getX() + " " + cordinate.getY());
            cordinates.add(cordinate);
        }

        return cordinates;
    }

    public Cordinate getCordinatesFromOcrOutput(String data, String characterPosition, double standardDeviation, double standardScannedBaseX,
                                                double standardScannedBaseY, double baseX, double baseY){
        Cordinate cordinate = new Cordinate();
/*        if(data.charAt(0) == '0')
            System.out.println("into");*/

        String[] cordinatesPosition = characterPosition.split(";")[0].split(" ");
        double x = roundTwoDecimalPoints((((Double.parseDouble(cordinatesPosition[1])) - standardScannedBaseX) / standardDeviation) - baseX);
        double y = roundTwoDecimalPoints(((standardScannedBaseY - (Double.parseDouble(cordinatesPosition[4]))) / standardDeviation) - baseY);

        cordinate.setX(x);
        cordinate.setY(y);
        cordinate.setData(data);
        return cordinate;
    }

    public double roundTwoDecimalPoints(double value){
        return (double)Math.round(value * 100) / 100;
    }
}
