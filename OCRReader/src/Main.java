import com.ocr.reader.OCROperation;
import net.sourceforge.tess4j.TesseractException;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws TesseractException, IOException {
        String filepath = "D:\\Development\\Temporary\\RemovableFiles\\test file\\Sample6\\CCF13017_0012.pdf";
        new OCROperation().getOcrParsedCharacterPosition(filepath, 4.15, 116, 3635, 490, 75);
    }
}
