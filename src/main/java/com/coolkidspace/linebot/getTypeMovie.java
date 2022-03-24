// package com.coolkidspace.linebot;

// import java.nio.file.Path;
// import java.util.Arrays;
// import java.util.Collections;
// import java.util.List;
// import java.util.concurrent.ExecutionException;

// import com.linecorp.bot.client.LineMessagingClient;
// import com.linecorp.bot.model.ReplyMessage;
// import com.linecorp.bot.model.event.Event;
// import com.linecorp.bot.model.event.MessageEvent;
// import com.linecorp.bot.model.event.message.TextMessageContent;
// import com.linecorp.bot.model.message.Message;
// import com.linecorp.bot.model.message.TextMessage;
// import com.linecorp.bot.model.response.BotApiResponse;
// import com.linecorp.bot.spring.boot.annotation.EventMapping;
// import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

// import org.springframework.beans.factory.annotation.Autowired;

// import lombok.NonNull;
// import lombok.Value;
// import lombok.extern.slf4j.Slf4j;


// @LineMessageHandler

// public class getTypeMovie {
//     private boolean work = false;
//     @Autowired
//     private LineMessagingClient lineMessagingClient;
    

//     public String getTypeMovie(String replyToken, Event event, TextMessageContent content, Object log) {

//         String text = content.getText();
//         log.info("Got text message from %s : %s", replyToken, text);

//         if (work == true) {
//             if (text.equals("m1")) {
//                 // this.reply(replyToken, new TextMessage("คุณเลือก\tm1\tThe Adam project"));
//                 this.reply(replyToken, Arrays.asList(new TextMessage("คุณเลือก\tm1\tThe Adam project"),
//                         new TextMessage(
//                         "เรื่องราวของ อดัม รี้ด (ไรอัน เรย์โนลด์ส) นักบินหนุ่มที่ได้เดินทางข้ามเวลา หลังจากประสบอุบัติเหตุยานตกในปี 2022 ก็ได้ย้อนเวลากลับไปและแอบเข้าไปในบ้านลึกลับแห่งหนึ่งจึงพบเข้ากับเด็กวัย 13 ปี และต่อมาก็ได้รับการเปิดเผยว่าเป็นร่างในวัยเด็กของตัวเขาเอง เขาทั้งสองจึงต้องร่วมมือกันตามหาพ่อ ( มาร์ค รัฟฟาโล ) พร้อมกับทำภารกิจกอบกู้อนาคต"),
//                         new TextMessage("รีวิวแบบเต็มค่ะ\t\t\t : •	https://zhort.link/GdH"),
//                         new TextMessage("Trailor : •	https://www.youtube.com/watch?v=nQm0wor_qoQ")));
//             } else if (text.equals("m2")) {
//                 // this.reply(replyToken, new TextMessage("คุณเลือก\tm2\tThe misfits"));
//                 this.reply(replyToken, Arrays.asList(new TextMessage("คุณเลือก\tm2\tThe misfits"),
//                         new TextMessage(
//                         "•	เรื่องราวของโจรอัจฉริยะอย่าง ริชาร์ด เพซ (เพียร์ซ บรอสแนน) ซึ่งแม้แต่คุกที่มีระบบการรักษาความปลอดภัยขั้นสูงสุดยังหยุดเขาไว้ไม่ได้ หลังจากหลบหนีการไล่ล่าของตำรวจ และเอฟบีไออย่างดุเดือด เพซได้เข้ามาเป็นส่วนหนึ่งของทีมโจรคนนอกคอกในนาม The Misfits โดยมี วิค (รับบทโดย ไมค์ พิรัชต์) เป็นเซียนระเบิดตัวอันตรายประจำทีม ทุกภารกิจของพวกเขาคือ การปล้นเอาคืนคนชั่วเพื่อช่วยเหลือคนถูกรังแก โดยไม่เปิดเผยตัวตน ซึ่งการได้หัวขโมยตัวพ่ออย่างเพซ มาร่วมทีมนำไปสู่ปฏิบัติการปล้นแห่งศตวรรษที่ทั้งลึกลับซับซ้อน และมาพร้อมกับแผนอันล้ำลึก เป้าหมายคือการโจรกรรมทองคำแท่งมูลค่านับล้านเหรียญจากชั้นใต้ดินของคุก ซึ่งมีระบบนิรภัยแน่นหนาที่สุดในโลก ทองคำแท่งเหล่านั้นเป็นท่อน้ำเลี้ยงของ ชัลต์ซ เจ้าพ่อธุรกิจมืดผู้อยู่เบื้องหลังแผนก่อการร้ายทั่วโลก"),
//                         new TextMessage("รีวิวแบบเต็มค่ะ\t\t\t : •	https://zhort.link/GdA"),
//                         new TextMessage("Trailor :•	https://www.youtube.com/watch?v=uIjCiU6ee8Q")));
//             } else if (text.equals("m3")) {
//                 // this.reply(replyToken, new TextMessage("คุณเลือก\tm3\tRed notice"));
//                 this.reply(replyToken, Arrays.asList(new TextMessage("คุณเลือก\tm3\tRed notice"),
//                         new TextMessage(
//                         "•	นักวิเคราะห์อาชญากรของเอฟบีไอตามจับโจรขโมยงานศิลป์ที่ทางการต้องการตัวมากที่สุด แต่กลับกลายมาเป็นคู่หูเฉพาะกิจเพื่อล่าตัวอาชญากรหลบหนีที่นำหน้าอยู่ก้าวหนึ่งเสมอ"),
//                         new TextMessage("รีวิวแบบเต็มค่ะ\t\t\t : •	https://www.sanook.com/movie/117465/"),
//                         new TextMessage("Trailor : •	https://www.youtube.com/watch?v=Pj0wz7zu3Ms")));
//             } else if (text.equals("m4")) {
//                 // this.reply(replyToken, new TextMessage("คุณเลือก\tm4\tThe divine fury"));
//                 this.reply(replyToken, Arrays.asList(new TextMessage("คุณเลือก\tm4\tThe divine fury"),
//                         new TextMessage(
//                         "•	ยงฮูเป็นนักมวยที่เก่งมากๆไม่เคยเเพ้ใครเเละเป็นคนที่ไม่เชื่อในพระเจ้าเนื่องมาจากปมในวัยเด็ก อยู่มาวันหนึ่งเขาก็มีแผลเป็นที่ฝ่ามือเหมือนพระเยซูซึ่งหมอก็ไม่สามารถให้คำตอบได้ สุดท้ายเขาจึงไปพบบาทหลวงเพื่อหวังจะรักษาแผล ไปๆมาๆด้วยความรู้สึกบางอย่างยงฮูก็ได้เข้าไปเป็นฝ่ายช่วย บาทหลวงอัน ที่เกือบตายมาจากการไล่ผีแทนเเละเขาก็เริ่มเรียนรู้พลังพิเศษที่สามารถขับไล่ปีศาจได้เเค่สัมผัสเเล้วราดน้ำมนต์ใส่(เหมือนเลือดของเขาเป็นไฟน้ำมนต์เป็นน้ำมัน) ทำให้ช่วยคนบริสุทธิ์ไว้ได้แต่เรื่องราวก็ไม่ได้ง่ายขนาดนั้นเพราะ จอมมารจีชินที่เป็นบาตรหลวงเหมือนกันเเต่กลับบูชาซาตานก็ได้มาขัดขวางเเละการต่อสู้อันดุเดือดก็ได้เริ่มขึ้น"),
//                         new TextMessage("รีวิวแบบเต็มค่ะ\t\t\t : •	https://zhort.link/GdB"),
//                         new TextMessage("Trailor : •	https://www.youtube.com/watch?v=jdvRbAG00p4")));
//                         // ----------------------------------------ทำถึงตรงนี------------------------------------------------
//             } else if (text.equals("m5")) {
//                 this.reply(replyToken, new TextMessage("คุณเลือก\tm5\tHunter killer"));
//                 this.reply(replyToken, Arrays.asList(new TextMessage(
//                         "เรื่องราวของ อดัม รี้ด (ไรอัน เรย์โนลด์ส) นักบินหนุ่มที่ได้เดินทางข้ามเวลา หลังจากประสบอุบัติเหตุยานตกในปี 2022 ก็ได้ย้อนเวลากลับไปและแอบเข้าไปในบ้านลึกลับแห่งหนึ่งจึงพบเข้ากับเด็กวัย 13 ปี และต่อมาก็ได้รับการเปิดเผยว่าเป็นร่างในวัยเด็กของตัวเขาเอง เขาทั้งสองจึงต้องร่วมมือกันตามหาพ่อ ( มาร์ค รัฟฟาโล ) พร้อมกับทำภารกิจกอบกู้อนาคต"),
//                         new TextMessage("รีวิวแบบเต็มค่ะ\t\t\t : •	https://zhort.link/GdH"),
//                         new TextMessage("Trailor : •	https://www.youtube.com/watch?v=nQm0wor_qoQ")));
//             } else if (text.equals("m6")) {
//                 this.reply(replyToken, new TextMessage("คุณเลือก\tm6\tThe old guard"));
//                 this.reply(replyToken, Arrays.asList(new TextMessage(
//                         "เรื่องราวของ อดัม รี้ด (ไรอัน เรย์โนลด์ส) นักบินหนุ่มที่ได้เดินทางข้ามเวลา หลังจากประสบอุบัติเหตุยานตกในปี 2022 ก็ได้ย้อนเวลากลับไปและแอบเข้าไปในบ้านลึกลับแห่งหนึ่งจึงพบเข้ากับเด็กวัย 13 ปี และต่อมาก็ได้รับการเปิดเผยว่าเป็นร่างในวัยเด็กของตัวเขาเอง เขาทั้งสองจึงต้องร่วมมือกันตามหาพ่อ ( มาร์ค รัฟฟาโล ) พร้อมกับทำภารกิจกอบกู้อนาคต"),
//                         new TextMessage("รีวิวแบบเต็มค่ะ\t\t\t : •	https://zhort.link/GdH"),
//                         new TextMessage("Trailor : •	https://www.youtube.com/watch?v=nQm0wor_qoQ")));
//             } else if (text.equals("m7")) {
//                 this.reply(replyToken, new TextMessage("คุณเลือก\tm7\tPee nak 3(พี่นาค3)"));
//                 this.reply(replyToken, Arrays.asList(new TextMessage(
//                         "เรื่องราวของ อดัม รี้ด (ไรอัน เรย์โนลด์ส) นักบินหนุ่มที่ได้เดินทางข้ามเวลา หลังจากประสบอุบัติเหตุยานตกในปี 2022 ก็ได้ย้อนเวลากลับไปและแอบเข้าไปในบ้านลึกลับแห่งหนึ่งจึงพบเข้ากับเด็กวัย 13 ปี และต่อมาก็ได้รับการเปิดเผยว่าเป็นร่างในวัยเด็กของตัวเขาเอง เขาทั้งสองจึงต้องร่วมมือกันตามหาพ่อ ( มาร์ค รัฟฟาโล ) พร้อมกับทำภารกิจกอบกู้อนาคต"),
//                         new TextMessage("รีวิวแบบเต็มค่ะ\t\t\t : •	https://zhort.link/GdH"),
//                         new TextMessage("Trailor : •	https://www.youtube.com/watch?v=nQm0wor_qoQ")));
//             } else if (text.equals("m8")) {
//                 this.reply(replyToken, new TextMessage("คุณเลือก\tm8\tJade dynasty"));
//                 this.reply(replyToken, Arrays.asList(new TextMessage(
//                         "เรื่องราวของ อดัม รี้ด (ไรอัน เรย์โนลด์ส) นักบินหนุ่มที่ได้เดินทางข้ามเวลา หลังจากประสบอุบัติเหตุยานตกในปี 2022 ก็ได้ย้อนเวลากลับไปและแอบเข้าไปในบ้านลึกลับแห่งหนึ่งจึงพบเข้ากับเด็กวัย 13 ปี และต่อมาก็ได้รับการเปิดเผยว่าเป็นร่างในวัยเด็กของตัวเขาเอง เขาทั้งสองจึงต้องร่วมมือกันตามหาพ่อ ( มาร์ค รัฟฟาโล ) พร้อมกับทำภารกิจกอบกู้อนาคต"),
//                         new TextMessage("รีวิวแบบเต็มค่ะ\t\t\t : •	https://zhort.link/GdH"),
//                         new TextMessage("Trailor : •	https://www.youtube.com/watch?v=nQm0wor_qoQ")));
//             } else if (text.equals("m9")) {
//                 this.reply(replyToken, new TextMessage("คุณเลือก\tm9\tBlack crab"));
//                 this.reply(replyToken, Arrays.asList(new TextMessage(
//                         "เรื่องราวของ อดัม รี้ด (ไรอัน เรย์โนลด์ส) นักบินหนุ่มที่ได้เดินทางข้ามเวลา หลังจากประสบอุบัติเหตุยานตกในปี 2022 ก็ได้ย้อนเวลากลับไปและแอบเข้าไปในบ้านลึกลับแห่งหนึ่งจึงพบเข้ากับเด็กวัย 13 ปี และต่อมาก็ได้รับการเปิดเผยว่าเป็นร่างในวัยเด็กของตัวเขาเอง เขาทั้งสองจึงต้องร่วมมือกันตามหาพ่อ ( มาร์ค รัฟฟาโล ) พร้อมกับทำภารกิจกอบกู้อนาคต"),
//                         new TextMessage("รีวิวแบบเต็มค่ะ\t\t\t : •	https://zhort.link/GdH"),
//                         new TextMessage("Trailor : •	https://www.youtube.com/watch?v=nQm0wor_qoQ")));
//             } else if (text.equals("m10")) {
//                 this.reply(replyToken, new TextMessage("คุณเลือก\tm10\tGet him girl(ส้มป่อย)"));
//                 this.reply(replyToken, Arrays.asList(new TextMessage(
//                         "เรื่องราวของ อดัม รี้ด (ไรอัน เรย์โนลด์ส) นักบินหนุ่มที่ได้เดินทางข้ามเวลา หลังจากประสบอุบัติเหตุยานตกในปี 2022 ก็ได้ย้อนเวลากลับไปและแอบเข้าไปในบ้านลึกลับแห่งหนึ่งจึงพบเข้ากับเด็กวัย 13 ปี และต่อมาก็ได้รับการเปิดเผยว่าเป็นร่างในวัยเด็กของตัวเขาเอง เขาทั้งสองจึงต้องร่วมมือกันตามหาพ่อ ( มาร์ค รัฟฟาโล ) พร้อมกับทำภารกิจกอบกู้อนาคต"),
//                         new TextMessage("รีวิวแบบเต็มค่ะ\t\t\t : •	https://zhort.link/GdH"),
//                         new TextMessage("Trailor : •	https://www.youtube.com/watch?v=nQm0wor_qoQ")));
//             }
//         } else if (work == false){
//             if (text.equals("ยกเลิก")){
//                 this.reply(replyToken, new TextMessage("รีวิวหนัง หรือ รีวิวซีรีส์"));
//             }
//         }
//         return text;
//     }

//     private void replyText(@NonNull String replyToken, @NonNull String message) {
//         if (replyToken.isEmpty()) {
//             throw new IllegalArgumentException("replyToken is not empty");
//         }

//         if (message.length() > 1000) {
//             message = message.substring(0, 1000 - 2) + "...";
//         }
//         this.reply(replyToken, new TextMessage(message));
//     }

//     private void reply(@NonNull String replyToken, @NonNull Message message) {
//         reply(replyToken, Collections.singletonList(message));
//     }

//     private void reply(@NonNull String replyToken, @NonNull List<Message> messages) {
//         try {
//             BotApiResponse response = lineMessagingClient.replyMessage(
//                     new ReplyMessage(replyToken, messages)).get();
//         } catch (InterruptedException | ExecutionException e) {
//             throw new RuntimeException(e);
//         }
//     }

// }
