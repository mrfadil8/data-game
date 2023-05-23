package com.data.game.model;

import javax.persistence.*;

@Entity
@Table(name="data_game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String tanggal;

    @Column
    private String timSatu;

    @Column
    private String timDua;

    @Column
    private String tempat;

    public Game(Long id, String tanggal, String timSatu, String timDua, String tempat) {
        this.id = id;
        this.tanggal = tanggal;
        this.timSatu = timSatu;
        this.timDua = timDua;
        this.tempat = tempat;
    }

    public Game() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getTimSatu() {
        return timSatu;
    }

    public void setTimSatu(String timSatu) {
        this.timSatu = timSatu;
    }

    public String getTimDua() {
        return timDua;
    }

    public void setTimDua(String timDua) {
        this.timDua = timDua;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }
}
