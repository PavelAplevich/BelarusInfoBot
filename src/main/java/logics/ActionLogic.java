package logics;

import main.Bot;
import main.BotButtons;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class ActionLogic {

    public static void sayHello(Bot bot, Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("Вас приветствует BelarusInfoBot.\n" +
                "Используйте команду /help для помощи по боту.\n" +
                "Используйте команду /settings для настройки бота.\n" +
                "Выберите необходимый вам город из меню снизу \u2b07");
        sendMessage.setChatId(message.getChatId());
        BotButtons.setCityButtons(sendMessage);
        bot.sendInfo(sendMessage);
    }

    public static void settings() {
        // TODO: 12/3/19 Написать настройки бота
    }

    public static void showHelp(Bot bot, Message message) {
        SendMessage sendMessage = new SendMessage();
        //// TODO: 11/17/19 Написать раздел помощи.
        sendMessage.setText("Тут необходимо написать раздел помощи");
        sendMessage.setChatId(message.getChatId());
        BotButtons.setHelpButtons(sendMessage);
        bot.sendInfo(sendMessage);
    }

    public static void getCity(Bot bot, Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("Выберите необходимый вам город из меню ниже \u2b07");
        sendMessage.setChatId(message.getChatId());
        BotButtons.setCityButtons(sendMessage);
        bot.sendInfo(sendMessage);
    }

    public static void doNotUnderstandYou(Bot bot, Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("Я вас не понял \uD83D\uDE14 Повторите...");
        sendMessage.setChatId(message.getChatId());
        bot.sendInfo(sendMessage);
    }
}
