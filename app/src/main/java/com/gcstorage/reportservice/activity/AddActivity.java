package com.gcstorage.reportservice.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.gcstorage.reportservice.R;
import com.gcstorage.reportservice.mvp.AddPresenter;
import com.gcstorage.reportservice.view.pickview.builder.TimePickerBuilder;
import com.gcstorage.reportservice.view.pickview.listener.OnTimeSelectChangeListener;
import com.gcstorage.reportservice.view.pickview.listener.OnTimeSelectListener;
import com.gcstorage.reportservice.view.pickview.view.TimePickerView;
import com.yrbase.baseactivity.BaseActivity;
import com.yrbase.utils.OnPerfectClickListener;
import com.yrbase.utils.ViewUtil;

import java.util.Calendar;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddActivity extends BaseActivity<AddPresenter.Presenter> implements AddPresenter.View {

    @Bind(R.id.tv_start_time)
    TextView tvStartTime;
    @Bind(R.id.tv_end_time)
    TextView tvEndTime;
    @Bind(R.id.mjcount)
    EditText mjcount;
    @Bind(R.id.fjcount)
    EditText fjcount;
    @Bind(R.id.carcount)
    EditText carcount;
    @Bind(R.id.confirm_tv)
    TextView confirmTv;
    private String stringtvStartTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_report);
        ButterKnife.bind(this);

        getToolBarX().setCenterText("添加任务").setRightText("确定").setRightTextOnClickListener(new OnPerfectClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                confirm();
            }
        });

        initSignDate();

    }

    @Override
    public void initPresenter() {
        mPresenter.setView(this);
    }

    @Override
    public void onSaveSuccess() {

        ViewUtil.Toast("添加成功");
        Intent intent = new Intent();
        intent.putExtra("stringtvStartTime",stringtvStartTime);
        setResult(RESULT_OK,intent);
        finish();

    }


    private void confirm() {

        String mjcountString = mjcount.getText().toString();

        if (!ViewUtil.validateText(mjcountString, "民警不能为空")) {
            return;
        }
          String fjcountString = fjcount.getText().toString();

        if (!ViewUtil.validateText(fjcountString, "辅警不能为空")) {
            return;
        }
        String carcountString = carcount.getText().toString();

        if (!ViewUtil.validateText(carcountString, "车辆不能为空")) {
            return;
        }


        stringtvStartTime = tvStartTime.getText().toString();

        if (!ViewUtil.validateText(stringtvStartTime, "请选择开始时间")) {
            return;
        }
        String stringtvEndTime = tvEndTime.getText().toString();

        if (!ViewUtil.validateText(stringtvEndTime, "请选择结束时间")) {
            return;
        }


        mPresenter.addTbServices(mjcountString,fjcountString,carcountString, stringtvStartTime,stringtvEndTime);


    }

    private TimePickerView pvTime;


    private boolean isStartClick;

    private void initSignDate() {
        //日期选择

        pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                if (isStartClick) {
                    ViewUtil.setText(tvStartTime, ViewUtil.getStringDate(ViewUtil.YMDHMS, date));
                } else {
                    ViewUtil.setText(tvEndTime, ViewUtil.getStringDate(ViewUtil.YMDHMS, date));

                }
            }
        })
                .setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
                    @Override
                    public void onTimeSelectChanged(Date date) {
                    }
                })
                .setLineSpacingMultiplier(2.0f)

                .setRangDate(Calendar.getInstance())//开始日期

                .setType(new boolean[]{true, true, true, true, true, true})

                .isDialog(true) //默认设置false ，内部实现将DecorView 作为它的父控件。
                .addOnCancelClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                })
                .build();

        Dialog mDialog = pvTime.getDialog();
        if (mDialog != null) {

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM);

            params.leftMargin = 0;
            params.rightMargin = 0;
            pvTime.getDialogContainerLayout().setLayoutParams(params);

            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(R.style.picker_view_slide_anim);//修改动画样式
                dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
                dialogWindow.setDimAmount(0.1f);
            }
        }

    }
    @OnClick({R.id.ll_start_time, R.id.ll_end_time, R.id.confirm_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_start_time:
                isStartClick = true;
                if (pvTime != null) {
                    String maxTime = tvEndTime.getText().toString();
                    pvTime.show(isStartClick, maxTime);
                }
                break;
            case R.id.ll_end_time:
                isStartClick = false;
                if (pvTime != null) {
                    String miniTime = tvStartTime.getText().toString();
                    if (ViewUtil.isEmpty(miniTime)) {
                        ViewUtil.Toast("请先选开始时间");
                    } else {
                        pvTime.show(isStartClick, miniTime);
                    }

                }

                break;
            case R.id.confirm_tv:
                confirm();
                break;
        }
    }
}
