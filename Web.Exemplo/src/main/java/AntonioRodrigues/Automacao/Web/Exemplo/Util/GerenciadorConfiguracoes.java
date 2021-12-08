package AntonioRodrigues.Automacao.Web.Exemplo.Util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GerenciadorConfiguracoes {
	public static String ObterConfig(String chaveConfig){
		String retorno = "";
		
		try (InputStream input = new FileInputStream(System.getProperty("user.dir").concat("\\config.properties"))) {
            Properties prop = new Properties();

            prop.load(input);

            retorno = prop.getProperty(chaveConfig); 

        } catch (IOException ex) {
            ex.printStackTrace();
        }
		
		return retorno;
	} 
}