package org.abhi.spring.service;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.abhi.spring.model.Movie;

public class GetRating {
	private ConcurrentLinkedQueue<Movie> list;
	private List<Future<Movie>> futures = new ArrayList<>();
	public ConcurrentLinkedQueue<Movie> getMovies(String[] names) throws UnsupportedEncodingException {
		list = new ConcurrentLinkedQueue<Movie>();
		ExecutorService executorService=Executors.newFixedThreadPool(2);
		for (int i = 0; i < names.length; i++) {
			if (names[i].length() < 2)
				continue;
			names[i] = names[i].replaceAll(
					"[\\W_]|brrip|bluray|blu\\sray|bdrip|" + "camrip|extended edition|extended collectors edition|Director s Cut|dual audio|dual|hdrip|webrip|web\\-dl", " ");
			Matcher m = Pattern.compile("((19|20)\\d{2})").matcher(names[i]);
			String year = "";
			if (m.find())
				year = m.group(0);
			String[] str = names[i].split("(19|20)\\d{2}");
			//System.out.println("string is=" + str[0] + "   " + year);
			futures.add(executorService.submit(new ConcurrentRatingHelper(URLEncoder.encode(str[0], "UTF-8"), year)));
		}
		executorService.shutdown();
		try {
			for(Future<Movie> f : futures)
			list.add(f.get());
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	
}
