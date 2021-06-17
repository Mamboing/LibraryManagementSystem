package com.example.librarymanagementsystem.ui.user.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.AttrRes
import com.example.librarymanagementsystem.R


class ItemGroup : FrameLayout {
    private var itemGroupLayout: LinearLayout? = null    //组合控件的布局
    private var titleTv: TextView? = null     //标题
    var contentEdt: TextView? = null     //输入框
    private var jtRightIv: ImageView? = null    //向右的箭头

    constructor(context: Context) : super(context) {
        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView(context)
        initAttrs(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, @AttrRes defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initView(context)
        initAttrs(context, attrs)
    }

    private fun initView(context: Context) {
        val view: View = LayoutInflater.from(context).inflate(R.layout.group, null)
        itemGroupLayout = view.findViewById<View>(R.id.item_group_layout) as LinearLayout
        titleTv = view.findViewById<View>(R.id.title_tv) as TextView
        contentEdt = view.findViewById<View>(R.id.content_edt) as TextView
        jtRightIv = view.findViewById<View>(R.id.jt_right_iv) as ImageView
        addView(view)
    }

    /**
     * 初始化相关属性，引入相关属性
     * @param context
     * @param attrs
     */
    private fun initAttrs(context: Context, attrs: AttributeSet?) {
        //标题的默认字体颜色
        val defaultTitleColor = context.resources.getColor(R.color.firebrick, null)
        //输入框的默认字体颜色
        val defaultEdtColor = context.resources.getColor(R.color.light_blue_A400, null)
        //输入框的默认的提示内容的字体颜色
        val defaultHintColor = context.resources.getColor(R.color.grey, null)
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ItemGroup)
        val title = typedArray.getString(R.styleable.ItemGroup_title)
        val paddingLeft = typedArray.getDimension(R.styleable.ItemGroup_paddingLeft, 15f)
        val paddingRight = typedArray.getDimension(R.styleable.ItemGroup_paddingRight, 15f)
        val paddingTop = typedArray.getDimension(R.styleable.ItemGroup_paddingTop, 5f)
        val paddingBottom = typedArray.getDimension(R.styleable.ItemGroup_paddingTop, 5f)
        val titleSize = typedArray.getDimension(R.styleable.ItemGroup_title_size, 15f)
        val titleColor = typedArray.getColor(R.styleable.ItemGroup_title_color, defaultTitleColor)
        val content = typedArray.getString(R.styleable.ItemGroup_edt_content)
        val contentSize = typedArray.getDimension(R.styleable.ItemGroup_edt_text_size, 15f)
        val contentColor =
            typedArray.getColor(R.styleable.ItemGroup_edt_text_color, defaultEdtColor)
        val hintContent = typedArray.getString(R.styleable.ItemGroup_edt_hint_content)
        val hintColor =
            typedArray.getColor(R.styleable.ItemGroup_edt_hint_text_color, defaultHintColor)
        //val isEditable = typedArray.getBoolean(R.styleable.ItemGroup_isEditable, true)
        //向右的箭头图标是否可见，默认可见
        val showJtIcon = typedArray.getBoolean(R.styleable.ItemGroup_jt_visible, true)
        typedArray.recycle()

        //设置内边距
        itemGroupLayout!!.setPadding(
            paddingLeft.toInt(),
            paddingTop.toInt(), paddingRight.toInt(), paddingBottom.toInt()
        )
        titleTv!!.text = title
        titleTv!!.textSize = titleSize
        titleTv!!.setTextColor(titleColor)
        contentEdt!!.text = content
        contentEdt!!.textSize = contentSize
        contentEdt!!.setTextColor(contentColor)
        contentEdt!!.hint = hintContent
        contentEdt!!.setHintTextColor(hintColor)
        //contentEdt!!.isFocusable = isEditable; //设置输入框是否可以编辑
        //contentEdt!!.isClickable = true;
        //contentEdt!!.keyListener = null;
        jtRightIv!!.visibility = if (showJtIcon) VISIBLE else GONE //设置向右的箭头图标是否可见
    }
}