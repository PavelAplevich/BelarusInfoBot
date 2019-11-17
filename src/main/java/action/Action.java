package action;

import main.Bot;
import org.telegram.telegrambots.meta.api.objects.Message;

public class Action {

    public void doAction(Bot bot, Message message) {
        switch (message.getText().toLowerCase()){
            case "минск":
            case "гродно":
            case "гомель":
            case "брест":
            case "могилев":
            case "витебск":
                ActionLogic.chooseCity(bot, message);
                break;
            case "перейти к выбору города":
                ActionLogic.getCity(bot, message);
                break;
            case "/settings":
            case "перейти в настройки":
                ActionLogic.settings();
                break;
            case "/help":
                ActionLogic.showHelp(bot, message);
                break;
            case "/start":
                ActionLogic.sayHello(bot, message);
                break;
            default:
                ActionLogic.doNotUnderstandYou(bot, message);

        }
    }
}
