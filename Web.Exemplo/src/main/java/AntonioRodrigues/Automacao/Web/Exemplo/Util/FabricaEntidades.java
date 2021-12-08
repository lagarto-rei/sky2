package AntonioRodrigues.Automacao.Web.Exemplo.Util;

import AntonioRodrigues.Automacao.Web.Core.HostExecucao;
import AntonioRodrigues.Automacao.Web.Core.Navegador;
import AntonioRodrigues.Automacao.Web.Exemplo.Entidades.AutomacaoEntidade;
import AntonioRodrigues.Automacao.Web.Exemplo.MassaDados.MassaDados;

public class FabricaEntidades {
	public static AutomacaoEntidade ObterAutomacaoEntidade(){
		AutomacaoEntidade retorno = new AutomacaoEntidade();
		org.json.simple.JSONObject jsonObject = null;
		
		try {
			jsonObject= new MassaDados().ObterJson(System.getProperty("user.dir") + GerenciadorConfiguracoes.ObterConfig("MassaDadosJson"));
		} catch (Exception e) {
			System.out.println(e);
		}

		retorno.ParametroConsultaCanal = (String)jsonObject.get("ParametroConsultaCanal");

		return retorno;
	} 
	
	public static HostExecucao ObterHostExecucaoFirefox(){
		HostExecucao retorno = new HostExecucao();

		retorno.Navegador = Navegador.MozillaFirefox;
		retorno.Url = "http://www.sky.com.br/";

		return retorno;
	} 
}
