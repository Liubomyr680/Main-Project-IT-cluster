package forest.detector;

import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.WebResourceSet;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.realm.DataSourceRealm;
import org.apache.catalina.session.JDBCStore;
import org.apache.catalina.session.PersistentManager;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.valves.PersistentValve;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.EmptyResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import org.apache.tomcat.util.descriptor.web.ContextResource;
import org.apache.tomcat.util.descriptor.web.LoginConfig;
import org.apache.tomcat.util.scan.StandardJarScanFilter;
import org.apache.tomcat.util.scan.StandardJarScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Launcher {

    private static Logger log = LoggerFactory.getLogger(Launcher.class);

    public static void main(String[] args) throws Exception {

//        File root = getRootFolder();
////        System.setProperty("org.apache.catalina.startup.EXIT_ON_INIT_FAILURE", "true");
////        Tomcat tomcat = new Tomcat();
////        Path tempPath = Files.createTempDirectory("tomcat-base-dir");
////        tomcat.setBaseDir(tempPath.toString());
////
////        //The port that we should run on can be set into an environment variable
////        //Look for that variable and default to 8080 if it isn't there.
////        String webPort = System.getenv("PORT");
////        if (webPort == null || webPort.isEmpty()) {
////            webPort = "8081";
////        }
////
////        tomcat.setPort(Integer.parseInt(webPort));
////        tomcat.getConnector();
////        File webContentFolder = new File(root.getAbsolutePath(), "target/classes");
////        if (!webContentFolder.exists()) {
////            webContentFolder = Files.createTempDirectory("default-doc-base").toFile();
////        }
////        StandardContext ctx = (StandardContext) tomcat.addWebapp("", webContentFolder.getAbsolutePath());
////        //Set execution independent of current thread context classloader (compatibility with exec:java mojo)
////        ctx.setParentClassLoader(Launcher.class.getClassLoader());
////        // hide warning message
////        StandardJarScanFilter jarScanFilter = new StandardJarScanFilter();
////        jarScanFilter.setDefaultPluggabilityScan(false);
////        jarScanFilter.setDefaultTldScan(false);
////        StandardJarScanner standardJarScanner = new StandardJarScanner();
////        standardJarScanner.setJarScanFilter(jarScanFilter);
////        ctx.setJarScanner(standardJarScanner);
////        log.info("configuring app with basedir: " + webContentFolder.getAbsolutePath());
////
////        // Declare an alternative location for your "WEB-INF/classes" dir
////        // Servlet 3.0 annotation will work
////        File additionWebInfClassesFolder = new File(root.getAbsolutePath(), "target/classes");
////        WebResourceRoot resources = new StandardRoot(ctx);
////
////        WebResourceSet resourceSet;
////        if (additionWebInfClassesFolder.exists()) {
////            resourceSet = new DirResourceSet(resources, "/WEB-INF/classes", additionWebInfClassesFolder.getAbsolutePath(), "/");
////            log.info("loading WEB-INF resources from as '" + additionWebInfClassesFolder.getAbsolutePath() + "'");
////        } else {
////            resourceSet = new EmptyResourceSet(resources);
////        }
////        resources.addPreResources(resourceSet);
////        ctx.setResources(resources);
////
////        serverConfiguration(ctx, tomcat);
////
////        tomcat.start();
////        tomcat.getServer().await();
////    }
////
////    /** YOUR CHANGES HERE **/
////    private static void serverConfiguration(StandardContext ctx, Tomcat tomcat) {
////        // server configs
//////        tomcat.getConnector().setAttribute("maxThreads", 2);
////
////        // context configs
////        ctx.setSessionTimeout(30);
////    }
////
////    private static File getRootFolder() {
////        try {
////            File root;
////            String runningJarPath = Launcher.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath().replaceAll("\\\\", "/");
////            int lastIndexOf = runningJarPath.lastIndexOf("/target/");
////            if (lastIndexOf < 0) {
////                root = new File("");
////            } else {
////                root = new File(runningJarPath.substring(0, lastIndexOf));
////            }
////            log.info("application resolved root folder: " + root.getAbsolutePath());
////            return root;
////        } catch (URISyntaxException ex) {
////            log.error(ex.getMessage(), ex);
////            throw new RuntimeException(ex);
////        }
////    }


        File root = getRootFolder();
        System.setProperty("org.apache.catalina.startup.EXIT_ON_INIT_FAILURE", "true");
        Tomcat tomcat = new Tomcat();
        Path tempPath = Files.createTempDirectory("tomcat-base-dir");
        tomcat.setBaseDir(tempPath.toString());

        //The port that we should run on can be set into an environment variable
        //Look for that variable and default to 8080 if it isn't there.
        String webPort = System.getenv("PORT");
        if (webPort == null || webPort.isEmpty()) {
            webPort = "8080";
        }

        tomcat.setPort(Integer.parseInt(webPort));
        // connector configurations
        // https://www.eclipse.org/virgo/documentation/virgo-documentation-3.6.4.RELEASE/docs/virgo-user-guide/html/ch13s07.html
        tomcat.getConnector();

        File webContentFolder = new File(root.getAbsolutePath(), "target/classes");
        if (!webContentFolder.exists()) {
            webContentFolder = Files.createTempDirectory("default-doc-base").toFile();
        }
        StandardContext ctx = (StandardContext) tomcat.addWebapp("", webContentFolder.getAbsolutePath());
        //Set execution independent of current thread context classloader (compatibility with exec:java mojo)
        ctx.setParentClassLoader(Launcher.class.getClassLoader());
        // hide warning message
        StandardJarScanFilter jarScanFilter = new StandardJarScanFilter();
        jarScanFilter.setDefaultPluggabilityScan(false);
        jarScanFilter.setDefaultTldScan(false);
        StandardJarScanner standardJarScanner = new StandardJarScanner();
        standardJarScanner.setJarScanFilter(jarScanFilter);
        ctx.setJarScanner(standardJarScanner);
        log.info("configuring app with basedir: " + webContentFolder.getAbsolutePath());

        // Declare an alternative location for your "WEB-INF/classes" dir
        // Servlet 3.0 annotation will work
        File additionWebInfClassesFolder = new File(root.getAbsolutePath(), "target/classes");
        WebResourceRoot resources = new StandardRoot(ctx);

        WebResourceSet resourceSet;
        if (additionWebInfClassesFolder.exists()) {
            resourceSet = new DirResourceSet(resources, "/WEB-INF/classes", additionWebInfClassesFolder.getAbsolutePath(), "/");
            log.info("loading WEB-INF resources from as '" + additionWebInfClassesFolder.getAbsolutePath() + "'");
        } else {
            resourceSet = new EmptyResourceSet(resources);
        }
        resources.addPreResources(resourceSet);
        ctx.setResources(resources);

        serverConfiguration(ctx, tomcat);

        tomcat.start();
        tomcat.getServer().await();
    }

    /** YOUR CHANGES HERE **/
    private static void serverConfiguration(StandardContext ctx, Tomcat tomcat) throws URISyntaxException {
        // server configs
        tomcat.getConnector().setAttribute("maxThreads", 200);
        tomcat.enableNaming();

        URI dbUri = new URI(System.getenv("DATABASE_URL"));
        log.debug(dbUri.toString());
        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String connectionURL = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();
        log.debug(connectionURL);

        // https://liferay.dev/blogs/-/blogs/tomcat-hikaricp
        // https://tomcat.apache.org/tomcat-9.0-doc/jdbc-pool.html
        ContextResource dataSource = new ContextResource();
        dataSource.setName("hikariDataSource");
        dataSource.setAuth("Container");
        dataSource.setProperty("factory", "com.zaxxer.hikari.HikariJNDIFactory");
        dataSource.setType("javax.sql.DataSource");
        dataSource.setProperty("minimumIdle", "1"); // min connection pool
        dataSource.setProperty("maximumPoolSize", "2"); // max connection pool
        dataSource.setProperty("connectionTimeout", "300000"); // miliseconds, cannot be less than 250ms
        dataSource.setProperty("dataSourceClassName", "org.postgresql.ds.PGSimpleDataSource");
        dataSource.setProperty("dataSource.url", connectionURL);
        dataSource.setProperty("dataSource.user", username);
        dataSource.setProperty("dataSource.password", password);

        tomcat.getServer().getGlobalNamingResources().addResource(dataSource);
        ctx.getNamingResources().addResource(dataSource);

        // https://tomcat.apache.org/tomcat-9.0-doc/realm-howto.html
        // https://tomcat.apache.org/tomcat-9.0-doc/config/realm.html
        // https://javapipe.com/blog/tomcat-realm/
        // jdbc realm configuration
//        JDBCRealm jdbcRealm = new JDBCRealm();
//        jdbcRealm.setConnectionURL(connectionURL);
//        jdbcRealm.setConnectionName(username);
//        jdbcRealm.setConnectionPassword(password);
//        jdbcRealm.setDriverName("org.postgresql.Driver");
//        jdbcRealm.setUserTable("users");
//        jdbcRealm.setUserNameCol("user_name");
//        jdbcRealm.setUserCredCol("user_passwd");
//        jdbcRealm.setUserRoleTable("user_roles");
//        jdbcRealm.setRoleNameCol("role_name");

        // datasource realm configuration
        DataSourceRealm dataSourceRealm = new DataSourceRealm();
        dataSourceRealm.setDataSourceName("hikariDataSource");
        dataSourceRealm.setLocalDataSource(true);
        dataSourceRealm.setUserTable("users");
        dataSourceRealm.setUserNameCol("user_name");
        dataSourceRealm.setUserCredCol("user_passwd");
        dataSourceRealm.setUserRoleTable("user_roles");
        dataSourceRealm.setRoleNameCol("role_name");

        // session management
        ctx.setSessionTimeout(1);
//        ctx.setCookies(false);

        // https://tomcat.apache.org/tomcat-9.0-doc/config/manager.html
        PersistentManager persistentManager = new PersistentManager();
        persistentManager.setMaxIdleBackup(1);
        persistentManager.setMinIdleSwap(0);
        persistentManager.setMaxIdleSwap(1);
        persistentManager.setProcessExpiresFrequency(1);
        persistentManager.setSaveOnRestart(true);
        JDBCStore jdbcStore = new JDBCStore();
        jdbcStore.setDataSourceName("hikariDataSource");
        jdbcStore.setSessionTable("tomcat_sessions");
        jdbcStore.setSessionIdCol("session_id");
        jdbcStore.setSessionValidCol("valid_sessions");
        jdbcStore.setSessionMaxInactiveCol("max_inactive");
        jdbcStore.setSessionLastAccessedCol("last_access");
        jdbcStore.setSessionAppCol("app_name");
        jdbcStore.setSessionDataCol("session_data");
        persistentManager.setStore(jdbcStore);

        ctx.setManager(persistentManager);

        PersistentValve persistentValve = new PersistentValve();
        tomcat.getHost().getPipeline().addValve(persistentValve);

        // custom realm
        // https://dzone.com/articles/how-to-implement-a-new-realm-in-tomcat

        // LockOutRealm - provide a user lock out mechanism if there are
        // too many failed authentication attempts in a given period of time.
//        ctx.setRealm(jdbcRealm);
        ctx.setRealm(dataSourceRealm);

        // https://docs.oracle.com/cd/E19226-01/820-7627/bncbn/index.html
        LoginConfig loginConfig = new LoginConfig();
        // FORM (jsp) - https://stackoverflow.com/questions/11382159/tomcat-7-form-based-authentification
        // FORM (html) - https://docs.oracle.com/cd/E19226-01/820-7627/bncby/index.html
        // Digest - https://docs.oracle.com/cd/E19226-01/820-7627/bncbw/index.html
        // CLIENT-CERT - https://docs.oracle.com/cd/E19226-01/820-7627/bncbs/index.html

        // http://www.mtitek.com/tutorials/samples/tomcat-form-auth.php
        loginConfig.setAuthMethod("BASIC");
//        loginConfig.setRealmName("DataSourceRealm");
//        loginConfig.setLoginPage("/login");
//        loginConfig.setLoginPage("/html/login.html");
//        loginConfig.setErrorPage("/html/login.html");
        ctx.setLoginConfig(loginConfig);

        //

        // SSL/TSL
        // https://stackoverflow.com/questions/31033751/embedded-tomcat-enable-ssl
        // http://tomcat.apache.org/tomcat-9.0-doc/ssl-howto.html
    }

    private static File getRootFolder() {
        try {
            File root;
            String runningJarPath = Launcher.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath().replaceAll("\\\\", "/");
            int lastIndexOf = runningJarPath.lastIndexOf("/target/");
            if (lastIndexOf < 0) {
                root = new File("");
            } else {
                root = new File(runningJarPath.substring(0, lastIndexOf));
            }
            log.info("application resolved root folder: " + root.getAbsolutePath());
            return root;
        } catch (URISyntaxException ex) {
            log.error(ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }
    }
}
