package com.psf.core.web;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
<<<<<<< HEAD
import com.psf.core.mapper.RaspberryMapper;
import com.psf.core.mapper.ResultMapper;
import com.psf.core.mapper.TargetMapper;
import com.psf.core.model.*;
=======
import com.psf.core.mapper.ResultMapper;
import com.psf.core.mapper.TargetMapper;
import com.psf.core.model.Result;
import com.psf.core.model.Target;
import com.psf.core.model.TargetExample;
>>>>>>> d0da1384dc3a5ead8783b5cea341fc41300e024f
import com.psf.core.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
<<<<<<< HEAD
import java.util.*;
=======
import java.util.Date;
import java.util.List;
>>>>>>> d0da1384dc3a5ead8783b5cea341fc41300e024f

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

    @GetMapping("/getresult")
    @ResponseBody
    public ResponseEntity getResult(String uid,  String name){
        String sdf = "yyyy-MM-dd HH:mm:ss";
        TargetExample t = new TargetExample();
        ResultExample res = new ResultExample();
        t.createCriteria().andTargetnameEqualTo(name);
        //resultMapper.selectByExample();
        List<Target> ex =  targetMapper.selectByExample(t);
        Target tar = ex.get(0);
        int tid = tar.getTid();
        res.createCriteria().andTidEqualTo(tid);
        List<Result> re = resultMapper.selectByExample(res);
        int m = re.size();
        List<String> rid = new ArrayList<String>();
        List<String>  time = new ArrayList<String>();
        List<String>  address = new ArrayList<String>();
        //System.out.println(re.toString());
        for(int i = 0;i<m;i++){
            Result r = re.get(i);
            //System.out.println(r.toString());
            rid.add(r.getRid());
            time.add(dateToString(r.getDiscoverytime(),sdf));
        }
        //System.out.println(rid);
        ListIterator<String> listIterator = rid.listIterator();
        while (listIterator.hasNext()){
            RaspberryExample ras = new RaspberryExample();
            ras.createCriteria().andRidEqualTo(listIterator.next());
            //System.out.println(ras);
            List<Raspberry> rasp = raspberryMapper.selectByExample(ras);
            //System.out.println(rasp);
            address.add(rasp.get(0).getAddress());

            /*
            ListIterator<Raspberry> rasIterator = rasl.listIterator();
            while (rasIterator.hasNext()){
            address.add( rasIterator.next().getAddress());
            }
            */
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
        //System.out.println(map);
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

    @PostMapping("/reupload")
    @ResponseBody
    public ResponseEntity uploadImage(@RequestParam(value = "image") MultipartFile image,@RequestParam(value = "name") String name){



        return ResponseEntity.ok("");
    }




}
