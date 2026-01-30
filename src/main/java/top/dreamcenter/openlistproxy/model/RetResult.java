package top.dreamcenter.openlistproxy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RetResult<T> {

    private int code;

    private String msg;

    private T data;

}
