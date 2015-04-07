package com.creditease.rc.util;

import java.util.concurrent.ConcurrentMap;

import org.apache.log4j.Logger;

import com.creditease.rc.vo.LockVo;

public class LockAndUnlockByLockUtil {
	private static Logger log = Logger.getLogger(LockAndUnlockByLockUtil.class);

	public LockAndUnlockByLockUtil() {

	}

	/**
	 * 加锁
	 * 
	 * @param <T>
	 * @param tableId 表的名字+表的ID(MFC_APPLICATION+','+ID)
	 * @param tableName 表的名字中文
	 * @return
	 */
	public synchronized static boolean lockOperate(String tableId, String tableName) {
		Boolean flag = false;
		LockUtil lockUtil = LockUtil.getInstance();

		
		if (null != tableId) {
			boolean key = lockUtil.iSExistKey(tableId); // 判断键是否存在
			/*
			 * 如果键不存在的话，把id放入key,表明放入value
			 */
			if (!key) {
				flag = true;

				LockVo lock = new LockVo();
				lock.setTableId(tableId);
				lock.setTableName(tableName);

				lockUtil.put(tableId, lock);
				log.info(tableId.toString() + "加锁成功");
			} else {
				log.info(tableId.toString() + "锁已存在，加锁失败");
			}
		}
		return flag;
	}

	/**
	 * 解锁
	 * 
	 * @param <T>
	 * @param tableId 表的名字+表的ID(MFC_APPLICATION+'_'+ID)
	 * @return
	 */
	public synchronized static boolean unlockOperate(String tableId) {
		Boolean flag = false;
		LockUtil lockUtil = LockUtil.getInstance();
		if (null != tableId) {
//			boolean key = lockUtil.iSExistKey(tableId);
            boolean key = true;
			if (key) {
				flag = true;
				lockUtil.remove(tableId);
				log.info(tableId.toString() + "解锁成功");
			} else {
				log.info(tableId.toString() + "未加锁。不需要解锁");
			}
		}
		return flag;
	}

	/**
	 * 得到内存锁map
	 * 
	 * @return
	 */
	public static ConcurrentMap<Object, Object> getLockMap() {
		LockUtil lockUtil = LockUtil.getInstance();
		return lockUtil.getLockMap();
	}
}
