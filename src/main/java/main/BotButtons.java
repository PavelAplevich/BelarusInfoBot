package main;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class BotButtons {

    public static InlineKeyboardMarkup getInlineKeyboard() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton inlineKeyboardButtonNews = new InlineKeyboardButton().setText("Новости").setCallbackData("news");
        InlineKeyboardButton inlineKeyboardButtonWeather = new InlineKeyboardButton().setText("Погода").setCallbackData("weather");
        InlineKeyboardButton inlineKeyboardButtonCourse = new InlineKeyboardButton().setText("Курсы валют").setCallbackData("course");
        InlineKeyboardButton inlineKeyboardButtonDogs = new InlineKeyboardButton().setText("Случайный пёс").setCallbackData("dog");
        List<InlineKeyboardButton> inlineKeyboardButtonsOne = new ArrayList<>();
        List<InlineKeyboardButton> inlineKeyboardButtonsTwo = new ArrayList<>();
        List<InlineKeyboardButton> inlineKeyboardButtonsThree = new ArrayList<>();
        List<InlineKeyboardButton> inlineKeyboardButtonsFour = new ArrayList<>();
        inlineKeyboardButtonsOne.add(inlineKeyboardButtonNews);
        inlineKeyboardButtonsTwo.add(inlineKeyboardButtonWeather);
        inlineKeyboardButtonsThree.add(inlineKeyboardButtonCourse);
        inlineKeyboardButtonsFour.add(inlineKeyboardButtonDogs);
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(inlineKeyboardButtonsOne);
        rowList.add(inlineKeyboardButtonsTwo);
        rowList.add(inlineKeyboardButtonsThree);
        rowList.add(inlineKeyboardButtonsFour);
        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }

    public static void setCityButtons(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        KeyboardRow keyboardSecondRow = new KeyboardRow();

        keyboardFirstRow.add(new KeyboardButton("Минск"));
        keyboardFirstRow.add(new KeyboardButton("Гродно"));
        keyboardFirstRow.add(new KeyboardButton("Брест"));
        keyboardSecondRow.add(new KeyboardButton("Гомель"));
        keyboardSecondRow.add(new KeyboardButton("Могилёв"));
        keyboardSecondRow.add(new KeyboardButton("Витебск"));

        keyboardRowList.add(keyboardFirstRow);
        keyboardRowList.add(keyboardSecondRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
    }

    public static void setHelpButtons(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        keyboardFirstRow.add(new KeyboardButton("Перейти к выбору города"));
        keyboardSecondRow.add(new KeyboardButton("Перейти в настройки"));
        keyboardRowList.add(keyboardFirstRow);
        keyboardRowList.add(keyboardSecondRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);

    }

    public static ReplyKeyboardMarkup getMenuButton() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add(new KeyboardButton("Меню"));
        keyboardRowList.add(keyboardFirstRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
        return replyKeyboardMarkup;
    }
}
