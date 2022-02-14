package com.sherpaBikes.sherpa.Dao;

import com.sherpaBikes.sherpa.Entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHotelDao extends JpaRepository<Hotel,Long> {
}
