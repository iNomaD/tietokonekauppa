package fi.jyu.tietokonekauppa.listeners;

import fi.jyu.tietokonekauppa.dao.SessionUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.metadata.ClassMetadata;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Map;

public class ApplicationStratup implements ServletContextListener {


    public void contextInitialized(ServletContextEvent arg0)
    {
        // prepare hibernate before jersey starts
        SessionUtil.getInstance();
        printManagedEntities();
    }


    public void contextDestroyed(ServletContextEvent arg0)
    {

    }

    public static void printManagedEntities(){
        final Session session = SessionUtil.getSession();
        try {
            System.out.println("querying all the managed entities...");
            final Map metadataMap = session.getSessionFactory().getAllClassMetadata();
            for (Object key : metadataMap.keySet()) {
                final ClassMetadata classMetadata = (ClassMetadata) metadataMap.get(key);
                final String entityName = classMetadata.getEntityName();
                final Query query = session.createQuery("from " + entityName);
                System.out.println("executing: " + query.getQueryString());
                for (Object o : query.list()) {
                    System.out.println("  " + o);
                }
            }
        } finally {
            session.close();
        }
    }
}