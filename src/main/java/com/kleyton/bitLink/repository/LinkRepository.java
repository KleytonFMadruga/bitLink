package com.kleyton.bitLink.repository;

import com.kleyton.bitLink.models.Link;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkRepository extends JpaRepository<Link, Integer> {

}
