package com.gestionatalento.gestiona_talento.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gestionatalento.gestiona_talento.Entity.Empleado;
import com.gestionatalento.gestiona_talento.Entity.Justificativo;

@Repository
public interface JustificativoRepository extends JpaRepository<Justificativo, Long> {
    @Query("SELECT e FROM Justificativo e WHERE e.tipoJustificativo.codTipJustificativo <> 9")
    List<Justificativo> listarJustificativos();

    @Query("SELECT e FROM Justificativo e WHERE e.tipoJustificativo.codTipJustificativo = 9")
    List<Justificativo> listarVacaciones();
}
