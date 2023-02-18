package kr.co.damdaorder.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class RequestDto {

    @Getter
    @AllArgsConstructor
    public static class CREATE_ORDER{
        private String productCode;

        private int amount;

        private int price;

        private int totalPrice;

        private String orderCode;

        private String identity;
    }
}