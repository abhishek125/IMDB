package org.abhi.spring.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Map;

import org.abhi.spring.model.Movie;
import org.abhi.spring.service.GetRating;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

//this annotation tells front controller that this is controller class
@Controller
// with annotation you don't need to extend one of base controller class of
// spring
public class HelloController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String homePage() throws Exception {
		return "index";
	}

	@RequestMapping(value = "/showrating.do", method = RequestMethod.POST,headers="Accept=application/json")
	@ResponseBody
	public ArrayList<Movie> showRating(@RequestBody  Map<String, String> json)
			throws UnsupportedEncodingException // new annotation
	{
		String textNames = json.get("textNames").replaceAll("<br>", "\n");
		//System.out.println(textNames);
		String[] movieNames = textNames.split("\n");
		GetRating rating = new GetRating();
		rating.imdbKey=json.get("imdbKey");
		//System.out.println("this is key="+rating.imdbKey);
		ArrayList<Movie> movies =new ArrayList<Movie>(rating.getMovies(movieNames));
		return movies;
	}

}
