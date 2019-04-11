package de.destrukt.sapconnection;

import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.ext.DestinationDataProvider;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sulzbachr
 */
public class SAPConnection {

    private JCoDestination destination;

    public SAPConnection() {
        SAPConnectionProperties sapc = new SAPConnectionProperties();
        try {
            Properties connectProperties = new Properties();
            connectProperties.setProperty(DestinationDataProvider.JCO_ASHOST, sapc.getSAPHost());
            connectProperties.setProperty(DestinationDataProvider.JCO_SYSNR, sapc.getSAPSystem());
            connectProperties.setProperty(DestinationDataProvider.JCO_CLIENT, sapc.getSAPClient());
            connectProperties.setProperty(DestinationDataProvider.JCO_USER, sapc.getSAPUser());
            connectProperties.setProperty(DestinationDataProvider.JCO_PASSWD, sapc.getSAPPassword());
            connectProperties.setProperty(DestinationDataProvider.JCO_LANG, sapc.getSAPLanguage());

            connectProperties.setProperty(DestinationDataProvider.JCO_POOL_CAPACITY, "5");
            connectProperties.setProperty(DestinationDataProvider.JCO_PEAK_LIMIT, "10");

            createDestinationDataFile(sapc.getSAPDestination(), connectProperties);
            destination = JCoDestinationManager.getDestination(sapc.getSAPDestination());
        } catch (JCoException ex) {
            Logger.getLogger(SAPConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public SAPConnection(String propsfile) {
        SAPConnectionProperties sapc = new SAPConnectionProperties(propsfile);
        try {
            Properties connectProperties = new Properties();
            connectProperties.setProperty(DestinationDataProvider.JCO_ASHOST, sapc.getSAPHost());
            connectProperties.setProperty(DestinationDataProvider.JCO_SYSNR, sapc.getSAPSystem());
            connectProperties.setProperty(DestinationDataProvider.JCO_CLIENT, sapc.getSAPClient());
            connectProperties.setProperty(DestinationDataProvider.JCO_USER, sapc.getSAPUser());
            connectProperties.setProperty(DestinationDataProvider.JCO_PASSWD, sapc.getSAPPassword());
            connectProperties.setProperty(DestinationDataProvider.JCO_LANG, sapc.getSAPLanguage());

            connectProperties.setProperty(DestinationDataProvider.JCO_POOL_CAPACITY, "5");
            connectProperties.setProperty(DestinationDataProvider.JCO_PEAK_LIMIT, "10");

            createDestinationDataFile(sapc.getSAPDestination(), connectProperties);
            destination = JCoDestinationManager.getDestination(sapc.getSAPDestination());
        } catch (JCoException ex) {
            Logger.getLogger(SAPConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public SAPConnection(String propsfile, String destinationname) {
        SAPConnectionProperties sapc = new SAPConnectionProperties(propsfile);
        try {
            Properties connectProperties = new Properties();
            connectProperties.setProperty(DestinationDataProvider.JCO_ASHOST, sapc.getSAPHost());
            connectProperties.setProperty(DestinationDataProvider.JCO_SYSNR, sapc.getSAPSystem());
            connectProperties.setProperty(DestinationDataProvider.JCO_CLIENT, sapc.getSAPClient());
            connectProperties.setProperty(DestinationDataProvider.JCO_USER, sapc.getSAPUser());
            connectProperties.setProperty(DestinationDataProvider.JCO_PASSWD, sapc.getSAPPassword());
            connectProperties.setProperty(DestinationDataProvider.JCO_LANG, sapc.getSAPLanguage());

            connectProperties.setProperty(DestinationDataProvider.JCO_POOL_CAPACITY, "5");
            connectProperties.setProperty(DestinationDataProvider.JCO_PEAK_LIMIT, "10");

            createDestinationDataFile(destinationname, connectProperties);
            destination = JCoDestinationManager.getDestination(destinationname);
        } catch (JCoException ex) {
            Logger.getLogger(SAPConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public JCoDestination getDestination() {
        return destination;
    }

    static void createDestinationDataFile(String destinationName, Properties properties) {
        File destCfg = new File(destinationName + ".jcoDestination");
        try {
            FileOutputStream fos = new FileOutputStream(destCfg, false);
            properties.store(fos, "for tests only!");
            fos.close();
        } catch (Exception e) {
            throw new RuntimeException("Unable to create the destination file", e);
        }
    }
}
