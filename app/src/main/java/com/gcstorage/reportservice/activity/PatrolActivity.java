package com.gcstorage.reportservice.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.yrbase.baseactivity.BaseActivity;

public class PatrolActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);






    }

    @Override
    public void initPresenter() {
        mPresenter.setView(this);
    }
}
