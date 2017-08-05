package com.team.solventa.service;

import org.springframework.security.access.prepost.PreAuthorize;

public interface MethodSecurityService {
 @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_NORMAL')")
 String requiresUserRole();
}