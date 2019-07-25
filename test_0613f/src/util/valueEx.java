package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class valueEx {

	public Integer monthDtoI(Date date) {

		SimpleDateFormat sdfm = new SimpleDateFormat("MM");
		String tdm = sdfm.format(date);
		Integer tdcm = Integer.valueOf(tdm);

		return tdcm;

	}

	public Integer dayDtoI(Date date) {

		SimpleDateFormat sdfm = new SimpleDateFormat("dd");
		String tdd = sdfm.format(date);
		Integer tdcd = Integer.valueOf(tdd);

		return tdcd;

	}

	public Date dateStoD(String month, String day) {

		Calendar calendar = Calendar.getInstance();

		int year = calendar.get(Calendar.YEAR);

		String y = String.valueOf(year);
		String m = String.valueOf(month);
		String d = String.valueOf(day);

		String ymd = y+m+d;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd ");


			Date formatDate = null;
			try {
				formatDate = sdf.parse(ymd);
			} catch (ParseException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}


		return  formatDate;

	}

}
