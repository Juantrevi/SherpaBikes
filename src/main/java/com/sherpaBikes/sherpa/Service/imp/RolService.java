package com.sherpaBikes.sherpa.Service.imp;
import com.sherpaBikes.sherpa.Dao.IRolDao;
import com.sherpaBikes.sherpa.Entity.Rol;
import com.sherpaBikes.sherpa.Enums.RolNombre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RolService {

    @Autowired
    private IRolDao rolDao;

    //----METODOS---//

    public void guardarRol(Rol rol){
        rolDao.save(rol);
    }
    public Optional<Rol> getByRolNombre(RolNombre rolNombre){
        return rolDao.findByRolNombre(rolNombre);
    }

    public boolean existsByRolNombre(RolNombre rolNombre){
        return rolDao.existsByRolNombre(rolNombre);
    }

}
