package app.service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.send.SendVideo;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.util.List;

public class Nilashree_bot extends TelegramLongPollingBot {
    public void onUpdateReceived(Update update) {
        System.out.println(update.getMessage().getText());
        System.out.println(update.getMessage().getFrom().getFirstName());

        SendMessage message=new SendMessage();
        message.setText("yyyy");

        System.out.println("chat id "+update.getMessage().getChatId());
        message.setChatId(update.getMessage().getChatId());
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    public void SENDtext()
    {
        SendMessage message=new SendMessage();
        message.setText("yyyy123");
        message.setChatId("1011549445");
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void SENDphoto()
    {
        SendPhoto message=new SendPhoto();
        File file = new File("F:\\Squarovention-2019\\Live projects\\royal.jpg");
        double size=getFileSizeMegaBytes(file);
        System.out.println(getFileSizeMegaBytes(file));
        if(size>5)
        {
            System.out.println("File size excedded.");
        }else {
            message.setPhoto(file);
            message.setChatId("1011549445");
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    private static double getFileSizeMegaBytes(File file) {
        return (double) file.length() / (1024 * 1024);
    }


    public void SENDVideo()
    {
        File file = new File("F:\\Squarovention-2019\\Live projects\\sample.mp4");

        double size=getFileSizeMegaBytes(file);
        System.out.println(getFileSizeMegaBytes(file));
        if(size>50)
        {
            System.out.println("File size excedded.");
        }else {
            SendVideo message = new SendVideo();
            message.setVideo(file);
            message.setChatId("1011549445");

            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }



    public String getBotUsername() {
        return "nilshree_bot";
    }

    public String getBotToken() {
        return "1041747083:AAHlYuwfBOqry5ftNQPWjt9g93074LHTH5c";
    }


}
