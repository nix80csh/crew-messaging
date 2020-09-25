package com.crew.messaging.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface TemplateKakaoRepo extends JpaRepository<TemplateKakao, Integer> {
  Optional<TemplateKakao> findByCode(String code);
}
