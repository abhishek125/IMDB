package org.abhi.spring.service;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.abhi.spring.model.Movie;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class GetRating {
	private List<Movie> list;

	
	public List<Movie> getMovies(String[] names) throws UnsupportedEncodingException {
		list = new ArrayList<>();
		for (int i = 0; i < names.length; i++) {
			if (names[i].length() < 2)
				continue;
			names[i] = names[i].replaceAll(
					"[\\W_]|brrip|bluray|blu\\sray|bdrip|" + "camrip|entended edition|dual audio|hdrip|webrip|web\\-dl", " ");
			Matcher m = Pattern.compile("((19|20)\\d{2})").matcher(names[i]);
			String year = "";
			if (m.find())
				year = m.group(0);
			String[] str = names[i].split("(19|20)\\d{2}");
			System.out.println("string is=" + str[0] + "   " + year);
			
			Movie movie = rating(URLEncoder.encode(str[0], "UTF-8"), year);
			if (movie != null)
				list.add(movie);
		}
		return list;
	}

	public Movie rating(String movieName, String year) {
		String responseString = "";
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet getRequest = new HttpGet(
					"http://www.theimdbapi.org/api/find/movie?title=" + movieName + "&year=" + year + "");
			getRequest.addHeader("accept", "application/json");
			HttpResponse response = httpClient.execute(getRequest);
			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
			}
			HttpEntity entity = response.getEntity();
			responseString = EntityUtils.toString(entity, "UTF-8");
			httpClient.getConnectionManager().shutdown();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return setValues(responseString);

	}

	private Movie setValues(String responseString) {
		Movie movie = new Movie();
		if (!responseString.equals("null")) {
			responseString= responseString.substring(1, responseString.length()-1);
			JSONObject json = new JSONObject(responseString);
			movie.setName((String) json.get("title"));
			movie.setImdbId((String) json.get("imdb_id"));
			movie.setYear((String) json.get("year"));
			movie.setRating((String)json.get("rating"));
			movie.setVotes((String) json.get("rating_count"));
			movie.setPlot((String) json.get("description"));
		}
		if(responseString.equals("null") || movie.getRating().equals(""))
		{
			movie.setRating("0");
			movie.setVotes("0");
		}
		return movie;
	}

}
