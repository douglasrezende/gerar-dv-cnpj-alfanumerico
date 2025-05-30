package com.douglas.dv.cnpj.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesCNPJ {
	
	
	public static String getProperty(String key, String propertiesFilename) throws Exception {
		
		try (InputStream input = PropertiesCNPJ.class.getClassLoader().getResourceAsStream(propertiesFilename)) {

            Properties prop = new Properties();

            if (input == null) {
                throw new Exception("Sorry, unable to find tabela.properties");
            }

            //load a properties file from class path, inside static method
            prop.load(input);

            //get the property value 
    		return 	prop.getProperty(key);


        } catch (IOException ex) {
            ex.printStackTrace();
        }
		
		return "";		
	}

}
