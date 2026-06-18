package assessmentfeedbacksystem;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PdfHeaderFooter extends PdfPageEventHelper {
    private final Font headerFont = new Font(Font.FontFamily.HELVETICA, 9, Font.NORMAL,
                                    new BaseColor(160, 160, 160));

    private final Font footerFont = new Font(Font.FontFamily.HELVETICA, 9, Font.NORMAL,
                                    new BaseColor(160, 160, 160));

    private final String timestamp;

    public PdfHeaderFooter() {
        this.timestamp = new SimpleDateFormat("dd MMM yyyy, HH:mm").format(new Date());
    }

    @Override
    public void onEndPage(PdfWriter writer, Document document) {

        PdfContentByte cb = writer.getDirectContent();
        Rectangle page = document.getPageSize();

        // header
        ColumnText.showTextAligned(
            cb,
            Element.ALIGN_LEFT,
            new Phrase("APU Assessment Feedback System", headerFont),
            page.getLeft() + 40,
            page.getTop() - 30,
            0
        );

        ColumnText.showTextAligned(
            cb,
            Element.ALIGN_RIGHT,
            new Phrase(timestamp, headerFont),
            page.getRight() - 40,
            page.getTop() - 30,
            0
        );

        // footer
        ColumnText.showTextAligned(
            cb,
            Element.ALIGN_CENTER,
            new Phrase("Page " + writer.getPageNumber(), footerFont),
            (page.getLeft() + page.getRight()) / 2,
            page.getBottom() + 25,
            0
        );
    }
}
