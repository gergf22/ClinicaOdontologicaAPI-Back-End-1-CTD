package com.BackEnd1.ClinicaOdontologica.security;

import com.BackEnd1.ClinicaOdontologica.entity.Usuario;
import com.BackEnd1.ClinicaOdontologica.entity.UsuarioRole;
import com.BackEnd1.ClinicaOdontologica.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DatosIniciales implements ApplicationRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        String passSinBCUser = "us";
        String passBCUser= encoder.encode(passSinBCUser);
        String passSinBCAdmin= "admin";
        String passBCAdmin = encoder.encode(passSinBCAdmin);


        Usuario usuario = new Usuario("user","german","us@us.com",passBCUser, UsuarioRole.ROLE_USER);

        Usuario admin = new Usuario("admin","ger","admin@admin.com",passBCAdmin,UsuarioRole.ROLE_ADMIN);

        usuarioRepository.save(usuario);
        usuarioRepository.save(admin);
    }
}
