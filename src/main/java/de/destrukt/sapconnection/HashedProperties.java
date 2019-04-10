package de.destrukt.sapconnection;

import java.util.HashMap;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sulzbachr
 * HashedProperties ist eine Hilfsklasse, mit der eine HasheMap mit den Werten 
 * einer Property Datei befüllt werden.
  */

public class HashedProperties extends java.util.Properties {
    HashMap hashedKeys = new HashMap();
    
    public HashedProperties() {
        super();
    }
    public HashedProperties(java.util.Properties defaults) {
        super(defaults);
    }
   
    public HashedProperties load(String name) throws IOException {
        HashedProperties props = null;

        java.io.InputStream is=new FileInputStream(System.getProperty("user.dir") + System.getProperty("file.separator") + name);	

        if ( null != is ) {
            props = new HashedProperties();
            props.load(is);
            return props;
        } else {
            if ( ! name.startsWith("/") ) {
                return load("/" + name);
            } else {
                throw new IOException("Properties could not be loaded.");
            }
        }
    }
    
    public void store(String name) {
        try {
            HashedProperties props = null;
            props = new HashedProperties();
            for (Object o : hashedKeys.keySet()) {
                String s = o.toString();
                props.put(s, hashedKeys.get(s).toString());
            }
            props.store(new FileOutputStream(System.getProperty("user.dir") + System.getProperty("file.separator") + name), null);
        } catch (IOException ex) {
            Logger.getLogger(HashedProperties.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
    @Override
    public synchronized Object put(Object key, Object value) {
        Object obj = super.put(key, value);

        hashedKeys.put(key, value);
        return obj;
    }
    
    @Override
    public synchronized Object remove(Object key) {
        Object obj = super.remove(key);

        hashedKeys.remove(key);
        return obj;
    }
}
 
