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
    READ_EXCHANGE_OFFER_SUCCESS(true, 1001, "교환 요청 조회에 성공하였습니다."),
    EXCHANGE_OFFER_SUCCESS(true, 1002, "교환 요청에 성공하였습니다."),
    EXCHANGE_APPROVAL_SUCCESS(true, 1003, "티켓 교환에 성공하였습니다."),
    EXCHANGE_REJECT_SUCCESS(true, 1004, "티켓 거절에 성공하였습니다."),
    LIKE_ADD_SUCCESS(true, 1005, "프로그램을 성공적으로 좋아요 목록에 추가했습니다."),
    LIKE_DELETE_SUCCESS(true, 1006, "프로그램을 좋아요 목록에서 성공적으로 삭제했습니다."),


    /**
     * 2000: 유저, point
     */
    NOT_FOUND_USER(false, 2000, "허가되지 않은 사용자 접근 혹은 유저 정보가 존재하지 않습니다."),
    USER_EMPTY_EMAIL(false, 2001, "이메일 등록란은 필수 항목입니다."),
    USER_EMPTY_NAME(false, 2002, "이름 등록란은 필수 항목입니다."),
    USER_EMPTY_PASSWORD(false, 2003, "패스워드 입력란은 필수 항목입니다."),
    EXISTED_EMAIL(false, 2004, "이미 가입된 이메일(아이디)입니다."),
    POINT_NOT_ENOUGH(false, 2101, "소유 포인트 보다 높은 포인트는 사용할 수 없습니다."),


    /**
     * 필터 단계에서 확인되는 에러는 실제 에러코드 형태여야 함.
     */
    EXPIRED_TOKEN(false, 400, "만료된 토큰 입니다."),
    NOT_INSERT_TOKEN(false, 400, "요청 헤더에 토큰이 존재하지 않습니다."),
    NOT_MATCH_USERDATA_TOKEN(false, 400, "토큰의 정보와 유저의 데이터가 일치 하지 않습니다."),
    UNSIGNED_FORMAT_TOKEN(false, 400, "지원하지 않는 형태의 JWT 토큰 양식 입니다."),

    /**
     * 3000: 프로그램
     */
    NOT_FOUND_PROGRAM(false, 3100, "프로그램이 존재하지 않습니다."),
    INVALID_PROGRAM_CREATION_REQUEST(false, 3101, "공연 생성에 실패했습니다."),

    NOT_FOUND_CATEGORY(false, 3200, "카테고리가 존재하지 않습니다."),
    DUPLICATE_CATEGORY(false, 3201, "이미 존재하는 카테고리입니다."),
    CATEGORY_CREATION_FAILED(false, 3202, "카테고리 생성에 실패했습니다."),
    INVALID_CATEGORY_DATA(false, 3203, "유효하지 않은 카테고리 데이터입니다."),

    NOT_FOUND_LOCATION(false, 3300, "로케이션이 존재하지 않습니다."),
    NOT_FOUND_LOCATION_ID(false, 3301, "해당 로케이션이 존재하지 않습니다."),
    DUPLICATE_LOCATION(false, 3302, "이미 존재하는 로케이션입니다."),
    LOCATION_CREATION_FAILED(false, 3303, "로케이션 생성에 실패했습니다."),
    INVALID_LOCATION_DATA(false, 3304, "유효하지 않은 로케이션 데이터입니다."),
    PROGRAM_NOT_IN_LOCATION(false, 3305, "해당 공연장에 해당 공연이 존재하지 않습니다."),

    SEARCH_PROGRAM_SUCCESS(true, 3204, "공연 검색을 성공했습니다."),
    SEARCH_PROGRAM_FAIL(false, 3205, "공연 검색을 실패했습니다."),
    CATEGORY_PROGRAM_SUCCESS(true, 3206, "카테고리별 공연 검색을 성공했습니다."),
    CATEGORY_PROGRAM_FAIL(false, 3207, "카테고리별 공연 검색을 실패했습니다."),

    INVALID_PAGE(false, 3400, "유효하지 않은 페이지 번호입니다."),
    INVALID_SIZE(false, 3401, "유효하지 않은 페이지 크기입니다."),

    /**
     * 4000: 결제
     */
    NOT_FOUND_PAYMENT(false, 4000, "결제 정보가 존재하지 않습니다."),
    INCORRECT_PAYMENT_INFO(false, 4100, "잘못된 결제 정보 입니다."),
    WRONG_API_SECRET(false, 4101, "잘못된 API KEY 입니다. 데이터와 만료 시기를 확인해 주시기 바랍니다."),
    WRONG_PAYMENT_ID(false, 4102, "결제 ID 코드값을 다시 확인해 주시기 바랍니다."),

    NOT_AGREE_PAYMENT_RULE(false, 4101, "결제 주의 사항에 동의가 반드시 필요합니다."),
    INCORRECT_RESERVATION_DATA(false, 4200, "잘못된 예매 정보입니다."),
    ALREADY_PAID_TICKET(false, 4201, "이미 결제된 티켓입니다."),

    NOT_FOUND_PAYMENT_DATA(false, 4300, "진행되지 않은 결제 데이터입니다."),
    FAIL_CANCELED_PAYMENT(false, 4301, "잘못된 결제 데이터로 인해 시스템 결제 취소를 시도했으나 실패하였습니다. 결제 기록을 확인하고 환불 처리를 신청해주시길 바랍니다."),
    INCORRECT_DATA_CANCEL_SUCCESS(false, 4302, "잘못된 결제과정에서 오류가 발생하여 결제를 정상 취소했습니다."),


    /**
     * 5000: 예매
     */
    ALREADY_CHOSEN_SEAT(false, 5000, "이미 선택된 좌석입니다."),
    NOT_FOUND_RESERVATION(false, 5001, "결제 정보가 존재하지 않습니다."),
    NOT_FOUND_TIMES(false, 5002, "회차 정보가 존재하지 않습니다."),
    NOT_FOUND_SEAT(false, 5003, "좌석 정보가 존재하지 않습니다."),
    NOT_MATCH_ROW(false, 5004, "행 정보가 일치하지 않습니다."),
    NOT_MATCH_COLUMN(false, 5004, "열 정보가 일치하지 않습니다."),
    NOT_EXIST_TIMES(false, 5005, "공연 회차정보가 존재하지 않습니다."),


    /**
     * 6000: 교환
     */

    FAIL_LOAD_EXCHANGE_OFFER(false, 6000, "교환 요청 조회에 실패하였습니다."),
    NOT_FOUND_EXCHANGE_OFFER(false, 6001, "일치하는 교환 내역이 존재하지 않습니다."),
    EXIST_EXCHANGE_OFFER(false, 6002, "동일한 요청을 보낼 수 없습니다."),
    WRONG_EXCHANGE_OFFER(false, 6003, "잘못된 교환 요청입니다."),
    ALREADY_SUCCESS_EXCHANGE_OFFER(false, 6004, "이미 교환 완료된 요청입니다."),
    ALREADY_FAIL_EXCHANGE_OFFER(false, 6005, "이미 교환 거절된 요청입니다."),
    UNAVAILABLE_EXCHANGE_OFFER(false, 6006, "유효하지 않은 교환 요청입니다."),
    NOT_REMAIN_EXCHANGE_OFFER(false, 6007, "교환 요청 가능 횟수가 남아있지 않습니다."),
    NOT_EXCHANGE_OFFER(false, 6008, "요청받은 교환이 존재하지 않습니다."),


    /**
     * 7000: 공연장, 구역, 좌석, 등급
     */
    NOT_FOUND_SECTION(false, 7000, "구역 정보가 존재하지 않습니다."),
    NOT_MATCH_SECTION(false, 7001, "해당 공연의 구역 정보가 존재하지 않습니다."),
    NOT_FOUND_PRICE(false, 7002, "등급별 가격 정보가 존재하지 않습니다."),

    /**
     * 8000: 공통 에러
     */
    INTERNAL_SERVER_ERROR(false, 8000, "일시적인 오류로 서비스 접속에 실패했습니다. 잠시 후 다시 시도 해 주세요."),
    BAD_REQUEST(false, 8001, "필수 값이 비어있거나, 유효성 검사에 실패했습니다."),
    INVALID_INPUT_CHARACTER(false, 8002, "특수문자 또는 허용되지 않는 값이 포함되어 있습니다.");


    private final boolean isSuccess;
    private final int code;
    private final String message;

    private BaseResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
