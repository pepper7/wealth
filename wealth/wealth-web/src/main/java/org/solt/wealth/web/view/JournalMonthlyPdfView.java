package org.solt.wealth.web.view;

import java.text.NumberFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.solt.wealth.model.AccountingBook;
import org.solt.wealth.model.Journal;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPTableEvent;
import com.itextpdf.text.pdf.PdfWriter;

public class JournalMonthlyPdfView extends AbstractIText5PdfView {

	@Override
	protected void buildPdfMetadata(Map<String, Object> model,
			Document document, HttpServletRequest request) {
	}

	@Override
	protected void buildPdfDocument(Map<String, Object> model,
			Document document, PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		NumberFormat qtyFm = NumberFormat.getNumberInstance();
		qtyFm.setMinimumFractionDigits(3);
		qtyFm.setMaximumFractionDigits(3);
		NumberFormat amountFm = NumberFormat.getCurrencyInstance();
		String month = (String) model.get("month");
		AccountingBook accBook = (AccountingBook) model.get("accBook");
		
		List<Journal> journalList = (List<Journal>) model.get("journalList");
		Double total = (Double) model.get("total");

		Font titleFont = getChineseFont(20);
		titleFont.setStyle(Font.BOLD);
		Paragraph title = new Paragraph(new Chunk(accBook.getAccBookName()
				+ "：" + month + "月明细", titleFont));
		title.setAlignment(Element.ALIGN_CENTER);
		document.add(title);

		float[] widths = { 15f, 30f, 30f, 30f, 30f, 30f, 30f, 30f, 30f, 30f,
				30f };
		String[] tabHeaderTitles = { "序号", "日期", "项目", "单位", "数量", "单价", "优惠价",
				"金额", "规格", "品牌", "说明" };
		PdfPTable table = new PdfPTable(widths);
		table.setSpacingBefore(20f);
		table.setTotalWidth(500);
		table.setWidthPercentage(100);
		PdfPCell cell = null;
		Font headerFont = getChineseFont(9);
		headerFont.setStyle(Font.BOLD);
		table.setHeaderRows(1);
		for (String tabHTitle : tabHeaderTitles) {
			cell = new PdfPCell(new Paragraph(tabHTitle, headerFont));
			cell.setFixedHeight(15);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(new BaseColor(102,153,204));
			table.addCell(cell);
		}

		// table body
		int row = 0;
		for (Journal journal : journalList) {
			row++;
			cell = new PdfPCell(new Paragraph(String.valueOf(row),
					getChineseFont(8)));
			cell.setFixedHeight(15);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(cell);
			cell = new PdfPCell(new Paragraph(journal.getJournalDate()
					.toString(), getChineseFont(8)));
			cell.setFixedHeight(15);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(cell);
			cell = new PdfPCell(new Paragraph(journal.getItem(),
					getChineseFont(8)));
			cell.setFixedHeight(15);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(cell);
			cell = new PdfPCell(new Paragraph(journal.getUom(),
					getChineseFont(8)));
			cell.setFixedHeight(15);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(cell);
			cell = new PdfPCell(new Paragraph(qtyFm.format(journal.getQuantity()),
					getChineseFont(8)));
			cell.setFixedHeight(15);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(cell);
			cell = new PdfPCell(new Paragraph(
					amountFm.format(journal.getUnitPrice()), getChineseFont(8)));
			cell.setFixedHeight(15);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(cell);
			cell = new PdfPCell(new Paragraph(
					journal.getDiscountPrice() == null ? "" : amountFm.format(journal
							.getDiscountPrice()), getChineseFont(8)));
			cell.setFixedHeight(15);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(cell);
			cell = new PdfPCell(new Paragraph(amountFm.format(journal.getAmount()),
					journal.getAmount()>=500?getChineseFontRed(8):getChineseFont(8)));
			cell.setFixedHeight(15);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(cell);
			cell = new PdfPCell(new Paragraph(journal.getSpecification(),
					getChineseFont(8)));
			cell.setFixedHeight(15);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setNoWrap(false);
			table.addCell(cell);
			cell = new PdfPCell(new Paragraph(journal.getBrand(),
					getChineseFont(8)));
			cell.setFixedHeight(15);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(cell);
			cell = new PdfPCell(new Paragraph(journal.getDescription(),
					getChineseFont(8)));
			cell.setFixedHeight(15);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(cell);
		}
		// table footer
		// table.setFooterRows(1);
		cell = new PdfPCell(new Paragraph("合计", headerFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setColspan(7);
		table.addCell(cell);

		cell = new PdfPCell(new Paragraph(amountFm.format(total), headerFont));
		cell.setFixedHeight(15);
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.addCell(cell);

		cell = new PdfPCell(new Paragraph("", getChineseFont(8)));
		cell.setColspan(3);
		table.addCell(cell);

		// 加入隔行换色事件
		PdfPTableEvent event = new AlternatingBackground();
		table.setTableEvent(event);

		document.add(table);
	}

}
