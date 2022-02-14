package com.sherpaBikes.sherpa.Dao;

import com.sherpaBikes.sherpa.Entity.Turist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITuristDao extends JpaRepository<Turist, Long>{

}