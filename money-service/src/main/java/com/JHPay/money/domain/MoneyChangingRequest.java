package com.JHPay.money.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

import java.util.Date;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MoneyChangingRequest {

    private final String moneyChangingRequestId;

    // 어떤 고객의 증액/ 감액 요청을 요청했는지의 맴버 정보
    private final String targetMembershipId;

    // 요청이 감액/증액 인지
    private final int changingType; // enum 0: 증액, 1: 감액

//    enum ChangingType {
//        INCREASING, // 증액
//        DECREASING // 감액
//    }

    // 증액 또는 감액 요청의 금액
    private final int changingMoneyAmount;

    // 머니 변액 요청에 대한 상태
    private final int changingMoneyStatus; // enum 0: 요청, 1: 성공, 2: 실패

//    enum ChangingMoneyStatus {
//        REQUEST, // 요청
//        SUCCEEDED, // 성공
//        FAILED, // 실패
//        CANCELLED // 취소
//    }

    private final String uuid;

    private final Date createdAt;

    public static MoneyChangingRequest generateMoneyChangingRequest(
            MoneyChangingRequestId moneyChangingRequestId,
            TargetMembershipId targetMembershipId,
            ChangingType changingType,
            ChangingMoneyAmount changingMoneyAmount,
            ChangingStatus changingStatus,
            String uuid
    ) {
        return new MoneyChangingRequest(
                moneyChangingRequestId.getMoneyChangingRequestId(),
                targetMembershipId.getTargetMembershipId(),
                changingType.getChangingType(),
                changingMoneyAmount.getChangingMoneyAmount(),
                changingStatus.getChangingMoneyStatus(),
                uuid,
                new Date()
        );
    }

    @Value
    public static class MoneyChangingRequestId {
        public MoneyChangingRequestId(String value) {
            this.moneyChangingRequestId = value;
        }

        String moneyChangingRequestId;
    }

    @Value
    public static class TargetMembershipId {
        public TargetMembershipId(String value) {
            this.targetMembershipId = value;
        }

        String targetMembershipId;
    }

    @Value
    public static class ChangingType {
        public ChangingType(int value) {
            this.changingType = value;
        }

        int changingType;
    }


    @Value
    public static class ChangingMoneyAmount {
        public ChangingMoneyAmount(int value) {
            this.changingMoneyAmount = value;
        }

        int changingMoneyAmount;
    }

    @Value
    public static class ChangingStatus {
        public ChangingStatus(int value) {
            this.changingMoneyStatus = value;
        }

        int changingMoneyStatus;
    }

    @Value
    public static class Uuid {
        public Uuid(String value) {
            this.uuid = value;
        }

        String uuid;
    }

}
