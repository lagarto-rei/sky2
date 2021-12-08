package AntonioRodrigues.Automacao.Web.Exemplo.MassaDados;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class MassaDados {
	public JSONObject ObterJson(String localArquivo) throws FileNotFoundException, IOException, ParseException {
		JSONParser jsonParser = new JSONParser();
		
		JSONObject retorno = (JSONObject) jsonParser.parse(new FileReader(localArquivo));
		
		return retorno;
	}
}
