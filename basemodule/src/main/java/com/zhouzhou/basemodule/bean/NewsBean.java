package com.zhouzhou.basemodule.bean;

import java.util.List;

public class NewsBean {

    /**
     * status : 0
     * msg : ok
     * result : {"channel":"头条","num":1,"list":[{"title":"今年4月，贾跃亭能否踏上回家路？","time":"2020-03-23 08:52:13","src":"新浪科技-自媒体综合","category":"tech","pic":"https://n.sinaimg.cn/tech/transform/667/w400h267/20200323/eda0-ireifzh8456032.jpg","content":"","weburl":"https://tech.sina.com.cn/i/2020-03-23/doc-iimxxsth1109191.shtml"}]}
     */

    private int status;
    private String msg;
    private ResultBean result;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * channel : 头条
         * num : 1
         * list : [{"title":"今年4月，贾跃亭能否踏上回家路？","time":"2020-03-23 08:52:13","src":"新浪科技-自媒体综合","category":"tech","pic":"https://n.sinaimg.cn/tech/transform/667/w400h267/20200323/eda0-ireifzh8456032.jpg","content":"","weburl":"https://tech.sina.com.cn/i/2020-03-23/doc-iimxxsth1109191.shtml"}]
         */

        private String channel;
        private int num;
        private List<ListBean> list;

        public String getChannel() {
            return channel;
        }

        public void setChannel(String channel) {
            this.channel = channel;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * title : 今年4月，贾跃亭能否踏上回家路？
             * time : 2020-03-23 08:52:13
             * src : 新浪科技-自媒体综合
             * category : tech
             * pic : https://n.sinaimg.cn/tech/transform/667/w400h267/20200323/eda0-ireifzh8456032.jpg
             * content :
             * weburl : https://tech.sina.com.cn/i/2020-03-23/doc-iimxxsth1109191.shtml
             */

            private String title;
            private String time;
            private String src;
            private String category;
            private String pic;
            private String content;
            private String weburl;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getSrc() {
                return src;
            }

            public void setSrc(String src) {
                this.src = src;
            }

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getWeburl() {
                return weburl;
            }

            public void setWeburl(String weburl) {
                this.weburl = weburl;
            }
        }
    }
}
