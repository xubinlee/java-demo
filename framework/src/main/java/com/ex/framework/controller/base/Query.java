package com.ex.framework.controller.base;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.Objects;

@Data
public class Query<T> extends Page<T> {

    private T eq;

    private Map<String, String> sort;

    private Map<String, String> like;

    private Map<String, String> ge;

    private Map<String, String> le;

    public Page<T> toPage(){
        return new Page<>(getCurrent(), getSize());
    }

    public enum SortEnum{

        ASC(1, "ASC"),
        DESC(2, "DESC");

        private Integer value;

        private String dir;

        SortEnum(Integer value, String dir) {
            this.value = value;
            this.dir = dir;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        public String getDir() {
            return dir;
        }

        public void setDir(String dir) {
            this.dir = dir;
        }

        public static SortEnum valueOf(Integer id) {
            return Objects.equals(id, ASC.value) ? ASC : DESC;
        }
    }
}
