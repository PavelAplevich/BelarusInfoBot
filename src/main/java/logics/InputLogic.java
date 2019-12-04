package logics;

public class InputLogic {
    public static String getCityName(String name) {
        switch (name){
            case "минск":
            case "minsk":
                return "minsk";
            case "гродно":
            case "grodno":
                return "grodno";
            case "брест":
            case "brest":
                return "brest";
            case "гомель":
            case "gomel":
                return "gomel";
            case "могилев":
            case "могилёв":
            case "mogilev":
                return "mogilev";
            case "витебск":
            case "vitebsk":
                return "vitebsk";
            default:
                return "unknown";
        }
    }
}
