package com.sherpaBikes.sherpa.Controller;
import com.sherpaBikes.sherpa.Entity.Rol;
import com.sherpaBikes.sherpa.Entity.Usuario;
import com.sherpaBikes.sherpa.Enums.RolNombre;
import com.sherpaBikes.sherpa.Service.imp.RolService;
import com.sherpaBikes.sherpa.Service.imp.UsuarioServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashSet;
import java.util.Set;
@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioServiceImp usuarioService;

    @Autowired
    private RolService rolService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/registro")
    public String registrar() {
        return "registro";
    }

    @PostMapping("/save")
    //Metodo para guardar Usuarios con el RolUser
    public String saveUser(String username, String password, RedirectAttributes redirect, Model model) {

        if (usuarioService.existsByUserName(username)) {
            model.addAttribute("usuarioRepetido", "Este usuario ya existe, intente otro");
            return "registro";
        }

        if (username == "" || password == "") {
            model.addAttribute("camposVacios", "Los campos no pueden estar vacíos");
            return "registro";
        }

        Usuario usuario = new Usuario();
        usuario.setUsername(username); //Viene del formulario de registro
        usuario.setPassword(passwordEncoder.encode(password)); //encripto la clave

        //Asigno ROL_ADMIN
         Rol rolAdmin = rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get();

        //Asigno ROL_HOTEL
//        Rol rolHotel = rolService.getByRolNombre(RolNombre.ROLE_HOTEL).get();


        //Creo un set para añadir roles porque pueden ser muchos
        Set<Rol> roles = new HashSet<Rol>();
        roles.add(rolAdmin);
        //roles.add(rolAdmin);

        //Asignamos el rol del usuario al usuario
        usuario.setRoles(roles);

        usuarioService.guardarUsuario(usuario);

        //Utilizamos el model para mandarle info a la vista
        redirect.addFlashAttribute("usuarioRegistrado", "Registro Completado, inicie sesión");

        //Aca el Javi se mando cualquiera, lo comente por las dudas para no borrar
        //@Juan
//        if (rolHotel == rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get()) {
//            return "redirect:/admin/gestion-hoteles";
//        }
//
//        if (rolHotel == rolService.getByRolNombre(RolNombre.ROLE_HOTEL).get()) {
//            return "redirect:/hotel/gestion-bicicletas";
//        }

        return "redirect:/guestForm";
    }
}

