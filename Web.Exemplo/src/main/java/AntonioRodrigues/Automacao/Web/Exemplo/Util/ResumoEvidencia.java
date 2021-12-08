package AntonioRodrigues.Automacao.Web.Exemplo.Util;

import java.text.SimpleDateFormat;
import java.util.List;

import AntonioRodrigues.Automacao.Evidencias.PDF.Cabecalho;
import AntonioRodrigues.Automacao.Evidencias.PDF.Resumo;
import AntonioRodrigues.Automacao.Web.Exemplo.ExecuteCucumber;

public class ResumoEvidencia {
	private Resumo _resumo;

	public String EvidenciaNomeArquivo = "";
	public String EvidenciaCaminho = "";
	
	public ResumoEvidencia(List<List<String>> dados){
		if(null != dados){
			_resumo = new Resumo();
			
			_resumo.IdDemanda = dados.get(1).get(0);
			_resumo.Sprint = dados.get(1).get(1);
			_resumo.Sistema = dados.get(1).get(2);
			_resumo.DescricaoCenario = dados.get(1).get(3);
			_resumo.ResultadoEsperado = dados.get(1).get(4);
			_resumo.ResultadoObtido = dados.get(1).get(4);
//			_resumo.ResultadoEsperado = _resumo.ResultadoObtido = dados.get(1).get(4);
			_resumo.ExecucaoData= new SimpleDateFormat("dd/MM/yyyy").format(new java.util.Date());
			_resumo.ExecucaoHorario = new SimpleDateFormat("HH:mm:ss").format(new java.util.Date());
			_resumo.Dispositivo = "Navegador";
			_resumo.Versao = "1";
			_resumo.Status = "Sucesso";
			_resumo.TipoTeste = "Automatizado";
			
			this.EvidenciaCaminho = new ExecuteCucumber().LocalExecucaoEvidencia;
			this.EvidenciaNomeArquivo = _resumo.IdDemanda.concat("_").concat(new SimpleDateFormat("yyyyMMddHHmmssSS").format(new java.util.Date())).concat(".pdf");
//			this.EvidenciaNomeArquivo = _resumo.IdDemanda.concat("_").concat(ExecuteCucumber.Ambiente).concat("_").concat(new SimpleDateFormat("yyyyMMddHHmmssSS").format(new java.util.Date())).concat(".pdf");
		}
	}

	public Cabecalho ObterCabecalho(){
		Cabecalho retorno = new Cabecalho();
		
		retorno.CaminhoLogo1 = System.getProperty("user.dir").concat(GerenciadorConfiguracoes.ObterConfig("local.logo1"));
		retorno.CaminhoLogo2 = System.getProperty("user.dir").concat(GerenciadorConfiguracoes.ObterConfig("local.logo2"));
		retorno.Titulo = "Evidencia de Teste";
		
		return retorno;		
	}

	public Resumo ObterResumo() {
		return _resumo;
	} 
}
