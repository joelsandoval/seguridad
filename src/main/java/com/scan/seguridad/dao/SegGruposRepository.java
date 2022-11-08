package com.scan.seguridad.dao;

import com.scan.seguridad.model.SegGrupos;
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
public interface SegGruposRepository  extends PagingAndSortingRepository<SegGrupos, Integer>{
    
    static final Logger LOGGER = LoggerFactory.getLogger(SegGruposRepository.class);      
        
    List<SegGrupos> findByGrupo();

}
