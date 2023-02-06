package kr.co.damdaorder.controller;

import kr.co.dto.*;
import kr.co.damdaorder.service.OrderService;
import kr.co.error.model.ResponseFormat;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/{identity}/orders")
    public ResponseFormat<ResponseOrderDto.READ_ORDER_INFO> createOrder(@PathVariable("identity") String identity,
                                                                        @RequestBody @Valid RequestOrderDto.CREATE_ORDER createOrder){
        return ResponseFormat.ok(orderService.createOrder(createOrder));
    }

    @GetMapping("/{identity}/orders")
    public ResponseFormat<List<ResponseOrderDto.READ_ORDER_INFO>> findOrderInfosByIdentity(@PathVariable("identity") String identity){
        return ResponseFormat.ok(orderService.findOrderInfosByIdentity(identity));
    }

    @GetMapping("/orders")
    public ResponseFormat<ResponseOrderDto.READ_ORDER_INFO> findOrderInfoByOrderCode(@RequestParam("orderCode") String orderCode){
        return ResponseFormat.ok(orderService.findOrderInfoByOrderCode(orderCode));
    }
}
