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
public class LineBotController implements checkInput{

    // -------------------------Attribute-------------------------------------
    public String text;
    private boolean work = false;

    // -----------------------End of Attribute------------------------

    @Autowired
    private LineMessagingClient lineMessagingClient;

    // ---------------------- Start coding from here-----------------------------------

    // Method
    @EventMapping // receive message from user
    public void handleTextMessage(MessageEvent<TextMessageContent> event) {
        log.info(event.toString());
        TextMessageContent message = event.getMessage();
        if (work) {
            getTypeMovie(event.getReplyToken(), event, message);
        } else
            handleTextContent(event.getReplyToken(), event, message);
    }

    // checktext ที่ user input เข้ามา
    public String checkText(String text) {

        String usermessage;

        if ((text.equals("รีวว")) || (text.equals("รัวิว")) || (text.equals("รีววิ"))
                || (text.equals("รัววิ")) || (text.equals("review")) || (text.equals("Review"))
                || (text.equals("rEVIEW")) || (text.equals("eview")) || (text.equals("revirw"))
                || (text.equals("Reviw")) || (text.equals("iu;b;")) || (text.equals("IU;B;"))) {
            text = "รีวิว";
        } else if ((text.equals("รีววหนัง")) || (text.equals("รัวิวหนัง")) || (text.equals("หนัง"))
                || (text.equals("หนีง")) || (text.equals("movie")) || (text.equals("review movie"))
                || (text.equals("Review movie")) || (text.equals("Review Movie")) || (text.equals("review Movie"))) {
            text = "รีวิวหนัง";
        } else if ((text.equals("รีววซีรีส์")) || (text.equals("รีววซีรีส์")) || (text.equals("รีวิววีรีส์"))
                || (text.equals("รีวิวซีรีย์")) || (text.equals("รีวิวซีรี่")) || (text.equals("รีวิวซีรี่ส์"))
                || (text.equals("ซีรี่ส์")) || (text.equals("ซีรีย์")) || (text.equals("ซีรี่"))
                || (text.equals("ซีรี่ย์"))
                || (text.equals("Review Series")) || (text.equals("Series")) || (text.equals("review Series"))
                || (text.equals("รีวิววีรีส์"))) {
            text = "รีวิวซีรีส์";
        } else if ((text.equals("ยกเลิก")) || (text.equals("ยพเลิก")) || (text.equals("ยหเลิก"))
                || (text.equals("ยดเลิก")) || (text.equals("ยปเลิก"))
                || (text.equals("นกเลิก")) || (text.equals("บกเลิก"))
                || (text.equals("ยกเลิด")) || (text.equals("ยกเลิเ")) || (text.equals("ยกเลิห"))
                || (text.equals("ยกเลิพ")) || (text.equals("ยกเลิป"))
                || (text.equals("Cancel")) || (text.equals("CANCEL")) || (text.equals("cancel"))) {
            text = "ยกเลิก";
            work = false;
        } else if ((text.equals("ใช้ยังไง")) || (text.equals("ทำยังไง")) || (text.equals("วิธีใช้"))
                || (text.equals("ขอวิธีใช้"))
                || (text.equals("ใช้ยังงาย")) || (text.equals("How to use")) || (text.equals("ใช้เเบบไหน"))
                || (text.equals("ใช้ไงอ่า"))
                || (text.equals("ใช้ไง")) || (text.equals("เริ่มใช้")) || (text.equals("เริ่มต้น"))) {
            text = "วิธีใช้งาน";
        }
        return text;
    }

    // check all code of movie or series that user input
    public String getTypeMovie(String replyToken, Event event, TextMessageContent content) {

        String text = content.getText();
        log.info("Got text message from %s : %s", replyToken, text);

        if (work == true) {
            if ((text.equals("m1")) || (text.equals("M1"))) {
                this.reply(replyToken, Arrays.asList(new TextMessage("คุณเลือก\tm1\tเรื่อง\tThe Adam project"),
                        new TextMessage(
                                "เรื่องราวของ อดัม รี้ด (ไรอัน เรย์โนลด์ส) นักบินหนุ่มที่ได้เดินทางข้ามเวลา หลังจากประสบอุบัติเหตุยานตกในปี 2022 ก็ได้ย้อนเวลากลับไปและแอบเข้าไปในบ้านลึกลับแห่งหนึ่งจึงพบเข้ากับเด็กวัย 13 ปี และต่อมาก็ได้รับการเปิดเผยว่าเป็นร่างในวัยเด็กของตัวเขาเอง เขาทั้งสองจึงต้องร่วมมือกันตามหาพ่อ ( มาร์ค รัฟฟาโล ) พร้อมกับทำภารกิจกอบกู้อนาคต"),
                        new TextMessage("รีวิวแบบเต็มค่ะ : https://zhort.link/GdH"),
                        new TextMessage("Trailer : https://www.youtube.com/watch?v=nQm0wor_qoQ")));
            } else if ((text.equals("m2")) || (text.equals("M2"))) {
                this.reply(replyToken, Arrays.asList(new TextMessage("คุณเลือก\tm2\tเรื่อง\tThe misfits"),
                        new TextMessage(
                                "เรื่องราวของโจรอัจฉริยะอย่าง ริชาร์ด เพซ (เพียร์ซ บรอสแนน) ซึ่งแม้แต่คุกที่มีระบบการรักษาความปลอดภัยขั้นสูงสุดยังหยุดเขาไว้ไม่ได้ หลังจากหลบหนีการไล่ล่าของตำรวจ และเอฟบีไออย่างดุเดือด เพซได้เข้ามาเป็นส่วนหนึ่งของทีมโจรคนนอกคอกในนาม The Misfits โดยมี วิค (รับบทโดย ไมค์ พิรัชต์) เป็นเซียนระเบิดตัวอันตรายประจำทีม ทุกภารกิจของพวกเขาคือ การปล้นเอาคืนคนชั่วเพื่อช่วยเหลือคนถูกรังแก โดยไม่เปิดเผยตัวตน ซึ่งการได้หัวขโมยตัวพ่ออย่างเพซ มาร่วมทีมนำไปสู่ปฏิบัติการปล้นแห่งศตวรรษที่ทั้งลึกลับซับซ้อน และมาพร้อมกับแผนอันล้ำลึก เป้าหมายคือการโจรกรรมทองคำแท่งมูลค่านับล้านเหรียญจากชั้นใต้ดินของคุก ซึ่งมีระบบนิรภัยแน่นหนาที่สุดในโลก ทองคำแท่งเหล่านั้นเป็นท่อน้ำเลี้ยงของ ชัลต์ซ เจ้าพ่อธุรกิจมืดผู้อยู่เบื้องหลังแผนก่อการร้ายทั่วโลก"),
                        new TextMessage("รีวิวแบบเต็มค่ะ : https://zhort.link/GdA"),
                        new TextMessage("Trailer : https://www.youtube.com/watch?v=uIjCiU6ee8Q")));
            } else if ((text.equals("m3")) || (text.equals("M3"))) {
                this.reply(replyToken, Arrays.asList(new TextMessage("คุณเลือก\tm3\tเรื่อง\tRed notice"),
                        new TextMessage(
                                "นักวิเคราะห์อาชญากรของเอฟบีไอตามจับโจรขโมยงานศิลป์ที่ทางการต้องการตัวมากที่สุด แต่กลับกลายมาเป็นคู่หูเฉพาะกิจเพื่อล่าตัวอาชญากรหลบหนีที่นำหน้าอยู่ก้าวหนึ่งเสมอ"),
                        new TextMessage("รีวิวแบบเต็มค่ะ : https://www.sanook.com/movie/117465/"),
                        new TextMessage("Trailer : https://www.youtube.com/watch?v=Pj0wz7zu3Ms")));
            } else if ((text.equals("m4")) || (text.equals("M4"))) {
                this.reply(replyToken, Arrays.asList(new TextMessage("คุณเลือก\tm4\tเรื่อง\tThe divine fury"),
                        new TextMessage(
                                "ยงฮูเป็นนักมวยที่เก่งมากๆไม่เคยเเพ้ใครเเละเป็นคนที่ไม่เชื่อในพระเจ้าเนื่องมาจากปมในวัยเด็ก อยู่มาวันหนึ่งเขาก็มีแผลเป็นที่ฝ่ามือเหมือนพระเยซูซึ่งหมอก็ไม่สามารถให้คำตอบได้ สุดท้ายเขาจึงไปพบบาทหลวงเพื่อหวังจะรักษาแผล ไปๆมาๆด้วยความรู้สึกบางอย่างยงฮูก็ได้เข้าไปเป็นฝ่ายช่วย บาทหลวงอัน ที่เกือบตายมาจากการไล่ผีแทนเเละเขาก็เริ่มเรียนรู้พลังพิเศษที่สามารถขับไล่ปีศาจได้เเค่สัมผัสเเล้วราดน้ำมนต์ใส่(เหมือนเลือดของเขาเป็นไฟน้ำมนต์เป็นน้ำมัน) ทำให้ช่วยคนบริสุทธิ์ไว้ได้แต่เรื่องราวก็ไม่ได้ง่ายขนาดนั้นเพราะ จอมมารจีชินที่เป็นบาตรหลวงเหมือนกันเเต่กลับบูชาซาตานก็ได้มาขัดขวางเเละการต่อสู้อันดุเดือดก็ได้เริ่มขึ้น"),
                        new TextMessage("รีวิวแบบเต็มค่ะ : https://zhort.link/GdB"),
                        new TextMessage("Trailer : https://www.youtube.com/watch?v=jdvRbAG00p4")));
            } else if ((text.equals("m5")) || (text.equals("M5"))) {
                this.reply(replyToken, Arrays.asList(new TextMessage("คุณเลือก\tm5\tเรื่อง\tHunter killer"),
                        new TextMessage(
                                "Hunter Killer (2018) สงครามอเมริกาผ่ารัสเซีย เป็นเรื่องราวที่เริ่มต้นด้วยการหายไปของเรือดำน้ำอเมริกันลำหนึ่งในทะเลหลังบ้านของรัสเซีย โจ กลาส (รับบทโดย เจอราร์ด บัตเลอร์) จึงต้องรับหน้าที่ผู้บังคับการคนใหม่ของเรือ ‘ยูเอสเอส อาร์คันซอ’ เพื่อปฏิบัติภารกิจกู้ภัย ในขณะเดียวกันหน่วยซีลของกองทัพเรือได้ถูกส่งเข้าไปชิงตัวประธานาธิบดีรัสเซียที่ถูกจับเป็นตัวประกันภายในฐานทัพเรือโพลิอานี เนื่องจากเกิดการรัฐประหารโดยรัฐมนตรีกระทรวงกลาโหม ดังนั้นทุกฝ่ายจึงต้องทำงานภายใต้ความกดดันและแข่งกับเวลา เพราะเดิมพันในครั้งนี้คือสงครามใหญ่ที่กำลังจะปะทุขึ้น"),
                        new TextMessage("รีวิวแบบเต็มค่ะ : https://www.siaminter.com/movie/1551"),
                        new TextMessage("Trailer : https://www.youtube.com/watch?v=mnP_z3qXDCQ")));
            } else if ((text.equals("m6")) || (text.equals("M6"))) {
                this.reply(replyToken, Arrays.asList(new TextMessage("คุณเลือก\tm6\tเรื่อง\tThe old guard"),
                        new TextMessage(
                                "ว่าด้วยเรื่องราวของทีมมนุษย์อมตะซึ่งมีชีวิตที่ยืนยาวมาแล้วหลายศตวรรษ พวกเขารับใช้ความถูกต้องและช่วยเหลือเหล่าผู้คนที่ตกทุกข์ได้ยากภายใต้ตัวตนที่เสมือนเงาไม่ปรากฏตัวต่อสาธารณะเพื่อปกปิดฐานะที่แท้จริง ต่อมาพวกเขาได้รับการว่าจ้างให้ทำภารกิจช่วยชีวิตเด็ก ๆ ที่ถูกผู้ร้ายจับเป็นตัวประกัน แต่เมื่อพวกเขาได้ไปยังพิกัดของภารกิจนั้น พวกเขาก็พบว่าถูกตลบหลังจัดฉากเพื่อให้ได้มาซึ่งข้อมูลที่ชี้ตัวพวกเขาได้ หลังจากที่เอาชีวิตรอดออกมาได้ทีมอมตะก็ถูกไล่ล่าจากพวกมนุษย์ที่หวังผลประโยชน์ในตัวพวกเขา เรื่องเหล่าของทีมมนุษย์อมตะจะจบลงอย่างไร แล้วใครกันที่จัดฉากพวกเขา สามารถติดตามชมได้ที่ Netflix"),
                        new TextMessage("รีวิวแบบเต็มค่ะ : https://www.sanook.com/movie/100987/"),
                        new TextMessage("Trailer : https://www.youtube.com/watch?v=qhsJGtg3OfY")));
            } else if ((text.equals("m7")) || (text.equals("M7"))) {
                this.reply(replyToken, Arrays.asList(new TextMessage("คุณเลือก\tm7\tเรื่อง\tPee nak 3(พี่นาค3)"),
                        new TextMessage(
                                "พี่นาค 3 เป็นเรื่องราวของ อ๊อด ที่กำลังจะเตรียมตัวเข้าพิธีบวชในเร็วๆ วันนี้ เมื่อใกล้ฤกษ์งามยามดี บอลลูน, เฟิร์ส และ โทมินจุน ได้มุ่งหน้าไปยังวัด เพื่อจะร่วมพิธีบวชของอ๊อด แต่กลับพบว่าเขากำลังป่วยหนักเพราะต้องคำสาปจากอาถรรพ์ของกำไล ที่เขาได้ขุดพบเจอโดยบังเอิญ และทำให้ดวงวิญญาณอาฆาตแค้นของ นาคคำ จากในอดีตชาติ ได้ปรากฏกายจองเวรเขากับเพื่อนๆ ที่ต้องแข่งกับเวลาเพื่อไขปริศนาและหาวิธีล้างคำสาปนี้ให้สิ้นซาก ก่อนที่ทุกอย่างจะสายจนเกินไป"),
                        new TextMessage("รีวิวแบบเต็มค่ะ : https://entertainment.trueid.net/detail/mx6zoMYPMnyx"),
                        new TextMessage("Trailer : https://www.youtube.com/watch?v=TPUaRbuWYAE")));
            } else if ((text.equals("m8")) || (text.equals("M8"))) {
                this.reply(replyToken, Arrays.asList(new TextMessage("คุณเลือก\tm8\tเรื่อง\tJade dynasty"),
                        new TextMessage(
                                "เรื่องราวของ เสี่ยวฝาน และ จิงอวี่ เด็กชายกำพร้าที่รอดตายจากโศกนาฏกรรมจากหมู่บ้านของเขา แม้พ่อแม่จะถูกฆ่าตายหมด แต่เขาได้รับความช่วยเหลือและได้พบกับอาจารย์สอนและฝึกฝนวิชาจากสำนักชิงหยุน เมื่อความลับของการตายของพ่อแม่เขาปรากฏ เขาจึงออกตามล่าศัตรูเพื่อแก้แค้นให้กับพ่อแม่ของเขาที่จากไป"),
                        new TextMessage("รีวิวแบบเต็มค่ะ : https://zhort.link/GdE"),
                        new TextMessage("Trailer : https://www.youtube.com/watch?v=7QLntC3Fg3E")));
            } else if ((text.equals("m9")) || (text.equals("M9"))) {
                this.reply(replyToken, Arrays.asList(new TextMessage("คุณเลือก\tm9\tเรื่อง\tBlack crab"),
                        new TextMessage(
                                "Black Crab แบล็กแคร็บ (2022) เป็นภาพยนตร์ที่สร้างจากหนังสือชื่อเดียวกันของนักเขียน Jerker Virdborg ผลงานการกำกับของ อดัม เบิร์ก ที่เรื่องนี้รับหน้าที่เขียนบทร่วมกับ เพลเล รอดสตรอม บอกเล่าเรื่องราวช่วงสงครามกลางเมืองในประเทศสวีเดน ที่ฐานทัพของฝ่ายทหารกำลังถูกรุกราน คาโรลิน เอียดห์ ทหารหญิงฝีมือดีที่พัดพรากจากลูกสาววัยรุ่น ได้รับคัดเลือกให้ปฏิบัติภาระกิจลับร่วมกับทหารผู้กล้าอีก 5 คน เพื่อขนพัสดุชิ้นสำคัญข้ามน้ำทะเลที่กลายเป็นน้ำแข็ง ในเส้นทางหลังแนวข้าศึก จากเมืองเท็สเซอนอยไปยังอีกฐานทัพหนึ่งที่เกาะเออเดอ ซึ่งอยู่ห่างไปเกินกว่า 100 กิโลเมตร โดยมีข้อตกลงว่าหากทำภารกิจสำเร็จเธอจะได้พบกับลูกสาวทันที"),
                        new TextMessage("รีวิวแบบเต็มค่ะ : https://zhort.link/GdF"),
                        new TextMessage("Trailer : https://www.youtube.com/watch?v=fmjKsL_-rfw")));
            } else if ((text.equals("m10")) || (text.equals("M10"))) {
                this.reply(replyToken, Arrays.asList(new TextMessage("คุณเลือก\tm10\tเรื่อง\tGet him girl(ส้มป่อย)"),
                        new TextMessage(
                                "'ส้มป่อย'(น้ำตาล พิจักขณา) สาวลำพูน สาวโสดขาแรง สายฮา ที่ไม่อยากติดแหง็กใช้ชีวิตที่เหลืออยู่ในบ้านเกิด การได้แฟนดี ๆ ไปใช้ชีวิตคูล ๆ อยู่กรุงเทพฯ ถือเป็นลาภอันประเสริฐ ความหวังเดียวที่จะช่วยให้ฝันเป็นจริงคือต้องหาแฟนเป็นหนุ่มชาวกรุง และแล้วฟ้าก็ประทาน 'แวน' (ป๊อป ธัชทร) ยูทูปเบอร์สายท่องเที่ยวมาให้ ส้มป่อยจึงโร่ปรึกษา 'แซ้ป' (ตี๋ ธนพล) เจ้าเข้าทรงสายแว้นท์นั่งทางในหากลเม็ดมัดใจผู้บ่าวเมืองกรุง โดยที่ส้มป่อยไม่รู้เลยว่าแซ้ปแอบชอบเธอ จู่ ๆ เสน่ห์ก็แรงขึ้นมา ส้มป่อยจะเดินหน้าอ่อยแวนต่อ หรือพอแค่นี้ ก็ต้องตามไปลุ้นกัน!"),
                        new TextMessage("รีวิวแบบเต็มค่ะ : https://entertainment.trueid.net/detail/GgE44NJ7BNe8"),
                        new TextMessage("Trailer : https://www.youtube.com/watch?v=GOIrHJRUAYU")));
            }
            // ------------------------------รหัส
            // ซีรีส์---------------------------------------------

            else if ((text.equals("s1")) || (text.equals("S1"))) {
                this.reply(replyToken, Arrays.asList(new TextMessage("คุณเลือก\ts1\tเรื่อง\tBusiness Proposal"),
                        new TextMessage(
                                "เรื่องราวของซีรีส์ Business Proposal เริ่มต้นขึ้นเมื่อ ชินฮารี (รับบทโดย คิมเซจอง) นักวิจัยด้านอาหารประจำบริษัท จีโอฟู้ดส์ (goFOOD) ถูกไหว้วานให้ไปนัดบอดแทนเพื่อนรักอย่าง จินยองซอ (รับบทโดย ซอลอินฮา) ด้วยเนื้อหาเบาสมองไม่ต้องประลองปัญญาอะไรมาก เต็มอิ่มทั้งความโรแมนติกและคอมเมดี้ที่ปรุงรสมาอย่างกลมกล่อมประหนึ่งอาหารสำเร็จรูปจัดส่งหน้าบ้านแกะทานได้เลย เช่นเดียวกับเรื่องนี้ที่ดูง่ายไม่ซับซ้อน อาจมีดราม่าเรื่องอดีตฝังใจของตัวละครพระเอกแต่เชื่อว่าไม่หนักหน่วงจนเสียน้ำตา ที่สำคัญเรื่องนี้ Netflix แปลไทยได้อรรถรสดีมาก ศัพท์แสงชาวเน็ตมาเต็ม เอาเป็นว่ามู้ดแอนด์โทนโดยรวมการันตีความบันเทิงระดับห้าดาว ใครอยากพักผ่อนร่างกายจิตใจที่เหนื่อยล้าเปิดเรื่องนี้มารับรองว่าได้รีเฟรชตัวเองอย่างแน่นอน"),
                        new TextMessage("รีวิวแบบเต็มค่ะ : https://intrend.trueid.net/article/ -trueidintrend_266759"),
                        new TextMessage("Trailer : https://youtu.be/M-PHcxPyasA")));
            } else if ((text.equals("s2")) || (text.equals("S2"))) {
                this.reply(replyToken, Arrays.asList(new TextMessage("คุณเลือก\ts2\tเรื่อง\ttwenty five twenty one"),
                        new TextMessage(
                                "เส้นเรื่องหลักของซีรีส์จะมีศูนย์กลางในปี 1998 นาฮีโด เธอคือลูกสาวของผู้ประกาศข่าวสาวชื่อดังที่ใฝ่ฝันจะเป็นนักกีฬาฟันดาบระดับประเทศเหมือน โกยูริม นักกีฬาระดับเหรียญทองจนถึงขั้นย้ายโรงเรียนและเข้าชมรมฟันดาบแต่ความฝันที่อยากจะสานสัมพันธ์กับโกยูริมก็กลายเป็นอากาศเมื่ออีกฝ่ายไม่รับในไมตรีของเธอและมองเป็นคู่แข่งเท่านั้น จุดเด่นของ ‘Twenty Five, Twenty One’ คือการกำหนดให้เรื่องราว 2 ยุคที่ถูกเล่าตัดสลับกันมีเหตุการณ์ร่วมสมัยเกิดขึ้นทั้งวิกฤตเศรษฐกิจที่ในซีรีส์ใช้ชื่อว่า “วิกฤติ IMF” เพื่อบอกเงื่อนไขอะไรหลาย ๆ อย่างในปี 1998"),
                        new TextMessage(
                                "รีวิวแบบเต็มค่ะ : https://www.beartai.com/lifestyle/movie-series-review/994091"),
                        new TextMessage("Trailer : https://youtu.be/B1-HXPccaxk")));
            } else if ((text.equals("s3")) || (text.equals("S3"))) {
                this.reply(replyToken, Arrays.asList(
                        new TextMessage("คุณเลือก\ts3\tเรื่อง\tForecasting Love and Weather"),
                        new TextMessage(
                                "ถึงเวลาพยากรณ์รักไปพร้อมกันกับ Forecasting Love and Weather ซีรีส์โรแมนติกเรื่องใหม่ของ พัคมินยอง ประกบคู่กับนักแสดงรุ่นน้องอย่าง ซงคัง กับการถ่ายทอดชีวิตการทำงานของผู้คนในกรมอุตุนิยมวิทยาเกาหลี ซึ่งต้องรับมือกับทั้งสภาพอากาศและความสัมพันธ์ที่คาดเดายากไปพร้อมกัน เพื่อออกรายงานข่าวรวมทั้งแจ้งเตือนให้ประชาชนสามารถรับมือกับปรากฏการณ์ธรรมชาติได้อย่างทันท่วงที"),
                        new TextMessage("รีวิวแบบเต็มค่ะ : https://intrend.trueid.net/article/ -trueidintrend_264219"),
                        new TextMessage("Trailer : https://youtu.be/GXYTOzB--8I")));
            } else if ((text.equals("s4")) || (text.equals("S4"))) {
                this.reply(replyToken, Arrays.asList(new TextMessage("คุณเลือก\ts4\tเรื่อง\tAll of Us Are Dead"),
                        new TextMessage(
                                "สำหรับจุดเริ่มต้นการเกิดซอมบี้ของเรื่องนี้ไม่มีอะไรแตกต่างออกไปมากนัก ทำนองน้ำผึ้งหยดเดียวจากความอยากรู้อยากเห็นของนักเรียนหญิงคนหนึ่งที่ดันแกว่งเท้าหาเสี้ยนด้วยความสงสัย จนทำให้ถูกหนูทดลองที่ติดเชื้อไวรัสซอบบี้กัดนิ้วจนเกิดภาวะหัวใจหยุดเต้นและไม่สามารถควบคุมตนเองได้ แม้ อีบยองชาน (รับบทโดย คิมบยองชอล) ครูสอนวิทยาศาสตร์ผู้คิดค้นเชื้อไวรัสดังกล่าวพยายามควบคุมเหตุการณ์ไม่ให้บานปลาย แต่ท้ายที่สุดเหตุการณ์อันร้ายแรงประหนึ่งวันสิ้นโลกก็ได้เริ่มต้นขึ้นอย่างเป็นทางการ"),
                        new TextMessage("รีวิวแบบเต็มค่ะ : https://intrend.trueid.net/article/-trueidintrend_261559"),
                        new TextMessage("Trailer : https://youtu.be/_fBcXem2C20")));
            } else if ((text.equals("s5")) || (text.equals("S5"))) {
                this.reply(replyToken, Arrays.asList(new TextMessage("คุณเลือก\ts5\tเรื่อง\tMy Roommate is a Gumiho"),
                        new TextMessage(
                                "ว่าด้วยเรื่องราวของ ชินอูยอ (รับบทโดย จางกียง) จิ้งจอกเก้าหางที่มีชีวิตเกือบพันปี ใช้ชีวิตบนโลกมนุษย์จนเรียกว่าพี่ผ่านมาทุกยุคของจริง เป้าหมายหลักของเขาคือการสะสมลูกแก้วจิ้งจอกให้เพียงพอเพื่อจะได้กลายเป็นมนุษย์โดยสมบูรณ์แบบ แต่เหตุการณ์ที่ไม่คาดฝันได้เกิดขึ้นเมื่อ อีดัม (รับบทโดย ฮเยริ) นักศึกษามหาวิทยาลัยที่ดันกลืนลูกแก้วจิ้งจอกของชินอูยอเข้าไปโดยบังเอิญ หากไม่สามารถนำลูกแก้วออกจากตัวอีดัมได้ภายในระยะเวลา 1 ปีเธอจะต้องตาย และชินอูยอจะไม่สามารถกลายร่างเป็นมนุษย์ได้ ทั้งคู่จึงต้องมาอาศัยอยู่ใต้ชายคาเดียวกัน จึงทำให้เกิดเรื่องราวชวนปวดหัวตามมาอีกมากมายนั่นเอง"),
                        new TextMessage("รีวิวแบบเต็มค่ะ : https://intrend.trueid.net/article/-trueidintrend_221095"),
                        new TextMessage("Trailer : https://youtu.be/yWFDfGiSIXc")));
            } else if ((text.equals("s6")) || (text.equals("S6"))) {
                this.reply(replyToken, Arrays.asList(new TextMessage("คุณเลือก\ts6\tเรื่อง\tJuvenile Justice"),
                        new TextMessage(
                                "ชิมอึนซอก ผู้พิพากษาที่มีความเกลียดชังเยาวชนที่ทำผิด มีความยึดมั่นในกระบวนการยุติธรรมอย่างหนักแน่น เมื่อเธอต้องรับมือกับคดีความอันซับซ้อนมากมายในศาลเยาวชน จนได้มาพบกับ ชาแทจู ผู้พิพากษาที่มีความอ่อนโยนและเอาใจใส่เยาวชนเป็นอย่างดี นำไปสู่การพิพากษาคดีและช่วยเหลือเด็กเยาวชนให้ได้รับความเป็นธรรมอย่างถูกต้องที่สุด"),
                        new TextMessage("รีวิวแบบเต็มค่ะ : https://intrend.trueid.net/article/ -trueidintrend_268208"),
                        new TextMessage("Trailer : https://youtu.be/9IbR4Fm-oMs")));
            } else if ((text.equals("s7")) || (text.equals("S7"))) {
                this.reply(replyToken, Arrays.asList(new TextMessage("คุณเลือก\ts7\tเรื่อง\tThe Silent Sea ทะเลสงัด"),
                        new TextMessage(
                                "เป็นซีรีส์ที่ดูเพลิน ดำเนินเรื่องเร็วและใส่ความระทึกมาไม่ยั้งสมกับเป็นแนวไซไฟทริลเลอร์ ไม่ได้ใช้ปรัชญานำจนต้องตีความให้ปวดหัว จึงทำให้เหมาะกับผู้ชมทุกกลุ่มแม้จะไม่ใช้สายอวกาศแต่สามารถดูได้ไม่งงอย่างแน่นอน อีกทั้งการเล่าไปตามสูตรสำเร็จไร้ท่าท่าผาดโผนยิ่งทำให้เข้าถึงง่ายมากขึ้น แม้จะต้องแลกมาด้วยประเด็นขาด ๆ เกิน ๆ จนต้องร้องเอ๊ะอยู่บ้าง แต่หากดูแบบไม่คิดอะไรมากยังคงเป็นอีกเรื่องที่สนุกและสร้างความประทับใจอยู่ไม่น้อย ยังไม่รวมตอนจบที่ค้างคาและเชื่อว่าสามารถต่อยอดไปยังซีซั่นต่อไปได้อีก"),
                        new TextMessage("รีวิวแบบเต็มค่ะ : https://intrend.trueid.net/article/ -trueidintrend_256248"),
                        new TextMessage("Trailer : https://youtu.be/2p1lDmP7s-k")));
            } else if ((text.equals("s8")) || (text.equals("S8"))) {
                this.reply(replyToken, Arrays.asList(new TextMessage("คุณเลือก\ts8\tเรื่อง\tThe Cursed"),
                        new TextMessage(
                                "เปิดด้วยในชนบทเกาหลีมีผู้หญิง 2 คนต้องการให้ทำพิธีสาปแช่งโดยที่ผู้ชายไปมีคนใหม่เลยอยากทำพิธีในตอนนั้นคนทำพิธีจริงๆแล้วเป็นเพียงเด็กน้อยเท่านั้น โดยมีแม่คอยเป็นคนคัดเลือกและดูแล ซีรีส์แสดงให้เห็นว่าคนที่ต้องการสาปแช่งจริงๆแล้วไม่รู้ผลของมันว่าร้ายแรงเพียงใดอาจทำไปเพื่อความสบายใจขอแค่ได้ทำร้ายคนที่มาทำตัวเองน้อยคนนักที่ต้องการให้ถึงตายและในการสาปแช่งทุกครั้งจะส่งผลต่อสุขภาพผู้สาปแช่งเองด้วย และเมื่อความผิดพลาดของพิธีกรรมเกิดขึ้นทำให้มีวิญญาณร้ายสิงร่าง นักข่าวที่ต้องการหาความจริงและตำรวจที่ต้องมาสืบความจริงของเหตุการณ์ที่แฟนเกี่ยวข้อง ชอบตัวละครทุกคนยกเว้นเลขาท่านประธานบอกความลับง่ายมากและขี้กลัวแบบงงๆว่าไปเล่าเรื่องจริงของบริษัททำไม แต่งานแก้ปัญหาอื่นๆทำได้ดี"),
                        new TextMessage("รีวิวแบบเต็มค่ะ : https://intrend.trueid.net/article/ -trueidintrend_265529"),
                        new TextMessage("Trailer : https://youtu.be/j_waWxA_j3U")));
            } else if ((text.equals("s9")) || (text.equals("S9"))) {
                this.reply(replyToken, Arrays.asList(new TextMessage("คุณเลือก\ts9\tเรื่อง\tCracow Monsters"),
                        new TextMessage(
                                "Cracow Monsters เรื่องนี้คือซีรีส์ที่ต้องใช้คำว่าดูสนุกในระดับหนึ่ง สนุกในที่นี้ไม่ใช่ว่าเป็นแนวระทึกขวัญเนื้อหวือวา เดินเรื่องไวอะไรแบบนั้น แต่สนุกไปกับความสดใหม่ของพล็อตเรื่อง ความแปลกของพิธีกรรม ความแปลกในเรื่องความเชื่อของคนยุโรปตะวันออก ถึงแม้จังหวะการเล่าเรื่องมันจะดูช้าไปสักหน่อยแต่ถ้าใครที่เป็นสายสโลว์เบิร์นสามารถจูนเครื่องติดไปกับซีรีส์ได้ก็สามารถฟินยาวๆ 8 ตอนรวดได้สบาย"),
                        new TextMessage("รีวิวแบบเต็มค่ะ : https://intrend.trueid.net/article/-trueidintrend_270786"),
                        new TextMessage("Trailer : https://youtu.be/LlXd24lmxg8")));
            } else if ((text.equals("s10")) || (text.equals("S10"))) {
                this.reply(replyToken, Arrays.asList(new TextMessage("คุณเลือก\ts10\tเรื่อง\tEmily in Paris season 2"),
                        new TextMessage(
                                "Emily in Paris Season 2 นักแสดงยังคงคับคั่งแถมเพิ่มมา 1 หนุ่มลูเชียน ลาวิสเคาท์ หล่อสูสีกับแกเบรียล (ลูคัส บราโว) เลือกไม่ถูกไม่รู้จะเชียร์ใครดี อยากเชียร์ทั้งสองคน  ในซีซั่น 2 ยังคงเป็นซีรีส์ที่ดูสนุกได้ทั้งความโรแมนติกและเสียงหัวเราะ จัดเป็นซีรีส์เบาสมองที่ดูแล้วไม่เบา เพราะได้ความรู้ทั้งเรื่องแฟชั่น การตลาด อาหาร แชมเปญ รวมถึงวัฒนธรรมการทำงานและการใช้ชีวิตของชาวปารีส รวมไปถึงความคิดสร้างสรรค์ต่าง ๆ เป็นซีรีส์ฟีลกู๊ดที่ช่วยเสริมพลังบวกในการทำงาน ความรัก และเพื่อนสนิท  ใครหลงใหลความเป็นฝรั่งเศสหรือ อยากหาซีรีส์ที่ทำให้หลุดจากโลกความเป็นจริงไปโผล่ปารีสล่ะก็ ไม่ควรพลาด Emily in Paris Season 2"),
                        new TextMessage(
                                "รีวิวแบบเต็มค่ะ : https://intrend.trueid.net/article/-netflix-trueidintrend_256027"),
                        new TextMessage("Trailer : https://youtu.be/n22xzIyZr64")));
            } else if (text.equals("ยกเลิก")) {
                this.reply(replyToken, new TextMessage("รีวิวหนัง หรือ รีวิวซีรีส์"));
                work = false;
            } else {
                this.reply(replyToken, Arrays.asList(
                        new TextMessage("POPCORN ไม่เข้าใจค่ะ"),
                        new TextMessage("ไหนลองพิมพ์ใหม่สิ")));
            }
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
            case "ยกเลิก": {
                this.reply(replyToken, new TextMessage("รีวิวหนัง หรือ รีวิวซีรีส์"));
                break;
            }
            case "รีวิวหนัง": {
                this.reply(replyToken, Arrays.asList(new TextMessage("พิมพ์รหัสหนังที่อยากอ่านรีวิวได้เลยค่ะ"),
                        new TextMessage(
                                "m1\tThe Adam project \nm2\tThe misfits \nm3\tRed notice \nm4\tThe divine fury \nm5\tHunter killer \nm6\tThe old guard \nm7\tPee nak 3(พี่นาค3) \nm8\tJade dynasty \nm9\tBlack crab \nm10\tGet him girl(ส้มป่อย)")));
                work = true;
                break;
            }
            case "รีวิวซีรีส์": {
                this.reply(replyToken, Arrays.asList(new TextMessage("พิมพ์รหัสซีรีส์ที่อยากอ่านรีวิวได้เลยค่ะ"),
                        new TextMessage(
                                "s1\tBusiness Proposal \ns2\ttwenty five twenty one \ns3\tForecasting Love and Weather \ns4\tAll of Us Are Dead \ns5\tMy Roommate is a Gumiho \ns6\tJuvenile Justice \ns7\tThe Silent Sea ทะเลสงัด \ns8\tThe Cursed \ns9\tCracow Monsters \ns10\tEmily in Paris season 2")));
                work = true;
                break;
            }
            case "วิธีใช้งาน": {
                this.reply(replyToken, Arrays.asList(new TextMessage(
                        "1.เริ่มต้นพิมพ์ 'รีวิว'\n 2.เลือกประเภทโดยพิมพ์ 'รีวิวหนัง' หรือ 'รีวิวซีรีส์'\n3.เลือกรหัสของหนังหรือซีรีส์ที่อยากอ่านรีวิว\n4.หากต้องการเปลี่ยนประเภทให้กด 'ยกเลิก'"),
                        new TextMessage("หมายเหตุ: หากพบปัญหาให้พิมพ์ 'ยกเลิก ก่อนใช้งาน")));
            }
            default:
                this.reply(replyToken, Arrays.asList(
                        new TextMessage("POPCORN ไม่เข้าใจค่ะ"),
                        new TextMessage("ไหนลองพิมพ์ใหม่สิ")));
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
    // ---------------------- End of Coding-------------------------------------

    @Value
    public static class DownloadedContent {
        Path path;
        String uri;
    }
}