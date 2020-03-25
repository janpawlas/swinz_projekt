package cz.osu.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Timer;
import cz.osu.ApiData.DBRecorder;

public class WebAppServletContextListener implements ServletContextListener {

    // Public constructor is required by cz.osu.servlet spec
    public WebAppServletContextListener() {
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
        Timer timer = new Timer();
        timer.schedule(new DBRecorder(), 0, 300000);
    }

    public void contextDestroyed(ServletContextEvent sce) {
      /* This method is invoked when the Servlet Context 
         (the Web application) is undeployed or 
         Application Server shuts down.
      */
    }

}
