package com.example.connectivity;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

/**
 * 自定义弹出框
 * Created by 杨航 on 2016/11/10.
 */

public class SelectPicPopupWindow extends PopupWindow {
    private Button alert_take_photo,alert_pick_photo,alert_cancel;
    private View mMenuView;

    public SelectPicPopupWindow(Activity context, View.OnClickListener itemsOnClick){
        super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.alert_dialog,null);
        alert_take_photo = (Button)mMenuView.findViewById(R.id.alert_take_photo);
        alert_pick_photo = (Button)mMenuView.findViewById(R.id.alert_pick_photo);
        alert_cancel = (Button)mMenuView.findViewById(R.id.alert_cancel);
        alert_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //销毁弹出框
                dismiss();
            }
        });
        //设置按钮监听
        alert_pick_photo.setOnClickListener(itemsOnClick);
        alert_take_photo.setOnClickListener(itemsOnClick);
        //设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        //设置SelectPicPopupWindow 弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        //this.setAnimationStyle(R.style.Popu);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        //mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        mMenuView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int height = mMenuView.findViewById(R.id.pop_layout).getTop();
                int y=(int) motionEvent.getY();
                if(motionEvent.getAction()==MotionEvent.ACTION_UP);{
                    if(y<height){
                        dismiss();
                    }
                }
                return true;
            }
        });
    }
}
