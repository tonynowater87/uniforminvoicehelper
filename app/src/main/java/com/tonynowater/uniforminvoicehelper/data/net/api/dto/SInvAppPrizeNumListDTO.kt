package com.tonynowater.uniforminvoicehelper.data.net.api.dto

/**
 * Created by tonyliao on 2018/3/16.
 */
data class SInvAppPrizeNumListDTO(val prizeAmtList: List<String>
                                  , val superPrizeNo: String
                                  , var spcPrizeNo: List<String>
                                  , var firstPrizeNo: List<String>
                                  , var sixPrizeNo: List<String>) {

    enum class PrizeType {
        SUPER, SPECIAL, FIRST, SECOND, THIRD, FORTH, FIFTH, SIXTH, ADDITION_SIXTH
    }

    fun getSpcPrizeNumbers(): String {
        var numbers = ""
        for (number in spcPrizeNo) {
            numbers += "$number\n"
        }
        return substractLastNewLine(numbers)
    }

    fun getFirstPrizeNumbers(): String {
        var numbers = ""
        for (number in firstPrizeNo) {
            numbers += "$number\n"
        }
        return substractLastNewLine(numbers)
    }

    fun getSecondToSixthPrizeNumbers(type: PrizeType): String {
        var numbers = ""
        for (number in firstPrizeNo) {
            when (type) {
                PrizeType.SECOND -> {
                    numbers += "${number.substring(number.length - 7, number.length)}\n"
                }
                PrizeType.THIRD -> {
                    numbers += "${number.substring(number.length - 6, number.length)}\n"
                }
                PrizeType.FORTH -> {
                    numbers += "${number.substring(number.length - 5, number.length)}\n"
                }
                PrizeType.FIFTH -> {
                    numbers += "${number.substring(number.length - 4, number.length)}\n"
                }
                PrizeType.SIXTH -> {
                    numbers += "${getLastThreeNumbers(number)}\n"
                }
            }
        }

        if (type == PrizeType.ADDITION_SIXTH) {
            for (number in sixPrizeNo) {
                numbers += "$number\n"
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
        numbers += "\n"
        for (number in sixPrizeNo) {
            numbers += "$number\n"
        }

        return substractLastNewLine(numbers).split("\n")
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
        var numbers = getSixthAndAdditionSixthList()
        var result = ""
        for (number in numbers) {
            result += "$number, "
        }
        return substractCommaAndSpace(result)
    }

    private fun substractLastNewLine(number: String) = if (number.isNotEmpty()) number.substring(0, number.length - 1) else number

    private fun substractCommaAndSpace(number: String) = if (number.isNotEmpty()) number.substring(0, number.length - 2) else number
}