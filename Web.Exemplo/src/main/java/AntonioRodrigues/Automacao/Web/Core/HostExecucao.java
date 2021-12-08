package AntonioRodrigues.Automacao.Web.Core;

public class HostExecucao {
	
	public String Url = "";
	public AntonioRodrigues.Automacao.Web.Core.Navegador Navegador;
	public String CaminhoWebDriver = "";
	public HostExecucao.Proxy Proxy = new Proxy();
	
	public class Proxy {
		public Integer HostPort = 0;
		public String HostName = "";
		public String UserLogin = "";
		public String UserDomain = "";
		public String UserPassword = "";
	}
}
