package cn.hft.entity;

import java.io.Serializable;

public class Result implements Serializable {
    private Integer codeTape;
    private String messager;

    public Result() {
    }

    @Override
    public String toString() {
        return "Result{" +
                "codeTape=" + codeTape +
                ", messager='" + messager + '\'' +
                '}';
    }

    public Result(Integer codeTape, String messager) {
        this.codeTape = codeTape;
        this.messager = messager;
    }

    public Integer getCodeTape() {
        return codeTape;
    }

    public void setCodeTape(Integer codeTape) {
        this.codeTape = codeTape;
    }

    public String getMessager() {
        return messager;
    }

    public void setMessager(String messager) {
        this.messager = messager;
    }
}
