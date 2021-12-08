package AntonioRodrigues.Automacao.Evidencias.PDF;

import java.util.HashMap;
import java.util.Map;

public class Massa {
	private Map<String, String> _listaMassa = new HashMap<String, String>();
	
	public void InserirParametro(String descricao, String valor) {
		this._listaMassa.put(descricao, valor);
	}
	
	public Map<String, String> ObterListaParametros(){
		return this._listaMassa;
	}
}
