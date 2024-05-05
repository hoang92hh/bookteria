package com.devteria.identityservice.dto.response;

import com.devteria.identityservice.entity.Permission;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleResponse {

    private String name;
    private  String  description;
    private Set<Permission> permissions;
}
