package br.com.dudzdev.me.repository;

import br.com.dudzdev.me.entity.Badge;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data repository for the Badge entity.
 */
@Repository
public interface BadgeRepository extends JpaRepository<Badge, Long> {

}
