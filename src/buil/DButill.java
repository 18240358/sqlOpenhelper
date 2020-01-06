package buil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DButill implements sqlDao{
	
	final private String url = "jdbc:mysql://localhost:3306/jspdemo?serverTimezone=UTC&useSSL=false";
	final private String user = "root";
	final private String pwd = "123456";
	final private String driver = "com.mysql.cj.jdbc.Driver";//jdbc方法链接数据库
	Connection conn;
	
	
	public  static void main(String []args) { 
		new DButill();
	}
	public DButill() {
		try {
			Class.forName(driver);//判断是否为空
			try {
				conn = DriverManager.getConnection(url,user,pwd);//连接数据库
				//预处理sql语句
				String Sql = "select * from logintable ";
				Map<String,String> values = new HashMap<>();
//				values.put("username","357");
//				PreparedStatement stmt = conn.prepareStatement("select * from logintable where username =?");
//				stmt.setString(1,"356");//条件查询
				//获取result
				ResultSet rs = this.selectexecute(Sql,null);//处理sql语句
				while(rs.next()){
					System.out.println(rs.getString("username"));
					System.out.println(rs.getString("password"));
				}
//				String a ="insert into logintable values(?,?)";
//			
//				int c = this.insertexecute(a,new String[] {"355","777"});
//				System.out.println(c);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	@Override
	public ResultSet selectexecute(String Sql, Map<String, String> values) {
		// TODO Auto-generated method stub
		PreparedStatement stmt;
		try {
			
			StringBuffer stb =  new StringBuffer(Sql);
			if(values != null) {
				stb.append(" where ");
				boolean falg = true;
			for(Map.Entry<String,String>entry:values.entrySet()) {
				if(falg ) {
					falg = false;
					
				stb.append(entry.getKey()+"='"+entry.getValue()+"' ");
			}
				stb.append("and "+entry.getKey()+"='"+entry.getValue()+"' ");
			}
			}
			stmt = conn.prepareStatement(stb.toString());
			return stmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public int insertexecute(String Sql, String[] arg) {
		PreparedStatement stmt;
		try {
			//sql = 'insert into logintable values(?,?)'
			
			stmt = conn.prepareStatement(Sql);
			for(int i = 0;i<arg.length;i++)
			stmt.setString(i+1,arg[i]);
			
			
			return stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
		
	}
	
}
