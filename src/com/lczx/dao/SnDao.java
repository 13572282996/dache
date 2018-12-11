/*



 */
package com.lczx.dao;

import com.lczx.entity.Sn.Type;


/**
 * Dao - 序列号
 * 


 */
public interface SnDao {

	/**
	 * 生成序列号
	 * 
	 * @param type
	 *            类型
	 * @return 序列号
	 */
	String generate(Type type);

}