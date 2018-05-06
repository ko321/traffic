package com.traffic;

public enum Lamp {
    S2N("N2S","S2W",false) ,S2W("N2E","E2W",false), E2W("W2E","E2S",false), E2S("W2N","N2S",false),
    N2S(null,null,false), N2E(null,null,false), W2E(null,null,false), W2N(null,null,false),
    S2E(null,null,true), E2N(null,null,true), N2W(null,null,true), W2S(null,null,true);

    private  Lamp(String opposite, String next, boolean lighted){
        this.opposite = opposite;
        this.next = next;
        this.lighted = lighted;
    }

    private  Lamp(){};

    private boolean lighted;
    private  String opposite;
    private String next;

    public boolean isLighted(){
        return lighted;
    }

    public void light(){
        this.lighted = true;
        if(opposite != null) {
            Lamp.valueOf(opposite).light();
        }
        System.out.println(name() + "lamp is green,下面总共应该有6个方向  能看到汽车穿过！");
    }
    public Lamp blackOut(){
        this.lighted = false;
        if(opposite != null){
            Lamp.valueOf(opposite).blackOut();
        }

        Lamp nextLamp= null;
        if(next != null) {
            nextLamp = Lamp.valueOf(next);
            System.out.println("绿灯从" + name() + "--------->切换为" + next);
            nextLamp.light();

        }
        return nextLamp;
    }
}
