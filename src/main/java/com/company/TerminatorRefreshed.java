package com.company;

@CustomLogic
@Marked
public class TerminatorRefreshed extends TerminatorQuoter implements Quoter{
    @Override
    public void sayQuote() {
        System.out.println("gg");
    }

    @Override
    public void say2() {
        System.out.println("gg2");
    }
}
