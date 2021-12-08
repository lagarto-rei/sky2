package AntonioRodrigues.Automacao.Evidencias.PDF;

import java.util.HashMap;
import java.util.Map;

public class HostExecucao {
	public String Url = "";
	public HostExecucao.Proxy Proxy = new Proxy();
	public Map<String, String> Capabilities = new HashMap<String, String>();
	
	public class Proxy {
		public Integer HostPort = 0;
		public String HostName = "";
		public String UserLogin = "";
		public String UserDomain = "";
		public String UserPassword = "";
	}
}
