package com.springboot.jobseeker.shared.data.enums;

import java.util.Arrays;
import java.util.List;

public enum Status {
    INVALID(0, "Invalid"),
    ACCEPT(1, "Accept"),
    REJECT(2, "Reject"),
    PENDING(3, "Pending");

    private final Integer code;
    private final String description;

    Status(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    private static List<StatusInfo> listInfo(){
        return Arrays.stream(
                Status.values()). map(
                status -> new StatusInfo(status.getCode(), status.getDescription())
        ).toList();
    }

    public static Status fromCode(int code){
        return Arrays.stream(
                        Status.values())
                .filter(status -> status.getCode() == code)
                .findFirst()
                .orElse(null);
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
