package AntonioRodrigues.Automacao.Evidencias.PDF;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.MessageFormat;
import java.util.Map;
import java.util.ArrayList;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class EvidenciaPDF {
	public Cabecalho Cabecalho = new Cabecalho();
    public Resumo Resumo = new Resumo();
	public Massa Massa = new Massa();
    private Document _pdf = new Document();
    private PadraoTabela _padraoTabela = new PadraoTabela();
	private ArrayList<Evidencia> _evidencias = new ArrayList<Evidencia>();
	private String _caminhoArquivo, _nomeArquivo;
	
	public EvidenciaPDF(String caminhoArquivo, String nomeArquivo) throws FileNotFoundException {
        try {
        	if(!caminhoArquivo.endsWith("\\"))
        		caminhoArquivo = caminhoArquivo.concat("\\");
        	
    		_caminhoArquivo = caminhoArquivo;
    		_nomeArquivo = nomeArquivo;
    		
    		new File(caminhoArquivo).mkdirs();
    		
			PdfWriter.getInstance(_pdf, new FileOutputStream(caminhoArquivo.concat(nomeArquivo)));
			
			_pdf.open();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	public void GravarPDF() throws MalformedURLException, IOException {
		this.InserirCabecalho();
		this.MontarResumo();
		this.InserirDadosMassa();
		this.InserirEvidencia();
		
		_pdf.close();
		
		//--> Renomeando arquivo
//		new File(_caminhoArquivo.concat(_nomeArquivo)).renameTo(new File(_caminhoArquivo.concat(MessageFormat.format("[{0}] ", Resumo.Status)).concat(_nomeArquivo)));
	}

    public void InserirEvidencia(String caminhoEvidencia, String descricaoPasso, String descricaoFalha) {
    	if (null == Resumo.ResultadoObtido || Resumo.ResultadoObtido.trim() == "" || Resumo.ResultadoObtido.equals(Resumo.ResultadoEsperado))
//    	if(!"".equals(descricaoFalha) && null != descricaoFalha)
    		Resumo.ResultadoObtido = descricaoFalha;
    	
    	_evidencias.add(new Evidencia(caminhoEvidencia, descricaoPasso, descricaoFalha));
    }
	
	private void MontarResumo() throws FileNotFoundException {
		PdfPTable tabela = new PdfPTable(7);
		
		try {
			tabela.setWidths(new int[] {100, 30, 60, 100, 100, 100, 100});
		
		//--> Linha 1
		tabela.addCell(_padraoTabela.DefinirCelulaTitulo("Id da demanda"));
		tabela.addCell(_padraoTabela.DefinirCelulaValor(Resumo.IdDemanda, 2));

		tabela.addCell(_padraoTabela.DefinirCelulaTitulo("Data execução"));
		tabela.addCell(_padraoTabela.DefinirCelulaValor(Resumo.ExecucaoData));

		tabela.addCell(_padraoTabela.DefinirCelulaTitulo("Hora execução"));
		tabela.addCell(_padraoTabela.DefinirCelulaValor(Resumo.ExecucaoHorario));

		//--> Linha 2
		tabela.addCell(_padraoTabela.DefinirCelulaTitulo("Ambiente"));
		tabela.addCell(_padraoTabela.DefinirCelulaValor(Resumo.Ambiente, 2));

		tabela.addCell(_padraoTabela.DefinirCelulaTitulo("Versão"));
		tabela.addCell(_padraoTabela.DefinirCelulaValor(Resumo.Versao));

		tabela.addCell(_padraoTabela.DefinirCelulaTitulo("Dispositivo"));
		tabela.addCell(_padraoTabela.DefinirCelulaValor(Resumo.Dispositivo));

		//--> Linha 3
		tabela.addCell(_padraoTabela.DefinirCelulaTitulo("Sistema", 2));
		tabela.addCell(_padraoTabela.DefinirCelulaValor(Resumo.Sistema, 5));

		//--> Linha 4
		tabela.addCell(_padraoTabela.DefinirCelulaTitulo("Descrição do cenário", 2));
		tabela.addCell(_padraoTabela.DefinirCelulaValor(Resumo.DescricaoCenario, 5));

		//--> Linha 5
		tabela.addCell(_padraoTabela.DefinirCelulaTitulo("Resultado esperado", 2));
		tabela.addCell(_padraoTabela.DefinirCelulaValor(Resumo.ResultadoEsperado, 5));

		//--> Linha 6
		tabela.addCell(_padraoTabela.DefinirCelulaTitulo("Resultado obtido", 2));
		tabela.addCell(_padraoTabela.DefinirCelulaValor("".equals(Resumo.ResultadoObtido) ? Resumo.ResultadoEsperado : Resumo.ResultadoObtido, 5));

		//--> Linha 7
		tabela.addCell(_padraoTabela.DefinirCelulaTitulo("Tipo de teste"));
		tabela.addCell(_padraoTabela.DefinirCelulaValor(Resumo.TipoTeste, 2));

		tabela.addCell(_padraoTabela.DefinirCelulaTitulo("Sprint"));
		tabela.addCell(_padraoTabela.DefinirCelulaValor(Resumo.Sprint));

		tabela.addCell(_padraoTabela.DefinirCelulaTitulo("Status"));
		tabela.addCell(_padraoTabela.DefinirCelulaValor(Resumo.Status));
		
	    _pdf.add(tabela);
	    
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
    private void InserirCabecalho(){
        try {
        	PdfPTable cabecalho = new PdfPTable(3);
        	
            cabecalho.setWidths(new int[]{5, 24, 5});
            cabecalho.setTotalWidth(420);
            cabecalho.setLockedWidth(true);
            cabecalho.getDefaultCell().setFixedHeight(40);
            cabecalho.getDefaultCell().setBorder(Rectangle.BOTTOM);

            //--> Definindo Logo1
            if("" != Cabecalho.CaminhoLogo1)
            	cabecalho.addCell(Image.getInstance(Cabecalho.CaminhoLogo1));
            else
            	cabecalho.addCell(new PdfPCell(new Phrase("", new Font(Font.FontFamily.HELVETICA, 14))));

            //--> Definindo o titulo
            PdfPCell titulo = new PdfPCell(new Phrase(Cabecalho.Titulo, new Font(Font.FontFamily.HELVETICA, 14)));
            titulo.setVerticalAlignment(Element.ALIGN_MIDDLE);
            titulo.setHorizontalAlignment(Element.ALIGN_CENTER);
            titulo.setBorder(Rectangle.BOTTOM);
            titulo.setBorderColor(BaseColor.LIGHT_GRAY);
            cabecalho.addCell(titulo);

            //--> Definindo Logo2
            if("" != Cabecalho.CaminhoLogo2)
            	cabecalho.addCell(Image.getInstance(Cabecalho.CaminhoLogo2));
            else
            	cabecalho.addCell(new PdfPCell(new Phrase("", new Font(Font.FontFamily.HELVETICA, 14))));

            _pdf.add(cabecalho);
            
            this.InserirEspacamento();

        } catch(DocumentException de) {
            throw new ExceptionConverter(de);
        } catch (IOException e) {
            throw new ExceptionConverter(e);
        }
    }
    
    private void InserirDadosMassa() {
    	try {
			this.InserirTextoTopico("Massa");
			this.InserirTabelaMassa();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
    }
    
    private void InserirEvidencia() throws MalformedURLException, IOException {
    	try {
    		for(Evidencia evidencia : _evidencias) {
		    	_pdf.newPage();
		    	
		    	this.InserirCabecalho();
		    	
		    	if (evidencia.DescricaoPasso != null && evidencia.DescricaoPasso.trim() != "")
						this.InserirTextoTopico(evidencia.DescricaoPasso);
		    	
		    	if (evidencia.DescricaoFalha != null && evidencia.DescricaoFalha.trim() != "")
		    		this.InserirTextoTopicoFalha(evidencia.DescricaoFalha);
		    	
		    	this.InserirEspacamento();
		    	
		    	if (evidencia.CaminhoEvidencia != null && evidencia.CaminhoEvidencia.trim() != "") {
		            Image imagem = Image.getInstance(evidencia.CaminhoEvidencia);
		            imagem.setAlignment(0);
		            
		            PdfPTable tabelaEvidencia = new PdfPTable(1);
		            tabelaEvidencia.setWidths(new int[]{10});
		            tabelaEvidencia.setTotalWidth(280);
		            tabelaEvidencia.setLockedWidth(true);
		            
		            tabelaEvidencia.addCell(imagem);
		    				
		    		_pdf.add(tabelaEvidencia);
		    	}
		    	else {
		    		_pdf.add(_padraoTabela.DefinirCelulaTitulo("Não foi possível fazer o carregamento da imagem especificada"));
		    		_pdf.add(_padraoTabela.DefinirCelulaValor(evidencia.CaminhoEvidencia));
		    	}
    		}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
    }
    
    private void InserirTabelaMassa() throws DocumentException {
    	InserirEspacamento();
    	
		PdfPTable tabelaMassa = new PdfPTable(2);

		for (Map.Entry<String, String> parametro : Massa.ObterListaParametros().entrySet()) {
			tabelaMassa.addCell(_padraoTabela.DefinirCelulaValor(parametro.getKey()));
			tabelaMassa.addCell(_padraoTabela.DefinirCelulaValor(parametro.getValue()));
		}
		
		_pdf.add(tabelaMassa);
    }
    
    private void InserirTextoTopico(String texto) throws DocumentException {
    	InserirEspacamento();
    	InserirEspacamento();
    	
        PdfPTable tabelaTopico = new PdfPTable(1);

        tabelaTopico.setTotalWidth(420);
        tabelaTopico.setLockedWidth(true);
        tabelaTopico.getDefaultCell().setFixedHeight(40);
        tabelaTopico.getDefaultCell().setBorder(Rectangle.TOP);
        

        PdfPCell celulaTopico = new PdfPCell(new Phrase(texto, new Font(Font.FontFamily.HELVETICA, 10)));
        celulaTopico.setVerticalAlignment(Element.ALIGN_MIDDLE);
        celulaTopico.setHorizontalAlignment(Element.ALIGN_LEFT);
        celulaTopico.setBorder(Rectangle.TOP);
        celulaTopico.setBorderColor(BaseColor.LIGHT_GRAY);
        tabelaTopico.addCell(celulaTopico);
        
        _pdf.add(tabelaTopico);
    }
    
    private void InserirTextoTopicoFalha(String texto) throws DocumentException {
    	InserirEspacamento();
    	InserirEspacamento();
    	
        PdfPTable tabelaTopico = new PdfPTable(1);

        tabelaTopico.setTotalWidth(420);
        tabelaTopico.setLockedWidth(true);
        tabelaTopico.getDefaultCell().setFixedHeight(40);
        tabelaTopico.getDefaultCell().setBorder(Rectangle.TOP);
        

        PdfPCell celulaTopico = new PdfPCell(new Phrase("Falha: ".concat(texto), new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.RED)));
        celulaTopico.setVerticalAlignment(Element.ALIGN_MIDDLE);
        celulaTopico.setHorizontalAlignment(Element.ALIGN_LEFT);
        celulaTopico.setBorder(Rectangle.TOP);
        celulaTopico.setBorderColor(BaseColor.LIGHT_GRAY);
        tabelaTopico.addCell(celulaTopico);
        
        _pdf.add(tabelaTopico);
    }
    
    private void InserirEspacamento() throws DocumentException {
        PdfPTable tabelaEspacamento = new PdfPTable(1);
        PdfPCell celula = new PdfPCell();
        
        celula.setFixedHeight(10);
        celula.setBorder(0);
        tabelaEspacamento.addCell(celula);
        
        _pdf.add(tabelaEspacamento);
    }
}
