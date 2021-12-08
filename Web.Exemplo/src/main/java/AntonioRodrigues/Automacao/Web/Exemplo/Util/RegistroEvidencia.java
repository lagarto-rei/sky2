package AntonioRodrigues.Automacao.Web.Exemplo.Util;

import AntonioRodrigues.Automacao.Evidencias.PDF.EvidenciaPDF;
import AntonioRodrigues.Automacao.Logs.Log;

public class RegistroEvidencia {
	public static EvidenciaPDF Evidencia;
	
	public static void RegistrarFalhaExecucao(String resultadoObtido, String mensagemErro, String caminhoEvidencia){
		try {
			Log.LogarErro("Falha na execucao");
			Log.LogarErro(resultadoObtido);
			Log.LogarErro(mensagemErro);
			
			Evidencia.Resumo.ResultadoObtido = resultadoObtido;
			Evidencia.Resumo.Status = "Falhou";
			Evidencia.InserirEvidencia(caminhoEvidencia, null, mensagemErro);
			Evidencia.GravarPDF();
		} catch (Exception ex) {
			Log.LogarErro("Falha ao gravar o documento de evidencia.");
			Log.LogarErro(ex.toString());
		}
	}
	
	public static void GravarDocumentoEvidencia(){
		try {
			Evidencia.GravarPDF();
		} catch (Exception ex) {
			Log.LogarErro("Falha ao gravar o documento de evidencia.");
			Log.LogarErro(ex.toString());
		}
	}
	
	public static void InserirPrintEvidencia(String caminhoPrintEvidencia){
		try {
			Evidencia.InserirEvidencia(caminhoPrintEvidencia, null, null);
		} catch (Exception ex) {
			Log.LogarErro("Falha ao gravar o documento de evidencia.");
			Log.LogarErro(ex.toString());
		}
	}
	
	public static void InserirPrintEvidenciaInfo(String caminhoPrintEvidencia, String descricaoPasso){
		try {
			Evidencia.InserirEvidencia(caminhoPrintEvidencia, descricaoPasso, null);
		} catch (Exception ex) {
			Log.LogarErro("Falha ao gravar o documento de evidencia.");
			Log.LogarErro(ex.toString());
		}
	}
	
	public static void InserirPrintEvidenciaErro(String caminhoPrintEvidencia, String descricaoFalha){
		try {
			Evidencia.InserirEvidencia(caminhoPrintEvidencia, null, descricaoFalha);
		} catch (Exception ex) {
			Log.LogarErro("Falha ao gravar o documento de evidencia.");
			Log.LogarErro(ex.toString());
		}
	}
	
	public static void InserirMassa(String chave, String valor){
		try {
			Log.LogarInfo("Massa: ".concat(chave).concat(": ").concat(valor));
			Evidencia.Massa.InserirParametro(chave, valor);
		} catch (Exception ex) {
			Log.LogarErro("Falha ao gravar o documento de evidencia.");
			Log.LogarErro(ex.toString());
		}
	}
}
