package forest.detector.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

@WebListener
public class ContextListener implements ServletContextListener {

    @Resource(name = "hikariDataSource")
    DataSource dataSource;

    private static Logger log = LoggerFactory.getLogger(ContextListener.class);

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        // only once when applications is going to shut down
        log.debug("context destroyed");
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        // only once when applications is starting
        servletContextEvent.getServletContext().setAttribute("datasource", dataSource);
        log.debug("context initialized");
    }
}