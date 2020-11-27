package com.dudungtak.seproject.controller.api;

import com.dudungtak.seproject.controller.AuthFilter;
import com.dudungtak.seproject.enumpackage.AccessType;
import com.dudungtak.seproject.network.Header;
import com.dudungtak.seproject.network.request.OrderGroupApiRequest;
import com.dudungtak.seproject.network.response.OrderGroupApiResponse;
import com.dudungtak.seproject.service.api.OrderGroupApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class OrderGroupApiController {
    @Autowired
    OrderGroupApiService orderGroupApiService;

    @PostMapping("/{id}/order")         // --> /order
    public Header<OrderGroupApiResponse> create(Authentication authentication, @RequestBody Header<OrderGroupApiRequest> request) {
        if(!AuthFilter.isValidAccess(authentication, AccessType.CUSTOMER))
            return Header.ERROR("permission denied");

        return orderGroupApiService.create(authentication, request);
    }

    @GetMapping("/{id}/orders")         // --> /orders
    public Header<List<OrderGroupApiResponse>> readAll(Authentication authentication, @PathVariable Long id, Pageable pageable) {
        if(!AuthFilter.isValidAccess(authentication, AccessType.LOGINEDALL))
            return Header.ERROR("permission denied");

        return orderGroupApiService.readAll(authentication, id, pageable);
    }

    @GetMapping("/order/confirm")
    public Header<List<OrderGroupApiResponse>> nextConfirm(Authentication authentication) {
        if(!AuthFilter.isValidAccess(authentication, AccessType.MANAGER))
            return Header.ERROR("permission denied");

        return orderGroupApiService.nextConfirm();
    }

    @PostMapping("/order/confirm")
    public Header confirm(Authentication authentication, @RequestBody Header<List<OrderGroupApiRequest>> request) {
        if(!AuthFilter.isValidAccess(authentication, AccessType.MANAGER))
            return Header.ERROR("permission denied");

        return orderGroupApiService.confirm(request);
    }

    @GetMapping("order/cook/{id}")              // --> order/cook
    public Header<OrderGroupApiResponse> nextCook(Authentication authentication, @PathVariable Long id) {
        if(!AuthFilter.isValidAccess(authentication, AccessType.COOK))
            return Header.ERROR("permission denied");

        return orderGroupApiService.nextCook(authentication, id);
    }

    @PostMapping("order/cook/{id}/finish")      // --> order/cook/finish
    public Header finishCook(Authentication authentication, @PathVariable Long id) {
        if(!AuthFilter.isValidAccess(authentication, AccessType.COOK))
            return Header.ERROR("permission denied");

        return orderGroupApiService.finishCook(authentication, id);
    }

    @GetMapping("order/delivery/{id}")          // --> order/delivery
    public Header<OrderGroupApiResponse> nextDelivery(Authentication authentication, @PathVariable Long id) {
        if(!AuthFilter.isValidAccess(authentication, AccessType.DELIVERYMAN))
            return Header.ERROR("permission denied");

        return orderGroupApiService.nextDelivery(authentication, id);
    }

    @PostMapping("order/delivery/{id}/finish")  // --> order/delivery/finish
    public Header finishDelivery(Authentication authentication, @PathVariable Long id) {
        if(!AuthFilter.isValidAccess(authentication, AccessType.DELIVERYMAN))
            return Header.ERROR("permission denied");

        return orderGroupApiService.finishDelivery(authentication, id);
    }

    @PutMapping("order/{id}/cancel")            // --> order/cancel
    public Header cancel(Authentication authentication, @RequestBody Header<OrderGroupApiRequest> request) {
        if(!AuthFilter.isValidAccess(authentication, AccessType.CUSTOMER))
            return Header.ERROR("permission denied");

        return orderGroupApiService.cancel(authentication, request);
    }
}
