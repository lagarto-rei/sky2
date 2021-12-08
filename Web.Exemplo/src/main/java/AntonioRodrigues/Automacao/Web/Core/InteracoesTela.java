package AntonioRodrigues.Automacao.Web.Core;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import AntonioRodrigues.Automacao.Logs.Log;

public class InteracoesTela {
    public static WebDriver WebDriver;
    public static String MensagemErro;

    public InteracoesTela(HostExecucao hostAutomacao) {
        DefinirArquivoLog();
        DefinirDriverPlataforma(hostAutomacao);
    }

    public InteracoesTela(HostExecucao hostAutomacao, String caminhoArquivoLog, String nomeArquivoLog) {
        DefinirArquivoLog(caminhoArquivoLog, nomeArquivoLog);
        DefinirDriverPlataforma(hostAutomacao);
    }

    public InteracoesTela(Navegador webDriverAlvo) {
        DefinirArquivoLog();
        if (null == WebDriver) WebDriver = FabricaWebDriver.CriarWebDriver(webDriverAlvo);
    }

    public InteracoesTela(Navegador webDriverAlvo, String caminhoWebDriver) {
        DefinirArquivoLog();
        if (null == WebDriver) WebDriver = FabricaWebDriver.CriarWebDriver(webDriverAlvo, caminhoWebDriver);
    }

    protected Elemento ObterElemento(LocalizaPor tipoLocalizador, String localizador) {
        Elemento retorno = new Elemento();

        try {
            Log.LogarInfo(MessageFormat.format("Buscando elemento --> Tipo Localizador: {0}. Localizador: {1}.", tipoLocalizador, localizador));

            By criterioPesquisa = null;

            switch (tipoLocalizador) {
                case AcessibilityId:
                    criterioPesquisa = By.id(localizador);
                    break;
                case Xpath:
                    criterioPesquisa = By.xpath(localizador);
                    break;
            }

            retorno.Instancia = WebDriver.findElement(criterioPesquisa);
        } catch (Exception ex) {
            retorno.Existe = false;
            Log.LogarErro(MensagemErro = ex.toString());
        }

        Log.LogarInfo(MessageFormat.format("Elemento encontrado: {0}}", retorno.Existe.toString()));

        return retorno;
    }

    protected Elementos ObterElementos(LocalizaPor tipoLocalizador, String localizador) {
        Elementos retorno = new Elementos();

        try {
            Log.LogarInfo(MessageFormat.format("Buscando elementos --> Tipo Localizador: {0}. Localizador: {1}.", tipoLocalizador, localizador));

            By criterioPesquisa = null;

            switch (tipoLocalizador) {
                case AcessibilityId:
                    criterioPesquisa = By.id(localizador);
                    break;
                case Xpath:
                    criterioPesquisa = By.xpath(localizador);
                    break;
            }

            retorno.Instancias = WebDriver.findElements(criterioPesquisa);
        } catch (Exception ex) {
            retorno.Existem = false;
            Log.LogarErro(MensagemErro = ex.toString());
        }

        Log.LogarInfo(MessageFormat.format("Elemento encontrado: {0}}", retorno.Existem.toString()));

        return retorno;
    }

    public boolean FecharAplicacao() {
        boolean retorno = true;

        Log.LogarInfo("Fechando Aplicacao");

        try {
            retorno = (WebDriver != null);
//            WebDriver.quit();
            WebDriver.close();
            WebDriver = null;
        } catch (Exception ex) {
            retorno = false;
            Log.LogarErro(MensagemErro = ex.toString());
        }

        return retorno;
    }

    public String CapturarPrint() {
        String retorno = "";

        try {
            retorno = ((TakesScreenshot) WebDriver).getScreenshotAs(OutputType.FILE).getAbsolutePath();

            Log.LogarInfo("Capturando print --> ".concat(retorno));
        } catch (Exception ex) {
            Log.LogarErro("Falha ao tentar capturar print.");
            Log.LogarErro(ex.toString());
        }

        return retorno;
    }

    /// <summary>
    /// Pausa a execução do programa por 2 segundos. Util para sincronia aguardando o carregamento de tela.
    /// </summary>
    public static void Aguardar() {
        AguardarPor(2);
    }

    /// <summary>
    /// Pausa a execução do programa. Util para sincronia aguardando o carregamento de tela.
    /// </summary>
    /// <param name="periodoEspera">Segundos de pausa</param>
    public static boolean AguardarPor(int periodoEspera) {
        boolean retorno = true;

        periodoEspera = periodoEspera * 1000;

        Log.LogarInfo("Fechando Aplicacao");

        try {
            Thread.sleep(periodoEspera);
        } catch (Exception ex) {
            retorno = false;
            Log.LogarErro(MensagemErro = ex.toString());
        }

        return retorno;
    }

    public boolean SelecionarAba(int indice) {
        boolean retorno;

        Log.LogarInfo("Selecionando a aba: ".concat(String.valueOf(indice)));

        ArrayList < String > tabs = new ArrayList < String > (WebDriver.getWindowHandles());

        if (retorno = tabs.size() >= indice) WebDriver.switchTo().window(tabs.get(indice));

        return retorno;
    }

    protected boolean AguardarPorExibicao(LocalizaPor tipoLocalizador, String localizador, int periodoEspera, int tentativas) {
        boolean retorno = true;

        AguardarPor(periodoEspera);

        do retorno = this.ConsultarExistencia(tipoLocalizador, localizador);
        while (!retorno && --tentativas < 0 && AguardarPor(periodoEspera));

        return retorno | tentativas < 0;
    }

    protected boolean AguardarVisibilidade(LocalizaPor tipoLocalizador, String localizador) {
        boolean retorno = true;
        Elemento elemento = this.ObterElemento(tipoLocalizador, localizador);

        Log.LogarInfo("Verificando visibilidade do elemento: ".concat(localizador));

        try {
            WebDriverWait webDriverWait = new WebDriverWait(WebDriver, 5);
            webDriverWait.until(ExpectedConditions.visibilityOf(elemento.Instancia));
            retorno = elemento.Instancia.isDisplayed();
        } catch (Exception ex) {
            Log.LogarErro(MensagemErro = ex.toString());
            retorno = false;
        }

        return elemento.Existe & retorno;
    }

    protected boolean AguardarVisibilidade(LocalizaPor tipoLocalizador, String localizador, int segundos) {
        boolean retorno = true;
        Elemento elemento = this.ObterElemento(tipoLocalizador, localizador);

        Log.LogarInfo("Verificando visibilidade do elemento: ".concat(localizador));

        try {
            WebDriverWait webDriverWait = new WebDriverWait(WebDriver, segundos);
            webDriverWait.until(ExpectedConditions.visibilityOf(elemento.Instancia));
            retorno = elemento.Instancia.isDisplayed();
        } catch (Exception ex) {
            Log.LogarErro(MensagemErro = ex.toString());
            retorno = false;
        }

        return elemento.Existe & retorno;
    }

    protected boolean AguardarElementoManipulavel(LocalizaPor tipoLocalizador, String localizador) {
        boolean retorno = true;
        Elemento elemento = this.ObterElemento(tipoLocalizador, localizador);

        Log.LogarInfo("Verificando visibilidade do elemento: ".concat(localizador));

        try {
            WebDriverWait webDriverWait = new WebDriverWait(WebDriver, 10);
            webDriverWait.until(ExpectedConditions.elementToBeClickable(elemento.Instancia));
            retorno = elemento.Instancia.isEnabled();
        } catch (TimeoutException ex) {
            Log.LogarErro(MensagemErro = ex.toString());
            retorno = false;
        }

        return elemento.Existe & retorno;
    }

    protected boolean AguardarElementoManipulavel(LocalizaPor tipoLocalizador, String localizador, int segundos) {
        boolean retorno = true;
        Elemento elemento = this.ObterElemento(tipoLocalizador, localizador);

        Log.LogarInfo("Verificando visibilidade do elemento: ".concat(localizador));

        try {
            WebDriverWait webDriverWait = new WebDriverWait(WebDriver, segundos);
            webDriverWait.until(ExpectedConditions.elementToBeClickable(elemento.Instancia));
            retorno = elemento.Instancia.isEnabled();
        } catch (TimeoutException ex) {
            Log.LogarErro(MensagemErro = ex.toString());
            retorno = false;
        }

        return elemento.Existe & retorno;
    }

    protected boolean AguardarPorExistencia(LocalizaPor tipoLocalizador, String localizador, int tentativas, int segundos) {
        Log.LogarInfo(MessageFormat.format("Aguardando existencia elemento --> Tipo Localizador: {0}; Localizador: {1}; tentativas: {2}; segundos: {3}.", tipoLocalizador, localizador, Integer.toString(tentativas), Long.toString(segundos)));

        boolean retorno = ObterElemento(tipoLocalizador, localizador).Existe;

        Log.LogarInfo("Elemento encontrado apos pausa: ".concat(Boolean.toString(retorno)));

        for (int i = 0; tentativas > i; i++)
            if (!retorno) {
                AguardarPor(segundos);
                retorno = ObterElemento(tipoLocalizador, localizador).Existe;
                Log.LogarInfo("Elemento encontrado apos pausa: ".concat(Boolean.toString(retorno)));
            }

        return retorno;
    }

    protected int ContarElementos(LocalizaPor tipoLocalizador, String localizador) {
        Log.LogarInfo("Realizando contagem de elementos: ".concat(localizador));

        int retorno = 0;
        Elementos elementos = ObterElementos(tipoLocalizador, localizador);

        if (elementos.Existem) retorno = elementos.Instancias.size();

        Log.LogarInfo("Elementos encontrados: ".concat(String.valueOf(retorno)));

        return retorno;
    }

    protected boolean FocarElemento(LocalizaPor tipoLocalizador, String localizador) {
        Log.LogarInfo("Focando no elemento: ".concat(localizador));

        Elemento elemento = this.ObterElemento(tipoLocalizador, localizador);

        Log.LogarInfo("Verificando visibilidade do elemento: ".concat(localizador));

        if (elemento.Existe)((JavascriptExecutor) WebDriver).executeScript("arguments[0].scrollIntoView(true);", elemento.Instancia);

        return elemento.Existe;
    }

    protected boolean ConsultarExistencia(LocalizaPor tipoLocalizador, String localizador) {
        Log.LogarInfo("Consultando existencia do elemento: ".concat(localizador));
        return ObterElemento(tipoLocalizador, localizador).Existe;
    }

    protected boolean ClicarBotao(LocalizaPor tipoLocalizador, String localizador) {
        Log.LogarInfo("Clicando no botao: ".concat(localizador));
        Elemento retorno = this.ObterElemento(tipoLocalizador, localizador);

        if (retorno.Existe) retorno.Instancia.click();

        return retorno.Existe;
    }

    protected boolean ClicarLink(LocalizaPor tipoLocalizador, String localizador) {
        Log.LogarInfo("Clicando no link: ".concat(localizador));
        Elemento retorno = this.ObterElemento(tipoLocalizador, localizador);

        if (retorno.Existe) retorno.Instancia.click();

        return retorno.Existe;
    }

    protected boolean ClicarElemento(LocalizaPor tipoLocalizador, String localizador) {
        Log.LogarInfo("Clicando no elemento: ".concat(localizador));
        Elemento retorno = this.ObterElemento(tipoLocalizador, localizador);

        if (retorno.Existe) retorno.Instancia.click();

        return retorno.Existe;
    }

    protected boolean InserirTexto(LocalizaPor tipoLocalizador, String localizador, String texto) {
        Log.LogarInfo("Inserindo texto no campo: ".concat(localizador));
        Elemento retorno = this.ObterElemento(tipoLocalizador, localizador);

        if (retorno.Existe) {
            retorno.Instancia.clear();
            retorno.Instancia.sendKeys(texto);
        }

        return retorno.Existe;
    }

    protected boolean ObterEstadoElementoHabilitado(LocalizaPor tipoLocalizador, String localizador) {
        Log.LogarInfo("Obtendo estado do elemento: ".concat(localizador));
        Elemento retorno = this.ObterElemento(tipoLocalizador, localizador);

        if (retorno.Existe) retorno.Existe = retorno.Instancia.isEnabled();

        Log.LogarInfo("Estado do elemento habilitado: ".concat(String.valueOf(retorno.Existe)));

        return retorno.Existe;
    }

    protected boolean ObterEstadoElementoHabilitadoPorIndice(LocalizaPor tipoLocalizador, String localizador, int indice) {
        Log.LogarInfo("Obtendo estado do elemento: ".concat(localizador));
        boolean retorno = false;
        Elementos elementos = ObterElementos(tipoLocalizador, localizador);

        if (elementos.Existem && elementos.Instancias.size() >= indice) retorno = elementos.Instancias.get(indice).isEnabled();

        Log.LogarInfo("Estado do elemento habilitado: ".concat(String.valueOf(elementos.Existem & retorno)));

        return elementos.Existem & retorno;
    }

    public boolean PressionarEnter(LocalizaPor tipoLocalizador, String localizador) {
        Log.LogarInfo("Pressionando a tecla Enter");
        return PressionarTeclasEspeciais(tipoLocalizador, localizador, TeclasEspeciais.Enter);
    }

    protected boolean PressionarTeclasEspeciais(LocalizaPor tipoLocalizador, String localizador, TeclasEspeciais teclaEspecial) {
        Log.LogarInfo("Pressionando a tecla: ".concat(teclaEspecial.toString()));
        Elemento elemento = this.ObterElemento(tipoLocalizador, localizador);

        if (elemento.Existe) elemento.Instancia.sendKeys(teclaEspecial.toString());

        return elemento.Existe;
    }

    protected String ObterTexto(LocalizaPor tipoLocalizador, String localizador) {
        Log.LogarInfo("Obtendo texto: ".concat(localizador));
        String retorno = "";
        Elemento elemento = this.ObterElemento(tipoLocalizador, localizador);

        if (elemento.Existe) retorno = elemento.Instancia.getText();

        Log.LogarInfo("Texto obtido: ".concat(retorno));

        return retorno;
    }

    protected boolean AlternarFrameset(LocalizaPor tipoLocalizador, String localizador) {
        Log.LogarInfo("Alterando Frameset: ".concat(localizador));
        Elemento elemento = this.ObterElemento(tipoLocalizador, localizador);

        if (elemento.Existe) WebDriver.switchTo().frame(elemento.Instancia);

        return elemento.Existe;
    }

    //  protected boolean CarregarUploadArquivo(LocalizaPor tipoLocalizador, String localizador, String pasta, String nomeArquivoComExtensao)
    //  {
    //	  boolean retorno = false;
    //
    //      if (this.ConsultarExistencia(tipoLocalizador, localizador))
    //      {
    //          if (!pasta.endsWith("\\"))
    //              pasta = pasta.concat("\\");
    //
    //          if (nomeArquivoComExtensao.startsWith("\\"))
    //              nomeArquivoComExtensao = nomeArquivoComExtensao.replace("\\", "");
    //
    //          this.ClicarElemento(tipoLocalizador, localizador);
    //
    ////          AutoIt.AutoItX.ControlFocus("Abrir", "", "Edit1");
    ////          Thread.Sleep(3000);
    ////          AutoIt.AutoItX.ControlSetText("Abrir", "", "Edit1", String.Concat(pasta, nomeArquivoComExtensao));
    ////          Thread.Sleep(3000);
    ////          AutoIt.AutoItX.ControlClick("Abrir", "", "Button1");
    //      }
    //
    //      return retorno;
    //  }

    private void DefinirArquivoLog(String caminhoArquivoLog, String nomeArquivoLog) {
        if ((null == Log.ArquivoLogNome || "" == Log.ArquivoLogNome.trim())) Log.DefinirLocalAquivoLog(caminhoArquivoLog, nomeArquivoLog);
    }

    private void DefinirArquivoLog() {
        if ((null == Log.ArquivoLogNome || "" == Log.ArquivoLogNome.trim())) Log.DefinirLocalAquivoLog(null, "Inmetrics.Automacao.Mobile.Core_".concat(new SimpleDateFormat("yyyyMMddHHmmssSS").format(new java.util.Date())).concat(".log"));
    }

    private void DefinirDriverPlataforma(HostExecucao hostExecucao) {
        if (null == WebDriver) {
            try {
                WebDriver = FabricaWebDriver.CriarWebDriver(hostExecucao);
            } catch (Exception ex) {
                Log.LogarErro(MensagemErro = ex.toString());
            }
        }
    }

    //    public boolean EnviarPostServico(String url)
    //    {
    //        return new Core.Servicos.Rest().EnviarPost<object>(url, Servicos.NetTcpEnum.Json, null);
    //    }
    //
    //    public boolean EnviarPostServico<T>(String url, T dadosEnvio)
    //    {
    //        return new Core.Servicos.Rest().EnviarPost<T>(url, Servicos.NetTcpEnum.Json, dadosEnvio);
    //    }
    //
}
