package AntonioRodrigues.Automacao.Web.Exemplo;

import org.junit.runner.RunWith;

import AntonioRodrigues.Automacao.Web.Exemplo.Util.GerenciadorConfiguracoes;
import cucumber.junit.Cucumber; 

@RunWith(Cucumber.class)
@Cucumber.Options(
		features = "AntonioRodrigues/Automacao/Web/Exemplo/Features",
		format = {"pretty", "html:target/cucumber"},
		glue = "AntonioRodrigues.Automacao.Web.Exemplo.Steps",
		tags = {"@CTA0001"}
)

public class ExecuteCucumber {
	public String LocalExecucaoEvidencia;
	public String LocalExecucaoLog;

	public ExecuteCucumber() {
		LocalExecucaoEvidencia = System.getProperty("user.dir").concat(GerenciadorConfiguracoes.ObterConfig("local.evidencias"));
		LocalExecucaoLog = System.getProperty("user.dir").concat(GerenciadorConfiguracoes.ObterConfig("local.evidencias").concat("/Logs"));
	}
}
