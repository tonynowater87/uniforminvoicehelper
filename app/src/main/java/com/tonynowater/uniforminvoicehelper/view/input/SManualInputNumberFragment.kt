package com.tonynowater.uniforminvoicehelper.view.input

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.tonynowater.uniforminvoicehelper.R
import com.tonynowater.uniforminvoicehelper.base.SBaseFragment
import com.tonynowater.uniforminvoicehelper.data.net.api.dto.SInvAppPrizeNumListDTO
import com.tonynowater.uniforminvoicehelper.util.STimeUtil
import kotlinx.android.synthetic.main.fragment_manual_input.*

class SManualInputNumberFragment : SBaseFragment<SManualInputNumberPresenter>(), SManualInputNumberPresenter.IManualInputNumberView, View.OnClickListener {

    override fun showData(dto: SInvAppPrizeNumListDTO) {
        tv_prize_number_term.text = "${STimeUtil.getCurrentInvoiceTermShowFormat()}[${dto.getSixthAndAdditionSixthString()}]"
    }

    private val mTextWatcher = object : TextWatcher {

        lateinit var beforeString: String

        override fun afterTextChanged(s: Editable?) {}

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            beforeString = s.toString()
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            et_input_field.removeTextChangedListener(this)
            mPresenter.afterTextChanged(beforeString, s.toString())
            et_input_field.addTextChangedListener(this)
        }
    }

    override fun getInputView(): EditText = et_input_field

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button_clear -> et_input_field.setText("")
            R.id.button_switch_month -> showToast("月份")
            else -> {
                et_input_field.setText((v as Button).text)
            }
        }
    }

    override fun getLayoutId() = R.layout.fragment_manual_input

    override fun initView() {
        mPresenter.attach(this)
        mPresenter.queryPrizeList()
        button0.setOnClickListener(this)
        button1.setOnClickListener(this)
        button2.setOnClickListener(this)
        button3.setOnClickListener(this)
        button4.setOnClickListener(this)
        button5.setOnClickListener(this)
        button6.setOnClickListener(this)
        button7.setOnClickListener(this)
        button8.setOnClickListener(this)
        button9.setOnClickListener(this)
        button_clear.setOnClickListener(this)
        button_switch_month.setOnClickListener(this)
        et_input_field.addTextChangedListener(mTextWatcher)
    }
}
