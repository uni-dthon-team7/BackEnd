package com.unidthon.jabuhae.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // 도메인
    // 타입(상태 코드, "메시지");

    // Default
    ERROR(400, "요청 처리에 실패했습니다."),

    // 400 Bad Request
    // 입력 에러
    INVALID_INPUT_FORMAT(400, "유효하지 않은 형식입니다."),
    INVALID_INPUT_LENGTH(400, "입력 길이가 잘못되었습니다."),
    INVALID_INPUT_VALUE(400, "입력값이 잘못되었습니다."),
    MISSING_PARAMETER(400, "필수 파라미터가 누락되었습니다."),
    INVALID_UNLOCK_CONDITION(400, "해제 조건이 잘못되었습니다."),
    // enum 값이 잘못됨
    INVALID_ENUM_VALUE(400, "enum 값이 잘못되었습니다."),

    // 401 Unauthorized
    // 로그인 상태여야 하는 요청
    NOT_AUTHENTICATED(401, "로그인 상태가 아닙니다."),
    // 권한이 없는 요청을 보냄
    UNAUTHORIZED_REQUEST(401,"권한이 없습니다."),

    // 404 Not Found
    // 각 리소스를 찾지 못함
    RESOURCE_NOT_FOUND(404, "리소스를 찾을 수 없습니다."),

    // 409 Conflict
    // 중복 리소스 생성 시도
    RESOURCE_ALREADY_EXISTS(409, "리소스가 이미 존재합니다."),

    // 500 Internal Server Error
    // 외부 API 사용 도중 에러
    EXTERNAL_API_ERROR(500, "외부 API 사용 중 문제가 발생했습니다."),
    INTERNAL_SERVER_ERROR(500, "서버 내부 오류가 발생했습니다.")
    ;

    private final int status;
    private final String message;

}
