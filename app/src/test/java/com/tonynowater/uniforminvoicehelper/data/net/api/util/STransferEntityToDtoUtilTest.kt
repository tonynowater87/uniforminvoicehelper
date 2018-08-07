package com.tonynowater.uniforminvoicehelper.data.net.api.util

import com.google.gson.Gson
import com.tonynowater.uniforminvoicehelper.data.net.api.entity.SCarrierInvoiceDetailEntity
import com.tonynowater.uniforminvoicehelper.data.net.api.entity.SCarrierInvoiceHeaderEntity
import com.tonynowater.uniforminvoicehelper.data.net.api.entity.SInvAppPrizeNumListEntity
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Created by tonyliao on 2018/3/28.
 */
class STransferEntityToDtoUtilTest {

    private val gson = Gson()
    private val sTransferEntityToDtoUtil = STransferEntityToDtoUtil()

    @Test
    fun transferInvPrizeNumEntityToDTO_1() {
        val reqInvTerm = "10706"
        val json = "{\"fifthPrizeAmt\":\"0001000\",\"firstPrizeAmt\":\"0200000\",\"firstPrizeNo1\":\"03696891\",\"firstPrizeNo10\":\"\",\"firstPrizeNo2\":\"79882491\",\"firstPrizeNo3\":\"77486437\",\"firstPrizeNo4\":\"\",\"firstPrizeNo5\":\"\",\"firstPrizeNo6\":\"\",\"firstPrizeNo7\":\"\",\"firstPrizeNo8\":\"\",\"firstPrizeNo9\":\"\",\"fourthPrizeAmt\":\"0004000\",\"invoYm\":\"10612\",\"secondPrizeAmt\":\"0040000\",\"sixthPrizeAmt\":\"0000200\",\"sixthPrizeNo1\":\"055\",\"sixthPrizeNo2\":\"816\",\"sixthPrizeNo3\":\"292\",\"sixthPrizeNo4\":\"\",\"sixthPrizeNo5\":\"\",\"sixthPrizeNo6\":\"\",\"spcPrizeAmt\":\"2000000\",\"spcPrizeNo\":\"67035249\",\"spcPrizeNo2\":\"\",\"spcPrizeNo3\":\"\",\"superPrizeAmt\":\"10000000\",\"superPrizeNo\":\"75350343\",\"thirdPrizeAmt\":\"0010000\",\"timeStamp\":{\"date\":25,\"day\":4,\"hours\":14,\"minutes\":27,\"month\":0,\"seconds\":53,\"time\":1516861673000,\"timezoneOffset\":-480,\"year\":118},\"updateDate\":\"1070125\",\"v\":\"0.2\",\"code\":\"200\",\"msg\":\"查詢成功\"}"
        val entity = gson.fromJson(json, SInvAppPrizeNumListEntity::class.java)

        val dto = sTransferEntityToDtoUtil.transferInvPrizeNumEntityToDTO(reqInvTerm, entity)
        assertEquals("10706", dto.invTerm)
        assertEquals(3, dto.firstPrizeNo.size)
        assertEquals(8, dto.prizeAmtList.size)
        assertEquals(3, dto.sixPrizeNo.size)
        assertEquals(1, dto.spcPrizeNo.size)
    }

    @Test
    fun transferCarrierHeaderEntityToDTO() {
        val json = "{\"v\":\"0.3\",\"code\":200,\"msg\":\"執行成功\",\"onlyWinningInv\":\"N\",\"details\":[{\"rowNum\":1,\"invNum\":\"BD42245191\",\"cardType\":\"3J0002\",\"cardNo\":\"/W6MU8BQ\",\"sellerName\":\"統一超商股份有限公司台中縣第二０一分公司\",\"invStatus\":\"2\",\"invDonatable\":true,\"amount\":16,\"invPeriod\":\"10704\",\"donateMark\":0,\"invDate\":{\"year\":107,\"month\":3,\"date\":10,\"day\":6,\"hours\":0,\"minutes\":0,\"seconds\":0,\"time\":1520611200000,\"timezoneOffset\":-480},\"sellerBan\":\"27233523\",\"sellerAddress\":\"台中市大里區西榮里益民路二段137號\",\"invoiceTime\":\"19:11:22\"},{\"rowNum\":2,\"invNum\":\"BE00044328\",\"cardType\":\"3J0002\",\"cardNo\":\"/W6MU8BQ\",\"sellerName\":\"統一超商股份有限公司台中市第三三三分公司\",\"invStatus\":\"2\",\"invDonatable\":true,\"amount\":69,\"invPeriod\":\"10704\",\"donateMark\":0,\"invDate\":{\"year\":107,\"month\":3,\"date\":17,\"day\":6,\"hours\":0,\"minutes\":0,\"seconds\":0,\"time\":1521216000000,\"timezoneOffset\":-480},\"sellerBan\":\"53672174\",\"sellerAddress\":\"台中市大里區國光路二段257號\",\"invoiceTime\":\"10:40:07\"},{\"rowNum\":3,\"invNum\":\"BF86939590\",\"cardType\":\"3J0002\",\"cardNo\":\"/W6MU8BQ\",\"sellerName\":\"統一超商股份有限公司台中市第三二八分公司\",\"invStatus\":\"2\",\"invDonatable\":true,\"amount\":68,\"invPeriod\":\"10704\",\"donateMark\":0,\"invDate\":{\"year\":107,\"month\":3,\"date\":19,\"day\":1,\"hours\":0,\"minutes\":0,\"seconds\":0,\"time\":1521388800000,\"timezoneOffset\":-480},\"sellerBan\":\"53666383\",\"sellerAddress\":\"台中市西屯區台灣大道三段688號690號\",\"invoiceTime\":\"18:25:24\"}]}"

        val entity = gson.fromJson<SCarrierInvoiceHeaderEntity>(json, SCarrierInvoiceHeaderEntity::class.java)

        val dtoList = sTransferEntityToDtoUtil.transferCarrierHeaderEntityToDTO(entity)
        assertEquals(3, dtoList.size)

        val dto = dtoList[0]
        assertEquals("3/10(六)", dto.date)
        assertEquals("2018/03/10", dto.formatCEDate)
        assertEquals("統一超商股份有限公司台中縣第二０一分公司", dto.sellerName)
        assertEquals("BD42245191", dto.invoiceNo)
        assertEquals("16", dto.amount)
        assertEquals("${dto.date} ${dto.invoiceNo} ${dto.sellerName}", dto.showedText())
    }

    @Test
    fun transferCarrierDetailEntityToDTO() {
        val json = "{\"v\":\"0.3\",\"code\":200,\"msg\":\"執行成功\",\"invNum\":\"AC28103667\",\"invDate\":\"20180216\",\"sellerName\":\"統一超商股份有限公司台中市第三八一分公司\",\"amount\":250,\"invStatus\":\"2\",\"invPeriod\":\"10702\",\"details\":[{\"rowNum\":\"1\",\"description\":\"(新)寶亨9號mojito雙晶球香菸\",\"quantity\":\"2\",\"unitPrice\":\"100\",\"amount\":200},{\"rowNum\":\"1\",\"description\":\"勁量鈕扣型鋰電池20163V1入\",\"quantity\":\"1\",\"unitPrice\":\"50\",\"amount\":50},{\"rowNum\":\"1\",\"description\":\"超跑點數\",\"quantity\":\"1\",\"unitPrice\":\"0\",\"amount\":0}],\"sellerBan\":\"24792024\",\"sellerAddress\":\"台中市大里區中興路2段480號\",\"invoiceTime\":\"21:04:04\"}"

        val entity = gson.fromJson<SCarrierInvoiceDetailEntity>(json, SCarrierInvoiceDetailEntity::class.java)

        val dtoList = sTransferEntityToDtoUtil.transferCarrierDetailEntityToDTO(entity)
        assertEquals(3, dtoList.size)

        val dto = dtoList[0]
        assertEquals(1, dto.rowNum)
        assertEquals("(新)寶亨9號mojito雙晶球香菸", dto.description)
        assertEquals("2", dto.quantity)
        assertEquals("100", dto.unitPrice)
        assertEquals(200, dto.amount)
    }
}