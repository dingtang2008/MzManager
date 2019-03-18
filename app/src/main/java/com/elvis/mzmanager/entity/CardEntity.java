package com.elvis.mzmanager.entity;

import com.elvis.mzmanager.network.CardLifeBeanAdapter;
import com.google.gson.annotations.JsonAdapter;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CardEntity {


    /**
     * count : 100
     * pageStartOffset : 0
     * total : 100
     * rows : [{"expirationDate":"","cardLifeEntity":"","id":"331C269A58414500BA0BA917E0C45F73","usedFlow":"0.00","phoneZuhe":"","iccidMark":"898604491818C6596710","typeStr":"包月","usedFlowBig":0,"totalFlow":"0.00","orderNo":"","remainingFlowBig":0,"pakageList":"","isChild":"1","serialNumber":880777275,"status":1,"isSpecial":"","expireDate":"","agentId":"92F616AE47774C658ED0315E95B48A8A","tab":"","accountName":"衡阳移动","jsonUpdateFlag":"0","type":1,"categoryStr":"移动","importNum":"314","noRechargeLoopTime":1552823344000,"currentSetMeal":"","totalFlowBig":0,"orginTotalBytesCnt":"","flowPool":"","surplusDay":"","balanceMoney":"","cardInfo":"","expireTime":"","name":"","rechargedLoopTime":"","totalBytesCnt":"0","setMealId":"6DFA418D3D524B15B37AE1EB61D0BE9C","synchroTime":"","category":"3","cuankaFlag":"","servCreateDate":"","flowSize":"46080","statusStr":"待激活","agentThreeId":"DE529591C9164D2788D905505AAE3958_127C7442868543A8B2FDDA4948E3B305_92F616AE47774C658ED0315E95B48A8A","cardPackage":"蚂蚁45G","mybatisRecordCount":0,"accountId":"H46BA7BFB81C4F8EAA7FF549919EB51H","isAgentRecharge":2,"phone":"1440493906710","remainingFlow":"0.00","lastYdFlowTime":"","developTime":"","codeChild":"","agentName":"郑荣军","cardCount":"","agentThreeName":"郑荣军","createDateTime":"20190309173527","cardFlow":"","cuankaTime":""}]
     * data :
     * pageCount : 100
     * limit : 1
     * page : 1
     * layui : false
     * pageNo : 1
     * framework :
     * pageSize : 1
     */

    private int count;
    private int pageStartOffset;
    private int total;
    private String data;
    private int pageCount;
    private int limit;
    private int page;
    private boolean layui;
    private int pageNo;
    private String framework;
    private int pageSize;
    private List<RowsBean> rows;


    @NoArgsConstructor
    @Data
    public static class RowsBean {
        /**
         * expirationDate :
         * cardLifeEntity :
         * id : 331C269A58414500BA0BA917E0C45F73
         * usedFlow : 0.00
         * phoneZuhe :
         * iccidMark : 898604491818C6596710
         * typeStr : 包月
         * usedFlowBig : 0
         * totalFlow : 0.00
         * orderNo :
         * remainingFlowBig : 0
         * pakageList :
         * isChild : 1
         * serialNumber : 880777275
         * status : 1
         * isSpecial :
         * expireDate :
         * agentId : 92F616AE47774C658ED0315E95B48A8A
         * tab :
         * accountName : 衡阳移动
         * jsonUpdateFlag : 0
         * type : 1
         * categoryStr : 移动
         * importNum : 314
         * noRechargeLoopTime : 1552823344000
         * currentSetMeal :
         * totalFlowBig : 0
         * orginTotalBytesCnt :
         * flowPool :
         * surplusDay :
         * balanceMoney :
         * cardInfo :
         * expireTime :
         * name :
         * rechargedLoopTime :
         * totalBytesCnt : 0
         * setMealId : 6DFA418D3D524B15B37AE1EB61D0BE9C
         * synchroTime :
         * category : 3
         * cuankaFlag :
         * servCreateDate :
         * flowSize : 46080
         * statusStr : 待激活
         * agentThreeId : DE529591C9164D2788D905505AAE3958_127C7442868543A8B2FDDA4948E3B305_92F616AE47774C658ED0315E95B48A8A
         * cardPackage : 蚂蚁45G
         * mybatisRecordCount : 0
         * accountId : H46BA7BFB81C4F8EAA7FF549919EB51H
         * isAgentRecharge : 2
         * phone : 1440493906710
         * remainingFlow : 0.00
         * lastYdFlowTime :
         * developTime :
         * codeChild :
         * agentName : 郑荣军
         * cardCount :
         * agentThreeName : 郑荣军
         * createDateTime : 20190309173527
         * cardFlow :
         * cuankaTime :
         */

        private String expirationDate;
        @JsonAdapter(CardLifeBeanAdapter.class)
        private CardLifeBean cardLifeEntity;
        private String id;
        private String usedFlow;
        private String phoneZuhe;
        private String iccidMark;
        private String typeStr;
        private String usedFlowBig;
        private String totalFlow;
        private String orderNo;
        private String remainingFlowBig;
        private String pakageList;
        private String isChild;
        private String serialNumber;
        private String status;
        private String isSpecial;
        private String expireDate;
        private String agentId;
        private String tab;
        private String accountName;
        private String jsonUpdateFlag;
        private String type;
        private String categoryStr;
        private String importNum;
        private String noRechargeLoopTime;
        private String currentSetMeal;
        private String totalFlowBig;
        private String orginTotalBytesCnt;
        private String flowPool;
        private String surplusDay;
        private String balanceMoney;
        private String cardInfo;
        private String expireTime;
        private String name;
        private String rechargedLoopTime;
        private String totalBytesCnt;
        private String setMealId;
        private String synchroTime;
        private String category;
        private String cuankaFlag;
        private String servCreateDate;
        private String flowSize;
        private String statusStr;
        private String agentThreeId;
        private String cardPackage;
        private String mybatisRecordCount;
        private String accountId;
        private String isAgentRecharge;
        private String phone;
        private String remainingFlow;
        private String lastYdFlowTime;
        private String developTime;
        private String codeChild;
        private String agentName;
        private String cardCount;
        private String agentThreeName;
        private String createDateTime;
        private String cardFlow;
        private String cuankaTime;

        @NoArgsConstructor
        @Data
        public static class CardLifeBean {
            /**
             * cardCount :
             * agentAppId :
             * status : 1
             * jsonUpdateFlag : 0
             * reserve :
             * mybatisRecordCount : 0
             * openid : oKcDd0TKMVKR-WKK02hZ4kJVlTrc
             * cardId : 5F0755C36B2147F78BB936F3242524A5
             * updateTime : 2019-03-17 21:46:29.0
             * flowAddDiscount :
             * mealName :
             * phoneZuhe :
             * isChild :
             * effectType :
             * type : 6
             * id : 3990A9BA4F064543A22C934CE62BAB57
             * flowSize : 66560
             * orderNo :
             * createBy : system
             * updateBy : system
             * mealId : 045B3F991B674580934FDF185D6A96BF
             * phone :
             * updateDateTime :
             * agentAppSecret :
             * updateDateTimeNew :
             * basisKuayueType :
             * thisMonthEffect : true
             * createTime : 2019-03-17 21:46:29.0
             * expireDate : 2019-04-17 00:00:00.0
             * iccidMark :
             * userId :
             */

            private String cardCount;
            private String agentAppId;
            private int status;
            private String jsonUpdateFlag;
            private String reserve;
            private int mybatisRecordCount;
            private String openid;
            private String cardId;
            private String updateTime;
            private String flowAddDiscount;
            private String mealName;
            private String phoneZuhe;
            private String isChild;
            private String effectType;
            private int type;
            private String id;
            private int flowSize;
            private String orderNo;
            private String createBy;
            private String updateBy;
            private String mealId;
            private String phone;
            private String updateDateTime;
            private String agentAppSecret;
            private String updateDateTimeNew;
            private String basisKuayueType;
            private boolean thisMonthEffect;
            private String createTime;
            private String expireDate;
            private String iccidMark;
            private String userId;
        }
    }
}
