package com.sherpaBikes.sherpa.Dao;

import com.sherpaBikes.sherpa.Entity.Bike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBikeDao extends JpaRepository<Bike,Long> {

}
