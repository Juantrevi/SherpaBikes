package com.sherpaBikes.sherpa.Service.imp;
import com.sherpaBikes.sherpa.Dao.ITuristDao;
import com.sherpaBikes.sherpa.Entity.Turist;
import com.sherpaBikes.sherpa.Errors.MisErrores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TuristServiceImp {

    @Autowired
    private ITuristDao iTuristDao;

    //Metodo para GUARDAR un turista
    @Transactional
    public void saveTurist(Turist turist, String name, String lastName, String nationality) throws MisErrores {
/*
        Date date = new Date();
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(dob);
            System.out.println(date.toString());
            System.out.println(nationality.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
*/
        if (name == null || name.isEmpty()) {
            throw new MisErrores("El nombre no puede ser nulo");
        }
        turist.setName(name);
        turist.setLastName(lastName);
        turist.setNationality(nationality);
/*        turist.setAddress(address);
        turist.setPassportNumber(passportNumber);
        turist.setAddress(address);
        turist.setEmail(email);
        turist.setDob(date);
        turist.setCelPhoneNumber(celPhoneNumber);
*/
        iTuristDao.save(turist);
    }
    //Metodo que devuelve LISTA de turistas
    public List<Turist> listadoTurista() {return iTuristDao.findAll();}

    //Método que me devuelve un turista por su id
    public Turist getTuristlById(Long id) {
        return iTuristDao.findById(id).orElse(null);
    }

    //Método para ELIMINAR un turista  por su id
    public void deleteTurist(Long id) {
        iTuristDao.deleteById(id);
    }


    //Método para MODIFICAR un turista
    @Transactional
    public void updateTurist(Long id, String name,  String lastName, String nationality) throws MisErrores{
/*
        Date date = new Date();
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(dob);
            System.out.println(date.toString());
            System.out.println(nationality.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
*/
        Optional<Turist> respuesta = iTuristDao.findById(id);

        if (respuesta.isPresent()){
            Turist turist = respuesta.get();
            turist.setName(name);
            turist.setLastName(lastName);
            turist.setNationality(nationality);
/*            turist.setPassportNumber(passportNumber);
            turist.setAddress(address);
            turist.setEmail(email);
            turist.setDob(date);
            turist.setCelPhoneNumber(celPhoneNumber);
*/
            iTuristDao.save(turist);
        }else {
            throw new MisErrores("Turista no encontrado");
        }
    }

}
