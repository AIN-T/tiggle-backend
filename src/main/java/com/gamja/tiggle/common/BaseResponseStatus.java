package com.gamja.tiggle.common;

import lombok.Getter;

/**
 * 에러 코드 관리
 */
@Getter
public enum BaseResponseStatus {

    FAIL(false, 500, "요청 실패"),


    /**
     * 1000 : 요청 성공
     */
    SUCCESS(true, 1000, "요청에 성공하였습니다."),

    /**
     * 2000: 유저
     */

    /**
     * 3000: 프로그램
     */

    NOT_FOUND_PROGRAM(false, 3100, "프로그램이 존재하지 않습니다."),

    /**
     * 4000: 결제
     */

    /**
     * 5000: 예매
     */
    ALREADY_CHOSEN_SEAT(false,5000,"이미 선택된 좌석입니다."),


    /**
     * 6000: 교환
     */

    /**
     * 7000: 공연장, 구역, 좌석
     */
    NOT_FOUND_SECTION(false,7000,"구역이 존재하지 않습니다.");


    private final boolean isSuccess;
    private final int code;
    private final String message;


    private BaseResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
