package com.kleyton.bitLink.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name="TB_LINK")
public class Link implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String link_longo;
    private String link_curto;
    private Integer quant_cliques;

    public Link(String link_longo, String link_curto) {
        this.link_longo = link_longo;
        this.link_curto = link_curto;
        this.quant_cliques = 0;
    }
    public Link() {

    }

    public Integer getId() {
        return id;
    }

    public String getLink_longo() {
        return link_longo;
    }

    public void setLink_longo(String link_longo) {
        this.link_longo = link_longo;
    }

    public String getLink_curto() {
        return link_curto;
    }

    public void setLink_curto(String link_curto) {
        this.link_curto = link_curto;
    }

    public Integer getQuant_cliques() {
        return quant_cliques;
    }

    public void setQuant_cliques(Integer quant_cliques) {
        this.quant_cliques = quant_cliques;
    }
}
