package dfcz.main.sourse;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;

public class Bot extends TelegramLongPollingBot {
    public static void main(String[] args) {
        ApiContextInitializer.init(); // ?????????????? ???
        TelegramBotsApi botApi = new TelegramBotsApi();
        try {
            botApi.registerBot(new Bot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            if (message.getText().equals("/help") || message.getText().equals("hi"))
                sendMsg(message, "Hi, right, look, I can say you date and time (/date).");
            else if (message.getText().equals("/date"))
                sendMsg(message, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            else
                sendMsg(message, "hmm, I don't know, what to say.");
        }
    }

    String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

    private void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public String getBotUsername() {
        return "UrFU Ft-202";
    }

    public String getBotToken() {
        return "616457548:AAEOiC4COqi8epMpNz9q-GhHx69hSPsiQFk";
    }
}
