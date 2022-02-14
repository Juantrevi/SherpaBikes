package com.sherpaBikes.sherpa.Entity;

import com.sherpaBikes.sherpa.Enums.RolNombre;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class UsuarioHotel {
    @Id
    @GeneratedValue()
    private Long id;

    @OneToOne
    private Hotel hotel;

    @OneToOne
    private Usuario usuario;

    @OneToOne
    private Rol rol;


    public UsuarioHotel() {
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
