package com.dudungtak.seproject.controller.api;

import com.dudungtak.seproject.controller.Permission;
import com.dudungtak.seproject.enumpackage.AccessType;
import com.dudungtak.seproject.network.Header;
import com.dudungtak.seproject.network.response.UserApiResponse;
import com.dudungtak.seproject.service.api.UserApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/staff")
public class StaffApiController {

    @Autowired
    UserApiService userApiService;

    @GetMapping("{id}")
    public Header<UserApiResponse> read(Authentication authentication, @PathVariable Long id) {
        if(!Permission.isValidAccess(authentication, AccessType.MANAGER))
            return Header.ERROR("permission denied");

        return userApiService.readStaff(id);
    }

    @GetMapping("")
    public Header<List<UserApiResponse>> readAll(Authentication authentication, Pageable pageable) {
        if(!Permission.isValidAccess(authentication, AccessType.MANAGER))
            return Header.ERROR("permission denied");

        return userApiService.readAllStaff(pageable);
    }

    @DeleteMapping("{id}")
    public Header<UserApiResponse> delete(Authentication authentication, @PathVariable Long id) {
        if(!Permission.isValidAccess(authentication, AccessType.MANAGER))
            return Header.ERROR("permission denied");

        return userApiService.deleteStaff(id);
    }
}
