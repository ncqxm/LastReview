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
    public String text;
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
        if (work == false) {
            typeMovieTextContent(event.getReplyToken(), event, message);
        } else {
            handleTextContent(event.getReplyToken(), event, message);
        }
    }

    // ---------------------- End of Coding-------------------------------------

    // @EventMapping
    // public void handleTextMessage(MessageEvent<TextMessageContent> event) {
    // log.info(event.toString());
    // TextMessageContent message = event.getMessage();
    // handleTextContent(event.getReplyToken(), event, message);
    // }

    // -------------------------เริ่มแก้ไข -->"Profile"----------------------------------------
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
        } else if ((text.equals("รีววซีรีส์")) || (text.equals("รีวว:ซีรีส์"))) {
            text = "รีวิวซีรีส์";
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
                this.reply(replyToken, new TextMessage("https://intrend.trueid.net/bangkok/%E0%B8%A3%E0%B8%B5%E0%B8%A7%E0%B8%B4%E0%B8%A7-the-adam-project-%E0%B8%A2%E0%B9%89%E0%B8%AD%E0%B8%99%E0%B9%80%E0%B8%A7%E0%B8%A5%E0%B8%B2%E0%B8%AB%E0%B8%B2%E0%B8%AD%E0%B8%94%E0%B8%B1%E0%B8%A1-%E0%B8%A3%E0%B8%A7%E0%B8%A1%E0%B8%99%E0%B8%B1%E0%B8%81%E0%B9%81%E0%B8%AA%E0%B8%94%E0%B8%87%E0%B8%8B%E0%B8%B8%E0%B8%9B%E0%B9%80%E0%B8%9B%E0%B8%AD%E0%B8%A3%E0%B9%8C%E0%B8%AE%E0%B8%B5%E0%B9%82%E0%B8%A3%E0%B9%88%E0%B8%82%E0%B8%AD%E0%B8%87%E0%B8%88%E0%B8%B1%E0%B8%81%E0%B8%A3%E0%B8%A7%E0%B8%B2%E0%B8%A5%E0%B8%A1%E0%B8%B2%E0%B9%80%E0%B8%A7%E0%B8%A5-trueidintrend_265423"));
                work = false;         
                this.ans = 1;
                break;
            }
            case "รีวิวซีรีส์": {
                this.reply(replyToken, new TextMessage("Business Proposal ดีมากจ้าา"));
                work = false;
                this.ans = 2;
                break;
            }
            default:
                this.reply(replyToken, Arrays.asList(
                        new TextMessage("POPCORN ไม่เข้าใจค่ะ"),
                        new TextMessage("ไหนลองพิมพืมพ์ใหม่สิ")));
        }
    }

    private void typeMovieTextContent(String replyToken, Event event, TextMessageContent content) {
        String text = content.getText();

        log.info("Got text message from %s : %s", replyToken, text);
        if (this.ans == 1) {
            this.reply(replyToken, Arrays.asList(new TextMessage("พิมพ์รหัสหนังที่อยากอ่านรีวิว"),
                    new TextMessage("m1"),
                    new TextMessage("m2")));
            work = false;

        } else if (this.ans == 2) {
            this.reply(replyToken, Arrays.asList(new TextMessage("พิมพ์รหัสซีรีส์ที่อยากอ่านรีวิว"),
                    new TextMessage("s1"),
                    new TextMessage("s2")));
            work = false;
        }
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