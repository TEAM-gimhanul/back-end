package com.backend.gimhanul.domain.user.service;

import com.backend.gimhanul.domain.user.domain.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
}
