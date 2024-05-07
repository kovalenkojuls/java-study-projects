package com.telegrambot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.send.SendSticker;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public abstract class AbstractTelegramBot extends TelegramLongPollingBot {
    private String name;
    private String token;

    private ThreadLocal<Update> updateEvent = new ThreadLocal<>();
    private HashMap<Long, Integer> gloryStorage = new HashMap<>();

    private List<Message> sendMessages = new ArrayList<>();

    public AbstractTelegramBot(String name, String token) {
        this.name = name;
        this.token = token;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public String getBotUsername() {
        return name;
    }

    @Override
    public final void onUpdateReceived(Update updateEvent) {
        this.updateEvent.set(updateEvent);
        onUpdateEventReceived(this.updateEvent.get());
    }

    public abstract void onUpdateEventReceived(Update updateEvent);

    public Long getCurrentChatId() {
        if (updateEvent.get().hasMessage()) {
            return updateEvent.get().getMessage().getFrom().getId();
        }

        if (updateEvent.get().hasCallbackQuery()) {
            return updateEvent.get().getCallbackQuery().getFrom().getId();
        }

        return null;
    }

    public String getMessageText() {
        return updateEvent.get().hasMessage() ? updateEvent.get().getMessage().getText() : "";
    }

    public String getCallbackQueryButtonKey() {
        return updateEvent.get().hasCallbackQuery() ? updateEvent.get().getCallbackQuery().getData() : "";
    }

    public void sendTextMessageAsync(String text) {
        try {
            SendMessage message = createMessage(text);
            var task = sendApiMethodAsync(message);
            this.sendMessages.add(task.get());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void sendTextMessageAsync(String text, Map<String, String> buttons) {
        try {
            SendMessage message = createMessage(text, buttons);
            var task = sendApiMethodAsync(message);
            this.sendMessages.add(task.get());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void sendPhotoMessageAsync(String photoKey) {
        SendPhoto photo = createPhotoMessage(photoKey);
        executeAsync(photo);
    }

    public void sendStickerMessageAsync(String file_id) {
        InputFile sticker = new InputFile();
        sticker.setMedia(file_id);
        executeAsync(new SendSticker(getCurrentChatId().toString(), sticker));
    }

    public void sendImageMessageAsync(String imagePath) {
        SendPhoto photo = createPhotoMessage(Paths.get(imagePath));
        executeAsync(photo);
    }

    public SendMessage createMessage(String text) {
        SendMessage message = new SendMessage();
        message.setText(new String(text.getBytes(), StandardCharsets.UTF_8));
        message.setParseMode("markdown");
        Long chatId = getCurrentChatId();
        message.setChatId(chatId);
        return message;
    }

    public SendMessage createMessage(String text, Map<String, String> buttons) {
        SendMessage message = createMessage(text);
        if (buttons != null && !buttons.isEmpty())
            attachButtons(message, buttons);
        return message;
    }

    private void attachButtons(SendMessage message, Map<String, String> buttons) {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        for (String buttonName : buttons.keySet()) {
            String buttonValue = buttons.get(buttonName);

            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(new String(buttonName.getBytes(), StandardCharsets.UTF_8));
            button.setCallbackData(buttonValue);

            keyboard.add(List.of(button));
        }

        markup.setKeyboard(keyboard);
        message.setReplyMarkup(markup);
    }

    public SendPhoto createPhotoMessage(String name) {
        try {
            var is = ClassLoader.getSystemResourceAsStream(STR."images/\{name}.jpg");
            return createPhotoMessage(is);
        } catch (Exception e) {
            throw new RuntimeException("Can't create photo message!");
        }
    }

    public SendPhoto createPhotoMessage(Path path) {
        try {
            var is = Files.newInputStream(path);
            return createPhotoMessage(is);
        } catch (IOException e) {
            throw new RuntimeException("Can't create image message!");
        }
    }

    public Message getLastSentMessage() {
        if (this.sendMessages.isEmpty()) return null;
        return this.sendMessages.getLast();
    }

    public List<Message> getAllSentMessages() {
        return this.sendMessages;
    }

    public void editTextMessageAsync(Integer messageId, String text) {
        try {
            EditMessageText editMessageText = new EditMessageText();
            editMessageText.setChatId(getCurrentChatId());
            editMessageText.setMessageId(messageId);
            editMessageText.setText(text);
            executeAsync(editMessageText);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setUserGlory(int glories) {
        gloryStorage.put( getCurrentChatId(), glories);
    }

    public int getUserGlory() {
        return gloryStorage.getOrDefault( getCurrentChatId(), 0);
    }

    public void addUserGlory(int glories) {
        gloryStorage.put(getCurrentChatId(), getUserGlory() + glories);
    }

    private SendPhoto createPhotoMessage(InputStream inputStream) {
        try {
            InputFile inputFile = new InputFile();
            inputFile.setMedia(inputStream, name);

            SendPhoto photo = new SendPhoto();
            photo.setPhoto(inputFile);
            Long chatId = getCurrentChatId();
            photo.setChatId(chatId);
            return photo;
        } catch (Exception e) {
            throw new RuntimeException("Can't create photo message!");
        }
    }

    public String getUserInfo() {
        User user = updateEvent.get().getMessage().getFrom();
        String info = STR."ID: \{user.getId()}\n";

        if (user.getFirstName() != null) info += STR."Имя: \{user.getFirstName()}\n";
        if (user.getLastName() != null) info += STR."Фамилия: \{user.getLastName()}\n";
        if (user.getUserName() != null) info += STR."Username: \{user.getUserName().replaceAll("_", "\\\\_")}\n";
        if (user.getIsPremium() != null) info += STR."Premium: \{user.getIsPremium() ? "да" : "нет"}\n";
        if (user.getLanguageCode() != null) info += STR."Язык: \{user.getLanguageCode()}\n";

        return info;
    }

    public void sendFileIdBySticker() {
        String f_id = updateEvent.get().getMessage().getSticker().getFileId();
        f_id = f_id.replaceAll("_", "\\\\_");
        sendTextMessageAsync(STR."file\\_id=\{f_id}");
    }

    public void sendAnswerCallbackQuery() {
        try {
            execute(new AnswerCallbackQuery(updateEvent.get().getCallbackQuery().getId()));
        } catch (TelegramApiException e) {
            throw new RuntimeException("Can't send answer callback query!");
        }
    }

    public void deleteMessageAsync(int messageId) {
        try {
            DeleteMessage deleteMessage = new DeleteMessage(getCurrentChatId().toString(), messageId);
            execute(deleteMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException("Can't delete message!");
        }
    }

    public void deleteLastMessageButtons() {
        Message lastMessage = getLastSentMessage();
        EditMessageReplyMarkup editedMessage = new EditMessageReplyMarkup(
                lastMessage.getChatId().toString(),
                lastMessage.getMessageId(),
                null,
                null);

        try {
            executeAsync(editedMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void printLog(String first_name, String last_name, String user_id, String txt, String bot_answer) {
        System.out.println("\n ----------------------------");
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        System.out.println(STR."Message from \{first_name} \{last_name}. (id = \{user_id}) \n Text - \{txt}");
        System.out.println(STR."Bot answer: \n Text - \{bot_answer}");
    }
}
