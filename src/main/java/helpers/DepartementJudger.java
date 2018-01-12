package helpers;

import entitys.DepartmentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import repositorys.mySqlRepositorys.DepartmentRepository;

@Component
public class DepartementJudger {

    private static DepartmentRepository repository;

    public DepartementJudger(){}

    @Autowired
    public static void setRepository(DepartmentRepository repository) {
        DepartementJudger.repository = repository;
    }

    public static boolean canAddToGroup(String adder,String added){

        if(adder.equals(added)){
            return true;
        }
        DepartmentEntity addedDepartment = repository.findByDepartmentid(added);

        if(addedDepartment==null){
            return false;
        }

        if(addedDepartment.getSuperior().equals(adder)){
            return true;
        }



        while (addedDepartment!=null&&addedDepartment.getSuperior()!=null){
            if(addedDepartment.getDepartmentid().equals(adder)){
                return true;
            }

            addedDepartment=repository.findByDepartmentid(addedDepartment.getSuperior());
        }

        return false;
    }
}
