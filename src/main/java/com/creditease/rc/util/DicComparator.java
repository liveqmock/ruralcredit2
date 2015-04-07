	/**
 *
	*/
package com.creditease.rc.util;
import java.util.Comparator;

import com.creditease.rc.domain.CodeTable;

/**
 * Title:
 * Description: 宜农贷2.0系统开发
 * Copyright:Copyright (c) 2011
 * Company:普信恒业科技发展（北京）有限公司
 * 2012-6-20 
 * @author 韩大年
 * @version v2.0
 */
public class DicComparator implements Comparator<CodeTable> {

	/**
	 * 
	 */
	public DicComparator() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param o1 01
	 * @param o2 o2
	 * @return 2012-11-5
	 */
	public int compare(CodeTable o1, CodeTable o2) {
			if (CommonUtil.isEmpty(o1.getSequence()) && CommonUtil.isNotEmpty(o2.getSequence())){
                return -1;
            }else if (CommonUtil.isNotEmpty(o1.getSequence()) && CommonUtil.isEmpty(o2.getSequence())){
                return 1;
            }
            else if (CommonUtil.isEmpty(o1.getSequence()) && CommonUtil.isEmpty(o2.getSequence())){
                return 0;
            }else{
            	return Integer.valueOf(o1.getSequence()).compareTo(Integer.valueOf(o2.getSequence()));
            }
	}

}
