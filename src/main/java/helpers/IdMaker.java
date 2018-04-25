package helpers;

import entitys.ExceptionEntity;
import entitys.KnowledgeEntity;
import entitys.MainFlowEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import repositorys.mySqlRepositorys.ExceptionRepository;
import repositorys.mySqlRepositorys.KnowledgeRepository;
import repositorys.mySqlRepositorys.MainFlowRepository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by tech on 2017/12/25.
 * ID生成帮助类
 */
@Component
public class IdMaker {



    private static MainFlowRepository repository;
    private static ExceptionRepository exceptionRepository;
    private static KnowledgeRepository knowledgeRepository;

    @Autowired
    public void setRepository(MainFlowRepository repository) {
        IdMaker.repository = repository;
    }

    @Autowired
    public void setExceptionRepository(ExceptionRepository exceptionRepository) {
        IdMaker.exceptionRepository = exceptionRepository;
    }

    @Autowired
    public void setKnowledgeRepository(KnowledgeRepository knowledgeRepository) {
        IdMaker.knowledgeRepository = knowledgeRepository;
    }

    public static String makeFlowId(String prf){
        Date today = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        String year = String.format("%04d",calendar.get(Calendar.YEAR));
        String mouth = String.format("%02d",calendar.get(Calendar.MONTH));
        String day = String.format("%02d",calendar.get(Calendar.DAY_OF_MONTH));
        String suf = year+mouth+day;
        String prff = prf+"-"+suf;
        List<MainFlowEntity> mainFlowEntities = repository.findMaxid(prff+"%");
        if(mainFlowEntities.size()!=0){
            String id = mainFlowEntities.get(0).getFlowId();
            String temp = id.split("-")[1];
            String tempi = temp.substring(8);
            int tt = Integer.valueOf(tempi)+1;
            return prff+String.format("%03d",tt);
        }
        else {
            return prff+"001";
        }
    }

    public static String makeExceptionId(){
        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        String year = String.format("%04d",calendar.get(Calendar.YEAR));
        String mouth = String.format("%02d",calendar.get(Calendar.MONTH)+1);
        String day = String.format("%02d",calendar.get(Calendar.DAY_OF_MONTH));
        String temid = year+mouth+day;
        List<ExceptionEntity> exceptionEntities = exceptionRepository.findMax(today);
        if(exceptionEntities.size()!=0){
            String id = exceptionEntities.get(0).getBid();
            int temp = Integer.valueOf(id.substring(8))+1;
            return temid+String.format("%03d",temp);
        }
        else {
            return temid+"001";
        }
    }

    public static String makeKnowledgeId(){
        Date today = new Date();
        String prf="know";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        String year = String.format("%04d",calendar.get(Calendar.YEAR));
        String mouth = String.format("%02d",calendar.get(Calendar.MONTH));
        String day = String.format("%02d",calendar.get(Calendar.DAY_OF_MONTH));
        String suf = year+mouth+day;
        String prff = prf+"-"+suf;
        List<KnowledgeEntity> knowledgeEntities = knowledgeRepository.findMax(today);
        if(knowledgeEntities.size()!=0){
            String id = knowledgeEntities.get(0).getBid();
            String temp = id.split("-")[1];
            String tempi = temp.substring(8);
            int tt = Integer.valueOf(tempi)+1;
            return prff+String.format("%03d",tt);
        }
        else {
            return prff+"001";
        }

    }
}
