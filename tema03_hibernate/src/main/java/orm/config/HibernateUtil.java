package orm.config;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Statement;
import java.util.Objects;

/**
 * Utilidad para la gestión de la {@link SessionFactory} de Hibernate.
 * Proporciona métodos para obtener la {@link SessionFactory}, construir la {@link SessionFactory} y ejecutar un script SQL inicial.
 * Esta clase es crucial para la configuración inicial de Hibernate y asegura que se maneje de manera eficiente la creación de la {@link SessionFactory}.
 *
 * @author <a href="https://about.me/prof.guazina">Fauno Guazina</a>
 */
public class HibernateUtil {

    static {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(java.util.logging.Level.SEVERE);
    }

    public static SessionFactory getSessionFactory() throws ExceptionInInitializerError {
        return sessionFactory;
    }

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Ejecuta un script SQL inicial para configurar la base de datos.
     * Este método es útil para preparar la base de datos con datos necesarios antes de iniciar la aplicación.
     */
    public static void executeInitSqlScript() {
        Session session = getSessionFactory().openSession();
        session.doWork(connection -> {
            try {
                String pathToSqlScript = Objects.requireNonNull(
                        HibernateUtil.class.getClassLoader().getResource("init.sql")).getPath();
                String sqlScript = new String(Files.readAllBytes(Paths.get(pathToSqlScript)));

                try (Statement statement = connection.createStatement()) {
                    for (String sqlStatement : sqlScript.split(";")) {
                        if (!sqlStatement.trim().isEmpty()) {
                            statement.execute(sqlStatement);
                        }
                    }
                }
            } catch (IOException e) {
                System.err.println("Failed to read init.sql: " + e.getMessage());
            }
        });
        session.close();
    }

}

