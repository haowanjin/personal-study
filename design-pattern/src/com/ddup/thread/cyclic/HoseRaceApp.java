package com.ddup.thread.cyclic;

public class HoseRaceApp {
    public static void main(String[] args) {
        int nHorse = 3;
        int pause = 200;
        new HorseRace(nHorse, pause);
    }
}
