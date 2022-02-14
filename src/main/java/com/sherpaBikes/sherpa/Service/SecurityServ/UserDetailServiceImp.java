package com.sherpaBikes.sherpa.Service.SecurityServ;
import com.sherpaBikes.sherpa.Entity.Usuario;
import com.sherpaBikes.sherpa.Service.imp.UsuarioServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImp implements UserDetailsService {

    @Autowired
    private UsuarioServiceImp usuarioService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = usuarioService.getUsuarioByUserName(username)
                .orElseThrow(()-> new UsernameNotFoundException(username));

        return UsuarioPrincipal.build(usuario);
    }
}

