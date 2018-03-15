package com.tonynowater.uniforminvoicehelper.api.entity;

import java.util.List;

/**
 * 載具發票表頭查詢Entity
 *
 * Created by tonyliao on 2018/3/14.
 */
public class SRiderInvoiceHeaderEntity extends BaseEntity {

    /**
     * v : 0.3
     * code : 200
     * msg : 執行成功
     * onlyWinningInv : N
     * details : [{"rowNum":1,"invNum":"AB99147856","invDate":{"date":3,"day":6,"hours":0,"minutes":0,"month":2,"seconds":0,"time":1517587200000,"timezoneOffset":-480,"year":107},"sellerName":"統一超商股份有限公司台中市第三三三分公司","invStatus":"2","invDonatable":true,"cardType":"3J0002","cardNo":"/W6MU8BQ","amount":66,"invPeriod":"10702","invoiceTime":"10:08:00","sellerBan":"53672174","sellerAddress":"台中市大里區國光路二段257號","donateMark":0},{"rowNum":2,"invNum":"AC28103667","invDate":{"date":16,"day":5,"hours":0,"minutes":0,"month":2,"seconds":0,"time":1518710400000,"timezoneOffset":-480,"year":107},"sellerName":"統一超商股份有限公司台中市第三八一分公司","invStatus":"2","invDonatable":true,"cardType":"3J0002","cardNo":"/W6MU8BQ","amount":250,"invPeriod":"10702","invoiceTime":"21:04:04","sellerBan":"24792024","sellerAddress":"台中市大里區中興路2段480號","donateMark":0}]
     */

    public String onlyWinningInv;
    public List<SRiderInvoiceHeaderDetailsEntity> details;

    public static class SRiderInvoiceHeaderDetailsEntity {
        /**
         * rowNum : 1
         * invNum : AB99147856
         * invDate : {"date":3,"day":6,"hours":0,"minutes":0,"month":2,"seconds":0,"time":1517587200000,"timezoneOffset":-480,"year":107}
         * sellerName : 統一超商股份有限公司台中市第三三三分公司
         * invStatus : 2
         * invDonatable : true
         * cardType : 3J0002
         * cardNo : /W6MU8BQ
         * amount : 66
         * invPeriod : 10702
         * invoiceTime : 10:08:00
         * sellerBan : 53672174
         * sellerAddress : 台中市大里區國光路二段257號
         * donateMark : 0
         */

        public int rowNum;
        public String invNum;
        public InvDateBean invDate;
        public String sellerName;
        public String invStatus;
        public boolean invDonatable;
        public String cardType;
        public String cardNo;
        public int amount;
        public String invPeriod;
        public String invoiceTime;
        public String sellerBan;
        public String sellerAddress;
        public int donateMark;

        public static class InvDateBean {
            /**
             * date : 3
             * day : 6
             * hours : 0
             * minutes : 0
             * month : 2
             * seconds : 0
             * time : 1517587200000
             * timezoneOffset : -480
             * year : 107
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
}
