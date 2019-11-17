package main;

import action.Action;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.util.List;

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

    public void sendMsg(SendMessage sendMessage) {
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage()){
            System.exit(12);
            CallbackQuery callbackQuery = update.getCallbackQuery();
            AnswerCallbackQuery answerCallbackQuery = new AnswerCallbackQuery();
            sendMsg(new SendMessage().setChatId(callbackQuery.getId()).setText(callbackQuery.getMessage().getText()));
            answerCallbackQuery.setText("Popo");
            answerCallbackQuery.setCallbackQueryId(callbackQuery.getId());
        }
    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {
            if(updates.get(0).hasCallbackQuery()){
                System.exit(13);
                CallbackQuery callbackQuery = updates.get(0).getCallbackQuery();
                sendMsg(new SendMessage().setChatId(callbackQuery.getId()).setText(callbackQuery.getMessage().getText()));
                AnswerCallbackQuery answerCallbackQuery = new AnswerCallbackQuery();
                answerCallbackQuery.setCallbackQueryId(callbackQuery.getId());
                answerCallbackQuery.setText("Hello!");
            } else {
                Message message = updates.get(0).getMessage();
                Action action = new Action();
                action.doAction(this, message);
            }

    }

    @Override
    public String getBotUsername() {
        return "Belarus_info_bot";
    }

    @Override
    public String getBotToken() {
        return "927060312:AAFL-khTKi00D070vZdhN_mS57sPnM55pP4";
    }
}
