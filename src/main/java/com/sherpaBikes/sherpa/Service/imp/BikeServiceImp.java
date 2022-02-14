package com.sherpaBikes.sherpa.Service.imp;
import com.sherpaBikes.sherpa.Dao.IBikeDao;
import com.sherpaBikes.sherpa.Dao.IHotelDao;
import com.sherpaBikes.sherpa.Entity.Bike;
import com.sherpaBikes.sherpa.Entity.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BikeServiceImp {

    @Autowired
    private IBikeDao iBikeDao;

    @Autowired
    private IHotelDao iHotelDao;

    public List<Bike>listadoBicis (){

        return iBikeDao.findAll() ;
    }

    public void deleteBike(Long id) { iBikeDao.deleteById(id);
    }


    public Optional<Bike> getBikeById(Long id) { return iBikeDao.findById(id);
    }

    @Transactional
    public void modificarBici(String bikeModel, Long idBici, Long idHotel) {

        Optional<Hotel> rtaHotel = iHotelDao.findById(idHotel);
        Optional<Bike> rtaBike = iBikeDao.findById(idBici);

        if (rtaHotel.isPresent() & rtaBike.isPresent()) {
            Hotel hotel = rtaHotel.get();
            Bike bike = rtaBike.get();

            bike.setHotel(null);

            List<Bike> listaDeBicis = hotel.getBikeList();

            listaDeBicis.add(bike);
        }
    }


    public void save(Bike bike) {
        iBikeDao.save(bike);
    }

    public void setearHotel(Bike bike, Hotel hotel){
        bike.setHotel(hotel);
    };
}
