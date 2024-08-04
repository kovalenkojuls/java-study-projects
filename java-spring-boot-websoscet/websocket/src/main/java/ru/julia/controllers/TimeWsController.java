package ru.julia.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import ru.julia.ApplConfig;

@Controller
public class TimeWsController {
    private final SimpMessagingTemplate template;

    public TimeWsController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @Scheduled(fixedDelay = 1000)
    public void broadcastCurrentTime() {
        template.convertAndSend(
                "/topic/currentTime", LocalDateTime.now().format(DateTimeFormatter.ofPattern(ApplConfig.DATE_TIME_FORMAT)));
    }
}
