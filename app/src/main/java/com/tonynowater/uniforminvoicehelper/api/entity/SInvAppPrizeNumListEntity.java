package com.tonynowater.uniforminvoicehelper.api.entity;

/**
 * Created by tonyliao on 2018/3/16.
 */
public class SInvAppPrizeNumListEntity extends BaseEntity {

    /**
     * fifthPrizeAmt : 0001000
     * firstPrizeAmt : 0200000
     * firstPrizeNo1 : 03696891
     * firstPrizeNo10 :
     * firstPrizeNo2 : 79882491
     * firstPrizeNo3 : 77486437
     * firstPrizeNo4 :
     * firstPrizeNo5 :
     * firstPrizeNo6 :
     * firstPrizeNo7 :
     * firstPrizeNo8 :
     * firstPrizeNo9 :
     * fourthPrizeAmt : 0004000
     * invoYm : 10612 開獎期別(yyyyMM)
     * secondPrizeAmt : 0040000
     * sixthPrizeAmt : 0000200
     * 增開六獎
     * sixthPrizeNo1 : 055
     * sixthPrizeNo2 : 816
     * sixthPrizeNo3 : 292
     * sixthPrizeNo4 :
     * sixthPrizeNo5 :
     * sixthPrizeNo6 :
     * spcPrizeAmt : 2000000
     * spcPrizeNo : 67035249 特獎
     * spcPrizeNo2 :
     * spcPrizeNo3 :
     * superPrizeAmt : 10000000
     * superPrizeNo : 75350343 千萬特獎
     * thirdPrizeAmt : 0010000
     * timeStamp : {"date":25,"day":4,"hours":14,"minutes":27,"month":0,"seconds":53,"time":1516861673000,"timezoneOffset":-480,"year":118}
     * updateDate : 1070125
     * v : 0.2
     * code : 200
     * msg : 查詢成功
     */

    public String fifthPrizeAmt;
    public String firstPrizeAmt;
    public String firstPrizeNo1;
    public String firstPrizeNo10;
    public String firstPrizeNo2;
    public String firstPrizeNo3;
    public String firstPrizeNo4;
    public String firstPrizeNo5;
    public String firstPrizeNo6;
    public String firstPrizeNo7;
    public String firstPrizeNo8;
    public String firstPrizeNo9;
    public String fourthPrizeAmt;
    public String invoYm;
    public String secondPrizeAmt;
    public String sixthPrizeAmt;
    public String sixthPrizeNo1;
    public String sixthPrizeNo2;
    public String sixthPrizeNo3;
    public String sixthPrizeNo4;
    public String sixthPrizeNo5;
    public String sixthPrizeNo6;
    public String spcPrizeAmt;
    public String spcPrizeNo;
    public String spcPrizeNo2;
    public String spcPrizeNo3;
    public String superPrizeAmt;
    public String superPrizeNo;
    public String thirdPrizeAmt;
    public TimeStampEntity timeStamp;
    public String updateDate;

    public static class TimeStampEntity {
        /**
         * date : 25
         * day : 4
         * hours : 14
         * minutes : 27
         * month : 0
         * seconds : 53
         * time : 1516861673000
         * timezoneOffset : -480
         * year : 118
         */

        public int date;
        public int day;
        public int hours;
        public int minutes;
        public int month;
        public int seconds;
        public long time;
        public int timezoneOffset;
        public int year;
    }
}
