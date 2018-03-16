package com.tonynowater.uniforminvoicehelper.api.entity;

import java.util.List;

/**
 * Created by tonyliao on 2018/3/16.
 */

public class SCarrierInvoiceDetailEntity extends BaseEntity {

    /**
     * v : 0.3
     * code : 200
     * msg : 執行成功
     * invNum : AC28103667
     * sellerName : 統一超商股份有限公司台中市第三八一分公司
     * amount : 250
     * invStatus : 2
     * invPeriod : 10702
     * invDate : 20180216
     * invoiceTime : 21:04:04
     * sellerBan : 24792024
     * sellerAddress : 台中市大里區中興路2段480號
     * details : [{"rowNum":1,"description":"(新)寶亨9號mojito雙晶球香菸","quantity":"2","unitPrice":"100","amount":200},{"rowNum":2,"description":"勁量鈕扣型鋰電池20163V1入","quantity":"1","unitPrice":"50","amount":50},{"rowNum":3,"description":"超跑點數","quantity":"1","unitPrice":"0","amount":0}]
     */

    public String invNum;
    public String sellerName;
    public int amount;
    public String invStatus;
    public String invPeriod;
    public String invDate;
    public String invoiceTime;
    public String sellerBan;
    public String sellerAddress;
    public List<DetailsEntity> details;

    public static class DetailsEntity {
        /**
         * rowNum : 1
         * description : (新)寶亨9號mojito雙晶球香菸
         * quantity : 2
         * unitPrice : 100
         * amount : 200
         */

        public int rowNum;
        public String description;
        public String quantity;
        public String unitPrice;
        public int amount;
    }
}
