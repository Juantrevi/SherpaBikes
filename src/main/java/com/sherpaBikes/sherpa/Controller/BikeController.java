package com.sherpaBikes.sherpa.Controller;

import com.sherpaBikes.sherpa.Dao.IBikeDao;
import com.sherpaBikes.sherpa.Dao.IHotelDao;
import com.sherpaBikes.sherpa.Entity.Bike;
import com.sherpaBikes.sherpa.Entity.Hotel;
import com.sherpaBikes.sherpa.Errors.MisErrores;
import com.sherpaBikes.sherpa.Service.imp.BikeServiceImp;
import com.sherpaBikes.sherpa.Service.imp.HotelServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/bikes")
public class BikeController {

    @Autowired
    private BikeServiceImp bikeService;

    @Autowired
    private HotelServiceImp hotelService;

    @Autowired
    private IHotelDao iHotelDao;

    @Autowired
    private IBikeDao iBikeDao;


    //Método para agregar una bici
    @GetMapping("/nueva-bici")
    public String nuevaBiciForm(Model model) {

        model.addAttribute("bike", new Bike());
        model.addAttribute("hoteles", hotelService.listHotels());

        return "admin/nuevaBiciForm";
    }

    //Método para anadir una bici a un hotel
    @GetMapping("/add-bikes/{id}") //Le pasamos el id de la hotel para que asocie a la bici
    public String addBikes(@PathVariable Long id, Model model){

        Hotel hotel = hotelService.getHotelById(id);
        model.addAttribute("bike", new Bike());

        return "admin/addBikesForm";
    }

    @PostMapping("/save-bike/")
    public String saveBike(Bike bike,@RequestParam(name = "modelo") String modelo,
                           RedirectAttributes redirect){

        bike.setBikeModel(modelo);

        bikeService.save(bike);

        redirect.addFlashAttribute("biciGuardada","Bici guardada con éxito!");

        return "redirect:/admin/gestion-hoteles";
    }

    //Método para eliminar bicicletas
    @GetMapping("/delete-bike/{id}")
    public String deleteBike(@PathVariable Long id, RedirectAttributes redirect){

        bikeService.deleteBike(id);
        redirect.addFlashAttribute("hotelEliminado","Bicicleta Eliminada");

        return "redirect:/admin/gestion-hoteles";}


    //Método para desplegar formulario de edición de hoteles
    @GetMapping("/editar-form/{id}")
    public String editarFormulario(@PathVariable Long id, Model model){
        Bike bike = null;

        if (id>0){
            bike = bikeService.getBikeById(id).orElse(null);

            model.addAttribute("bici", bike);
            model.addAttribute("hotel", new Hotel());
        }
        return "admin/editarBici";
    }


    @PostMapping("/editar-bici")
    public String editHotel(@RequestParam String bikeModel,
                            @RequestParam Long biciId, @RequestParam (name = "hotelId") Long idHotel, RedirectAttributes redirect) throws MisErrores {

        bikeService.modificarBici(bikeModel, biciId, idHotel);


        return "redirect:/admin/gestion-hoteles";
    }

    @PostMapping("/set-hotel")
    public String setHotel (@RequestParam Long idHotel, Model model, @RequestParam Long idBici){
        Bike bike = new Bike();
        Hotel hotel = new Hotel();

        Optional<Hotel> rtaHotel = iHotelDao.findById(idHotel);
        Optional<Bike> rtaBike = iBikeDao.findById(idBici);

        if (rtaHotel.isPresent() & rtaBike.isPresent()){
            hotel = rtaHotel.get();
            bike = rtaBike.get();

            bikeService.setearHotel(bike,hotel);
        }


        System.out.println(idHotel + "id hotel");
        System.out.println(idBici + "id Bici");



        return "hotel/addBikeToHotel";
    }

}