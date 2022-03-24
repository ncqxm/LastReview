package com.coolkidspace.linebot;

// import java.io.IOException;
// import java.io.OutputStream;
// import java.io.UncheckedIOException;
// import java.nio.file.Files;
import java.nio.file.Path;
// import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
// import java.util.UUID;
import java.util.concurrent.ExecutionException;

// import com.google.common.io.ByteStreams;
import com.linecorp.bot.client.LineMessagingClient;
// import com.linecorp.bot.client.MessageContentResponse;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
// import com.linecorp.bot.model.event.message.ImageMessageContent;
// import com.linecorp.bot.model.event.message.LocationMessageContent;
// import com.linecorp.bot.model.event.message.StickerMessageContent;
import com.linecorp.bot.model.event.message.TextMessageContent;
// import com.linecorp.bot.model.message.ImageMessage;
// import com.linecorp.bot.model.message.LocationMessage;
import com.linecorp.bot.model.message.Message;
// import com.linecorp.bot.model.message.StickerMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.response.BotApiResponse;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.NonNull;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@LineMessageHandler
public class LineBotController {

    // -------------------------Attribute-------------------------------------
    public String text;
    public int ans;

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

    // @EventMapping
    // public void handleStickerMessage(MessageEvent<StickerMessageContent> event) {
    // log.info(event.toString());
    // StickerMessageContent message = event.getMessage();
    // reply(event.getReplyToken(), new StickerMessage(
    // message.getPackageId(), message.getStickerId()));
    // }

    // @EventMapping
    // public void handleLocationMessage(MessageEvent<LocationMessageContent> event)
    // {
    // log.info(event.toString());
    // LocationMessageContent message = event.getMessage();
    // reply(event.getReplyToken(), new LocationMessage(
    // (message.getTitle() == null) ? "Location replied" : message.getTitle(),
    // message.getAddress(),
    // message.getLatitude(),
    // message.getLongitude()));
    // }

    // @EventMapping
    // public void handleImageMessage(MessageEvent<ImageMessageContent> event) {
    // log.info(event.toString());
    // ImageMessageContent content = event.getMessage();
    // String replyToken = event.getReplyToken();

    // try {
    // MessageContentResponse response =
    // lineMessagingClient.getMessageContent(content.getId()).get();
    // DownloadedContent jpg = saveContent("jpg", response);
    // DownloadedContent previewImage = createTempFile("jpg");

    // system("convert", "-resize", "240x",
    // jpg.path.toString(),
    // previewImage.path.toString());

    // reply(replyToken, new ImageMessage(jpg.getUri(), previewImage.getUri()));

    // } catch (InterruptedException | ExecutionException e) {
    // reply(replyToken, new TextMessage("Cannot get image: " + content));
    // throw new RuntimeException(e);
    // }

    // }

    // -------------------------เริ่มแก้ไข -->
    // "Profile"----------------------------------------
    // checktext ที่ user input เข้ามา
    public String checkText(String text) {
        String usermessage;
        if ((text.equals("รีวว")) || (text.equals("รัวิว")) || (text.equals("รีววิ"))
                || (text.equals("รัววิ"))) {
            usermessage = "รีวิว";
        } else {
            usermessage = text;
        }
        return text;
    }

    private void handleTextContent(String replyToken, Event event, TextMessageContent content) {
        String text = content.getText();

        log.info("Got text message from %s : %s", replyToken, text);

        switch (text) {
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
            case "review": {
                this.reply(replyToken, new TextMessage("movie or series"));
                break;
            }
            case "ดูหนังบ่": {
                this.reply(replyToken, new TextMessage("อยากดูหนังแนวไหนอ่ะ"));
                break;
            }
            case "New movie": {
                this.reply(replyToken, new TextMessage("Let's see new movie"));
                break;
            }
            case "รีวิวหนัง": {
                this.reply(replyToken, new TextMessage("Review movie gun ka"));
                this.ans = 1;
                break;
            }
            case "รีวิวซีรีส์": {
                this.reply(replyToken, new TextMessage("Review series gun ka"));
                this.ans =2;
                break;
            }
            default:
                this.reply(replyToken, Arrays.asList(
                        new TextMessage("POPCORN is confused."),
                        new TextMessage(
                                "ไหนลองใหม่สิ"),
                        new TextMessage("Good afternoon")));

        }
    }


    private void typeMovieTextContent(String replyToken, Event event, TextMessageContent content) {
        String text = content.getText();

        log.info("Got text message from %s : %s", replyToken, text);
        if (this.ans == 1) {
            this.reply(replyToken, Arrays.asList(new TextMessage("พิมพ์รหัสหนังที่อยากอ่านรีวิว"),
                                                new TextMessage("m1"), 
                                                new TextMessage("m2")));
        } else if (this.ans == 2){
            this.reply(replyToken, Arrays.asList(new TextMessage("พิมพ์รหัสซีรีส์ที่อยากอ่านรีวิว"),
            new TextMessage("s1"),
            new TextMessage("s2")));
        }
    }



    // private void handleStickerContent(String replyToken, StickerMessageContent
    // content) {
    // reply(replyToken, new StickerMessage(
    // content.getPackageId(), content.getStickerId()));
    // }

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

    // private void system(String... args) {
    // ProcessBuilder processBuilder = new ProcessBuilder(args);
    // try {
    // Process start = processBuilder.start();
    // int i = start.waitFor();
    // log.info("result: {} => {}", Arrays.toString(args), i);
    // } catch (InterruptedException e) {
    // log.info("Interrupted", e);
    // Thread.currentThread().interrupt();
    // } catch (IOException e) {
    // throw new UncheckedIOException(e);
    // }
    // }

    // private static DownloadedContent saveContent(String ext,
    // MessageContentResponse response) {
    // log.info("Content-type: {}", response);
    // DownloadedContent tempFile = createTempFile(ext);
    // try (OutputStream outputStream = Files.newOutputStream(tempFile.path)) {
    // ByteStreams.copy(response.getStream(), outputStream);
    // log.info("Save {}: {}", ext, tempFile);
    // return tempFile;
    // } catch (IOException e) {
    // throw new UncheckedIOException(e);
    // }
    // }

    // private static DownloadedContent createTempFile(String ext) {
    // String fileName = LocalDateTime.now() + "-" + UUID.randomUUID().toString() +
    // "." + ext;
    // Path tempFile = Application.downloadedContentDir.resolve(fileName);
    // tempFile.toFile().deleteOnExit();
    // return new DownloadedContent(tempFile, createUri("/downloaded/" +
    // tempFile.getFileName()));

    // }

    // private static String createUri(String path) {
    // return ServletUriComponentsBuilder.fromCurrentContextPath()
    // .path(path).toUriString();
    // }

    @Value
    public static class DownloadedContent {
        Path path;
        String uri;
    }
}