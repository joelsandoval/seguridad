package com.scan.seguridad.dao;

import com.scan.seguridad.model.SegPermisos;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author joel.sandoval
 */
//@Service
@Repository
public interface SegPermisosRepository extends PagingAndSortingRepository<SegPermisos, Integer> {

    static final Logger LOGGER = LoggerFactory.getLogger(SegPermisosRepository.class);
    
    SegPermisos findByPermiso(String permiso);
    
    List<SegPermisos> findByProceso(Integer proceso);
    
    

}
