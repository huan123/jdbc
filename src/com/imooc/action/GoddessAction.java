package com.imooc.action;

import com.imooc.dao.GoddessDao;
import com.imooc.model.Goddess;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by huan on 2015/5/6.
 */
public class GoddessAction  {

    public void add(Goddess goddess) throws SQLException {
        GoddessDao dao = new GoddessDao();
        dao.addGoddess(goddess);
    }

    public Goddess get(Integer id) throws SQLException {
      GoddessDao dao = new GoddessDao();
        return dao.get(id);

    }

    public void edit(Goddess goddess) throws SQLException {
        GoddessDao dao = new GoddessDao();
        dao.updateGoddess(goddess);
    }
    public void del(Integer id) throws SQLException {
        GoddessDao dao = new GoddessDao();
        try {
            dao.delGoddness(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Goddess> query() throws Exception
    {
        GoddessDao dao = new GoddessDao();
         return dao.query();
    }

    public List<Goddess>  query(List<Map<String,Object>> params) throws SQLException {
        GoddessDao dao = new GoddessDao();
        return dao.query(params);
    }


    /*public static  void  main(String[] args) throws SQLException {

        GoddessDao g = new GoddessDao();

//       List<Goddess> result =  g.query("小夏","18722222","xiao");
        List<Map<String,Object>> params = new ArrayList<Map<String, Object>>();
        Map<String,Object> param = new HashMap<String, Object>();
        param.put("name","user_name");
        param.put("rela","like");
        param.put("value","'%小夏%'");
        params.add(param);

        param = new HashMap<String, Object>();
        param.put("name","mobile");
        param.put("rela","like");
        param.put("value","'%18722222%'");
        params.add(param);

        param = new HashMap<String, Object>();
        param.put("name","birthday");
        param.put("rela","like");
        param.put("value","'%2015-05-12%'");

        params.add(param);
        List<Goddess> result = g.query(params);
        for (int i =0 ;i<result.size();i++)
        {
            System.out.println(result.get(i).toString());
        }
      //  for(int i = 0;i<result.size();i++)
      //  {
      //      System.out.println(result.get(i).toString());
      //  }

        Goddess g1 = new Goddess();
        g1.setUser_name("小仙");
        g1.setAge(21);
        g1.setSex(1);
        //g1.setBirthday(new Date());
        g1.setBirthday(new Date());
        g1.setEmail("xiaoxia@imooc.com");
        g1.setMobile("18722222");
        g1.setUpdate_user("ADMIN");
        g1.setIsdel(1);
        g1.setId(4);

       // g.addGoddess(g1);

        g.updateGoddess(g1);

        //Goddess g2 = g.get(7);
       // System.out.println(g2.toString());
        //g.delGoddness(6);


    }*/
}
