package com.example.ebanking.backend_admin.dto;

public class ClientGrowthDto {
    private int month;
    private long count;

    public ClientGrowthDto(int month, long count) {
        this.month = month;
        this.count = count;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}

