package AntonioRodrigues.Automacao.Web.Exemplo.Base;

import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;

import AntonioRodrigues.Automacao.Logs.Log;
import AntonioRodrigues.Automacao.Web.Core.InteracoesTela;
import AntonioRodrigues.Automacao.Web.Core.LocalizaPor;
import AntonioRodrigues.Automacao.Web.Exemplo.Util.FabricaEntidades;
import AntonioRodrigues.Automacao.Web.Exemplo.Util.RegistroEvidencia;

@SuppressWarnings("static-access")
public class TelaBase extends InteracoesTela {
	public TelaBase(){
		super(FabricaEntidades.ObterHostExecucaoFirefox());
	}
	
	public boolean ConsultarExistenciaTela(LocalizaPor localizaPor, String localizador) throws Exception {
		if(!super.AguardarPorExistencia(localizaPor, localizador, 3, 5)){
			this.RegistrarFalhaExecucao(super.MensagemErro);
			throw new Exception(super.MensagemErro);
		}

		return true;
	}
	
	public boolean ConsultarExistenciaTela(Map<String, LocalizaPor> localizadores) throws Exception {
		boolean retorno = false;
		
		for(Map.Entry<String, LocalizaPor> localizador : localizadores.entrySet())
			if(retorno |= super.AguardarPorExistencia(localizador.getValue(), localizador.getKey(), 3, 5))
				break;
		
		if(!retorno){
			this.RegistrarFalhaExecucao(super.MensagemErro);
			throw new Exception(super.MensagemErro);
		}
		
		return retorno;
	}
	
	public void RegistrarFalhaExecucao(String mensagemErro){
		RegistroEvidencia.RegistrarFalhaExecucao("Falha na execucao", mensagemErro, super.CapturarPrint());
		this.FecharAplicacao();
	}

	@Override
    public boolean FecharAplicacao() {
		boolean retorno = super.FecharAplicacao();
		Log.ArquivoLogNome = null;
		return retorno;
    }
	
	public String CapturarPrint(){
		return super.CapturarPrint();
	}
	
	public boolean RolarParaBaixo() {
		JavascriptExecutor js = (JavascriptExecutor)super.WebDriver;
		js.executeScript("window.scrollBy(0,1000)");
		return true;
	}
}
