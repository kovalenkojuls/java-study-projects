package ru.julia;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.julia.dao.InMemoryUserDao;
import ru.julia.dao.UserDao;
import ru.julia.server.UsersWebServer;
import ru.julia.server.UsersWebServerSimple;
import ru.julia.services.TemplateProcessor;
import ru.julia.services.TemplateProcessorImpl;

/*
    http://localhost:8080
    http://localhost:8080/users
    http://localhost:8080/api/user/3
*/
public class WebServerSimpleDemo {
    private static final int WEB_SERVER_PORT = 8080;
    private static final String TEMPLATES_DIR = "/templates/";

    public static void main(String[] args) throws Exception {
        UserDao userDao = new InMemoryUserDao();
        Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
        TemplateProcessor templateProcessor = new TemplateProcessorImpl(TEMPLATES_DIR);

        UsersWebServer usersWebServer = new UsersWebServerSimple(WEB_SERVER_PORT, userDao, gson, templateProcessor);

        usersWebServer.start();
        usersWebServer.join();
    }
}
