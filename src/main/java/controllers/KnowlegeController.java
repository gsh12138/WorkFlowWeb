package controllers;

import entitys.KnowledgeEntity;
import entitys.KnowledgehandingEntity;
import entitys.UserEntity;
import helpers.IdMaker;
import helpers.SaveFilePathHelper;
import helpers.UserHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import repositorys.mySqlRepositorys.KnowledgeHandingRepository;
import repositorys.mySqlRepositorys.KnowledgeRepository;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/knowledge")
public class KnowlegeController {

    private KnowledgeRepository repository;
    private KnowledgeHandingRepository handingRepository;


    @Autowired
    public void setRepository(KnowledgeRepository repository) {
        this.repository = repository;
    }

    @Autowired
    public void setHandingRepository(KnowledgeHandingRepository handingRepository) {
        this.handingRepository = handingRepository;
    }

    @RequestMapping(value = "/submit", method = RequestMethod.GET)
    public String submitView() {
        return "knowledge/submit";
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public String submit(@RequestParam(value = "file", required = false) MultipartFile file,
                         KnowledgeEntity entity) {

        UserEntity user = UserHelper.currentUser();
        if (user == null) {
            return "login";
        }
        if (entity != null && entity.getZt() != null) {
            entity.setBid(IdMaker.makeKnowledgeId());
            entity.setSubmitdate(new Date(System.currentTimeMillis()));
            entity.setSubmiter(user.getUserid());
            if (!file.getOriginalFilename().equals("")) {
                if (!SaveFilePathHelper.saveFile(file, entity)) {
                    return "err500";
                }
            }
            repository.save(entity);
            return "home";
        }


        return "knowledge/submit";
    }

    @RequestMapping(value = "/modify/{bid}",method = RequestMethod.GET)
    public String modifyGet(@PathVariable String bid,Model model){
        model.addAttribute("modify",true);
        return this.detile(bid,null,model);
    }

    @RequestMapping(value = "/modify/{bid}",method = RequestMethod.POST)
    public String modifyPost(@PathVariable String bid, HttpServletRequest request){
        KnowledgeEntity entity = repository.findByBid(bid);
        String ycms = request.getParameter("ycms");
        entity.setYcms(ycms);
        repository.save(entity);
        return "redirect:/knowledge/"+bid;
    }



    @RequestMapping(value = "/{bid}", method = RequestMethod.GET)
    public String detile(@PathVariable String bid, @RequestParam(required = false) Integer hid, Model model) {
        UserEntity user = UserHelper.currentUser();
        if (user == null) {
            return "login";
        }
        KnowledgeEntity entity = repository.findByBid(bid);
        if(user.getUserid().equals(entity.getSubmiter())){
            model.addAttribute("submiter",true);
        }
        entity.setSubmiter(UserHelper.getUserName(entity.getSubmiter()));
        List<KnowledgehandingEntity> handingEntities = new ArrayList<KnowledgehandingEntity>();
        handingEntities = handingRepository.findAllByBid(bid);

        KnowledgehandingEntity handingEntity = null;
        for (KnowledgehandingEntity know : handingEntities
                ) {
            know.setSubmiter(UserHelper.getUserName(know.getSubmiter()));
            if (hid != null) {
                if (know.getId() == hid) {
                    handingEntity = know;
                }
            }

        }
        model.addAttribute("currhandling", handingEntity);
        model.addAttribute("currhandlingid", hid);


        model.addAttribute("detile", entity);
        model.addAttribute("handlingList", handingEntities);


        return "knowledge/handing";
    }

    @RequestMapping(value = "/handing/{bid}", method = RequestMethod.POST)
    public String handing(@RequestParam(value = "file", required = false) MultipartFile file,
                          @PathVariable String bid,
                          KnowledgehandingEntity entity) {
        UserEntity user = UserHelper.currentUser();
        if (user == null) {
            return "login";
        }
        if (entity != null && entity.getContent() != null) {
            entity.setBid(bid);
            entity.setSubmitdate(new Date(System.currentTimeMillis()));
            entity.setSubmiter(user.getUserid());
            if (file != null && !file.getOriginalFilename().equals("")) {
                if (!SaveFilePathHelper.saveFile(file, entity)) {
                    return "err500";
                }
            }
            handingRepository.save(entity);
            return "home";
        }
        return "knowledge/" + bid;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getlist(Model model){
        List<KnowledgeEntity> entities = new ArrayList<KnowledgeEntity>();
        PageRequest pr = new PageRequest(0,50);
        entities=repository.findAllDec(pr);
        for (KnowledgeEntity k:entities
             ) {
            k.setSubmiter(UserHelper.getUserName(k.getSubmiter()));
        }
        model.addAttribute("list",entities);
        return "knowledge/list";

    }
}
