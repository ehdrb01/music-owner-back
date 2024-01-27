package kr.co.strato.wrp.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Paging {
    private Integer size;
    private Integer offset;

    public int getPage(){
        return (this.offset/this.size) + 1;
    }
}
