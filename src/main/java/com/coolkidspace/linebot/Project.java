package com.coolkidspace.linebot;

import java.util.Scanner;

class Project {
    // Attribute
    public String text1;
    public String ans;
    public String boredAns;
    public String yesAns;
    public String noAns;

    // Constructor
    // public Project(String text1) {
    // this.text1 = text1;
    // }

    // Method
    public void InputAnswer() {
        switch (text1) {
            case "รีวิว":
                Scanner sc1 = new Scanner(System.in);
                System.out.println("รีวิวหนัง หรือ รีวิวซีรีส์");
                String ans = sc1.nextLine();
                switch (ans) {
                    case "รีวิวหนัง":
                        System.out.println("You should movie review.");
                        System.out.println("5555555555555555555555");
                        break;
                    case "รีวิวซีรีส์":
                        System.out.println("You should series review.");
                        System.out.println("5555555555555555555555");
                        break;
                    default:
                        System.out.println("Looking forward to somthing.");
                        System.out.println("5555555555555555555555");
                }
                break;
            case "หนังยอดฮิต":
                System.out.println("POPCORN is looking interesred movie for you.");
                // add link of interested movie
                break;
            case "ซีรีส์ยอดฮิต":
                System.out.println("POPCORN is looking interesred series for you.");
                break;
            case "เบื่อ":
                Scanner sc2 = new Scanner(System.in);
                System.out.println("Can i recommend you anything interest?");
                String boredAns = sc2.nextLine();
                switch (boredAns) {
                    case "ได้สิ":
                        System.out.println("คุณชอบดูหนังหรือซีรีย์มากกกว่ากัน");
                        String yesAns = sc2.nextLine();
                        switch (yesAns) {
                            case "หนัง":
                                System.out.println("POPCORN is looking interesred movie for you.");
                                // เดี๋ยวเพิ่มไปว่าชอบมั้ย ถ้าไม่ก็แนะนำใหม่
                                break;
                            case "ซีรีย์":
                                System.out.println("POPCORN is looking interesred series for you.");
                                // เดี๋ยวเพิ่มไปว่าชอบมั้ย ถ้าไม่ก็แนะนำใหม่
                                break;
                            default:
                                System.out.println("Sorry, Popcorn don't understand what you input.");
                                break;
                        }
                    case "ไม่":
                        // ตอนพิมพ์ว่า หนัง หรือ ซีรีส์ จะdisplay ไปนอนเหอะมาด้วย ยังหาทางแก้ไม่ได้
                        // แต่วาง case ถูกแล้วนะ งงเว่อ
                        System.out.println("ไปนอนเหอะ");
                        break;
                    default:
                        System.out.println("Oh oo Popcorn don't understand what you input.");
                        break;
                }
        }
    }

}
