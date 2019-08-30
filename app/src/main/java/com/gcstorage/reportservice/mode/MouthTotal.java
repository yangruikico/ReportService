package com.gcstorage.reportservice.mode;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MouthTotal {


    /**
     * data : {"1566921600000":"2","1564934400000":"1","1567008000000":"2","1566835200000":"1"}
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        if (data == null) {
            return new ArrayList<>();
        }
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {


        private long time;
        private long size;

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public long getSize() {
            return size;
        }

        public void setSize(long size) {
            this.size = size;
        }
    }
}
