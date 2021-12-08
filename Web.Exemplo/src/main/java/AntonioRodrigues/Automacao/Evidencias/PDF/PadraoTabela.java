package AntonioRodrigues.Automacao.Evidencias.PDF;

import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;

public class PadraoTabela {
	private Font _fontTitulo = new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD);
	private Font _fontTexto = new Font(Font.FontFamily.HELVETICA, 7, Font.NORMAL);
	private final int _padTop = 3, _padRod = 6;
	
	protected PdfPCell DefinirCelulaTitulo(String texto) {
		PdfPCell celula = new PdfPCell(new Phrase(texto, _fontTitulo));
		
		celula.setPaddingTop(_padTop);
		celula.setPaddingBottom(_padRod);
		celula.setNoWrap(false);
		
		return celula;
	}
	
	protected PdfPCell DefinirCelulaTitulo(String texto, int colunasMescladas) {
		PdfPCell celula = new PdfPCell(new Phrase(texto, _fontTitulo));
		
		celula.setColspan(colunasMescladas);
		celula.setPaddingTop(_padTop);
		celula.setPaddingBottom(_padRod);
		celula.setNoWrap(false);
		
		return celula;
	}
	
	protected PdfPCell DefinirCelulaValor(String texto) {
		PdfPCell celula = new PdfPCell(new Phrase(texto, _fontTexto));
		
		celula.setPaddingTop(_padTop);
		celula.setPaddingBottom(_padRod);
		celula.setNoWrap(false);
		
		return celula;
	}
	
	protected PdfPCell DefinirCelulaValor(String texto, int colunasMescladas) {
		PdfPCell celula = new PdfPCell(new Phrase(texto, _fontTexto));
		
		celula.setColspan(colunasMescladas);
		celula.setPaddingTop(_padTop);
		celula.setPaddingBottom(_padRod);
		celula.setNoWrap(false);
		
		return celula;
	}
}
