package com.devteria.identityservice.controller;


import com.devteria.identityservice.dto.request.ApiResponse;
import com.devteria.identityservice.dto.request.PermissionRequest;
import com.devteria.identityservice.dto.request.RoleRequest;
import com.devteria.identityservice.dto.response.PermissionResponse;
import com.devteria.identityservice.dto.response.RoleResponse;
import com.devteria.identityservice.service.PermissionService;
import com.devteria.identityservice.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {

    @Autowired
    RoleService roleService;


    @PostMapping
    public ApiResponse<RoleResponse> create(@RequestBody  RoleRequest request){

        return ApiResponse.<RoleResponse>builder()
                .result(roleService.create(request))
                .build();
    }

    @GetMapping
    public ApiResponse<List<RoleResponse>> getAll(){
        return ApiResponse.<List<RoleResponse>>builder()
                .result(roleService.getAll())
                .build();
    }

    @DeleteMapping("/{roleName}")
    public ApiResponse<Void> delete(@PathVariable String roleName){
        roleService.delete(roleName);
        return ApiResponse.<Void>builder().build();
    }



}
