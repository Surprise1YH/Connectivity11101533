package com.example.connectivity;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by 杨航 on 2016/10/31.
 */
public class MyTopbar extends RelativeLayout {
    //    先要定义一下所要用到的控件 左按钮，右按钮，中间title1
    private Button leftButton, rightButton;
    private TextView tvTitle1;
    //    再声明左右按钮所要用到的属性
    private int leftTextColor;
    private Drawable leftBackGround;
    private String leftText;
    //private float leftTextSize;

    private int rightTextColor;
    private Drawable rightBackGround;
    private String rightText;
    //private float rightTextSize;

    private float title1TextSize;
    private int title1TextColor;
    private String title1;

    //LayoutParams是布局的属性
    private LayoutParams leftParams, rightParams, title1Params;

    private topbarClickListener listener;

    //创建接口
    public interface topbarClickListener {
        public void leftClick();

        public void rightClick();
    }

    //传进来接口型的方法,通过本方法，把模板的值与调用者联系在一起
    public void setOnTopbarClickListener(topbarClickListener listener) {
        this.listener = listener;
    }

    public MyTopbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        //通过TypedArray来获取所自定义的值,在此实例化对象
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MyTopbar);

        //获得左右两边按钮和中间标题的值

        title1TextSize = ta.getDimension(R.styleable.MyTopbar_title1TextSize, 0);
        title1TextColor = ta.getColor(R.styleable.MyTopbar_title1TextColor, 0);
        title1 = ta.getString(R.styleable.MyTopbar_title1);

        //从对象ta中获取自定义的值 MyTopbat是前边atts的文件名，通过下划线_的方式把值连起来,要有默认值0
        leftTextColor = ta.getColor(R.styleable.MyTopbar_leftTextColor, 0);
        leftBackGround = ta.getDrawable(R.styleable.MyTopbar_leftBackground);
        leftText = ta.getString(R.styleable.MyTopbar_leftText);
        //leftTextSize = ta.getDimension(R.styleable.MyTopbar_leftTextSize, 0);

        rightTextColor = ta.getColor(R.styleable.MyTopbar_rightTextColor, 0);
        rightBackGround = ta.getDrawable(R.styleable.MyTopbar_rightBackground);
        rightText = ta.getString(R.styleable.MyTopbar_rightText);
        //rightTextSize = ta.getDimension(R.styleable.MyTopbar_rightTextSize, 0);
        //回收对象资源
        ta.recycle();

        //创建需要的控件
        leftButton = new Button(context);
        rightButton = new Button(context);
        tvTitle1 = new TextView(context);

        //把属性赋给控件
        leftButton.setTextColor(leftTextColor);
        //leftButton.setTextSize(leftTextSize);
        leftButton.setBackground(leftBackGround);
        leftButton.setText(leftText);

        rightButton.setTextColor(rightTextColor);
        //rightButton.setTextSize(rightTextSize);
        rightButton.setBackground(rightBackGround);
        rightButton.setText(rightText);

        tvTitle1.setTextColor(title1TextColor);
        tvTitle1.setTextSize(title1TextSize);
        tvTitle1.setText(title1);
        //让title1居中
        tvTitle1.setGravity(Gravity.CENTER);

        setBackgroundColor(0xFFF59563);
        //定义宽高属性
        leftParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        //定义左按钮的布局规则,TRUE为常量,居左对齐
        leftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
        //为控件添加属性
        this.addView(leftButton, leftParams);

        rightParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        //定义右按钮的布局规则,TRUE为常量,居右对齐
        rightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);
        //为控件添加属性
        this.addView(rightButton, rightParams);

        title1Params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        //定义左按钮的布局规则,TRUE为常量,居左对齐
        title1Params.addRule(RelativeLayout.CENTER_IN_PARENT, TRUE);
        //为控件添加属性

        this.addView(tvTitle1, title1Params);

        leftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.leftClick();
            }
        });

        rightButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.rightClick();
            }
        });
    }

    //设置左按钮的隐藏属性
    public void setLeftIsVisable(boolean flag) {
        if (flag) {
            //显示
            leftButton.setVisibility(View.VISIBLE);
        } else {
            //隐藏
            leftButton.setVisibility(View.GONE);
        }
    }

    public void setRightIsVisable(boolean flag) {
        if (flag) {
            //显示
            rightButton.setVisibility(View.VISIBLE);
        } else {
            //隐藏
            rightButton.setVisibility(View.GONE);
        }
    }
}
