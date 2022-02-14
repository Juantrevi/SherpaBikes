package com.sherpaBikes.sherpa.Controller;

import com.sherpaBikes.sherpa.Dao.IHotelDao;
import com.sherpaBikes.sherpa.Entity.Hotel;
import com.sherpaBikes.sherpa.Service.imp.BikeServiceImp;
import com.sherpaBikes.sherpa.Service.imp.HotelServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    private IHotelDao iHotelDao;

    @Autowired
    private BikeServiceImp bikeService;

    //Método que me muestra home del hotel para gestionarlos
    @GetMapping("/home-hotel/{id}")
    public String homeHotel(Model model){
        model.addAttribute("bici", bikeService.listadoBicis());

        return "hotel/homeHotel";
    }

    //Método que me muestra home del hotel para gestionarlos
    @GetMapping("/add-bikes/{id}")
    public String addBiketoHotel(Model model, @PathVariable Long id){

        Optional<Hotel> hotel = iHotelDao.findById(id);

        model.addAttribute("hotel", hotel);
        model.addAttribute("bicis", bikeService.listadoBicis());

        return "hotel/addBikeToHotel";
    }

}
