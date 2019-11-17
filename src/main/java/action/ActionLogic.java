package action;

import main.Bot;
import main.BotLogic;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class ActionLogic {

    public static void sayHello(Bot bot, Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("Вас приветствует BelarusInfoBot.\n" +
                "Используйте команду /help для помощи по боту.\n" +
                "Используйте команду /settings для настройки бота.\n" +
                "выберите необходимый вам город из меню снизу \u2b07");
        sendMessage.setChatId(message.getChatId());
        BotLogic.setCityButtons(sendMessage);
        bot.sendMsg(sendMessage);
    }

    public static void chooseCity(Bot bot, Message message) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton inlineKeyboardButtonNews = new InlineKeyboardButton().setText("Новости").setCallbackData("T");
        InlineKeyboardButton inlineKeyboardButtonWeather = new InlineKeyboardButton().setText("Погода").setUrl("https://www.gismeteo.by/");
        InlineKeyboardButton inlineKeyboardButtonAfisha = new InlineKeyboardButton().setText("Афиша").setCallbackData("T");
        InlineKeyboardButton inlineKeyboardButtonCourse = new InlineKeyboardButton().setText("Курсы валют").setCallbackData("T");
        List<InlineKeyboardButton> inlineKeyboardButtonsOne = new ArrayList<>();
        List<InlineKeyboardButton> inlineKeyboardButtonsTwo = new ArrayList<>();
        List<InlineKeyboardButton> inlineKeyboardButtonsThree = new ArrayList<>();
        List<InlineKeyboardButton> inlineKeyboardButtonsFour = new ArrayList<>();
        inlineKeyboardButtonsOne.add(inlineKeyboardButtonNews);
        inlineKeyboardButtonsTwo.add(inlineKeyboardButtonWeather);
        inlineKeyboardButtonsThree.add(inlineKeyboardButtonAfisha);
        inlineKeyboardButtonsFour.add(inlineKeyboardButtonCourse);
        List<List<InlineKeyboardButton>> rowList= new ArrayList<>();
        rowList.add(inlineKeyboardButtonsOne);
        rowList.add(inlineKeyboardButtonsTwo);
        rowList.add(inlineKeyboardButtonsThree);
        rowList.add(inlineKeyboardButtonsFour);
        inlineKeyboardMarkup.setKeyboard(rowList);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("Выберите интересующий вас раздел \u2b07");
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        sendMessage.setChatId(message.getChatId());
        bot.sendMsg(sendMessage);

    }

    public static void settings() {
    }

    public static void showHelp(Bot bot, Message message) {
        SendMessage sendMessage = new SendMessage();
        //// TODO: 11/17/19 Написать раздел помощи.
        sendMessage.setText("Тут необходимо написать раздел помощи");
        sendMessage.setChatId(message.getChatId());
        BotLogic.setHelpButtons(sendMessage);
        bot.sendMsg(sendMessage);
    }

    public static void getCity(Bot bot, Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("Выберите необходимый вам город из меню ниже \u2b07");
        sendMessage.setChatId(message.getChatId());
        BotLogic.setCityButtons(sendMessage);
        bot.sendMsg(sendMessage);
    }

    public static void doNotUnderstandYou(Bot bot, Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("Я вас не понял \uD83D\uDE14 Повторите...");
        sendMessage.setChatId(message.getChatId());
        bot.sendMsg(sendMessage);
    }
}
