import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot {
    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramApi = new TelegramBotsApi();
        try{
            telegramApi.registerBot(new Bot());
        } catch (TelegramApiException e){
            e.printStackTrace();
        }
    }

    public void onUpdateReceived(Update update) {
        SendMessage sendMessage = new SendMessage();
        Message message = update.getMessage();
        sendMessage.setChatId(message.getChatId().toString());


        if(message == null){
            try {
                execute(sendMessage.setText("Хей, я снова здесь!"));
            }catch (TelegramApiException e){
                e.printStackTrace();
            }

        }else
        if(message !=null && message.hasText()){
            switch (message.getText()){
                case "/help":
                    try {
                        execute(sendMessage.setText("Чем могу помочь?"));
                    }catch (TelegramApiException e){
                        e.printStackTrace();
                    }
                    break;
                case "/settings":
                    try {
                        execute(sendMessage.setText("Что будем настраивать?"));
                    }catch (TelegramApiException e){
                        e.printStackTrace();
                    }
                    break;
                case "хочуфото":
                    try {
                        execute(sendMessage.setText("Сейчас попробую загрузить"));
                        File fileToUpload = new File("photo.jpg");
                        SendPhoto sendPhoto = new SendPhoto();
                        sendPhoto.setPhoto(fileToUpload);
                        System.out.println("файл загружен");
                        if (sendPhoto != null) {
                            sendPhoto.setChatId(message.getChatId());
                            try {
                                execute(sendPhoto);
                            } catch (TelegramApiException e) {
                                e.printStackTrace();
                            }
                        }

                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    break;
                default:
                    try {
                        execute(sendMessage.setText("\"" + message.getText() + "\"" + " - ниче не понял.."));
                    }catch (TelegramApiException e){
                        e.printStackTrace();
                    }
                    break;
            }
        }

    }

    public void setButtons(SendMessage sendMessage){
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> listKeyboardRow = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();

        keyboardFirstRow.add(new KeyboardButton("/help"));
        keyboardFirstRow.add(new KeyboardButton("/settings"));
        keyboardFirstRow.add(new KeyboardButton("/хочуфото"));

    }

    public String getBotUsername() {
        return System.getenv("BOT_USERNAME");
    }

    public String getBotToken() {
        return System.getenv("BOT_TOKEN");
    }
}
