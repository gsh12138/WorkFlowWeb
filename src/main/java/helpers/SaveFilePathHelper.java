package helpers;

import entitys.HaveUpdateFileEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SaveFilePathHelper {

    private static String beacePath="E:/OAFiles";


    public static boolean saveFile(MultipartFile file, HaveUpdateFileEntity entity){

        File newfile = new File(SaveFilePathHelper.getSavePath()+"/"+file.getOriginalFilename());
        try {
            file.transferTo(newfile);
            entity.setUpdatefile(newfile.getPath());
            entity.setUpdatefilename(file.getOriginalFilename());
            return true;
        }catch (Exception ex){
            return false;
        }


    }

    public static String getSavePath(){
        Date today = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String todayString = dateFormat.format(today);
        File dir = new File(beacePath+"/"+todayString+"/");
        if(dir.exists()){
            return beacePath+"/"+todayString;
        }
        if(dir.mkdir()){
            return beacePath+"/"+todayString;
        }
        else {
            return null;
        }
    }

    public static boolean downloadFile(String path, String filename,HttpServletResponse response) throws IOException{
        File file = new File(path);
        if(file.exists()){
            long fileLength = file.length();
            response.setContentType("application/octet-stream");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-disposition", "attachment; filename="
                    + new String(filename.getBytes("utf-8"),"ISO8859-1"));
            response.setHeader("Content-Length", String.valueOf(fileLength));

            InputStream bis = new BufferedInputStream(new FileInputStream(path));
            OutputStream bos = new BufferedOutputStream(response.getOutputStream());
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
            bis.close();
            bos.close();
            return true;
        }
        return false;

    }
}
