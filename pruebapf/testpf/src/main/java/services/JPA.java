package services;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPA {

    private static final String PERSISTENCE = "bandelsal";
    private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE);

    public static EntityManagerFactory getEntityManagerFactory(){
        return factory;
    }

    public static void shutdown(){
        if (factory != null){
            factory.close();
        }
    }
}

