package ru.otus;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.services.GameProcessor;

@SuppressWarnings({"squid:S125", "squid:S106"})
@ComponentScan
public class App {
    public static void main(String[] args) {
//        EquationPreparer equationPreparer = new EquationPreparerImpl();
//        IOService ioService = new IOServiceStreams(System.out, System.in);
//        PlayerService playerService = new PlayerServiceImpl(ioService);
//        GameProcessor gameProcessor = new GameProcessorImpl(ioService, equationPreparer, playerService);

//        ApplicationContext ctx = new ClassPathXmlApplicationContext("/spring-context.xml");
         ApplicationContext ctx = new AnnotationConfigApplicationContext(App.class);

         GameProcessor gameProcessor = ctx.getBean(GameProcessor.class);
         gameProcessor.startGame();
    }
}
