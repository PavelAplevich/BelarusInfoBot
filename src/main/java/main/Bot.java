package main;

import logics.ActionLogic;
import logics.GetToken;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

public class Bot extends TelegramLongPollingBot {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new Bot());

        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }

    public void sendInfo(SendMessage sendMessage) {
        try {
            execute(sendMessage.disableNotification());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendInfo(SendPhoto sendPhoto) {
        try {
            execute(sendPhoto.disableNotification());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendInfo(AnswerCallbackQuery answerCallbackQuery) {
        try {
            execute(answerCallbackQuery);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            try {
                Action.doAction(this, update.getMessage());
            } catch (NullPointerException e) {
                ActionLogic.getCity(this, update.getMessage());
            }
        } else if (update.hasCallbackQuery()) {
            sendInfo(new AnswerCallbackQuery().setCallbackQueryId(update.getCallbackQuery().getId()));
            try {
                Action.doCallBack(this, update.getCallbackQuery());
            } catch (NullPointerException e) {
                ActionLogic.getCity(this, update.getCallbackQuery().getMessage());
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "Belarus_info_bot";
    }

    @Override
    public String getBotToken() {
        return GetToken.getBotToken();
    }

}
