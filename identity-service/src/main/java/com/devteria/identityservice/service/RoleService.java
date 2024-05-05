package com.devteria.identityservice.service;

import com.devteria.identityservice.dto.request.PermissionRequest;
import com.devteria.identityservice.dto.request.RoleRequest;
import com.devteria.identityservice.dto.response.PermissionResponse;
import com.devteria.identityservice.dto.response.RoleResponse;
import com.devteria.identityservice.entity.Permission;
import com.devteria.identityservice.entity.Role;
import com.devteria.identityservice.mapper.PermissionMapper;
import com.devteria.identityservice.mapper.RoleMapper;
import com.devteria.identityservice.repository.PermissionRepository;
import com.devteria.identityservice.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
//@RequiredArgsConstructor
@Slf4j
//@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PermissionRepository permissionRepository;

    @Autowired
    RoleMapper roleMapper;


    public RoleResponse create(RoleRequest request) {
        Role role = roleMapper.toRole(request);

        List<Permission> permissions =  permissionRepository.findAllById(request.getPermissions());
        role.setPermissions((new HashSet<>(permissions)));

        roleRepository.save(role);
        return roleMapper.toRoleResponse(role);
    }

    public List<RoleResponse> getAll() {
//        List<Role> role = roleRepository.findAll();
//        return role.stream().map(roleMapper::toRoleResponse).toList();
        return roleRepository.findAll()
                .stream()
                .map(roleMapper::toRoleResponse)
                .toList();
    }

    public void delete(String roleName) {
        roleRepository.deleteById(roleName);
    }

}
