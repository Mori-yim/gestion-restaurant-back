package com.example.restau.repository;



import com.example.restau.model.Plat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlatRepository extends JpaRepository<Plat, Long> {
    List<Plat> findByDisponibleTrue();
    List<Plat> findByCategorieId(Long categorieId);
    Page<Plat> findByDisponibleTrue(Pageable pageable);
}
