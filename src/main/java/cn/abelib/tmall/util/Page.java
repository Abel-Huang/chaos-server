package cn.abelib.tmall.util;

/**
 * Created by abel on 2017/11/6.
 */
public class Page {
    private int start;
    private int count;
    private int total;
    private String param;
    // Ĭ��ÿҳ��ʾ5��
    private static final int defaultCount = 5;

    public Page(){
        this.count = defaultCount;
    }

    public Page(int start, int count){
        super();
        this.start = start;
        this.count = count;
    }

    public boolean isHasPreviouse(){
        return start != 0;
    }

    public boolean isHasNext(){
        return start == getLast();
    }

    public int getTotalPage(){
        int totalPage;
        // ����������50�����ܹ���5�����ģ���ô����10ҳ
        if (0 == total % count)
            totalPage = total /count;
            // ����������51�����ܹ���5�����ģ���ô����11ҳ
        else
            totalPage = total / count + 1;

        if(0==totalPage)
            totalPage = 1;
        return totalPage;

    }

    public int getLast(){
        int last;
        // ����������50�����ܹ���5�����ģ���ô���һҳ�Ŀ�ʼ����45
        if (0 == total % count)
            last = total - count;
            // ����������51�����ܹ���5�����ģ���ô���һҳ�Ŀ�ʼ����50
        else
            last = total - total % count;
        last = last<0?0:last;
        return last;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }
}
