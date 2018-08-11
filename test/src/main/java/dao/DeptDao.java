package dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import model.Dept;

//Dao가 갖춰야할것은 SqlSessionFactory

public class DeptDao {
    private SqlSessionFactory sqlSessionFactory;
    public DeptDao(){
        InputStream is = null;
        try {
            is = Resources.getResourceAsStream(
                    "MybatisConfiguration.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);

//            sqlSessionFactoryBuilder은 만듦과 동시에 builder함수만 쓰고 갖다버림


//            .build(is)하자마자 바로 뒤짐


//            언제든지 세션을 open하기 위해서는 sqlSessionFactory는 계속 있어야됨

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally
        {
            try {
                if(is != null)
                    is.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public List<Dept> selectAll(){
        SqlSession session = sqlSessionFactory.openSession();

//        sqlSessionFactory는 지가 쓸꺼 다 쓰고나서 session.close()하면서 소멸
        try
        {
//            여기로 가서 처리해라
            return session.selectList("dao.deptMapper.selectAll");
        }
        finally
        {
            session.close();
        }
    }

}