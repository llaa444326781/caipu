package com.bwie.com.my.bean;

import java.util.List;

/**
 * Created by Liuxiaoyu on 2016/11/4.
 */
public class FoodNaem {

    /**
     * status : true
     * tngou : [{"count":31,"description":"，巴西里少许，蒜末少许，新鲜墨鱼酱3大匙做法1、锅子加热，放入少许橄榄油，再加入蒜末爆香后即可放入花枝丁略炒；2、再放入白饭及墨鱼酱，用中火翻炒至熟，即可盛起放入碗中，再加上少许的巴西里即完成了","fcount":0,"food":"白饭,花枝,巴西里,墨鱼酱","id":38706,"images":"","img":"/cook/080303/36e6f4faf13aab128c1c899e9726b0ff.jpg","keywords":"少许 放入 墨鱼 即可 花枝 ","message":"<h2>材料 <\/h2><hr>  \n<p>白饭3碗，花枝4两(切丁)，巴西里少许，蒜末少许，新鲜墨鱼酱3大匙<\/p>   <h2>做法 <\/h2><hr>  \n<p>1、锅子加热，放入少许橄榄油，再加入蒜末爆香后即可放入花枝丁略炒； <\/p> \n<p>2、再放入白饭及墨鱼酱，用中火翻炒至熟，即可盛起放入碗中，再加上少许的巴西里即完成了。<\/p>   <h2>小诀窍<\/h2><hr>  \n<p>如果将墨鱼饭装入小碗中，再倒扣于盘中，周围再装饰一下生菜或副菜，就成为高级的意大利餐了。<\/p>","name":"意大利墨鱼饭","rcount":0}]
     */

    private boolean status;
    /**
     * count : 31
     * description : ，巴西里少许，蒜末少许，新鲜墨鱼酱3大匙做法1、锅子加热，放入少许橄榄油，再加入蒜末爆香后即可放入花枝丁略炒；2、再放入白饭及墨鱼酱，用中火翻炒至熟，即可盛起放入碗中，再加上少许的巴西里即完成了
     * fcount : 0
     * food : 白饭,花枝,巴西里,墨鱼酱
     * id : 38706
     * images :
     * img : /cook/080303/36e6f4faf13aab128c1c899e9726b0ff.jpg
     * keywords : 少许 放入 墨鱼 即可 花枝
     * message : <h2>材料 </h2><hr>
     <p>白饭3碗，花枝4两(切丁)，巴西里少许，蒜末少许，新鲜墨鱼酱3大匙</p>   <h2>做法 </h2><hr>
     <p>1、锅子加热，放入少许橄榄油，再加入蒜末爆香后即可放入花枝丁略炒； </p>
     <p>2、再放入白饭及墨鱼酱，用中火翻炒至熟，即可盛起放入碗中，再加上少许的巴西里即完成了。</p>   <h2>小诀窍</h2><hr>
     <p>如果将墨鱼饭装入小碗中，再倒扣于盘中，周围再装饰一下生菜或副菜，就成为高级的意大利餐了。</p>
     * name : 意大利墨鱼饭
     * rcount : 0
     */

    private List<TngouBean> tngou;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<TngouBean> getTngou() {
        return tngou;
    }

    public void setTngou(List<TngouBean> tngou) {
        this.tngou = tngou;
    }

    public static class TngouBean {
        private int count;
        private String description;
        private int fcount;
        private String food;
        private int id;
        private String images;
        private String img;
        private String keywords;
        private String message;
        private String name;
        private int rcount;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getFcount() {
            return fcount;
        }

        public void setFcount(int fcount) {
            this.fcount = fcount;
        }

        public String getFood() {
            return food;
        }

        public void setFood(String food) {
            this.food = food;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getKeywords() {
            return keywords;
        }

        public void setKeywords(String keywords) {
            this.keywords = keywords;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getRcount() {
            return rcount;
        }

        public void setRcount(int rcount) {
            this.rcount = rcount;
        }
    }
}
