/**   
* @Title: PoliceCodeNotUniqueException.java 
* @Package com.brt.policeMonitor.rest.exception 
* @Description: TODO(用一句话描述该文件做什么) 
* @author wangmeilan   
* @date 2016年8月22日 上午10:17:57 
* @version V1.0   
*/
package com.example.manager.excption;

/** 
 * @ClassName: PoliceCodeNotUniqueException 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author wangmeilan
 * @date 2016年8月22日 上午10:17:57 
 *  
 */

public class CustomBusinessException extends RuntimeException{

	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	
	private static final long serialVersionUID = 8745024787809638608L;
	
    public CustomBusinessException(){
		
	}
	public CustomBusinessException(String info){
		super(info);
	}

}
