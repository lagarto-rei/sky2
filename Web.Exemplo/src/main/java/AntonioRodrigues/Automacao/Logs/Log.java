package AntonioRodrigues.Automacao.Logs;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Log {
	public static String ArquivoLogPasta = null;
	public static String ArquivoLogNome = null;
	
	public static void DefinirLocalAquivoLog(){
		RegistrarPastaArquivoLog(null);
		RegistrarNomeArquivoLog(null);
	}
	
	public static void DefinirLocalAquivoLog(String arquivoLogPasta){
		RegistrarPastaArquivoLog(arquivoLogPasta);
		RegistrarNomeArquivoLog(null);
	}
	
	public static void DefinirLocalAquivoLog(String arquivoLogPasta, String arquivoLogNome){
		RegistrarPastaArquivoLog(arquivoLogPasta);
		RegistrarNomeArquivoLog(arquivoLogNome);
	}
	
	private static void RegistrarPastaArquivoLog(String arquivoLogPasta){
		if(null == arquivoLogPasta || "" == arquivoLogPasta.trim())
			ArquivoLogPasta = System.getProperty("user.dir").concat("\\").concat("Log");
		else if(arquivoLogPasta.contains(":"))
			ArquivoLogPasta = arquivoLogPasta;
		else if(null == ArquivoLogPasta || "" == ArquivoLogPasta.trim())
			ArquivoLogPasta = System.getProperty("user.dir").concat("\\").concat(arquivoLogPasta);
	}
	
	private static void RegistrarNomeArquivoLog(String arquivoLogNome){
		if(null == arquivoLogNome || "" == arquivoLogNome.trim())
			ArquivoLogNome = "Log_".concat(new SimpleDateFormat ("yyyyMMddHHmmssSS").format(new java.util.Date())).concat(".log");
		else if(null == ArquivoLogNome || "" == ArquivoLogNome.trim())
			ArquivoLogNome = (arquivoLogNome.endsWith(".log") ? arquivoLogNome : arquivoLogNome.concat(".log"));
	}
	
	public static void LogarInfo(String textoLog) {
		RegistrarLog("Info", textoLog);
	}
	
	public static void LogarErro(String textoLog) {
		RegistrarLog("Erro", textoLog);
	}
	
	private static void RegistrarLog(String tipo, String mensagem) {
		try {
		    Path path = Paths.get(ObterEnderecoArquivoLog());
		    List<String> linhas;
        
			linhas = Files.readAllLines(path);
			
			String mensagemFormatada = FormatarMensagem(tipo, mensagem);
			
			System.out.println(mensagemFormatada);
			
	        linhas.add(mensagemFormatada);
	
	        Files.write(path, linhas);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String FormatarMensagem(String tipo, String mensagem) {
		StringBuilder retorno = new StringBuilder();
		
		retorno.append("[" + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()) + "] ");
		retorno.append("[" + tipo + "] ");
		retorno.append(mensagem);
		
		return retorno.toString();
	}
	
	private static String ObterEnderecoArquivoLog() throws IOException {		
		new java.io.File(ArquivoLogPasta).mkdirs();
		
		String enderecoArquivoLog = ArquivoLogPasta.concat("\\").concat(ArquivoLogNome);
		
		if(!new File(enderecoArquivoLog).exists()){
			FileWriter arq = new FileWriter(enderecoArquivoLog);
			arq.close();
		}
		
		return enderecoArquivoLog;
	}
}
