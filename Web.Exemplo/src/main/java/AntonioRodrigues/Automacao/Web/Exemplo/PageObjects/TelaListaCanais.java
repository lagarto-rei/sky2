package AntonioRodrigues.Automacao.Web.Exemplo.PageObjects;

import AntonioRodrigues.Automacao.Web.Core.LocalizaPor;
import AntonioRodrigues.Automacao.Web.Core.TeclasEspeciais;
import AntonioRodrigues.Automacao.Web.Exemplo.Base.TelaBase;
import AntonioRodrigues.Automacao.Web.Exemplo.Mapeamentos.TelaListaCanaisMap;

public class TelaListaCanais extends TelaBase {
	public boolean ConsultarExistenciaTela() throws Exception {
//		super.AguardarPor(4);
		
		return super.ConsultarExistenciaTela(LocalizaPor.AcessibilityId, TelaListaCanaisMap.CampoFiltroCanais.toString());
	}
	
	public boolean InserirTextoFiltroCanais(String texto) {
		super.InserirTexto(LocalizaPor.AcessibilityId, TelaListaCanaisMap.CampoFiltroCanais.toString(), texto);
		return super.PressionarTeclasEspeciais(LocalizaPor.AcessibilityId, TelaListaCanaisMap.CampoFiltroCanais.toString(), TeclasEspeciais.Enter);
	}
	
	public String ObterTextoCanalFiltrado() {
		return super.ObterTexto(LocalizaPor.Xpath, TelaListaCanaisMap.TextoCanalFiltrado.toString());
	}
}
