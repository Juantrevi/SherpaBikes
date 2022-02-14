package com.sherpaBikes.sherpa.Dao;

import com.sherpaBikes.sherpa.Entity.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPictureDao extends JpaRepository<Picture, Long> {

}
