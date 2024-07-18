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


    NOT_FOUND_CATEGORY(false, 3200, "카테고리가 존재하지 않습니다."),
    DUPLICATE_CATEGORY(false, 3201, "이미 존재하는 카테고리입니다."),
    CATEGORY_CREATION_FAILED(false, 3202, "카테고리 생성에 실패했습니다."),
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
    NOT_FOUND_SECTION(false,7000,"구역이 존재하지 않습니다."),

    /**
     * 8000: 공통 에러
     */
    INTERNAL_SERVER_ERROR(false, 8000, "일시적인 오류로 서비스 접속에 실패했습니다. 잠시 후 다시 시도 해 주세요."),
    BAD_REQUEST(false, 8001, "필수 값이 비어있거나, 유효성 검사에 실패했습니다.");

    private final boolean isSuccess;
    private final int code;
    private final String message;


    private BaseResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
