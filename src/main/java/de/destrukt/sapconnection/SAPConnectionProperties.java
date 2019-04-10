package de.destrukt.sapconnection;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sulzbachr
 * 
 */

public class SAPConnectionProperties {
    HashedProperties props = null;
    private String SAPHost;
    private String SAPSystem;
    private String SAPClient;
    private String SAPUser;
    private String SAPPassword;
    private String SAPLanguage;
    private String SAPDestination;

    public String getSAPHost() {
        return SAPHost;
    }

    public String getSAPSystem() {
        return SAPSystem;
    }

    public String getSAPClient() {
        return SAPClient;
    }

    public String getSAPUser() {
        return SAPUser;
    }

    public String getSAPPassword() {
        return SAPPassword;
    }

    public String getSAPLanguage() {
        return SAPLanguage;
    }

    public String getSAPDestination() {
        return SAPDestination;
    }
   
    public SAPConnectionProperties() {
        try {
            props = new HashedProperties().load("sapconnection.props");
            setProps();
        } catch (IOException ex) {
            Logger.getLogger(SAPConnectionProperties.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   public SAPConnectionProperties(String propsfile) {
        try {
            props = new HashedProperties().load(propsfile);
            setProps();
        } catch (IOException ex) {
            Logger.getLogger(SAPConnectionProperties.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
   private void setProps() {
        SAPHost = props.getProperty("SAPHOST");
        SAPSystem = props.getProperty("SAPSYSTEM");
        SAPClient = props.getProperty("SAPCLIENT");
        SAPUser = props.getProperty("SAPUSER");
        SAPPassword = props.getProperty("SAPPW");
        SAPLanguage = props.getProperty("SAPLANGUAGE");
        SAPDestination = props.getProperty("SAPDESTINATION");
   }
}
