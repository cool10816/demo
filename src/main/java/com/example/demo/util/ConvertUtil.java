package com.example.demo.util;

import java.util.ArrayList;
import java.util.List;

public class ConvertUtil {

	public static List<Object[]> test(int page, int totalpage) {
		List<Object[]> listPage = new ArrayList<Object[]>();
		int pageround = 5;
		if (totalpage == 0) {
			return listPage;
		}

		int startpage = ((page / pageround) * pageround);
		int endpage = 0;
		if (page % pageround == 0) {
			endpage = startpage;
			startpage = startpage - pageround + 1;
		} else {
			startpage = startpage + 1;
			endpage = startpage + pageround - 1;
		}

		if (endpage > totalpage) {
			endpage = totalpage;
		}

		int prev = page - 1;
		int next = page + 1;
		listPage.add(new Object[] { "first", "1", "<<" });

		if (prev != 0) {
			listPage.add(new Object[] { "prev", prev, "<" });
		} else {
			listPage.add(new Object[] { "prev", "1", "<" });
		}
		
		for (int i = startpage; i <= endpage; i++) {
			listPage.add(new Object[] { "page_" + i, i, i });
		}

		if (next < totalpage) {
			listPage.add(new Object[] { "next", next, ">" });
		} else {
			listPage.add(new Object[] { "next", totalpage, ">" });
		}
		
		listPage.add(new Object[] { "last", totalpage, ">>" });

		return listPage;
	}
}
