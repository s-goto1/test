package util;

import java.util.ArrayList;
import java.util.List;

public class Division {
	public static List<String> listToDivision(){
		// String型のListを生成
		List<String> divisionList = new ArrayList<>();

		// リストに追加
		divisionList.add("有給休暇");
		divisionList.add("生理休暇");
		divisionList.add("慶弔休暇");
		divisionList.add("産前産後休暇");
		divisionList.add("転勤休暇");
		divisionList.add("特別休暇");

		// リストを返却
		return divisionList;
	}
}
