package PrinterUtil;

import chiper.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.TextMarginFinder;
import com.itextpdf.text.pdf.parser.TextRenderInfo;
import com.ocr.reader.Cordinate;
import com.ocr.reader.OCROperation;
import net.sourceforge.tess4j.TesseractException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by HP on 01-Jun-17.
 */
public class PrinterOperation {
    Util util = new Util();

    public void writeToPdfFromExisting(String srcPath, String destPath, String text) throws IOException, DocumentException {
        PdfReader reader = new PdfReader(srcPath);

        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document,new FileOutputStream(destPath));
        document.open();
        PdfImportedPage page = writer.getImportedPage(reader, 1);

        com.itextpdf.text.Image imgPento = Image.getInstance(page);
        float width = imgPento.getWidth();
        float height = imgPento.getHeight();
        System.out.println("Width: " + width + " , Height: " + height);

        PdfContentByte contentunder = writer.getDirectContentUnder();
        PdfTemplate template = contentunder.createTemplate(width, height);
        template.setColorFill(new BaseColor(255, 0, 255));
        template.rectangle(482, 400, 60, 310);
        template.fill();
        template.setColorFill(new BaseColor(0, 0, 0));
        template.beginText();
        template.setFontAndSize(BaseFont.createFont("c:/windows/fonts/arial.ttf", BaseFont.WINANSI, BaseFont.NOT_EMBEDDED), 9);

//        Get Text Co-ordinates
        ArrayList<CharacterInfo> characterInfos = new ArrayList<CharacterInfo>();
        try {
            characterInfos = new ChiperOperation().getCordinatesFromText(text);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        For Testing Start
        /*template.showTextAligned(Element.ALIGN_CENTER, "c", 10, 10, 0);
        template.showTextAligned(Element.ALIGN_CENTER, "c", 20, 20, 0);
        template.showTextAligned(Element.ALIGN_CENTER, "c", 30, 10, 0);*/
//        For Testing End

//        Adding indices
        StringBuffer index = new StringBuffer();
        for (int i = 0; i < characterInfos.size() ; i++) {
            index.append(characterInfos.get(i).getIndex());
            if(i < characterInfos.size()-1)
                index.append("#");
        }

        template.showTextAligned(Element.ALIGN_LEFT, index.toString(), 10, 10, 0);

//        System.out.println("Before write");
        int len = text.length();
        for(int i=0; i<len; i++) {
            System.out.println("(x,y): ("+ (float)characterInfos.get(i).getX() + "," + (float)characterInfos.get(i).getY() +")");
//            System.out.println("(x,y): ("+ (float)(xy[i][0]+ Constants.BASE_X) + "," + (float)(xy[i][1]+Constants.BASE_Y) +")");
            template.showTextAligned(Element.ALIGN_RIGHT, "o", (float)(characterInfos.get(i).getX()+ Constants.BASE_X),
                    (float)(characterInfos.get(i).getY()+Constants.BASE_Y), 0);
        }

        template.endText();
        template.addImage(imgPento, imgPento.getWidth(), 0, 0, imgPento.getHeight(), 0, 0);
        com.itextpdf.text.Image imgPento2 = Image.getInstance(template);
        document.add(imgPento2);
        document.close();

    }

    public ArrayList<Cordinate> readCordinatesFromPDF(String srcPath) throws IOException {
        PdfReader reader2 = new PdfReader(srcPath);

        System.out.println("-------- After read ----------");

        ArrayList<Cordinate> cordinates = new ArrayList<Cordinate>();
        PdfReaderContentParser parser = new PdfReaderContentParser(reader2);
        parser.processContent(1, new TextMarginFinder(){
            @Override
            public void renderText(TextRenderInfo renderInfo) {
                super.renderText(renderInfo);

//                System.out.println(renderInfo.getText());

                if(renderInfo.getText().equals("o")){
                    double x = util.roundTwoDecimalPoints(renderInfo.getBaseline().getBoundingRectange().getX()-Constants.BASE_X-Constants.DAVIATION_X);
                    double y = util.roundTwoDecimalPoints(renderInfo.getBaseline().getBoundingRectange().getY()-Constants.BASE_Y-Constants.DAVIATION_Y);

                    System.out.println("(x,y): ("+ util.roundTwoDecimalPoints(renderInfo.getBaseline().getBoundingRectange().getX()-Constants.BASE_X-Constants.DAVIATION_X) + ","
                            + util.roundTwoDecimalPoints(renderInfo.getBaseline().getBoundingRectange().getY()-Constants.BASE_Y-Constants.DAVIATION_Y + 50) +")");

                    Cordinate cordinate = new Cordinate();
                    cordinate.setX(x);
                    cordinate.setY(y);
                    cordinates.add(cordinate);
                }
            }
        });

        return  cordinates;
    }

    public ArrayList<Cordinate> readCordinatesFromScannedPDF(String inputFileName, double standardDeviation, double standardScannedBaseX,
                                                             double standardScannedBaseY, double baseX, double baseY) throws IOException {
        OCROperation ocrOperation = new OCROperation();
        ArrayList<Cordinate> cordinates = new ArrayList<Cordinate>();
        try {
            cordinates = ocrOperation.getOcrParsedCharacterPosition(inputFileName, standardDeviation, standardScannedBaseX, standardScannedBaseY,
                    baseX, baseY);
        } catch (TesseractException e) {
            e.printStackTrace();
        }
        return  cordinates;
    }
}
