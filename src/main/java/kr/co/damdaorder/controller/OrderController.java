package kr.co.damdaorder.controller;

import io.swagger.annotations.ApiOperation;
import kr.co.damdaorder.dto.RequestDto;
import kr.co.damdaorder.dto.ResponseDto;
import kr.co.damdaorder.error.model.ResponseFormat;
import kr.co.damdaorder.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @ApiOperation("상품 주문 / 유저만 가능")
    @PostMapping("/{identity}/orders")
    public ResponseFormat<ResponseDto.READ_ORDER_INFO> createOrder(@PathVariable("identity") String identity,
                                                                   @RequestBody @Valid RequestDto.CREATE_ORDER createOrder){
        return ResponseFormat.ok(orderService.createOrder(createOrder));
    }

    @ApiOperation("회원 아이디로 주문 조회 / 본인 및 관리자만 가능")
    @GetMapping("/{identity}/orders")
    public ResponseEntity<List<ResponseDto.READ_ORDER_INFO>> findOrderInfosByIdentity(@PathVariable("identity") String identity){
        return ResponseEntity.ok().body(orderService.findOrderInfosByIdentity(identity));
    }

    @ApiOperation("주문 코드로 모든 주문 기록 조회")
    @GetMapping("/orders")
    public ResponseFormat<ResponseDto.READ_ORDER_INFO> findOrderInfoByOrderCode(@RequestParam("orderCode") String orderCode){
        return ResponseFormat.ok(orderService.findOrderInfoByOrderCode(orderCode));
    }

    @ApiOperation("주문 기록 삭제 / 관리자만 가능")
    @DeleteMapping("/orders")
    public ResponseFormat<String> deleteOrder(@RequestParam("orderCode") String orderCode){
        //TODO delete order service
        return null;
    }
}
