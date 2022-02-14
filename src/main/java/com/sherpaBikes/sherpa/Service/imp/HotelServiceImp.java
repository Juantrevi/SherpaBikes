package com.sherpaBikes.sherpa.Service.imp;

import com.sherpaBikes.sherpa.Entity.Bike;
import com.sherpaBikes.sherpa.Errors.MisErrores;
import com.sherpaBikes.sherpa.Dao.IHotelDao;
import com.sherpaBikes.sherpa.Entity.Hotel;
import com.sherpaBikes.sherpa.Entity.Picture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class HotelServiceImp {

    @Autowired
    private IHotelDao iHotelDao;

    @Autowired
    private PictureServiceImp pictureService;



    @Transactional
    public void save(MultipartFile archivo, String name, String location) throws MisErrores {

        if (name == null || name.isEmpty()) {
            throw new MisErrores("El nombre del hotel no puede ser nulo");
        }
        Hotel hotel = new Hotel();
        hotel.setName(name);
        hotel.setLocation(location);

        Picture picture = pictureService.save(archivo);
        hotel.setPicture(picture);

        iHotelDao.save(hotel);
    }

    //Método que me devuelve una lista de todos los hoteles
    public List<Hotel> listHotels() {
        return iHotelDao.findAll();
    }

    //Método que me devuelve un hotel por su id
    public Hotel getHotelById(Long id) {
        return iHotelDao.findById(id).orElse(null);
    }

    //Método para eliminar un hotel por su id
    public void deleteHotel(Long id) {
        iHotelDao.deleteById(id);
    }

    //Metodo que me devuelve la lista de Bicis del Hotel
    public  List<Bike> listadoBiciHotel(Long id){

        Optional<Hotel> hotel = iHotelDao.findById(id);

        List<Bike>listadoBicis = hotel.get().getBikeList();

        return listadoBicis;
    }


    @Transactional
    public void modificarHotel (Long id, String name, String location,
                                MultipartFile archivo) throws MisErrores{

        Optional<Hotel> respuesta = iHotelDao.findById(id);

        if (respuesta.isPresent()){
            Hotel hotel = respuesta.get();
            hotel.setName(name);
            hotel.setLocation(location);


            Long idpicture = null;
            if (hotel.getPicture() != null){
                idpicture = hotel.getPicture().getId();
            }

            Picture picture = pictureService.updatePicture(idpicture,archivo);
            hotel.setPicture(picture);

            iHotelDao.save(hotel);
        }else {
            throw new MisErrores("Hotel no encontrado");
        }
    }


}
