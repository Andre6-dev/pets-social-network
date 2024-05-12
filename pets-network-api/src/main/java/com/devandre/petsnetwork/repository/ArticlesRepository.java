package com.devandre.petsnetwork.repository;

import com.devandre.petsnetwork.domain.Articles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticlesRepository extends JpaRepository<Articles, Long> {
}