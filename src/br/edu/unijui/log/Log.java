/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.unijui.log;

import br.edu.unijui.model.dao.LivroImpl;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.XMLFormatter;

/**
 *
 * @author chayk
 */
public class Log {

    private static Logger logger = Logger.getLogger(LivroImpl.class.getPackage().getName());

    public void GravaLog(String nivel, String mensagem) {
        try {
            Properties conf = getLogConf();
            Handler handler;

            handler = new FileHandler(conf.getProperty("arquivo"), Boolean.parseBoolean(conf.getProperty("acrescentar")));

            Logger.getLogger("").addHandler(handler);

            // formato de saída 
            if (conf.getProperty("formato-saida").equalsIgnoreCase("XML")) {
                handler.setFormatter(new XMLFormatter());
            } else if (conf.getProperty("formato-saida").equalsIgnoreCase("SIMPLE")) {
                handler.setFormatter(new SimpleFormatter());
            }

            // opções de console
            if (Boolean.parseBoolean(conf.getProperty("suprimir-saida-console"))) {
                Logger l = Logger.getLogger("");

                Handler[] handlers = l.getHandlers();

                if (handlers[0] instanceof ConsoleHandler) {
                    l.removeHandler(handlers[0]);
                }
            }
            
            Level lv = null;

            if (nivel.equalsIgnoreCase("SEVERE")) {
                lv = Level.SEVERE;
                logger.severe(mensagem);
            } else if (nivel.equalsIgnoreCase("WARNING")) {
                lv = Level.WARNING;
                logger.warning(mensagem);
            } else if (nivel.equalsIgnoreCase("INFO")) {
                lv = Level.INFO;
                logger.info(mensagem);
            } else if (nivel.equalsIgnoreCase("CONFIG")) {
                lv = Level.CONFIG;
                logger.config(mensagem);
            } else if (nivel.equalsIgnoreCase("FINE")) {
                lv = Level.FINE;
                logger.fine(mensagem);
            } else if (nivel.equalsIgnoreCase("FINER")) {
                lv = Level.FINER;
                 logger.finer(mensagem);
            }
            
        } catch (IOException ex1) {
            Logger.getLogger(LivroImpl.class.getName()).log(Level.SEVERE, null, ex1);
        } catch (SecurityException ex1) {
            Logger.getLogger(LivroImpl.class.getName()).log(Level.SEVERE, null, ex1);
        }
    }

    private static Properties getLogConf() {
        Properties conf = new Properties();

        conf.setProperty("arquivo", "Logs.log");
        conf.setProperty("add", "true");
        conf.setProperty("suprimir-saida-console", "false");
        conf.setProperty("nivel", "INFO");
        conf.setProperty("formato-saida", "XML");

        return conf;
    }
}
