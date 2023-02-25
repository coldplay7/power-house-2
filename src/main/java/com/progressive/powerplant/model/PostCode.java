package com.progressive.powerplant.model;

public record PostCode(Integer code) {
    public static final int MIN_CODE_LENGTH = 4;

    public PostCode(Integer code) {
        this.code = code;
        validate();
    }

    void validate() {
        if (null == code) throw new IllegalArgumentException("Post code cannot null.");
        if (String.valueOf(code).length() < MIN_CODE_LENGTH)
            throw new IllegalArgumentException("Post code cannot be less than 4 digit.");
    }

}
