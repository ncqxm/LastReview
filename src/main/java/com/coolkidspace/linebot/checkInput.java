package com.coolkidspace.linebot;

public class checkInput{

    public String text;
    public boolean work =false;


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isWork() {
        return work;
    }

    public void setWork(boolean work) {
        this.work = work;
    }

    public String checkText(){
        return text;
    }
   public String checkText(String text) {

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
}
    
