package com.sherpaBikes.sherpa.Controller;

import com.sherpaBikes.sherpa.Errors.MisErrores;
import com.sherpaBikes.sherpa.Entity.*;
import com.sherpaBikes.sherpa.Service.imp.BikeServiceImp;
import com.sherpaBikes.sherpa.Service.imp.HotelServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private BikeServiceImp bikeService;

    @Autowired
    private HotelServiceImp hotelService;

    //Metodo para que me despliegue un formulario de carga de hoteles
    @GetMapping("/hotel-form")
    public String hotelForm(Model model) { //Para pasarle el objeto que quiero guardar a la base de datos utilizo Model
        model.addAttribute("usuariohotel", new UsuarioHotel());
        return "admin/hotelForm";
    }

    //Metodo POST para guardar hoteles
    @PostMapping("/save-hotel")
    public String saveHotel(MultipartFile archivo, @RequestParam String name, @RequestParam String location,
                            RedirectAttributes redirect, @ModelAttribute("hotelParaUser")Hotel hotel){

        try {
            hotelService.save(archivo,name,location);
        } catch (MisErrores e) {
            e.printStackTrace();
        }


        redirect.addFlashAttribute("hotelGuardado",
                "Hotel Guardado con éxito");

        return "redirect:/registro"; //para que me redirecciona al formulario de carga de hoteles nuevamente
    }


    //Método que me muestra listado de los hoteles para gestionarlos
    @GetMapping("/gestion-hoteles")
    public String listadoHoteles(Model model){
        model.addAttribute("hotel", hotelService.listHotels());

        return "admin/gestionHoteles";
    }

    //Método para eliminar hoteles
    @GetMapping("/delete-hotel/{id}")
    public String deleteHotel(@PathVariable Long id, RedirectAttributes redirect){

        hotelService.deleteHotel(id);
        redirect.addFlashAttribute("hotelEliminado","Hotel Eliminado");

        return "redirect:/admin/gestion-hoteles";}

    //Método para desplegar formulario de edición de hoteles
    @GetMapping("/editar-form/{id}")
    public String editarFormulario(@PathVariable Long id, Model model){
        Hotel hotel = null;

        if (id>0){
            hotel = hotelService.getHotelById(id);
            model.addAttribute("hotel", hotel);
        }
        return "admin/editarHotel";
    }

    //Método POST para editar hotel
    @PostMapping("/editar-hotel")
    public String editHotel(MultipartFile archivo, @RequestParam String name, @RequestParam String location,
                            @RequestParam Long id, RedirectAttributes redirect) throws MisErrores {


        hotelService.modificarHotel(id, name, location, archivo);

        return "redirect:/admin/gestion-hoteles";
    }





//---------------------METODOS PARA BICIS -----------------------------------------//



    /*Metodo para que me despliegue un formulario de carga de bicis
    @GetMapping("/bike-form")
    public String bikeForm(Model model) { //Para pasarle el objeto que quiero guardar a la base de datos utilizo Model
        model.addAttribute("bike", new Bike());
        return "admin/bikeForm";
    }
*/
    /*Metodo para guardar una bici
    @PostMapping("/save-bike")
    public String saveBike(Bike bike, RedirectAttributes redirect){

//        bikeService.(bike);
//        redirect.addFlashAttribute("biciGuardada",
 //                       "Bici Guardada con éxito"); //"biciGuardada" es el valor que recojo de la vista html5 "biciForm" con las etiquetas Thymeleaf
        return "admin/bikeForm"; //para que me redireccione al formulario de carga de bicis
    }
*/
    @GetMapping("/listado-bicis")
    public String listadeBicis(Model model) { //Para pasarle el objeto que quiero guardar a la base de datos utilizo Model

        model.addAttribute("listaBicis", bikeService.listadoBicis());

        return "admin/listadoBicis";
    }


}

