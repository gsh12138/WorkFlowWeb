package controllers;

import Flow.FlowDateInterface;
import Flow.FlowDetail;
import Flow.FlowState;
import Flow.FullFlow;
import entitys.*;
import helpers.FullFlowProcessorImp;
import helpers.IdMaker;
import helpers.SaveFilePathHelper;
import helpers.UserHelper;
import message.Message;
import message.MessageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import repositorys.mySqlRepositorys.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.*;

@Controller
@RequestMapping("/exceptionHandle")
public class ExceptionHandlingController {

    private ExceptionClazzRepository clazzRepository;
    private YcclgroupRepository groupRepository;
    private YcclgroupmemberRepository groupmemberRepository;
    private ExceptionRepository repository;
    private HandlingRepository handlingRepository;
    private FlowDateInterface flowDate;
    private UserRespository userRespository;
    private MainFlowRepository mainFlowRepository;

    @Autowired
    public ExceptionHandlingController(ExceptionClazzRepository clazzRepository, YcclgroupRepository groupRepository) {
        this.clazzRepository = clazzRepository;
        this.groupRepository = groupRepository;
    }

    @Autowired
    public void setRepository(ExceptionRepository repository) {
        this.repository = repository;
    }

    @Autowired
    public void setHandlingRepository(HandlingRepository handlingRepository) {
        this.handlingRepository = handlingRepository;
    }

    @Autowired
    public void setFlowDate(FlowDateInterface flowDate) {
        this.flowDate = flowDate;
    }


    @Autowired
    public void setGroupmemberRepository(YcclgroupmemberRepository groupmemberRepository) {
        this.groupmemberRepository = groupmemberRepository;
    }

    @Autowired
    public void setUserRespository(UserRespository userRespository) {
        this.userRespository = userRespository;
    }

    @Autowired
    public void setMainFlowRepository(MainFlowRepository mainFlowRepository) {
        this.mainFlowRepository = mainFlowRepository;
    }

    @RequestMapping(value = "launch", method = RequestMethod.GET)
    public String launch(Model model) {
        List<ExceptionclazzEntity> clazzs = clazzRepository.findAll();
        List<YcclgroupEntity> groups = groupRepository.findAll();
        model.addAttribute("clazzs", clazzs);
        model.addAttribute("groups", groups);
        return "launchHandFrom";
    }

    @RequestMapping(value = "expfrom/{bid}", method = RequestMethod.GET)
    public String expfrom(@PathVariable String bid, Model model) {
        ExceptionEntity entity = repository.findByBid(bid);
        if (entity != null) {
            model.addAttribute("downloadpath", entity.getUpdatefile());
            return "home";
        }
        return "home";
    }

    @RequestMapping(value = "downloadfile/{bid}", method = RequestMethod.GET)
    public void downloadfile(@PathVariable String bid, HttpServletResponse response) throws IOException {
        ExceptionEntity entity = repository.findByBid(bid);
        SaveFilePathHelper.downloadFile(entity.getUpdatefile(), entity.getUpdatefilename(), response);
    }

    @RequestMapping(value = "launch", method = RequestMethod.POST)
    public String submitFrom(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request) {


        Date today = new Date(System.currentTimeMillis());
        UserEntity userEntity = UserHelper.currentUser();
        if (userEntity == null) {
            return "login";
        }
        String handleteam = request.getParameter("handleteam");
        String happendate = request.getParameter("happendate");
        String theme = request.getParameter("theme");
        String clazz = request.getParameter("clazz");
        String content = request.getParameter("content");
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
        if (!file.getOriginalFilename().equals("")) {
            if (!SaveFilePathHelper.saveFile(file, entity)) {
                return "err500";
            }
        }


        FullFlow flow = FullFlowProcessorImp.buildNoTemplateFlow(entity.getBid(), entity.getCreater(), groupEntity.getCharger(), "yycl");

        flowDate.insertFullFlow(flow);

        repository.save(entity);

        Message message = MessageFactory.creatWithLink(userEntity.getUserid(), flow.getFlow().getHanding()
                , "新的异常处理", "/exceptionHandle/exception/" + entity.getBid());
        message.send();

        return "home";
    }

    @RequestMapping(value = "exception/{bid}", method = RequestMethod.GET)
    public String detile(@PathVariable String bid, @RequestParam(required = false) Integer hid, Model model) {
        UserEntity userEntity = UserHelper.currentUser();
        if (userEntity == null) {
            return "login";
        }
        FullFlow flow = flowDate.findFullFlowByBid(bid);
        ExceptionEntity entity = repository.findByBid(bid);
        List<YcclgroupmemberEntity> ycclgroupmemberEntityList =
                groupmemberRepository.findAllByGroupid(entity.getHandleteam());
        String createrName = userRespository.findByUserid(entity.getCreater()).getName();
        String groupName = groupRepository.findByGroupid(entity.getHandleteam()).getGroupname();
        entity.setCreater(createrName);
        entity.setHandleteam(groupName);

        List<HandlingEntity> handlingEntityList = new ArrayList<HandlingEntity>();
        handlingEntityList = handlingRepository.findByBidOrderById(bid);
        for (HandlingEntity handlingEntity : handlingEntityList
                ) {
            String handlerName = userRespository.findByUserid(handlingEntity.getHandler()).getName();
            handlingEntity.setHandler(handlerName);
        }
        List<HandlingEntity> dechandlingList = new ArrayList<HandlingEntity>(handlingEntityList);
        if (flow.getHandlingStep().getFlowStepNumber() == 2) {
            model.addAttribute("members", ycclgroupmemberEntityList);
        }
        model.addAttribute("detile", entity);
        model.addAttribute("flow", flow);
        model.addAttribute("handlingList", handlingEntityList);
        if (handlingEntityList.size() == 0 || flow.getFlow().getState() == FlowState.REPULED) {
            model.addAttribute("currstep", 0);
        } else {
            Collections.sort(dechandlingList);
            model.addAttribute("currstep", dechandlingList.get(0).getId());
        }
        if (hid != null) {
            HandlingEntity currHandling = null;
            for (HandlingEntity handlingEntity : handlingEntityList
                    ) {
                if (handlingEntity.getId() == hid) {
                    currHandling = handlingEntity;
                    for (FlowDetail detail : flow.getFlowDetail()
                            ) {
                        if (detail.getFlowStepNumber() == currHandling.getHandstep() + 1) {
                            if (detail.getHandResult() != null && detail.getHandResult().equals(FlowState.REPULED.toString())) {
                                model.addAttribute("returnreson", detail.getHandcontent());
                                break;
                            }
                        }
                    }
                    model.addAttribute("currhandling", currHandling);
                    model.addAttribute("currhandlingid", hid);
                    break;
                }
            }
        }
        if (flow.getFlow().getState() == FlowState.CLOSE ||
                !flow.getFlow().getHanding().equals(userEntity.getUserid())) {
            return "exceptionReadOnly";
        }

        return "exception";
    }

    @RequestMapping(value = "exception/{bid}", method = RequestMethod.POST)
    public String handlingException(@RequestParam(value = "file", required = false) MultipartFile file,
                                    @PathVariable String bid, HttpServletRequest request) {
        Date today = new Date(System.currentTimeMillis());
        UserEntity userEntity = UserHelper.currentUser();
        if (userEntity == null) {
            return "login";
        }
        FullFlow flow = flowDate.findFullFlowByBid(bid);
        if (!flow.getHandlingStep().getHandller().equals(userEntity.getUserid())) {
            return "login";
        }
        String reason = request.getParameter("reason");
        String result = request.getParameter("result");
        String keyword = request.getParameter("keyword");
        HandlingEntity handlingEntity = new HandlingEntity();
        handlingEntity.setBid(bid);
        handlingEntity.setFinishdate(today);
        handlingEntity.setHandler(userEntity.getUserid());
        handlingEntity.setReason(reason);
        handlingEntity.setResult(result);
        handlingEntity.setKeyword(keyword);
        handlingEntity.setHandstep(flow.getFlow().getFlowStepNumber());
        if (!file.getOriginalFilename().equals("")) {
            if (!SaveFilePathHelper.saveFile(file, handlingEntity)) {
                return "err500";
            }
        }

        flow.getFlow().setState(FlowState.RETREATED);
        flow.getFlow().setHanding(flow.getFlow().getStarter());
        flow.getFlow().setFlowStepNumber(flow.getHandlingStep().getFlowStepNumber() + 1);
        flow.getHandlingStep().setHandDate(new java.util.Date());


        FlowDetailEntity flowDetail = new FlowDetailEntity();
        flowDetail.setHandller(flow.getFlow().getStarter());
        flowDetail.setFlowStepNumber(flow.getFlow().getFlowStepNumber());
        flowDetail.setFlowId(flow.getFlow().getFlowId());
        flow.getFlowDetail().add(flowDetail);
        handlingRepository.save(handlingEntity);
        flowDate.updateFullFlow(flow);
        return "home";

    }

    @RequestMapping(value = "sethandler/{bid}", method = RequestMethod.POST)
    public String setupHandler(@PathVariable String bid, HttpServletRequest request) {
        UserEntity userEntity = UserHelper.currentUser();
        if (userEntity == null) {
            return "login";
        }
        String handler = request.getParameter("handler").split("-")[0];
        FullFlow flow = flowDate.findFullFlowByBid(bid);
        if (!flow.getHandlingStep().getHandller().equals(userEntity.getUserid())) {
            return "login";
        }
        flow.getFlow().setHanding(handler);
        flow.getFlow().setFlowStepNumber(flow.getHandlingStep().getFlowStepNumber() + 1);
        flow.getHandlingStep().setHandDate(new java.util.Date());
        flow.getFlow().setState(FlowState.PASS);

        FlowDetailEntity detailEntity = new FlowDetailEntity();
        detailEntity.setFlowId(flow.getFlow().getFlowId());
        detailEntity.setFlowStepNumber(flow.getFlow().getFlowStepNumber());
        detailEntity.setHandller(handler);
        flow.getFlowDetail().add(detailEntity);
        flowDate.updateFullFlow(flow);
        return "home";

    }

    @RequestMapping(value = "returnException/{bid}", method = RequestMethod.POST)
    public String returnedException(@PathVariable String bid, HttpServletRequest request) {
        UserEntity userEntity = UserHelper.currentUser();
        if (userEntity == null) {
            return "login";
        }
        ExceptionEntity exceptionEntity = repository.findByBid(bid);
        if (!exceptionEntity.getCreater().equals(userEntity.getUserid())) {
            return "login";
        }
        String returnReason = request.getParameter("returnreason");
        if (returnReason == null) {
            //TODO
        }
        FullFlow flow = flowDate.findFullFlowByBid(bid);
        flow.getFlow().setState(FlowState.REPULED);
        flow.getFlow().setFlowStepNumber(flow.getHandlingStep().getFlowStepNumber() + 1);
        flow.getFlow().setHanding(flow.getLastSetp().getHandller());
        flow.getHandlingStep().setHandDate(new java.util.Date());
        flow.getHandlingStep().setHandcontent(returnReason);
        flow.getHandlingStep().setHandResult(FlowState.REPULED.toString());

        FlowDetailEntity flowDetail = new FlowDetailEntity();
        flowDetail.setFlowId(flow.getFlow().getFlowId());
        flowDetail.setFlowStepNumber(flow.getFlow().getFlowStepNumber());
        flowDetail.setHandller(flow.getFlow().getHanding());

        flow.getFlowDetail().add(flowDetail);
        flowDate.updateFullFlow(flow);
        return "home";

    }

    @RequestMapping(value = "closeException/{bid}", method = RequestMethod.GET)
    public String closeExcetpion(@PathVariable String bid) {
        UserEntity userEntity = UserHelper.currentUser();
        if (userEntity == null) {
            return "login";
        }
        ExceptionEntity exceptionEntity = repository.findByBid(bid);
        if (!exceptionEntity.getCreater().equals(userEntity.getUserid())) {
            return "login";
        }
        FullFlow flow = flowDate.findFullFlowByBid(bid);
        flow.getFlow().setState(FlowState.CLOSE);
        flow.getHandlingStep().setHandDate(new java.util.Date());
        flow.getHandlingStep().setHandResult(FlowState.CLOSE.toString());
        flowDate.updateFullFlow(flow);
        return "home";
    }

    @RequestMapping(value = "myException/{state}", method = RequestMethod.GET)
    public String myException(Model model, @PathVariable String state) {
        UserEntity userEntity = UserHelper.currentUser();
        if (userEntity == null) {
            return "login";
        }
        List<MainFlowEntity> mainFlowEntities = new ArrayList<MainFlowEntity>();
        if (state.equals("tome")) {
            mainFlowEntities =
                    mainFlowRepository.findHandling("yycl", userEntity.getUserid());
        } else if (state.equals("my")) {
            mainFlowEntities =
                    mainFlowRepository.findFlowByClazzIdAndStarter("yycl", userEntity.getUserid());
        } else {
            return "err500";
        }
        Collections.sort(mainFlowEntities, new Comparator<MainFlowEntity>() {
            public int compare(MainFlowEntity o1, MainFlowEntity o2) {
                if (o1.getState() == FlowState.CLOSE) {
                    return 1;
                }
                if (o1.getStartDate().after(o2.getStartDate())) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });
        Map<MainFlowEntity, ExceptionEntity> exceptionEntities = new LinkedHashMap<MainFlowEntity, ExceptionEntity>();
        for (MainFlowEntity flow : mainFlowEntities
                ) {
            ExceptionEntity entity = repository.findByBid(flow.getBid());
            if (entity != null) {
                exceptionEntities.put(flow, entity);
                UserEntity username = userRespository.findByUserid(flow.getStarter());
                flow.setStarter(username.getName());
            }

        }

        model.addAttribute("flows", exceptionEntities);

        model.addAttribute("title", "我发起的异常");
        return "exceptionList";

    }


}


