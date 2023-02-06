package kr.co.damdaorder.service;

import kr.co.dto.RequestOrderDto;
import kr.co.dto.ResponseOrderDto;
import kr.co.damdaorder.jpa.OrderEntity;
import kr.co.damdaorder.jpa.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;

    /**
     * order and order info read
     * @param createOrder order product
     * @return order info
     */
    public ResponseOrderDto.READ_ORDER_INFO createOrder(RequestOrderDto.CREATE_ORDER createOrder){
        isOrderCode(createOrder.getOrderCode());
        final OrderEntity orderEntity = OrderEntity.of(createOrder);

        orderRepository.save(orderEntity);
        final ResponseOrderDto.READ_ORDER_INFO readOrderInfo = OrderEntity.of(orderEntity);

        return  readOrderInfo;
    }

    /**
     * find Orders By Identity Service
     * @param identity find idenity
     * @return List read orderinfos
     */
    public List<ResponseOrderDto.READ_ORDER_INFO> findOrderInfosByIdentity(String identity){
        final List<OrderEntity> orderEntities = orderRepository.findByIdentity(identity);
        final List<ResponseOrderDto.READ_ORDER_INFO> readOrderInfos = OrderEntity.of(orderEntities);

        return readOrderInfos;
    }

    /**
     * find OrdrEntity Service
     * @param orderCode find Order Code
     * @return read order info one
     */
    public ResponseOrderDto.READ_ORDER_INFO findOrderInfoByOrderCode(String orderCode){
        final OrderEntity orderEntity = findOrderByOrderCode(orderCode);
        final ResponseOrderDto.READ_ORDER_INFO readOrderInfo = OrderEntity.of(orderEntity);

        return readOrderInfo;
    }

    /**
     * find OrderEntity Service
     * @param orderCode find Order Code
     * @return OrderEntity
     */
    private OrderEntity findOrderByOrderCode(String orderCode){
        return orderRepository.findByOrderCode(orderCode)
                .orElseThrow(() -> new RuntimeException("No exist orderCode"));

    }

    /**
     * orderCode Duplicated Check
     * @param orderCode check orderCode
     */
    private void isOrderCode(String orderCode){
        if(orderRepository.existsByOrderCode(orderCode))
            throw new RuntimeException("Duplicated OrderCode");
    }
}
