package com.tonynowater.uniforminvoicehelper.data.net.api.dto

import com.tonynowater.uniforminvoicehelper.R
import com.tonynowater.uniforminvoicehelper.SApplication
import com.tonynowater.uniforminvoicehelper.util.SNumberFormatUtil

/**
 * Created by tonyliao on 2018/3/16.
 */
data class SInvAppPrizeNumListDTO(
        val invTerm: String
        , val prizeAmtList: Map<String, String>
        , val superPrizeNo: String
        , var spcPrizeNo: List<String>
        , var firstPrizeNo: List<String>
        , var sixPrizeNo: List<String>) {

    enum class PrizeType {
        SUPER, SPECIAL, FIRST, SECOND, THIRD, FOURTH, FIFTH, SIXTH, ADDITION_SIXTH
    }

    private val NEWLINE = "\n"

    data class PrizeNumberDataObject(val title: String, val number: List<String>, val value: String)

    fun getInvoiceTermText(): String {
        val year = invTerm.substring(0, 3)
        val month2 = SNumberFormatUtil.formatNumberWithThousand(invTerm.substring(invTerm.length - 2, invTerm.length))
        val month1 = (month2.toInt() - 1).toString()
        return "${year}年$month1~${month2}月"
    }

    fun getSpcPrizeNumbers(): String {
        var numbers = ""
        for (number in spcPrizeNo) {
            numbers += "$number$NEWLINE"
        }
        return substractLastNewLine(numbers)
    }

    fun getFirstPrizeNumbers(): String {
        var numbers = ""
        for (number in firstPrizeNo) {
            numbers += "$number$NEWLINE"
        }
        return substractLastNewLine(numbers)
    }

    fun getSecondToSixthPrizeNumbers(type: PrizeType): String {
        var numbers = ""
        for (number in firstPrizeNo) {
            when (type) {
                PrizeType.SECOND -> {
                    numbers += "${number.substring(number.length - 7, number.length)}$NEWLINE"
                }
                PrizeType.THIRD -> {
                    numbers += "${number.substring(number.length - 6, number.length)}$NEWLINE"
                }
                PrizeType.FOURTH -> {
                    numbers += "${number.substring(number.length - 5, number.length)}$NEWLINE"
                }
                PrizeType.FIFTH -> {
                    numbers += "${number.substring(number.length - 4, number.length)}$NEWLINE"
                }
                PrizeType.SIXTH -> {
                    numbers += "${getLastThreeNumbers(number)}$NEWLINE"
                }
            }
        }

        if (type == PrizeType.ADDITION_SIXTH) {
            for (number in sixPrizeNo) {
                numbers += "$number$NEWLINE"
            }
        }

        return substractLastNewLine(numbers)
    }

    /**
     * @return 傳入字串的後三碼
     */
    private fun getLastThreeNumbers(number: String) = number.substring(number.length - 3, number.length)

    /** @return List<String> 所有六獎號碼 */
    fun getSixthAndAdditionSixthList(): List<String> {
        var numbers = getSecondToSixthPrizeNumbers(PrizeType.SIXTH)
        numbers += NEWLINE
        for (number in sixPrizeNo) {
            numbers += "$number$NEWLINE"
        }

        return substractLastNewLine(numbers).split(NEWLINE)
    }

    /**
     * @return List<String> 所有Special&Super號碼的後三碼
     */
    fun getSpecialPrizeNumberList(): List<String> {
        val numbers: MutableList<String> = arrayListOf()
        for (number in spcPrizeNo) {
            numbers.add(0, getLastThreeNumbers(number))
        }
        numbers.add(0, getLastThreeNumbers(superPrizeNo))
        return numbers.toList()
    }

    /** @return String 所有六獎號碼 */
    fun getSixthAndAdditionSixthString(): String {
        val numbers = getSixthAndAdditionSixthList()
        var result = ""
        for (number in numbers) {
            result += "$number, "
        }
        return substractCommaAndSpace(result)
    }

    private fun substractLastNewLine(number: String) = if (number.isNotEmpty()) number.substring(0, number.length - 1) else number

    private fun substractCommaAndSpace(number: String) = if (number.isNotEmpty()) number.substring(0, number.length - 2) else number

    /**
     * @param enum 獎項的列舉
     * @return 各獎項對應的標題、號碼、金額
     */
    fun getDataObject(enum: PrizeType): PrizeNumberDataObject {
        val titles = SApplication.mInstance.applicationContext.resources.getStringArray(R.array.prize_name_array)
        val descriptions = SApplication.mInstance.applicationContext.resources.getStringArray(R.array.prize_description_array)
        val amt = prizeAmtList.getOrDefault(enum.name, "")
        return when (enum) {
            PrizeType.SUPER -> PrizeNumberDataObject(titles[0], listOf(superPrizeNo), amt)
            PrizeType.SPECIAL -> PrizeNumberDataObject(titles[1], spcPrizeNo, amt)
            PrizeType.FIRST -> PrizeNumberDataObject(titles[2], firstPrizeNo, amt)
            PrizeType.SECOND -> PrizeNumberDataObject(titles[3], listOf(descriptions[3]), amt)
            PrizeType.THIRD -> PrizeNumberDataObject(titles[4], listOf(descriptions[4]), amt)
            PrizeType.FOURTH -> PrizeNumberDataObject(titles[5], listOf(descriptions[5]), amt)
            PrizeType.FIFTH -> PrizeNumberDataObject(titles[6], listOf(descriptions[6]), amt)
            PrizeType.SIXTH -> PrizeNumberDataObject(titles[7], listOf(descriptions[7]), amt)
            PrizeType.ADDITION_SIXTH -> PrizeNumberDataObject(titles[8], sixPrizeNo, prizeAmtList[PrizeType.SIXTH.name]!!)
        }
    }
}