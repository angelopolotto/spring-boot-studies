package mobify.app.blog.controller;

import mobify.app.blog.entity.Post;
import mobify.app.blog.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class PostController {
    private PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("/index");
        mv.addObject("posts", service.findAll());

        return mv;
    }

    @GetMapping("/admin")
    public ModelAndView findAll() {
        ModelAndView mv = new ModelAndView("/post");
        mv.addObject("posts", service.findAll());

        return mv;
    }

    @GetMapping("/add")
    public ModelAndView add(Post post) {
        ModelAndView mv = new ModelAndView("/postAdd");
        mv.addObject("post", post);

        return mv;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id) {
        return add(service.findOne(id));
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id) {
        service.delete(id);
        return findAll();
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid Post post, BindingResult result) {
        if (result.hasErrors()) {
            return add(post);
        }

        service.save(post);

        return findAll();
    }
}
