package com.wdtpr.augus.jsoupdemo.jsoupModel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by augus on 2018/2/27.
 *
 */

public class Jsoup_cn_baidu implements Serializable {

    //
    private LevelOne appDoc;

    public LevelOne getAppDoc() {
        return appDoc;
    }

    public void setAppDoc(LevelOne appDoc) {
        this.appDoc = appDoc;
    }

    @Override
    public String toString() {
        return "Jsoup_cn_baidu{" +
                "appDoc=" + appDoc +
                '}';
    }

    public static class LevelOne implements Serializable{
        //
        public LevelTwo resList;

        public LevelTwo getResList() {
            return resList;
        }

        public void setResList(LevelTwo resList) {
            this.resList = resList;
        }

        @Override
        public String toString() {
            return "LevelOne{" +
                    "resList=" + resList +
                    '}';
        }

        public static class LevelTwo implements Serializable{

            public LevelThree d_23282776;

            public LevelThree getD_23282776() {
                return d_23282776;
            }

            public void setD_23282776(LevelThree d_23282776) {
                this.d_23282776 = d_23282776;
            }

            @Override
            public String toString() {
                return "LevelTwo{" +
                        "d_23282776=" + d_23282776 +
                        '}';
            }

            public static class LevelThree implements Serializable{

                public LevelFour data;

                public LevelFour getData() {
                    return data;
                }

                public void setData(LevelFour data) {
                    this.data = data;
                }

                @Override
                public String toString() {
                    return "LevelThree{" +
                            "data=" + data +
                            '}';
                }

                public static class LevelFour implements Serializable{

                    public LevelFive appDocNew;

                    public LevelFive getAppDocNew() {
                        return appDocNew;
                    }

                    public void setAppDocNew(LevelFive appDocNew) {
                        this.appDocNew = appDocNew;
                    }

                    @Override
                    public String toString() {
                        return "LevelFour{" +
                                "appDocNew=" + appDocNew +
                                '}';
                    }

                    public static class LevelFive implements Serializable{

                        private List<cardList> cardList;

                        public List<LevelFive.cardList> getCardList() {
                            return cardList;
                        }

                        public void setCardList(List<LevelFive.cardList> cardList) {
                            this.cardList = cardList;
                        }


                        public String getVersion(){
                            /**
                             * applanding.baidu.com/feedback/23282776?sname=口袋爱美语&version=1.0.0
                             */
                            String version = "NON";
                            cardList detailCardDev = null;
                            try {

                                for(cardList c : cardList ){
                                    if(c.getCardType().equals("detailCardDev")){
                                        detailCardDev = c;
                                    }
                                }
                                if(detailCardDev == null){
                                    return version;
                                }

                                String result[] = detailCardDev.feedBackLink.split("\\?"); // path | query
                                String query[] = new String[0];
                                for (String s : result) {
                                    if (s.contains("=")) {
                                        query = s.split("&");
                                    }
                                }
                                //query
                                for (String s : query) {
                                    String value[] = s.split("=");
                                    if(value[0].equals("version") ){
                                        version = value[1];
                                    }
                                }

                            }catch (Exception e){
                                version = "error:" + e.getMessage();
                            }

                            return version;
                        }


                        @Override
                        public String toString() {
                            return "LevelFive{" +
                                    "cardList=" + cardList +
                                    '}';
                        }

                        public static class cardList implements Serializable{
                            /**
                             * typeName : detailHeader ; 無內容
                             * typeName : detailCardApp ; [appName(app名字),appDownloadNum(下載人數),appUpdateDate(更新時間)]
                             * typeName : none ; 無內容
                             * typeName : detailDownload ; [appDoc(下載訊息)]
                             * typeName : detailCardDev ; [feedBackLink(version)]
                             */
                            private String cardType;//type
                            private String appName;
                            private int appDownloadNum;
                            private String appUpdateDate;
                            private DetailDowload appDoc;
                            private String feedBackLink;

                            public String getCardType() {
                                return cardType;
                            }

                            public void setCardType(String cardType) {
                                this.cardType = cardType;
                            }

                            public String getAppName() {
                                return appName;
                            }

                            public void setAppName(String appName) {
                                this.appName = appName;
                            }

                            public int getAppDownloadNum() {
                                return appDownloadNum;
                            }

                            public void setAppDownloadNum(int appDownloadNum) {
                                this.appDownloadNum = appDownloadNum;
                            }

                            public String getAppUpdateDate() {
                                return appUpdateDate;
                            }

                            public void setAppUpdateDate(String appUpdateDate) {
                                this.appUpdateDate = appUpdateDate;
                            }

                            public DetailDowload getAppDoc() {
                                return appDoc;
                            }

                            public void setAppDoc(DetailDowload appDoc) {
                                this.appDoc = appDoc;
                            }

                            public String getFeedBackLink() {
                                return feedBackLink;
                            }

                            public void setFeedBackLink(String feedBackLink) {
                                this.feedBackLink = feedBackLink;
                            }


                            @Override
                            public String toString() {
                                return "cardList{" +
                                        "cardType='" + cardType + '\'' +
                                        ", appName='" + appName + '\'' +
                                        ", appDownloadNum=" + appDownloadNum +
                                        ", appUpdateDate='" + appUpdateDate + '\'' +
                                        ", appDoc=" + appDoc +
                                        ", feedBackLink='" + feedBackLink + '\'' +
                                        '}';
                            }

                            //下載訊息
                            public static class DetailDowload{
                                //下載路徑
                                private String downloadUrl;

                                public String getDownloadUrl() {
                                    return downloadUrl;
                                }

                                public void setDownloadUrl(String downloadUrl) {
                                    this.downloadUrl = downloadUrl;
                                }

                                @Override
                                public String toString() {
                                    return "DetailDowload{" +
                                            "downloadUrl='" + downloadUrl + '\'' +
                                            '}';
                                }
                            }

                        }

                    }

                }

            }

        }
    }
}
