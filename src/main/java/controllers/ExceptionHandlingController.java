package controllers;

import entitys.ExceptionEntity;
import entitys.ExceptionclazzEntity;
import entitys.UserEntity;
import entitys.YcclgroupEntity;
import helpers.IdMaker;
import helpers.SaveFilePathHelper;
import helpers.UserHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import repositorys.mySqlRepositorys.ExceptionClazzRepository;
import repositorys.mySqlRepositorys.ExceptionRepository;
import repositorys.mySqlRepositorys.YcclgroupRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping("/exceptionHandle")
public class ExceptionHandlingController {

    private ExceptionClazzRepository clazzRepository;
    private YcclgroupRepository groupRepository;
    private ExceptionRepository repository;


    @Autowired
    public ExceptionHandlingController(ExceptionClazzRepository clazzRepository, YcclgroupRepository groupRepository) {
        this.clazzRepository = clazzRepository;
        this.groupRepository = groupRepository;
    }

    @Autowired
    public void setRepository(ExceptionRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "launch",method = RequestMethod.GET)
    public String launch(Model model){
        List<ExceptionclazzEntity> clazzs = clazzRepository.findAll();
        List<YcclgroupEntity> groups = groupRepository.findAll();
        model.addAttribute("clazzs",clazzs);
        model.addAttribute("groups",groups);
        return "launchHandFrom";
    }

    @RequestMapping(value = "expfrom/{bid}",method = RequestMethod.GET)
    public String expfrom(@PathVariable String bid,Model model){
        ExceptionEntity entity = repository.findByBid(bid);
        if(entity!=null){
            model.addAttribute("downloadpath",entity.getUpdatefile());
            return "home";
        }
        return "home";
    }

    @RequestMapping(value = "downloadfile/{bid}",method = RequestMethod.GET)
    public void downloadfile (@PathVariable String bid, HttpServletResponse response) throws IOException{
        ExceptionEntity entity = repository.findByBid(bid);
        SaveFilePathHelper.downloadFile(entity.getUpdatefile(),entity.getUpdatefilename(),response);
    }

    @RequestMapping(value = "launch",method = RequestMethod.POST)
    public String submitFrom(@RequestParam(value = "file",required = false) MultipartFile file,HttpServletRequest request){


        Date today = new Date(System.currentTimeMillis());
        UserEntity userEntity = UserHelper.currentUser();
        if(userEntity==null){
            return "login";
        }
        String handleteam= request.getParameter("handleteam");
        String happendate =request.getParameter("happendate");
        String theme = request.getParameter("theme");
        String clazz = request.getParameter("clazz");
        String content =request.getParameter("content");
        String other = request.getParameter("other");
        ExceptionEntity entity = new ExceptionEntity();
        YcclgroupEntity groupEntity = groupRepository.findByGroupname(handleteam.split("-")[0]);
        entity.setHandleteam(groupEntity.getGroupid());
        entity.setHappendate(Date.valueOf(happendate));
        entity.setTheme(theme);
        entity.setClazz(clazz);
        entity.setContent(content);
        entity.setOther(other);
        entity.setBid(IdMaker.makeExceptionId());
        entity.setCreatdate(today);
        entity.setCreatdep(userEntity.getDepartment());
        entity.setCreater(userEntity.getUserid());
        if(file!=null){
            File newfile = new File(SaveFilePathHelper.getSavePath()+"/"+file.getOriginalFilename());
            try {
                file.transferTo(newfile);
                entity.setUpdatefile(newfile.getPath());
                entity.setUpdatefilename(file.getOriginalFilename());
            }catch (Exception ex){
                return "err500";
            }
        }

        repository.save(entity);



        return "home";
    }



}
