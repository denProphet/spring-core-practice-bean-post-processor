package com.company;

@Marked
@CustomLogic
@ClassChanged(newClass = TerminatorRefreshed.class)
public class TerminatorQuoter implements Quoter {
    @InjectRandomInt(min = 1, max = 7)
    private int repeatCount;
    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void sayQuote() {
        for (int i = 0; i < repeatCount ; i++) {
            System.out.println("message " + message);
        }

    }
    @Override
    public void say2(){
        System.out.println(message.toUpperCase());
    }
}
