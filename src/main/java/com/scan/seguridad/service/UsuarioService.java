/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scan.seguridad.service;

import com.scan.seguridad.dao.SegGruposRepository;
import com.scan.seguridad.dao.SegUsuariosRepository;
import com.scan.seguridad.model.SegGrupos;
import com.scan.seguridad.model.SegUsuarios;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Joel
 */
@Service
public class UsuarioService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(UsuarioService.class);

    @Autowired
    private SegUsuariosRepository userRepo;

    @Autowired
    private SegGruposRepository grupoRepo;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        SegUsuarios user = userRepo.findByUsuario(username);
        
        if (user == null) {
            logger.error(username);
            throw new UsernameNotFoundException("No existe el usuario" + username);
        }
        List<SegGrupos> grupos = grupoRepo.findByGrupo();

        List<GrantedAuthority> authorities = grupos
                .stream()
                .map(rol -> new SimpleGrantedAuthority(rol.getRole()))
                .peek(auth -> logger.info("Role: " + auth.getAuthority()))
                .collect(Collectors.toList());

        return new User(user.getUsuario(), user.getPassword(), true, true, true, true, authorities);
    }

}
