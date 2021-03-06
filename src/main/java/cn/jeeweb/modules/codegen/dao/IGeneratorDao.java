package cn.jeeweb.modules.codegen.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;

import cn.jeeweb.core.common.dao.ICommonDao;
import cn.jeeweb.modules.codegen.codegenerator.data.DbColumnInfo;
import cn.jeeweb.modules.codegen.codegenerator.data.DbTableInfo;

/**
 * http://blog.csdn.net/lmy86263/article/details/51020759 Spring
 * JDBC学习笔记（3）：使用JdbcTemplate来获取数据库表和列的元数据
 * 
 * @author Administrator
 *
 */
public interface IGeneratorDao extends ICommonDao {

	/**
	 * 获取所有的表明
	 * 
	 * @return
	 */
	public List<DbTableInfo> getDbTables();

	/**
	 * 通过表名获取所有的表名
	 * 
	 * @param tableName
	 * @return
	 */
	public List<DbColumnInfo> getDbColumnInfo(String tableName);

	public Boolean createTableByXml(String xml) throws HibernateException, SQLException;
	
	public Boolean isExistTable(String tableName);

	public void dropTable(String tableName);

}