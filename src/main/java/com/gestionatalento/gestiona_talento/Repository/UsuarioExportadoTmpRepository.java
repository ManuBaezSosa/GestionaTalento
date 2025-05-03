package com.gestionatalento.gestiona_talento.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gestionatalento.gestiona_talento.Entity.UsuarioExportadoTmp;

import jakarta.transaction.Transactional;

@Repository
public interface UsuarioExportadoTmpRepository extends JpaRepository<UsuarioExportadoTmp, Long> {
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM usuarios_exportados_tmp", nativeQuery = true)
    void truncateUsuarioExportadoTmp();
}
