package com.scan.seguridad.dao;

import com.scan.seguridad.model.SegAdscripcion;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author joel.sandoval
 */
//@Service
@Repository
public interface SegAdscripcionRepository extends PagingAndSortingRepository<SegAdscripcion, Integer> {

    static final Logger LOGGER = LoggerFactory.getLogger(SegAdscripcionRepository.class);

    @Query("Select a From SegAdscripcion a Where a.padre = :area or a.abuelo = :area")
    List<SegAdscripcion> dameAreaEva(@Param("area") Integer area);

    @Query("Select a From SegAdscripcion a Where a.id = :area ")
    SegAdscripcion dameAdscripcion(@Param("area") Integer area);

    @Query("Select a From SegAdscripcion a Where a.padre = :area and a.estatus = true and a.eia = true "
            + "Order By a.nivel, a.userCorto")
    List<SegAdscripcion> dameAreaHijos(@Param("area") Integer area);


}
