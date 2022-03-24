package com.coolkidspace.linebot;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.response.BotApiResponse;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.NonNull;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@LineMessageHandler
public class LineBotController {

    // -------------------------Attribute-------------------------------------
    public String text1;
    public int ans;
    private boolean work = true;

    // -----------------------End of Attribute------------------------

    @Autowired
    private LineMessagingClient lineMessagingClient;

    // ---------------------- Start from here-----------------------------------
    // Constructor
    // public Project(String text1) {
    // this.text1 = text1;
    // }

    // Method
    @EventMapping // receive message from user
    public void handleTextMessage(MessageEvent<TextMessageContent> event) {
        log.info(event.toString());
        TextMessageContent message = event.getMessage();
            handleTextContent(event.getReplyToken(), event, message);
        }


    // ---------------------- End of Coding-------------------------------------

    // @EventMapping
    // public void handleTextMessage(MessageEvent<TextMessageContent> event) {
    // log.info(event.toString());
    // TextMessageContent message = event.getMessage();
    // handleTextContent(event.getReplyToken(), event, message);
    // }

    // -------------------------เริ่มแก้ไข
    // -->"Profile"----------------------------------------
    // checktext ที่ user input เข้ามา
    public String checkText(String text) {
        String usermessage;
        if ((text.equals("รีวว")) || (text.equals("รัวิว")) || (text.equals("รีววิ"))
                || (text.equals("รัววิ")) || (text.equals("review")) || (text.equals("Review"))
                || (text.equals("rEVIEW")) || (text.equals("eview")) || (text.equals("revirw"))
                || (text.equals("Reviw"))) {
            text = "รีวิว";
        } else if ((text.equals("รีววหนัง")) || (text.equals("รัวิวหนัง"))) {
            text = "รีวิวหนัง";
        } else if ((text.equals("รีววซีรีส์")) || (text.equals("รีววซีรีส์")) || (text.equals("รีวิววีรีส์"))
                || (text.equals("รีวิววีรีส์"))) {
            text = "รีวิวซีรีส์";
        } 
        return text;
    }

    public String getTypeMovie(String replyToken, Event event, TextMessageContent content){
        String text = content.getText();
        log.info("Got text message from %s : %s", replyToken, text);
        if (text.equals("m1")){
            this.reply(replyToken, Arrays.asList(new TextMessage("เรื่องราวของ อดัม รี้ด (ไรอัน เรย์โนลด์ส) นักบินหนุ่มที่ได้เดินทางข้ามเวลา หลังจากประสบอุบัติเหตุยานตกในปี 2022 ก็ได้ย้อนเวลากลับไปและแอบเข้าไปในบ้านลึกลับแห่งหนึ่งจึงพบเข้ากับเด็กวัย 13 ปี และต่อมาก็ได้รับการเปิดเผยว่าเป็นร่างในวัยเด็กของตัวเขาเอง เขาทั้งสองจึงต้องร่วมมือกันตามหาพ่อ ( มาร์ค รัฟฟาโล ) พร้อมกับทำภารกิจกอบกู้อนาคต"),
                                                new TextMessage("รีวิวแบบเต็มค่ะ\t\t\t : •	https://zhort.link/GdH"),
                                                new TextMessage("Trailor : •	https://www.youtube.com/watch?v=nQm0wor_qoQ")));
        }
        return text;
    }

    private void handleTextContent(String replyToken, Event event, TextMessageContent content) {
        String text = content.getText();

        log.info("Got text message from %s : %s", replyToken, text);

        switch (checkText(text)) {
            case "Profile": {
                String userId = event.getSource().getUserId();
                if (userId != null) {
                    lineMessagingClient.getProfile(userId)
                            .whenComplete((profile, throwable) -> {
                                if (throwable != null) {
                                    this.replyText(replyToken, throwable.getMessage());
                                    return;
                                }
                                this.reply(replyToken, Arrays.asList(
                                        new TextMessage("Display name: " + profile.getDisplayName()),
                                        new TextMessage("Status message: " + profile.getStatusMessage()),
                                        new TextMessage("User ID: " + profile.getUserId())));
                            });
                }
                break;
            }
            case "รีวิว": {
                this.reply(replyToken, new TextMessage("รีวิวหนัง หรือ รีวิวซีรีส์"));
                break;
            }
            case "รีวิวหนัง": {
                this.reply(replyToken, Arrays.asList(new TextMessage("พิมพ์รหัสหนังที่อยากอ่านรีวิวได้เลยค่ะ"),
                new TextMessage("m1\t\t\tThe Adam project \nm2\t\t\tThe misfits \nm3\t\t\tRed notice \nm4\t\t\tThe divine fury \nm5\t\t\tHunter killer \nm6\t\t\tThe old guard \nm7\t\t\tPee nak 3(พี่นาค3) \nm8\t\t\tJade dynasty \nm9\t\t\tBlack crab \nm10\tGet him girl(ส้มป่อย)")));
                break;
            }
            case "รีวิวซีรีส์": {
                this.reply(replyToken, Arrays.asList(new TextMessage("พิมพ์รหัสซีรีส์ที่อยากอ่านรีวิวได้เลยค่ะ"),
                new TextMessage("s1\t\t\tBusiness Proposal \ns2\t\t\ttwenty five twenty one \ns3\t\t\tForecasting Love and Weather \ns4\t\t\tAll of Us Are Dead \ns5\t\t\tMy Roommate is a Gumiho \ns6\t\t\tJuvenile Justice \ns7\t\t\tThe Silent Sea ทะเลสงัด \ns8\t\t\tThe Cursed \ns9\t\t\tCracow Monsters \ns10\tEmily in Paris season 2")));
                break;
            }
            default:
                this.reply(replyToken, Arrays.asList(
                        new TextMessage("POPCORN ไม่เข้าใจค่ะ"),
                        new TextMessage("ไหนลองพิมพืมพ์ใหม่สิ")));
        }
    }

    private void typeMovieTextContent(String replyToken, Event event, TextMessageContent content) {
        String text1 = content.getText();

        log.info("Got text message from %s : %s", replyToken, text1);
 
    }

    private void replyText(@NonNull String replyToken, @NonNull String message) {
        if (replyToken.isEmpty()) {
            throw new IllegalArgumentException("replyToken is not empty");
        }

        if (message.length() > 1000) {
            message = message.substring(0, 1000 - 2) + "...";
        }
        this.reply(replyToken, new TextMessage(message));
    }

    private void reply(@NonNull String replyToken, @NonNull Message message) {
        reply(replyToken, Collections.singletonList(message));
    }

    private void reply(@NonNull String replyToken, @NonNull List<Message> messages) {
        try {
            BotApiResponse response = lineMessagingClient.replyMessage(
                    new ReplyMessage(replyToken, messages)).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
    // --------------จบของ Profile------------------------------------------

    @Value
    public static class DownloadedContent {
        Path path;
        String uri;
    }
}