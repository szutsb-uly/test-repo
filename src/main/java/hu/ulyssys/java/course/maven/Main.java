package hu.ulyssys.java.course.maven;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.WorkbookUtil;

import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

public class Main {
    @Inject
    private TestService testService;

    public static void main(String[] args) {
        CDI<Object> cdi = CDI.getCDIProvider().initialize();
        Main main = cdi.select(Main.class).get();
        main.main(Arrays.asList(args));

        Workbook wb = new HSSFWorkbook();  // or new XSSFWorkbook();
        Sheet sheet1 = wb.createSheet("new sheet");
        Sheet sheet2 = wb.createSheet("second sheet");
// Note that sheet name is Excel must not exceed 31 characters
// and must not contain any of the any of the following characters:
// 0x0000
// 0x0003
// colon (:)
// backslash (\)
// asterisk (*)
// question mark (?)
// forward slash (/)
// opening square bracket ([)
// closing square bracket (])
// You can use org.apache.poi.ss.util.WorkbookUtil#createSafeSheetName(String nameProposal)}
// for a safe way to create valid names, this utility replaces invalid characters with a space (' ')
        String safeName = WorkbookUtil.createSafeSheetName("[O'Brien's sales*?]"); // returns " O'Brien's sales   "
        Sheet  sheet3 = wb.createSheet(safeName);
        try (OutputStream fileOut = new FileOutputStream("workbook.xls")) {
            wb.write(fileOut);
        }catch (Exception e){
            e.printStackTrace();
        }

        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("test.pdf"));
            document.open();
            Paragraph subPara = new Paragraph("Subcategory 2");
            document.add(subPara);
            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void main(List<String> args) {
        testService.print();
    }

}
