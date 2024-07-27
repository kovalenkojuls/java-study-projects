package ru.julia;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.net.URI;
import org.eclipse.jetty.security.HashLoginService;
import org.eclipse.jetty.security.LoginService;
import org.eclipse.jetty.util.resource.PathResourceFactory;
import org.eclipse.jetty.util.resource.Resource;
import ru.julia.dao.InMemoryUserDao;
import ru.julia.dao.UserDao;
import ru.julia.helpers.FileSystemHelper;
import ru.julia.server.UsersWebServer;
import ru.julia.server.UsersWebServerWithBasicSecurity;
import ru.julia.services.TemplateProcessor;
import ru.julia.services.TemplateProcessorImpl;

/*
    http://localhost:8080
    http://localhost:8080/users
    http://localhost:8080/api/user/3
*/
public class WebServerWithBasicSecurityDemo {
    private static final int WEB_SERVER_PORT = 8080;
    private static final String TEMPLATES_DIR = "/templates/";
    private static final String HASH_LOGIN_SERVICE_CONFIG_NAME = "realm.properties";
    private static final String REALM_NAME = "AnyRealm";

    public static void main(String[] args) throws Exception {
        UserDao userDao = new InMemoryUserDao();
        Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
        TemplateProcessor templateProcessor = new TemplateProcessorImpl(TEMPLATES_DIR);

        String hashLoginServiceConfigPath =
                FileSystemHelper.localFileNameOrResourceNameToFullPath(HASH_LOGIN_SERVICE_CONFIG_NAME);
        PathResourceFactory pathResourceFactory = new PathResourceFactory();
        Resource configResource = pathResourceFactory.newResource(URI.create(hashLoginServiceConfigPath));

        LoginService loginService = new HashLoginService(REALM_NAME, configResource);
        // LoginService loginService = new InMemoryLoginServiceImpl(userDao); // NOSONAR

        UsersWebServer usersWebServer =
                new UsersWebServerWithBasicSecurity(WEB_SERVER_PORT, loginService, userDao, gson, templateProcessor);

        usersWebServer.start();
        usersWebServer.join();
    }
}
