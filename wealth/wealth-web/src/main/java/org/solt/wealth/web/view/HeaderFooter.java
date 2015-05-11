package org.solt.wealth.web.view;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

public class HeaderFooter extends PdfPageEventHelper {

	private String header;
	private String footer;
	private String oddHeader;

	@Override
	public void onEndPage(PdfWriter writer, Document document) {
		Rectangle rect = writer.getBoxSize("art");
		// header
		if (header != null) {
			switch (writer.getPageNumber() % 2) {
			case 0:
				ColumnText.showTextAligned(writer.getDirectContent(),
						Element.ALIGN_RIGHT, new Phrase(header),
						rect.getRight(), rect.getTop(), 0);
				break;
			case 1:
				ColumnText.showTextAligned(writer.getDirectContent(),
						Element.ALIGN_LEFT, new Phrase(oddHeader),
						rect.getLeft(), rect.getTop(), 0);
				break;
			}
		}
		// footer
		ColumnText.showTextAligned(writer.getDirectContent(),
				Element.ALIGN_CENTER,
				new Phrase(String.format("第 %d 页", writer.getPageNumber()), AbstractIText5PdfView.getChineseFont(7)),
				(rect.getLeft() + rect.getRight()) / 2, rect.getBottom() - 30,
				0);
	}
	
	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getFooter() {
		return footer;
	}

	public void setFooter(String footer) {
		this.footer = footer;
	}

	public String getOddHeader() {
		return oddHeader;
	}

	public void setOddHeader(String oddHeader) {
		this.oddHeader = oddHeader;
	}

}
