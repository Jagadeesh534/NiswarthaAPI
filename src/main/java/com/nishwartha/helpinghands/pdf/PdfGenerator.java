package com.nishwartha.helpinghands.pdf;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.nishwartha.helpinghands.modal.DonarRegisterDTO;

public class PdfGenerator {

	private static Logger logger = LoggerFactory.getLogger(PdfGenerator.class);

	public static ByteArrayInputStream bloodDonarsPDFReport(List<DonarRegisterDTO> donars) {
		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {

			PdfWriter.getInstance(document, out);
			document.open();

			// Add Text to PDF file ->
			Font font = FontFactory.getFont(FontFactory.COURIER, 14);
			Paragraph para = new Paragraph("Blood Donars List", font);
			para.setAlignment(Element.ALIGN_CENTER);
			document.add(para);
			document.add(Chunk.NEWLINE);

			PdfPTable table = new PdfPTable(8);
			table.setWidthPercentage(100);
			table.setWidths(new int[] { 6, 10, 10, 5, 10, 20, 10, 20 });
			// Add PDF Table Header ->
			Stream.of("S.No", "Name", "Blood Group", "Age", "Weight", "Email", "Mobile", "Address")
					.forEach(headerTitle -> {
						PdfPCell header = new PdfPCell();
						Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
						header.setHorizontalAlignment(Element.ALIGN_CENTER);
						header.setPhrase(new Phrase(headerTitle, headFont));
						table.addCell(header);
					});
			long count = 0;
			for (DonarRegisterDTO donar : donars) {
				Font cellFont = FontFactory.getFont(FontFactory.TIMES_ROMAN,10);
				count = count + 1;
				int age = (int)Math.round(donar.getAge());
				PdfPCell idCell = new PdfPCell();
				idCell.setPaddingLeft(4);
				idCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				idCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				idCell.setPhrase(new Phrase(Long.toString(count),cellFont));
				table.addCell(idCell);

				PdfPCell nameCell = new PdfPCell();
				nameCell.setPaddingLeft(4);
				nameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				nameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				nameCell.setPhrase(new Phrase(donar.getName(),cellFont));
				table.addCell(nameCell);

				PdfPCell bloodGroupCell = new PdfPCell();
				bloodGroupCell.setPaddingLeft(4);
				bloodGroupCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				bloodGroupCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				bloodGroupCell.setPhrase(new Phrase(donar.getBloodgroup(),cellFont));
				table.addCell(bloodGroupCell);

				PdfPCell ageCell = new PdfPCell();
				ageCell.setPaddingLeft(4);
				ageCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				ageCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				ageCell.setPhrase(new Phrase(String.valueOf(age),cellFont));
				table.addCell(ageCell);

				PdfPCell weightCell = new PdfPCell();
				weightCell.setPaddingLeft(4);
				weightCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				weightCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				weightCell.setPhrase(new Phrase(donar.getWeight().toString() + " Kg's",cellFont));
				table.addCell(weightCell);

				PdfPCell emailCell = new PdfPCell();
				emailCell.setPaddingLeft(4);
				emailCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				emailCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				emailCell.setPhrase(new Phrase(donar.getEmail(),cellFont));
				table.addCell(emailCell);

				PdfPCell mobileCell = new PdfPCell();
				mobileCell.setPaddingLeft(4);
				mobileCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				mobileCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				mobileCell.setPhrase(new Phrase(donar.getMobilenumber(),cellFont));
				table.addCell(mobileCell);

				PdfPCell addressCell = new PdfPCell();
				addressCell.setPaddingLeft(4);
				addressCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				addressCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				addressCell.setPhrase(new Phrase(donar.getAddress(),cellFont));
				table.addCell(addressCell);

			}
			document.add(table);

			document.close();
		} catch (DocumentException e) {
			logger.error(e.toString());
		}

		return new ByteArrayInputStream(out.toByteArray());
	}
}
