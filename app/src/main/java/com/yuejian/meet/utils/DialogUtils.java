package com.yuejian.meet.utils;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.yuejian.meet.R;


/**
 * ProjectName: MvpRxjavaRetrofitDemo
 * PackageName com.yuejian.meet.utils
 * FileName: com.yuejian.meet.utils.DialogUtils.java
 * Author: ljh
 * Date: 2016-12-12 14:15
 * Description: 对话框的工具类
 */
public class DialogUtils {

    /**
     * 获取一个含有确定取消的对话框
     *
     * @param context 上下文
     * @param msg     文字说明
     * @return 对话框的对象
     */
    public static Dialog createOneBtnDialog(Activity context, String titles, String msg,String negativeButtonTv) {
		/*
         * 获得view填充器对象
		 */
        LayoutInflater inflater = LayoutInflater.from(context);
        /*
         * 得到加载view
         */
        View v = inflater.inflate(R.layout.dialog_tips_layout_one, null);
        TextView message =  v.findViewById(R.id.message);// 提示内容
        TextView title =  v.findViewById(R.id.title);// 提示标题
        ImageView cancel_img =  v.findViewById(R.id.cancel_img);// 提示标题
        Button negativeButton =  v.findViewById(R.id.negativeButton);// 提示文字
        if (msg != null && !msg.equals("")) {
            message.setText(msg);// 设置内容
        }
        if (titles != null && !titles.equals("")) {
            title.setText(titles);// 设置标题
        }

        Dialog loadingDialog = new Dialog(context);// 创建自定义样式dialog
        loadingDialog.setCancelable(true);// 可以用“返回键”取消
        loadingDialog.setCanceledOnTouchOutside(true);//
        loadingDialog.setContentView(v);// 设置布局
        cancel_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               loadingDialog.dismiss();
            }
        });
        negativeButton.setText(negativeButtonTv);
        negativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtil.call(context,msg);
            }
        });
        return loadingDialog;
    }
}
