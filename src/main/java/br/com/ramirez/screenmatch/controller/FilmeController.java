package br.com.ramirez.screenmatch.controller;

import br.com.ramirez.screenmatch.domain.filme.DadosAlteracaoFilme;
import br.com.ramirez.screenmatch.domain.filme.DadosCadastroFilme;
import br.com.ramirez.screenmatch.domain.filme.Filme;
import br.com.ramirez.screenmatch.domain.filme.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/filmes")
public class FilmeController {

    @Autowired
    private FilmeRepository repository;
    @GetMapping("/formulario")
    public String carregaPaginaFormulario(Long id, Model model){
        if(id != null){
            var filme = repository.getReferenceById(id);
            model.addAttribute("filme", filme);
        }
        return "filmes/formulario";
    }

    @GetMapping
    public String carregaPaginaListagem(Model model){
        model.addAttribute("lista", repository.findAll());
        return "filmes/listagem";
    }

    @PostMapping
    @Transactional
    public String cadastroFilme(DadosCadastroFilme dados){
        var filme = new Filme(dados);
        repository.save(filme);
        return "redirect:/filmes";
    }

    @PutMapping
    @Transactional
    public String alteraFilme(DadosAlteracaoFilme dados){
        var filme = repository.getReferenceById(dados.id());
        filme.atualizaDados(dados);
        System.out.println(filme);
        return "redirect:/filmes";
    }

    @DeleteMapping
    @Transactional
    public String deletaFilme(Long id){
        repository.deleteById(id);
        return "redirect:/filmes";
    }

}
