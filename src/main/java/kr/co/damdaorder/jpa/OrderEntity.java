package kr.co.damdaorder.jpa;

import kr.co.damdaorder.dto.RequestDto;
import kr.co.damdaorder.dto.ResponseDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_order")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderEntity {

    @Id @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(name = "order_code", unique = true, nullable = false, length = 120)
    private String orderCode;

    @Column(name = "product_code", unique = true, nullable = false, length = 120)
    private String productCode;

    @Column(name = "identity", unique = true, nullable = false, length = 50)
    private String identity;

    @Column(name = "amount", nullable = false)
    private int amount;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "total_price", nullable = false)
    private int totalPrice;

    @Builder
    public OrderEntity(String orderCode,
                       String productCode,
                       String identity,
                       int amount,
                       int price,
                       int totalPrice) {
        this.orderCode = orderCode;
        this.productCode = productCode;
        this.identity = identity;
        this.amount = amount;
        this.price = price;
        this.totalPrice = totalPrice;
    }

    public static OrderEntity of(RequestDto.CREATE create){
        return OrderEntity.builder()
                .orderCode(create.getOrderCode())
                .productCode(create.getProductCode())
                .identity(create.getIdentity())
                .amount(create.getAmount())
                .price(create.getPrice())
                .totalPrice(create.getTotalPrice())
                .build();
    }

    public static ResponseDto.READ_ORDER_INFO of(OrderEntity orderEntity){
        return ResponseDto.READ_ORDER_INFO.builder()
                .orderCode(orderEntity.orderCode)
                .productCode(orderEntity.productCode)
                .amount(orderEntity.amount)
                .price(orderEntity.price)
                .totalPrice(orderEntity.totalPrice)
                .build();
    }

    public static List<ResponseDto.READ_ORDER_INFO> of(List<OrderEntity> orderEntities){
        List<ResponseDto.READ_ORDER_INFO> readOrderInfos = new ArrayList<>();

        for(OrderEntity orderEntity : orderEntities){
            ResponseDto.READ_ORDER_INFO readOrderInfo = OrderEntity.of(orderEntity);
            readOrderInfos.add(readOrderInfo);
        }

        return readOrderInfos;
    }
}
