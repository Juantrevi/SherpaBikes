package com.sherpaBikes.sherpa.Controller;


import com.sherpaBikes.sherpa.Errors.MisErrores;
import com.sherpaBikes.sherpa.Entity.Hotel;
import com.sherpaBikes.sherpa.Service.imp.HotelServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/foto")
public class PictureController {

    @Autowired
    private HotelServiceImp hotelService;

    @GetMapping("/hotel/{id}")
    public ResponseEntity<byte[]> fotoHotel(@PathVariable Long id) {
        try {
            Hotel hotel = hotelService.getHotelById(id);
            if (hotel.getPicture() == null){
                throw new MisErrores("El hotel no tiene foto");
            }

            byte[] foto = hotel.getPicture().getContent();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);

            return new ResponseEntity<>(foto, headers, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
