package org.example.w2.common;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageInfo {
/*
     this.page = page <= 0? 1:page;

    end = Math.ceil(page/10.0) * 10

    start = end - 9

    prev = start == 1 ? false : true

    next = if((end*size) < total ) next = true,  else { next = false, end = Math.ceil(total/(double)size) }

*/

    private int page;
    //private int size;
    private int start;
    private int end;

    private boolean prev;
    private boolean next;

    public PageInfo(int page, int size, int total) {
        this.page = page <= 0 ? 1 : page;

        end = (int) (Math.ceil(this.page / 10.0) * 10);

        start = end - 9;

        prev = start == 1 ? false : true;

        //total = 120
        if (end * size < total)
            next = true;
        else {
            next = false;
            end = (int) (Math.ceil(total / (double) size));
        }
    }


}
