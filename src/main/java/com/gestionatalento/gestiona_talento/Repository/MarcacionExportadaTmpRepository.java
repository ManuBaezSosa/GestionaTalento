package com.gestionatalento.gestiona_talento.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gestionatalento.gestiona_talento.Entity.MarcacionExportadaTmp;

import jakarta.transaction.Transactional;

@Repository
public interface MarcacionExportadaTmpRepository extends JpaRepository<MarcacionExportadaTmp, Long> {
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM marcaciones_exportadas_tmp", nativeQuery = true)
    void truncateMarcacionExportadaTmp();
}
