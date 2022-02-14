package com.sherpaBikes.sherpa.Service.imp;


import com.sherpaBikes.sherpa.Errors.MisErrores;
import com.sherpaBikes.sherpa.Dao.IPictureDao;
import com.sherpaBikes.sherpa.Entity.Picture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public class PictureServiceImp {

    @Autowired
    private IPictureDao pictureDao;

    public Picture save(MultipartFile multipartFile) throws MisErrores {
        if (multipartFile != null){
            try{
                Picture picture = new Picture();
                picture.setMime(multipartFile.getContentType());
                picture.setName(multipartFile.getName());
                picture.setContent(multipartFile.getBytes());

                return pictureDao.save(picture);}
            catch (Exception e){
                e.getMessage();
            }

        }
        return null;
    }

    public Picture updatePicture (Long idPicture, MultipartFile multipartFile) throws MisErrores{
        if (multipartFile != null){
            try{
                Picture picture = new Picture();

                if (idPicture != null){
                    Optional<Picture> answer = pictureDao.findById(idPicture);
                    if (answer.isPresent()){
                        picture = answer.get();
                    }
                }

                picture.setMime(multipartFile.getContentType());
                picture.setName(multipartFile.getName());
                picture.setContent(multipartFile.getBytes());

                return pictureDao.save(picture);}
            catch (Exception e){
                e.getMessage();
            }

        }

        return null;
    }



}

