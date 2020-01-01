import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

//import static java.awt.DefaultKeyboardFocusManager.sendMessage;

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

   /* public void sendMsg(Message message, String text){
        SendMessage sendMs = new SendMessage();
        sendMs.enableMarkdown(true);
        sendMs.setChatId(message.getChatId().toString());
        sendMs.setReplyToMessageId(message.getMessageId());
        sendMs.setText(text);
        try {
            Execute(sendMs);

        }catch (TelegramApiException e){
            e.printStackTrace();
        }
    } */


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
                case "сиськи":
                    try {
                        File fileToUpload = new File("C:\\Users\\Paper Planes\\Documents\\Telegram Bot\\сиськи.jpg");
                        SendPhoto sendPhoto = new SendPhoto();
                        sendPhoto.setPhoto(fileToUpload);
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
        keyboardFirstRow.add(new KeyboardButton("/сиськи"));


        //keyboardFirstRow


    }


    public String getBotUsername() {
        return "medigi_bot";
    }

    public String getBotToken() {
        return "1008611486:AAEa6GqWNmFseQ6xvt6LhUblqnks1fkWiP8";
    }
}
