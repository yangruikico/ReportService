package com.gcstorage.reportservice.pop;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gcstorage.reportservice.R;
import com.yrbase.adapter.CommonAdapter;
import com.yrbase.adapter.base.ViewHolder;
import com.yrbase.utils.OnPerfectClickListener;
import com.yrbase.utils.ViewUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by EA on 2018/5/29
 */
public class GetPatrolPop {

    public Activity mActivity;
    PopupWindow popupWindow;
    private View inflate;


    private CommonAdapter<String> storeCouponCommonAdapter;

    private onRefreshData mOnRefreshData;

    private int positionTemp = -1;

    private List<String> strings = new ArrayList<>();

    public void setData(List<String> data) {
        if (data == null || data.size() == 0) {
            storeCouponCommonAdapter.clear();
        } else {
            storeCouponCommonAdapter.replaceAll(data);
        }
    }

    public interface onRefreshData {

        void refreshData(String type);
    }

    public GetPatrolPop(Activity mActivity, onRefreshData mOnRefreshData) {
        this.mActivity = mActivity;
        this.mOnRefreshData = mOnRefreshData;
        initPopup();
    }

    private void initPopup() {
        inflate = ViewUtil.getView(R.layout.room_dia_layout, null);
        View ic_close = inflate.findViewById(R.id.ic_close);

        ic_close.setOnClickListener(new OnPerfectClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                dismiss();
            }
        });
        View confirm_tv = inflate.findViewById(R.id.confirm_tv);

        confirm_tv.setOnClickListener(new OnPerfectClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {

                if (positionTemp != -1) {

                    if (mOnRefreshData != null) {
                        mOnRefreshData.refreshData(strings.get(positionTemp));
                    }

                    dismiss();

                } else {
                    ViewUtil.Toast("请选择巡逻方式");
                }

            }
        });

        RecyclerView recyclerView = inflate.findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));


        strings.add("车辆");//车辆、电动车、步行
        strings.add("电动车");
        strings.add("步行");


        storeCouponCommonAdapter = new CommonAdapter<String>(mActivity, R.layout.item_patrol, strings) {
            @Override
            protected void convert(ViewHolder holder, final String data, final int position) {


                holder.setText(R.id.name_tv, data);

                holder.setVisible(R.id.selected, positionTemp == position);

                holder.setOnClickListener(R.id.item_root, new OnPerfectClickListener() {
                    @Override
                    protected void onNoDoubleClick(View v) {
                        positionTemp = position;
                        notifyDataSetChanged();
                    }
                });
            }

        };
        recyclerView.setAdapter(storeCouponCommonAdapter);


        //底部滑动pop
        popupWindow = new PopupWindow(inflate, WindowManager.LayoutParams.MATCH_PARENT, (ViewUtil.getHeightInPx() / 2), true);
        popupWindow.setTouchable(true);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
                //这里如果返回true的话，touch事件将被拦截
                //拦截后 PoppWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });
        //（注意一下！！）如果不设置popupWindow的背景，无论是点击外部区域还是Back键都无法取消
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.setAnimationStyle(R.style.popwindow_anim_style);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = mActivity.getWindow().getAttributes();
                lp.alpha = 1.0f;
                mActivity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                mActivity.getWindow().setAttributes(lp);

            }
        });
        popupWindow.setOutsideTouchable(false);
    }

    /**
     * 显示弹窗
     */
    public void showPop() {
        if (popupWindow != null && !popupWindow.isShowing()) {
            popupWindow.showAtLocation(inflate, Gravity.BOTTOM, 0, 0);
            WindowManager.LayoutParams lp = mActivity.getWindow().getAttributes();
            lp.alpha = 0.4f;
            mActivity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            mActivity.getWindow().setAttributes(lp);
        }

    }

    /**
     * 是否正在显示
     */
    public boolean isShow() {
        return popupWindow != null && popupWindow.isShowing();
    }


    /**
     * 隐藏弹窗
     */
    public void dismiss() {
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
        }
    }

}
