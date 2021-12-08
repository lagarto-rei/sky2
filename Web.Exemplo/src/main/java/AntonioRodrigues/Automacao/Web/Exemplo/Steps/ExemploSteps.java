package AntonioRodrigues.Automacao.Web.Exemplo.Steps;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;

import AntonioRodrigues.Automacao.Web.Exemplo.PageObjects.*;
import AntonioRodrigues.Automacao.Web.Exemplo.Util.FabricaEntidades;
import AntonioRodrigues.Automacao.Web.Exemplo.Util.RegistroEvidencia;
import AntonioRodrigues.Automacao.Web.Exemplo.Util.ResumoEvidencia;
import AntonioRodrigues.Automacao.Evidencias.PDF.EvidenciaPDF;
import AntonioRodrigues.Automacao.Logs.Log;
import AntonioRodrigues.Automacao.Web.Exemplo.ExecuteCucumber;
import AntonioRodrigues.Automacao.Web.Exemplo.Entidades.*;

import cucumber.annotation.pt.*;
import cucumber.table.DataTable;

@SuppressWarnings("static-access")
public class ExemploSteps {
	private TelaHome TelaHome = new TelaHome();
	private TelaListaCanais TelaListaCanais = new TelaListaCanais();
	private TelaProgramacao TelaProgramacao = new TelaProgramacao(); 
	private AutomacaoEntidade AutomacaoEntidade = FabricaEntidades.ObterAutomacaoEntidade();
	
	@Dado("^Que configuro o documento de evidencia da execucao$")
	public void Que_configuro_o_documento_de_evidencia_da_execucao(DataTable dataTable) throws Exception {
		try {
			if(null != dataTable){
				ResumoEvidencia resumoEvidencia = new ResumoEvidencia(dataTable.raw());

				Log.DefinirLocalAquivoLog(new ExecuteCucumber().LocalExecucaoLog);
				Log.LogarInfo("Dado Que configuro o documento de evidencia da execucao");

				RegistroEvidencia.Evidencia = new EvidenciaPDF(resumoEvidencia.EvidenciaCaminho, resumoEvidencia.EvidenciaNomeArquivo);
				RegistroEvidencia.Evidencia.Cabecalho = resumoEvidencia.ObterCabecalho();
				RegistroEvidencia.Evidencia.Resumo = resumoEvidencia.ObterResumo();
				RegistroEvidencia.Evidencia.Resumo.Ambiente = "Firefox";

				RegistroEvidencia.InserirMassa("URL", FabricaEntidades.ObterHostExecucaoFirefox().Url);
				RegistroEvidencia.InserirMassa("Parâmetro pesquisa canal", this.AutomacaoEntidade.ParametroConsultaCanal);
			}
		}
		catch(Exception ex){
			this.TelaHome.MensagemErro = ex.toString();
			RegistroEvidencia.RegistrarFalhaExecucao("Falha na execucao", this.TelaHome.MensagemErro, this.TelaHome.CapturarPrint());
			this.TelaHome.FecharAplicacao();
			Assert.fail(this.TelaHome.MensagemErro);
			throw new Exception(ex.toString()); 
		}
	}

	@Dado("^Estou na tela home do portal Sky$")
	public void Estou_na_tela_home_do_portal_Sky() {
		try{
			this.TelaHome.ConsultarExistenciaTela();
			RegistroEvidencia.InserirPrintEvidenciaInfo(this.TelaHome.CapturarPrint(), "Tela Home - Modal");
			this.TelaHome.ClicarBotaoFecharModal();
		}
		catch(Exception ex){
			this.TelaHome.RegistrarFalhaExecucao(ex.toString());							
			Assert.fail(ex.toString());
		}
	}

	@Quando("^Acesso a area de Programacao$")
	public void Acesso_a_area_de_Programacao() {
		try{
			this.TelaHome.ConsultarExistenciaTela();
			this.TelaHome.ClicarLinkProgramacao();
			RegistroEvidencia.InserirPrintEvidenciaInfo(this.TelaProgramacao.CapturarPrint(), "Tela Home");
		}
		catch(Exception ex){
			this.TelaProgramacao.RegistrarFalhaExecucao(ex.toString());							
			Assert.fail(ex.toString());
		}
	}

	@Quando("^Acesso a Lista de Canais$")
	public void Acesso_a_Lista_de_Canais() {
		try{
			this.TelaProgramacao.ConsultarExistenciaTela();
			this.TelaProgramacao.RolarParaBaixo();
			
			RegistroEvidencia.InserirPrintEvidenciaInfo(this.TelaProgramacao.CapturarPrint(), "Tela de Programação");
			
			this.TelaProgramacao.ClicarBotaoListaCanais();
		}
		catch(Exception ex){
			this.TelaProgramacao.RegistrarFalhaExecucao(ex.toString());							
			Assert.fail(ex.toString());
		} 
	}

	@Quando("^Aplico filtro na pesquisa de canais$")
	public void Aplico_filtro_na_pesquisa_de_canais() {
		try{
			this.TelaListaCanais.ConsultarExistenciaTela();
			RegistroEvidencia.InserirPrintEvidenciaInfo(this.TelaProgramacao.CapturarPrint(), "Tela Lista de Canais");
			this.TelaListaCanais.InserirTextoFiltroCanais(AutomacaoEntidade.ParametroConsultaCanal);
		}
		catch(Exception ex){
			this.TelaProgramacao.RegistrarFalhaExecucao(ex.toString());							
			Assert.fail(ex.toString());
		}
	}

	@Entao("^Verifico se o canal pesquisado e exibido na lista$")
	public void Verifico_se_o_canal_pesquisado_e_exibido_na_lista() {
		try{
			RegistroEvidencia.InserirPrintEvidenciaInfo(this.TelaListaCanais.CapturarPrint(), "Validando canal filtrado: ".concat(this.AutomacaoEntidade.ParametroConsultaCanal));
			
			assertEquals(this.AutomacaoEntidade.ParametroConsultaCanal, this.TelaListaCanais.ObterTextoCanalFiltrado());
			
			RegistroEvidencia.GravarDocumentoEvidencia();
			
			this.TelaListaCanais.FecharAplicacao();
		}
		catch(Exception ex){
			this.TelaListaCanais.RegistrarFalhaExecucao(ex.toString());							
			Assert.fail(ex.toString());
		}
	}
}
