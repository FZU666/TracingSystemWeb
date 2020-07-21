package com.psf.core.web;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.psf.core.mapper.RaspberryMapper;
import com.psf.core.mapper.ResultMapper;
import com.psf.core.mapper.TargetMapper;
import com.psf.core.model.*;
import com.psf.core.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.springframework.http.ResponseEntity.ok;

/**
 * @author PSF(52260506 @ qq.com)
 * @introduction 控制器类，根据需求编写接口
 */

@RestController
@RequestMapping("/tracing")
public class TracingController {

    @Autowired
    private StorageService storageService;
    //***********************************其佳的部分
    @Autowired
    private TargetMapper targetMapper;
    @Autowired
    private ResultMapper resultMapper;
    @Value("${storage.path.str.image}")
    String imgPathStr = "images";

    @Autowired
    private RaspberryMapper raspberryMapper;

    @GetMapping("/imageurl")
    @ResponseBody
    public ResponseEntity imageurl()
    {
        TargetExample targetExample=new TargetExample();
        List<Target> t=targetMapper.selectByExampleWithBLOBs(targetExample);
        JSONArray jsonArray=new JSONArray();
        for(Target target:t)
        {
            JSONObject jsonObject=new JSONObject();
            String tid=target.getTid().toString();
            String imageurl=target.getImgurl();
            jsonObject.put(tid,imageurl);
            jsonArray.add(jsonObject);
        }
        return ResponseEntity.ok().body(jsonArray);
    }

    @GetMapping("/eigenvalue")
    @ResponseBody
    public ResponseEntity<List<Target>> eigenvalue()
    {
        TargetExample targetExample=new TargetExample();
        List<Target> t=targetMapper.selectByExampleWithBLOBs(targetExample);
        return ok().body(t);
    }

    @PostMapping("/result")
    @ResponseBody
    public void result(Integer id, String rid, Integer tid, Date discoverytime)
    {
        Result result=new Result();
        result.setDiscoverytime(discoverytime);
        result.setId(id);
        result.setRid(rid);
        result.setTid(tid);
        resultMapper.insert(result);
    }

    /*
     *将日期转换为字符串
     *查询结果处使用
     * wzq
     */
    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {

        //转换日期
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));// CustomDateEditor为自定义日期编辑器
    }

    /*
     *
     *
     */
    @PostMapping("/test")
    @ResponseBody
    public ResponseEntity test(@RequestParam("image") MultipartFile image) {

        String url = storageService.storeImage(image);
        if (!StrUtil.isEmpty(url)) {
            System.out.println("上传成功");
            return ok("————————");
        }


        return ok("xxxxxx");
    }

    public static String dateToString(Date data, String formatType) {
        return new SimpleDateFormat(formatType).format(data);
    }

    /*
     *获取查询结果
     * 所取参数姓名，uid
     *wzq
     */
    @GetMapping("/getresult")
    @ResponseBody
    public ResponseEntity getResult(String uid,  String name){
        String sdf = "yyyy-MM-dd HH:mm:ss";
        TargetExample t = new TargetExample();
        ResultExample res = new ResultExample();
        t.createCriteria().andTargetnameEqualTo(name);
        List<Target> ex =  targetMapper.selectByExample(t);
        Target tar = ex.get(0);
        int tid = tar.getTid();
        res.createCriteria().andTidEqualTo(tid);
        List<Result> re = resultMapper.selectByExample(res);
        int m = re.size();
        List<String> rid = new ArrayList<String>();
        List<String>  time = new ArrayList<String>();
        List<String>  address = new ArrayList<String>();
        for(int i = 0;i<m;i++){
            Result r = re.get(i);
            rid.add(r.getRid());
            time.add(dateToString(r.getDiscoverytime(),sdf));
        }
        ListIterator<String> listIterator = rid.listIterator();
        while (listIterator.hasNext()){
            RaspberryExample ras = new RaspberryExample();
            ras.createCriteria().andRidEqualTo(listIterator.next());
            List<Raspberry> rasp = raspberryMapper.selectByExample(ras);
            address.add(rasp.get(0).getAddress());
        }
        Map<String,String> map= new TreeMap<>(new Comparator<String>() {

            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }

        });
        ListIterator<String> timeIterator = time.listIterator();
        ListIterator<String> addressIterator = address.listIterator();
        while(timeIterator.hasNext()&&addressIterator.hasNext()){
            map.put(timeIterator.next(),addressIterator.next());
        }
        JSONArray jsonArray = new JSONArray();

        Set<String> ks = map.keySet();

        Iterator<String> it = ks.iterator();

        while (it.hasNext()) {
            String key = (String) it.next();

            String value = map.get(key);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("time:", key);
            jsonObject.put("address", value);
            jsonArray.add(jsonObject);
        }
        return ResponseEntity.ok().body(jsonArray);
    }

    public MultipartFile createImg(File file){
        try {
            // File转换成MutipartFile
            FileInputStream inputStream = new FileInputStream(file);
            MultipartFile multipartFile = new MockMultipartFile(file.getName(), inputStream);
            //注意这里面填啥，MultipartFile里面对应的参数就有啥，比如我只填了name，则
            //MultipartFile.getName()只能拿到name参数，但是originalFilename是空。
            return multipartFile;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }
    /*
     *上传图片
     * 所需参数图片，姓名,uid
     *wzq
     */
    @PostMapping("/reupload")
    @ResponseBody
    public ResponseEntity uploadImage(@RequestParam(value = "userid") String uid,
                                      @RequestParam(value = "name") String name,
                                      @RequestParam(value = "file") MultipartFile img){

        if (img.isEmpty()) {
            return null;
        }
        String fileName = img.getOriginalFilename();  // 文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
        if(!suffixName.equals(".jpg") && !suffixName.equals(".png")){
            return null;
        }


        fileName = java.util.UUID.randomUUID() + suffixName; // 新文件名
        File dest = new File(imgPathStr + fileName);
        String url = dest.getPath();
        /*if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }*/
        try {
            img.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //FileUploadController f = new FileUploadController();
        //URL path = this.getClass().getClassLoader().getResource(fileName);
        //String url =path.toString();
        String imgurl = storageService.storeImage(createImg(dest));
        System.out.println(createImg(dest).isEmpty());
        TargetExample t=new TargetExample();
        Target tt = new Target();
        tt.setTargetname(name);
        tt.setImgurl(imgurl);
        tt.setUid(uid);
        targetMapper.insert(tt);

        return ResponseEntity.ok("");
    }




}
