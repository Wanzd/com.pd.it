package com.pd.common.calobject;

import lombok.Data;

@Data
public class TimerCO {
    private Long start;
    private Long end;
    private String name;

    public TimerCO(String name) {
        this.name = name;
        this.start = System.currentTimeMillis();
    }

    public void end() {
        end = System.currentTimeMillis();
    }

    public long getUsedTime() {
        return end - start;
    }
}
