package AntonioRodrigues.Automacao.Web.Exemplo.PageObjects;

import AntonioRodrigues.Automacao.Web.Core.LocalizaPor;
import AntonioRodrigues.Automacao.Web.Exemplo.Base.TelaBase;
import AntonioRodrigues.Automacao.Web.Exemplo.Mapeamentos.TelaProgramacaoMap;

public class TelaProgramacao extends TelaBase {
	public boolean ConsultarExistenciaTela() throws Exception {
		super.AguardarPor(4);
		
		return super.ConsultarExistenciaTela(LocalizaPor.Xpath, TelaProgramacaoMap.BotaoListaCanais.toString());
	}

	public boolean ClicarBotaoListaCanais() {
		return super.ClicarBotao(LocalizaPor.Xpath, TelaProgramacaoMap.BotaoListaCanais.toString());
	}
}
