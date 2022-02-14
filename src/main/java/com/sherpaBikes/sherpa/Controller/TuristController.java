package com.sherpaBikes.sherpa.Controller;
import com.sherpaBikes.sherpa.Dao.ITuristDao;
import com.sherpaBikes.sherpa.Entity.Turist;
import com.sherpaBikes.sherpa.Errors.MisErrores;
import com.sherpaBikes.sherpa.Service.imp.TuristServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/turista")
public class TuristController {

    @Autowired
    private TuristServiceImp turistServiceImp;

    @Autowired
    private ITuristDao iTuristDao;

    //Metodo para mostrar FORMULARIO para CARGAR turistas
    @GetMapping("/turist-form")
    public String turistForm(Model model) {
        model.addAttribute("turist", new Turist());
        return "hotel/turistForm";

    }

    //Metodo POST para GUARDAR turistas
    @PostMapping("/save-turist")
    public String saveTurist( Turist turist, @RequestParam String name, @RequestParam String lastName,
                              @RequestParam String nationality, RedirectAttributes redirect){


        try {
            turistServiceImp.saveTurist(turist,name, lastName, nationality);
        } catch (MisErrores e) {
            e.printStackTrace();
        }

        redirect.addFlashAttribute("turistaGuardado",
                "Turista Guardado con éxito");

        return "hotel/turistForm";
    }

    //Método que me muestra LISTADO de los turistas para gestionarlos
    @GetMapping("/gestion-turista")
    public String listadoTurista(Model model){
        model.addAttribute("turist", turistServiceImp.listadoTurista());

        return "hotel/gestionTurista";
    }

    //Método para BORRAR turista
    @GetMapping("/borrar-turista/{id}")
    public String deleteTurist(@PathVariable Long id, RedirectAttributes redirect){

        turistServiceImp.deleteTurist(id);
        redirect.addFlashAttribute("turistaEliminado","Turista Eliminado");

        return "redirect:/turista/gestion-turista";
    }


    //Método para desplegar formulario de edición de turistas
    @GetMapping("/editar-turista-form/{id}")
    public String editarFormulario(@PathVariable Long id, Model model){
        Turist turist = null;

        if (id>0){
            turist = turistServiceImp.getTuristlById(id);
            model.addAttribute("turist", turist);
        }
        return "hotel/turistUpdate";
    }


    //Método para editar turista
    @PostMapping("/editar-turista/")
    public String updateTurista(@RequestParam Long id, @RequestParam String name, @RequestParam String lastName, @RequestParam String nationality, @RequestParam String passportNumber,
                                @RequestParam String address, @RequestParam String email, @RequestParam String dob, @RequestParam Long celPhoneNumber, RedirectAttributes redirect) throws MisErrores {

        turistServiceImp.updateTurist(id, name, lastName, nationality);
        redirect.addFlashAttribute("turistaModificado", "Turista Modificado");

        return "redirect:/turista/gestion-turista";
    }
}
