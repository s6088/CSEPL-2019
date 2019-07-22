package com.example.csepl2019;

public class Over {


    private boolean whichTeam;
    private int ball;
    private int over;
    private int run1;
    private int wicket1;
    private int run2;
    private int wicket2;
    private String team1;
    private String team2;
    private String tossStatus;
    private String resultStatus;

    public Over() {
        over = 0;
        ball = 0;
        run1 = 0;
        wicket1 = 0;
        run2 = 0;
        wicket2 = 0;
        whichTeam = false;
        resultStatus = "";
    }

    public Over(String team1, String team2, String tossStatus) {
        this.team1 = team1;
        this.team2 = team2;
        this.tossStatus = tossStatus;
        over = 0;
        ball = 0;
        run1 = 0;
        wicket1 = 0;
        run2 = 0;
        wicket2 = 0;
        whichTeam = false;
        resultStatus = "";
    }

    public void setWhichTeam(boolean whichTeam) {
        this.whichTeam = whichTeam;
    }

    public void setBall(int ball) {
        this.ball = ball;
    }

    public void setOver(int over) {
        this.over = over;
    }



    public void setResultStatus(String resultStatus) {
        this.resultStatus = resultStatus;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public void setTossStatus(String tossStatus) {
        this.tossStatus = tossStatus;
    }

    public boolean isWhichTeam() {
        return whichTeam;
    }

    public int getBall() {
        return ball;
    }

    public int getOver() {
        return over;
    }

    public int getRun1() {
        return run1;
    }

    public void setRun1(int run1) {
        this.run1 = run1;
    }

    public int getWicket1() {
        return wicket1;
    }

    public void setWicket1(int wicket1) {
        this.wicket1 = wicket1;
    }

    public int getRun2() {
        return run2;
    }

    public void setRun2(int run2) {
        this.run2 = run2;
    }

    public int getWicket2() {
        return wicket2;
    }

    public void setWicket2(int wicket2) {
        this.wicket2 = wicket2;
    }

    public String getTeam1() {
        return team1;
    }

    public String getTeam2() {
        return team2;
    }

    public String getTossStatus() {
        return tossStatus;
    }

    public String getResultStatus() {
        return resultStatus;
    }
}
