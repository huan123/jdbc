package com.imooc.dao;

import com.imooc.db.DUBtil;
import com.imooc.model.Goddess;

import javax.crypto.MacSpi;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by huan on 2015/5/6.
 */
public class GoddessDao {

    public void addGoddess(Goddess g) throws SQLException {

        Connection conn = DUBtil.getConnection();
        String sql= ""+"insert into imooc_goddess"+"(user_name,sex,age,birthday,email,mobile,"+"create_user,create_date,update_user,update_date,isdel)"+
        "values("+"?,?,?,?,?,?,?,current_date(),?,current_date(),?)";

        PreparedStatement ptmt =  conn.prepareStatement(sql);
        ptmt.setString(1, g.getUser_name());
       // g.setSex(1);
        ptmt.setInt(2, g.getSex());
        ptmt.setInt(3,g.getAge());
        ptmt.setDate(4, new Date(g.getBirthday().getTime()));
        ptmt.setString(5, g.getEmail());
        ptmt.setString(6,g.getMobile());
        ptmt.setString(7,g.getCreate_user());
        ptmt.setString(8,g.getUpdate_user());
        ptmt.setInt(9,g.getIsdel());
        //ptmt.setInt(9,g.getId());

        ptmt.execute();

    }
    public  void updateGoddess(Goddess g) throws SQLException {
        Connection conn = DUBtil.getConnection();
        String sql= " update imooc_goddess     "+
                    " set user_name=?,sex=?,age=?,birthday=?,email=?,mobile=?, "+
                    " update_user=?,update_date=current_date(),isdel=? "+
                    " where id=? ";

        PreparedStatement ptmt =  conn.prepareStatement(sql);
        ptmt.setString(1, g.getUser_name());
        ptmt.setInt(2, g.getSex());
        ptmt.setInt(3,g.getAge());
        //ptmt.setDate(4, (Date) g.getBirthday());
        ptmt.setDate(4, (java.sql.Date) new java.sql.Date(g.getBirthday().getTime()));
        ptmt.setString(5,g.getEmail());
        ptmt.setString(6,g.getMobile());

        ptmt.setString(7,g.getUpdate_user());
        ptmt.setInt(8,g.getIsdel());
        ptmt.setInt(9,g.getId());

        //execute是修改数据库的操作，比如增加，修改，删除
        ptmt.execute();

    }

    public void delGoddness(Integer id) throws SQLException {
        Connection conn = DUBtil.getConnection();
        String sql= " delete from imooc_goddess"+
                " where id=?";

        PreparedStatement ptmt =  conn.prepareStatement(sql);

        try {
            ptmt.setInt(1, id);//第一个问好代表的是id
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ptmt.execute();
    }

    public List<Goddess> query() throws SQLException {

        List<Goddess> result = new ArrayList<Goddess>();

        Connection conn =  DUBtil.getConnection();

        StringBuilder sb = new StringBuilder();

        sb.append("select id,user_name,age from imooc_goddess ");

        PreparedStatement ptmt;
        ptmt = conn.prepareStatement(sb.toString());

        ResultSet rs = ptmt.executeQuery();

        Goddess g=null;
        while (rs.next())
        {
            g=new Goddess();
            g.setId(rs.getInt("id"));
            g.setUser_name(rs.getString("user_name"));
            g.setAge(rs.getInt("age"));
            // System.out.println(rs.getString("user_name") + "," + rs.getInt("age"));
            result.add(g);
        }

        return result;
    }
    public List<Goddess> query(String name,String mobile,String email) throws SQLException {

        List<Goddess> result = new ArrayList<Goddess>();
        Connection conn =  DUBtil.getConnection();

        StringBuilder sb = new StringBuilder();

        sb.append("select * from imooc_goddess ");

        sb.append(" where user_name like ? and mobile like ? and email like ?");

        PreparedStatement ptmt;

        ptmt = conn.prepareStatement(sb.toString());

        //设置问好代表的含义
        ptmt.setString(1,"%"+name+"%");
        ptmt.setString(2,"%"+mobile+"%");
        ptmt.setString(3,"%"+email+"%");

        //输出sql语句
        System.out.println(sb.toString());


        ResultSet rs = ptmt.executeQuery();

        Goddess g=null;

        while (rs.next())
        {
            g=new Goddess();
            g.setId(rs.getInt("id"));
            g.setUser_name(rs.getString("user_name"));
            g.setAge(rs.getInt("age"));
            g.setSex(rs.getInt("sex"));
            g.setBirthday(rs.getDate("birthday"));
            g.setEmail(rs.getString("email"));
            g.setMobile(rs.getString("mobile"));
            g.setCreate_date(rs.getDate("create_date"));
            g.setCreate_user(rs.getString("create_user"));
            g.setUpdate_date(rs.getDate("update_date"));
            g.setCreate_user(rs.getString("update_user"));
            g.setIsdel(rs.getInt("isdel"));
           // System.out.println(rs.getString("user_name") + "," + rs.getInt("age"));
            result.add(g);
        }

        return result;
    }
    public List<Goddess> query(List<Map<String,Object>> params) throws SQLException {

        List<Goddess> result = new ArrayList<Goddess>();
        Connection conn =  DUBtil.getConnection();

        StringBuilder sb = new StringBuilder();

        sb.append("select * from imooc_goddess where 1=1 ");

        if (params!=null && params.size()>0)
        {
            for (int i=0;i<params.size();i++)
            {
                Map<String,Object> map = params.get(i);
                sb.append(" and "+map.get("name")+" "+map.get("rela")+map.get("value"));

            }
        }
        PreparedStatement ptmt;
        ptmt = conn.prepareStatement(sb.toString());

        System.out.println(sb.toString());

        //执行sql语句
        ResultSet rs = ptmt.executeQuery();

        Goddess g=null;

        //读取ResultSet里面的内容放到g里
        while (rs.next())
        {
            g=new Goddess();
            g.setId(rs.getInt("id"));
            g.setUser_name(rs.getString("user_name"));
            g.setAge(rs.getInt("age"));
            g.setSex(rs.getInt("sex"));
            g.setBirthday(rs.getDate("birthday"));
            g.setEmail(rs.getString("email"));
            g.setMobile(rs.getString("mobile"));
            g.setCreate_date(rs.getDate("create_date"));
            g.setCreate_user(rs.getString("create_user"));
            g.setUpdate_date(rs.getDate("update_date"));
            g.setCreate_user(rs.getString("update_user"));
            g.setIsdel(rs.getInt("isdel"));
            // System.out.println(rs.getString("user_name") + "," + rs.getInt("age"));
            //添加到结果集
            result.add(g);
        }
        //返回结果集
        return result;
    }

    //获取某个员工
    public Goddess get(Integer id) throws SQLException {

        Goddess g=null;
        Connection conn = DUBtil.getConnection();
        String sql= ""+" select * from imooc_goddess    "+
                    " where id=?";
        //预编译sql语句
        PreparedStatement ptmt = conn.prepareStatement(sql);

        //设置第一个问好代表id
        ptmt.setInt(1,id);

        //查询数据库的操作
        ResultSet rs = ptmt.executeQuery();
        while (rs.next())
        {
            g = new Goddess();
            g.setId(rs.getInt("id"));
            g.setUser_name(rs.getString("user_name"));
            g.setAge(rs.getInt("age"));
            g.setSex(rs.getInt("sex"));
            g.setBirthday(rs.getDate("birthday"));
            g.setEmail(rs.getString("email"));
            g.setMobile(rs.getString("mobile"));
            g.setCreate_date(rs.getDate("create_date"));
            g.setCreate_user(rs.getString("create_user"));
            g.setUpdate_date(rs.getDate("update_date"));
            g.setUpdate_user(rs.getString("update_user"));
            g.setIsdel(rs.getInt("isdel"));
        }

        return g;
    }

}
