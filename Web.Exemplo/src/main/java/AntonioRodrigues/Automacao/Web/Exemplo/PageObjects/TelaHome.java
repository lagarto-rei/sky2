package AntonioRodrigues.Automacao.Web.Exemplo.PageObjects;

import AntonioRodrigues.Automacao.Web.Core.LocalizaPor;
import AntonioRodrigues.Automacao.Web.Exemplo.Base.TelaBase;
import AntonioRodrigues.Automacao.Web.Exemplo.Mapeamentos.TelaHomeMap;

@SuppressWarnings("static-access")
public class TelaHome extends TelaBase {
	public boolean ConsultarExistenciaTela() throws Exception {
		boolean retorno = false;

		retorno |= super.ConsultarExistenciaTela(LocalizaPor.Xpath, TelaHomeMap.BotaoFecharModal.toString());
		retorno |= super.ConsultarExistenciaTela(LocalizaPor.Xpath, TelaHomeMap.LinkProgramacao.toString());
		
		return retorno;
	}
	
	public boolean ClicarBotaoFecharModal() {
		return super.ClicarBotao(LocalizaPor.Xpath, TelaHomeMap.BotaoFecharModal.toString());
	}
	
	public boolean ClicarLinkProgramacao() {
		return super.ClicarBotao(LocalizaPor.Xpath, TelaHomeMap.LinkProgramacao.toString());
	}
	
	//-->>
	public boolean InserirTextoPesquisa(String texto) {
		return super.InserirTexto(LocalizaPor.Xpath, TelaHomeMap.CampoPesquisa.toString(), texto);
	}
	
	public boolean ClicarBotaoLogin() {
		return super.ClicarBotao(LocalizaPor.Xpath, TelaHomeMap.BotaoLogin.toString());
	}
	
	public boolean ClicarBotaoPesquisar() {
		return super.ClicarBotao(LocalizaPor.Xpath, TelaHomeMap.BotaoPesquisar.toString());
	}
}
