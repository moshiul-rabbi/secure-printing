import com.ocr.reader.OCROperation;
import net.sourceforge.tess4j.TesseractException;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws TesseractException, IOException {
//        String filepath = "D:\\Development\\Temporary\\RemovableFiles\\test file\\pdf-sample-01\\CCF05017_0008.pdf";
        String filepath = "D:\\Development\\Temporary\\RemovableFiles\\test file\\Sample6\\CCF11017_0002.pdf";
        new OCROperation().getOcrParsedCharacterPosition(filepath, 4.15, 116, 3635, 490, 405);
    }
}
