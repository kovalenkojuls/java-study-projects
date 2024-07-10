package com.telegrambot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Map;

import static com.telegrambot.ContentTelegramBot.*;
import static java.lang.StringTemplate.STR;

public class GameTelegramBot extends AbstractTelegramBot {
    public static final String NAME = "";//SET TELEGRAM BOT USERNAME
    public static final String TOKEN = "";//SET TELEGRAM BOT TOKEN

    public GameTelegramBot() {
        super(NAME, TOKEN);
    }

    public void checkButtonEvent(String valueBtn, int glory, String messageText, String imageName, Map<String, String> buttons) {
        if (valueBtn.equals(getCallbackQueryButtonKey())) {
            deleteMessageAsync(getLastSentMessage().getMessageId());
            if (glory > 0) addUserGlory(glory);
            if (imageName != null) sendPhotoMessageAsync(imageName);
            if (buttons != null) {
                sendTextMessageAsync(String.format(messageText, getUserGlory()), buttons);
            } else {
                sendTextMessageAsync(String.format(messageText, getUserGlory()));
            }
        }
    }

    @Override
    public void onUpdateEventReceived(Update updateEvent) {
        if ("/start".equals(getMessageText())) {
            setUserGlory(0);
            sendTextMessageAsync(STEP_1_TEXT, Map.of("Взлом холодильника", "step_1_btn"));
        }

        if ("/glory".equals(getMessageText())) {
            sendTextMessageAsync(STR."Накоплено: \{getUserGlory()} славы.");
        }

        checkButtonEvent("step_1_btn", 20, STEP_2_TEXT, null, Map.of(
                "Взять сосиску! +20 славы", "step_2_btn",
                "Взять рыбку! +20 славы", "step_2_btn",
                "Скинуть чашку! +20 славы", "step_2_btn"));

        checkButtonEvent("step_2_btn", 0, STEP_3_TEXT, null, Map.of(
                "Покататься на роботе!", "step_3_btn",
                "Убежать от робота!", "step_3_btn"));

        checkButtonEvent("step_3_btn", 30, STEP_4_TEXT, null, Map.of(
                "Съесть вкусняшек! +30 славы", "step_4_btn",
                "Поспать! +30 славы", "step_4_btn"));

        checkButtonEvent("step_4_btn", 0, STEP_5_TEXT, null, Map.of(
                "Надеть GoPro и включить!", "step_5_btn",
                "Уронить шкаф!", "step_5_btn"));

        checkButtonEvent("step_5_btn", 40, STEP_6_TEXT, null, Map.of(
                "Взять печеньки! +40 славы", "step_6_btn",
                "Поплавать в ванне! +40 славы", "step_6_btn",
                "Залипнуть в телек! +40 славы", "step_6_btn"));

        checkButtonEvent("step_6_btn", 0, STEP_7_TEXT, null, Map.of(
                "Залить отснятый материал!", "step_7_btn",
                "Посёрфить в сети!", "step_7_btn"));

        checkButtonEvent("step_7_btn", 50, STEP_8_TEXT, null, Map.of(
                "Обрадоваться!", "step_8_btn"));

        checkButtonEvent("step_8_btn", 0, FINAL_TEXT, "cat", null);
    }

    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new GameTelegramBot());
    }
}