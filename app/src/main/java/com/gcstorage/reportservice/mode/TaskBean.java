package com.gcstorage.reportservice.mode;

import java.util.ArrayList;
import java.util.List;

public class TaskBean {


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
        /**
         * planid : a87cd837572b4c409f70d1226ed287f2
         * mjcount : 5
         * fjcount : 5
         * carcount : 5
         * receivecount : 0
         * isReceive : 0
         * dutystarttimes : 1564588800000
         * dutyendtimes : 1567180800000
         * tbLal : []
         */

        private String planid;
        private int mjcount;
        private int fjcount;
        private int carcount;
        private int receivecount;
        private int isReceive;
        private long dutystarttimes;
        private long dutyendtimes;
        private List<?> tbLal;

        public String getPlanid() {
            return planid;
        }

        public void setPlanid(String planid) {
            this.planid = planid;
        }

        public int getMjcount() {
            return mjcount;
        }

        public void setMjcount(int mjcount) {
            this.mjcount = mjcount;
        }

        public int getFjcount() {
            return fjcount;
        }

        public void setFjcount(int fjcount) {
            this.fjcount = fjcount;
        }

        public int getCarcount() {
            return carcount;
        }

        public void setCarcount(int carcount) {
            this.carcount = carcount;
        }

        public int getReceivecount() {
            return receivecount;
        }

        public void setReceivecount(int receivecount) {
            this.receivecount = receivecount;
        }

        public int getIsReceive() {
            return isReceive;
        }

        public void setIsReceive(int isReceive) {
            this.isReceive = isReceive;
        }

        public long getDutystarttimes() {
            return dutystarttimes;
        }

        public void setDutystarttimes(long dutystarttimes) {
            this.dutystarttimes = dutystarttimes;
        }

        public long getDutyendtimes() {
            return dutyendtimes;
        }

        public void setDutyendtimes(long dutyendtimes) {
            this.dutyendtimes = dutyendtimes;
        }

        public List<?> getTbLal() {
            return tbLal;
        }

        public void setTbLal(List<?> tbLal) {
            this.tbLal = tbLal;
        }
    }
}
