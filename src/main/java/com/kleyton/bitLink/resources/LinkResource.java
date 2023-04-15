package com.kleyton.bitLink.resources;

import com.kleyton.bitLink.models.Link;
import com.kleyton.bitLink.repository.LinkRepository;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping(value = "/bit.link")
public class LinkResource {

    Link link = new Link();

    @Autowired
    LinkRepository linkRepository;

    @GetMapping(value = "/links")
    public List<Link> listLinks(){
        return linkRepository.findAll();
    }

    @GetMapping("/{link_curto}")
    public String getLink(@PathVariable(value = "link_curto") String link_curto){
        int interacoes = 1;
        String result = "Not Found";
        for( Link link : listLinks()){
            if(link.getLink_curto().equals(link_curto)){
                result = link.getLink_longo();
                interacoes += link.getQuant_cliques();
                link.setQuant_cliques(interacoes);
                linkRepository.save(link);
            }
        }
        return result;
    }

    @GetMapping("/{link_curto}/interacoes")
    public Integer getInteracoes(@PathVariable(value = "link_curto") String link_curto){
        int result = 0;
        for (Link link : listLinks()){
            if(link.getLink_curto().equals(link_curto)){
                result = link.getQuant_cliques();
            }
        }
        return result;
    }

    @PostMapping("/gerador_link")
    public Link gera_e_salva_link(@RequestBody String link_longo){
        do {
            link.setLink_curto(geradorCodigo());
        } while (listLinks().contains(link.getLink_curto()));
        Link postLink = new Link(link_longo, link.getLink_curto());
        return linkRepository.save(postLink);
    }


    @DeleteMapping("/{link_curto}/delete")
    public String deleteLink(@PathVariable(value = "link_curto") String link_curto){
        String result = "Not Found";
        for( Link link : listLinks()){
            if(link.getLink_curto().equals(link_curto)){
                linkRepository.delete(link);
                result = "Deleted successfully";
            }
        }
        return result;
    }

    public String geradorCodigo(){

        Random indice = new Random();
        String codigo = "";
        String caracteres[] = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "l", "m", "n", "o", "p", "q", "r",
                "s", "t", "u", "v", "x", "z", "k", "w", "y", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
                "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "0", "1", "2", "3", "4", "5", "6",
                "7", "8", "9" };

        while (codigo.length() != 7){
            codigo += caracteres[indice.nextInt(62)];
        }
        return codigo;
    }

}
