package fi.jyu.tietokonekauppa;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigProperties {

    public static final ConfigProperties INSTANCE = new ConfigProperties();

    public String host;
    public String db_username;
    public String db_password;

    private ConfigProperties(){
        loadProperties();
    }

    private void loadProperties(){
        Properties prop = new Properties();
        InputStream input = null;

        try {

            String filename = "config.properties";
            input = getClass().getClassLoader().getResourceAsStream(filename);
            if(input==null){
                System.out.println("Sorry, unable to find " + filename);
                return;
            }

            //load a properties file from class path, inside static method
            prop.load(input);

            //get the property value
            host = prop.getProperty("host");
            db_username = prop.getProperty("db_username");
            db_password = prop.getProperty("db_password");

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally{
            if(input!=null){
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
