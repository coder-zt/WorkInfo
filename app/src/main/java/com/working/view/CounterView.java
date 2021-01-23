package com.working.view;

import android.content.Context;
import android.media.Image;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.working.R;
import com.working.utils.ToastUtil;

public class CounterView extends LinearLayout {

    public float maxValue = 9999.0f;
    public float minValue = 0.0f;
    private float num;
    private float oldNum;
    private EditText mEtInput;
    private OnNumberChangedListener mCallback;
    private boolean disableEdit;

    public CounterView(Context context) {
        this(context, null);
    }

    public CounterView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CounterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.custom_counter_layout, this, true);
        initView();
    }

    private void initView() {
        ImageView leftBtn = findViewById(R.id.btn_left);
        leftBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (disableEdit) {
                    return;
                }
                if(num > minValue){
                    num--;
                }else{
                    ToastUtil.showMessage("数据最小为" + minValue);
                }
                showValue();
            }
        });
        ImageView rightBtn = findViewById(R.id.btn_right);
        rightBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (disableEdit) {
                    return;
                }
                if(num < maxValue){
                    num++;
                }else{
                    ToastUtil.showMessage("数据最大为" + maxValue);
                }
                showValue();
            }
        });
        mEtInput = findViewById(R.id.et_input);
        mEtInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String s1 = s.toString();
                if(s1.contains(".")){
                    String[] split = s1.split("\\.");
                    if (split.length ==2 && split[1].length()>4) {
                        String newDecl = split[1].substring(0,4);
                        String newValue = s1.replace(split[1], newDecl);
                        mEtInput.setText(newValue);
                        return;
                    }
                }
                if (s1.length() == 0) {
                    num = 0;
                    mEtInput.setText("0.0");
                }else{
                    float intNum = 0.0f;
                    try {
                        intNum = Float.parseFloat(s1);
                    }catch (Exception e){

                    }
                    if(intNum<minValue){
                        mEtInput.setText(String.valueOf(minValue));
                        ToastUtil.showMessage("数据最小为" + minValue);
                    }else if(intNum > maxValue){
                        mEtInput.setText(String.valueOf(maxValue));
                        ToastUtil.showMessage("数据最大为" + maxValue);
                    }else {
                        num = intNum;
                    }
                }
                if(oldNum != num && mCallback!= null){
                    oldNum = num;
                    mCallback.onNumChanged(num);
                }
//                mEtInput.setSelection(s.length());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public float getNum(){
        return num;
    }

    public void setNum(float num){
        if(num < minValue){
            num = minValue;
        }else if( num > maxValue){
            num = maxValue;
        }
        this.num = num;
        showValue();
    }

    private void showValue() {
        mEtInput.setText(String.valueOf(num));
        if(oldNum != num && mCallback!= null){
            oldNum = num;
            mCallback.onNumChanged(num);
        }
    }

    public void setNumChangeListener(OnNumberChangedListener listener){
        mCallback = listener;
    }

    public void setScopeValue(String min, String max){
        float maxV = 0.0f;
        float minV = 0.0f;
        if (min != null) {
            minV = Float.parseFloat(min);
        }
        if (max != null) {
            maxV = Float.parseFloat(max);
        }
        minValue = Math.max(minV, 0.0f);
        if(maxV > minValue){
            maxValue = maxV;
        }else{
            maxValue = 9999.0f;
        }
    }

    public void setCanEdit(boolean canEdit) {
        disableEdit = !canEdit;
        mEtInput.setFocusable(canEdit);
    }

    public interface OnNumberChangedListener{
        void onNumChanged(float num);
    }

}
