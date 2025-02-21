package com.PlayerManger.Baseball.lineup.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Player implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String team;
    private String position;

    @Column(nullable = false, updatable = false)
    private String playerCode;

    public Player(){}

    public Player(Long id,String name, String team, String position, String playerCode){
        this.id = id;
        this.name = name;
        this.team = team;
        this.position = position;
        this.playerCode = playerCode;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getTeam() {
        return team;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public void setPlayerCode(String playerCode) {
        this.playerCode = playerCode;
    }

    public String getPlayerCode() {
        return playerCode;
    }

    @Override
    public String toString(){
        return "id: " + id + " name: " + name + " team: " + team ;
    }
}
